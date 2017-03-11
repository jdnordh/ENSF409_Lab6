package exercise4;
import java.io.IOException;

public class RandomPlayer extends Player{
	/** Use rand.discrete(lo,hi) for random number from lo to hi*/
	protected RandomGenerator rand = new RandomGenerator();
	/** 
	 * Creates Random Player with name and mark
	 * @param name Name to give this player
	 * @param mark Mark that this player will use
	 */
	public RandomPlayer(String name, char mark) {
		super(name,mark);
		String s = "RandomBot " + name;
		setName(s);
	}
	/** If board is not full then make a move*/
	public void play() throws IOException{
		if (!board.isFull()){
			makeMove();
		}
	}
	/** get a random unused point to place a mark*/
	public void makeMove() throws IOException {
		int row = 0, col = 0;
		do {
			row = rand.discrete(0, 2);
			col = rand.discrete(0, 2);
		} while (board.getMark(row, col) != ' ');
		board.addMark(row, col, mark);
		System.out.printf("\n%s played at (%d,%d)\n", name, row, col);
	}
	
}
