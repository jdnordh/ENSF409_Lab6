package exercise4;
import java.io.IOException;
/**
 * DISCLAIMER:
 * This class is a hyper exaggeration of what was needed in the outline of the lab
 * 
 * See if you can beat it
 * 
 * Instead of playing a random spot after not being able to win or block, it has an extensive algorithm for where to play
 * 
 * @author Jordan
 *
 */
public class SmartPlayer extends BlockingPlayer{
	/**
	 * Used for where SmartBot plays first
	 */
	private int csCase;
	/** Used for which strategy SmartBot uses*/
	private int strat;
	/**
	 * true if SmartBot plays first
	 */
	Boolean first = false;
	/**
	 * Contruct SmartBot
	 * @param name Name
	 * @param mark Mark
	 */
	public SmartPlayer(String name, char mark) {
		super(name,mark);
		String s = "SmartBot " + name;
		setName(s);
	}
	/**
	 * First: Tries to win if possible
	 * Second: Tries to block if possible
	 * Third: Makes an educated move based on algorithms
	 */
	public void makeMove() throws IOException {
		if (board.getCount()==0) first = true;
		int row = 0, col = 0;
		int [] a = findBlock(mark);
		while(true){
			if (a[2]==0){
				row = a[0];
				col = a[1];
				break;
			}
			a = findBlock(opponent.getMark());
			if (a[2]==0){
				row = a[0];
				col = a[1];
				break;
			}
			else {
				a = smartPlay();
				row = a[0];
				col = a[1];
				break;
			}
		}
		board.addMark(row, col, mark);
		System.out.printf("\n%s played at (%d,%d)\n", name, row%3, col%3);
	}
	
	/** Returns true if last mark was edge */
	private Boolean opPlayedEdge(){
		if (board.getLast() == 'e') return true;
		else return false;
	}
	/** Returns true if last mark was corner */
	private Boolean opPlayedCorner(){
		if (board.getLast() == 'c') return true;
		else return false;
	}
	/** Returns true if last mark was middle */
	private Boolean opPlayedMiddle(){
		if (board.getLast() == 'm') return true;
		else return false;
	}
	
	/** Makes a good Play ;)  */
	private int [] smartPlay(){
		int [] b = {0,0,0};
	
		if (first){ 
			if (board.getCount()==0){
				first = true;
				strat = 1;
				b = randCorner();
				csCase = b[2];
				return b;
			}
			else if (board.getCount()==2){
				if (strat == 2){
					if (opPlayedEdge()){
						b = randCorner();
						strat = 21;
						return b;
					}
					else if (opPlayedCorner()){
						strat = 22;
						b = randCorner();
					}
				}
				else if (opPlayedEdge()){
					strat = 11;
					b[0] = 1;
					b[1] = 1;
					return b;
				}
				else if (opPlayedCorner()){
					strat = 12;
					b = randCorner();
					return b;
				}
				else if (opPlayedMiddle()){
					strat = 13;
					b = opCorner(csCase);
					csCase = b[2];
					return b;
				}
			}
			else if (board.getCount()==4){
				if (strat == 21){
					b = adjCorner(csCase);	//last case of 21
					return b;
				}
				else if (strat==22){
					b = randCorner();
				}
				else if (strat==11){
					b = adjCorner(csCase); //Last case of 11
					return b;
				}
				else if (strat==12){
					b = randCorner(); //Last case of 12
					return b;
				}
				else if (strat==13){
					if (opPlayedEdge()){
						b = randCorner(); //Last case of 13
					}
					return b;
				}
			}
			else if (board.getCount()==6){
				if (strat==11){
					b = adjCorner(csCase);
					return b;
				}
				else if (strat==22){
					b = random();
				}
			}
			else if (board.getCount()==8){
				if (strat==11){
					b = adjCorner(csCase);
					return b;
				}
				else if (strat==22){
					b = random();	//Draw of 22
					return b;
				}
			}
		}
		
		else {
			if (board.getCount()==1){
				if (opPlayedCorner()){
					strat = 3;
					b[0] = 1;
					b[1] = 1;
					return b;
				}
				else if (opPlayedEdge()){
					strat = 4;
					if (board.getMark(0,1) == opponent.getMark() || board.getMark(1, 0) == opponent.getMark()){
						b[0] = 0;
						b[1] = 0;
						return b;
					}
					else {
						b[0] = 2;
						b[1] = 2;
						return b;
					}
				}
				else if (opPlayedMiddle()){
					strat = 5;
					b = randCorner();
					return b;
				}
			}
			else if (board.getCount()==3){
				if (strat==3){
					if (opPlayedEdge()){
						strat=31;
						b = randCorner();
						return b;
					}
					else {
						strat=32;
						b = randEdge();
						return b;
					}
				}
				else if (strat==4){
					b[0] = 1;
					b[1] = 1;
					return b;
				}
			}
			
			else if (board.getCount()==5){
				if (strat==31 || strat==32 || strat==4){
					b = randCorner();
					return b;
				}
				if (strat==3){
					strat=31;
					b = randEdge();
				}
			}
			else if (board.getCount()==7){
				if (strat==31 || strat==32 || strat==4){
					b = randCorner();
					return b;
				}
			}
		}
		b = random();
		return b;
	}
	
	/**
	 * Returns random edge
	 * @return Coordinates of edge
	 */
	private int [] randEdge(){
		int [] b = {0,0,0};
		int c;
		do{
		c = rand.discrete(0, 3);
		switch(c){
		case 0:
			b[0]=0;
			b[1]=1;
			break;
		case 1:
			b[0]=1;
			b[1]=0;
			break;
		case 2:
			b[0]=1;
			b[1]=2;
			break;
		case 3:
			b[0]=2;
			b[1]=1;
			break;
		}
		} while (board.getMark(b[0], b[1]) != ' ');
		b[2] = c;
		return b;
	}
	
	/**
	 * returns random unplayed point
	 */
	private int [] random(){
		int [] b = {0,0,0};
		do {
			b[0] = rand.discrete(0, 2);
			b[1] = rand.discrete(0, 2);
		} while (board.getMark(b[0], b[1]) != ' ');
		return b;
	}
	
	/** 
	 * returns an adjacent corner to a corner
	 * @param c Original corner
	 * @return Coordinates to adjecent corner
	 */
	private int [] adjCorner(int c){
		int [] b = {0,0,0};
		switch(c){
		case 0:
			if (board.getMark(0, 1) != opponent.getMark() && board.getMark(0, 2) != opponent.getMark()){
				b[0] = 0;
				b[1] = 2;
				return b;
			}
			if (board.getMark(1, 0) != opponent.getMark() && board.getMark(2, 0) != opponent.getMark()){
				b[0] = 2;
				b[1] = 0;
				return b;
			}
			break;
		case 1:
			if (board.getMark(1, 2) != opponent.getMark() && board.getMark(2, 2) != opponent.getMark()){
				b[0] = 2;
				b[1] = 2;
				return b;
			}
			if (board.getMark(0, 0) != opponent.getMark() && board.getMark(0, 1) != opponent.getMark()){
				b[0] = 0;
				b[1] = 0;
				return b;
			}
			break;
		case 2:
			if (board.getMark(1, 0) != opponent.getMark() && board.getMark(0, 0) != opponent.getMark()){
				b[0] = 0;
				b[1] = 0;
				return b;
			}
			if (board.getMark(2, 1) != opponent.getMark() && board.getMark(2, 2) != opponent.getMark()){
				b[0] = 2;
				b[1] = 2;
				return b;
			}
			break;
		case 3:
			if (board.getMark(1, 2) != opponent.getMark() && board.getMark(0, 2) != opponent.getMark()){
				b[0] = 0;
				b[1] = 2;
				return b;
			}
			if (board.getMark(2, 1) != opponent.getMark() && board.getMark(2, 0) != opponent.getMark()){
				b[0] = 2;
				b[1] = 0;
				return b;
			}
			break;
		}
		return b;
	}
	
	/**
	 * Find opposite corner
	 * @param c Starting corner
	 * @return	Coordinates
	 */
	private int [] opCorner(int c){
		System.out.println("Opposite Corner found");
		int [] b = {0,0,0};
		switch(c){
		case 0:
			b[0]=2;
			b[1]=2;
			break;
		case 1:
			b[0]=2;
			b[1]=0;
			break;
		case 2:
			b[0]=0;
			b[1]=2;
			break;
		case 3:
			b[0]=0;
			b[1]=0;
			break;
		}
		return b;
	}
	
	/** Returns a random unplayed corner */
	private int [] randCorner(){
		int [] b = {0,0,0};
		int c;
		do{
		c = rand.discrete(0, 4);
		switch(c){
		case 0:
			b[0]=0;
			b[1]=0;
			break;
		case 1:
			b[0]=0;
			b[1]=2;
			break;
		case 2:
			b[0]=2;
			b[1]=0;
			break;
		case 3:
			b[0]=2;
			b[1]=2;
			break;
		case 4:
			b[0] = 1;
			b[1] = 1;
			strat= 2;
			break;
		}
		} while (board.getMark(b[0], b[1]) != ' ');
		b[2] = c;
		return b;
	}
}
