package exercise4;
import java.io.IOException;

public class BlockingPlayer extends RandomPlayer{	
	/**
	 * Construct blocking player
	 * @param name Name
	 * @param mark Mark
	 */
	public BlockingPlayer(String name, char mark) {
		super(name,mark);
		String s = "BlockBot " + name;
		setName(s);
	}

	/**
	 * Will block if it is possible, otherwise, play somewhere random
	 */
	public void makeMove() throws IOException {
		int row = 0, col = 0;
		int [] a = findBlock(opponent.getMark());
		row = a[0];
		col = a[1];
		while (board.getMark(row, col) != ' '){
			row = rand.discrete(0, 2);
			col = rand.discrete(0, 2);
		}
		board.addMark(row, col, mark);
		System.out.printf("\n%s played at (%d,%d)\n", name, row%3, col%3);
	}
	
	/**
	 * Finds two of mark c in a row and offers a blocking spot
	 * @param c	mark you want to block
	 * @return Returns coordinates of suggested block in an array
	 */
	protected int [] findBlock(char c){
		int row=0, col=0;
		int colCount=0, rowCount = 0, d1Count=0, d2Count=0;
		int [] b = {rand.discrete(0, 2),rand.discrete(0, 2), 0};
		for (row=0; row<3; row++){
			rowCount = 0;
			for (col=0; col<4; col++){
				if (board.getMark(row, col) == c) rowCount++;
				else rowCount = 0;
				if (rowCount>1 && board.getMark(row, col+1) == ' ') {
					b[0] = row;
					b[1] = col+1;
					return b;	//found horizontal block
				}
			}
		}
		
		for (col=0; col<3; col++){
			colCount = 0;
			for (row=0; row<4; row++){
				if (board.getMark(row, col) == c) colCount++;
				else colCount = 0;
				if (colCount>1 && board.getMark(row+1, col) == ' ') {
					b[0] = row+1;
					b[1] = col;
					return b;	//found vertical block
				}
			}
		}
		
		for (row=0,col=0; row < 4; row++, col++){
			if (board.getMark(row, col) == c) d1Count++;
			else d1Count=0;
			if (d1Count>1 && board.getMark(row+1, col+1) == ' '){
				b[0] = row+1;
				b[1] = col+1;
				return b;		//Found diagonal 1 block
			}
		}
		for (row=3,col=2; row<7; row++, col+=2){
			if (board.getMark(row, col) == c) d2Count++;
			else d2Count=0;
			if (d2Count>1  && board.getMark(row+1, (col%3)+2) == ' '){
				b[0] = row+1;
				b[1] = (col%3)+2;
				return b;		//Found diagonal 2 block
			}
		}
		b[2] = 1; 		//No block found
		return b;
	}
	

}
