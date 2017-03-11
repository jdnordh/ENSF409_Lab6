package exercise4;
import java.io.IOException;

/**
 * Defines the input as certain player
 * @author Jordan Nordh
 *
 */
public abstract class Player{
	abstract public void makeMove() throws IOException;
	
	/** Name of the player */
	protected String name;
	/**Board being used to play game*/
	protected Board board;
	/** Who the player is playing against */
	protected Player opponent;
	/** Which mark the player is using */
	protected char mark;
	/** True if player is human, for display purposes */
	boolean human = false;
	
	/** sets player to human*/
	protected void setHuman(){
		human = true;
	}
	/**Check if Player is human
	 * @return True if Player is human
	 */
	public Boolean isHuman(){
		return human;
	}
	
	/** Sets name of the player
	 * @param s Name
	 */
	public void setName(String s){
		name = s;
	}
	/** * Sets mark for the player
	 * @param x Mark
	 */
	public void setMark(char x){
		mark = x;
	}
	/**
	 * Constructor sets name and mark
	 * @param name2	Name of Player
	 * @param letterX Mark of the player
	 */
	public Player(String name2, char letterX) {
		name = new String(name2);
		mark = letterX;
	}
	
	/**
	 * If the game is still playable, make a move, unused
	 * @throws IOException
	 */
	public void play() throws IOException{
		if (!board.isFull() && !board.oWins() && !board.xWins()){
			makeMove();
		}
	}
	
	/**
	 * Sets oPlayers opponent
	 * @param op Opponent to be set
	 */
	public void setOpponent(Player op){
		opponent = op;
	}
	
	/**
	 * Sets the game board
	 * @param b Input a board
	 */
	public void setBoard(Board b){
		board = b;
	}
	
	/**
	 * Returns true is the string is numeric
	 * @param s Input string
	 * @return True if numeric
	 */
	public static boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	/**
	 * Called if the player wins
	 */
	public void wins(){
		board.display();
		System.out.print("\n=========================================\n"
				+ name+" wins!\n"
				+ "=========================================");
	}
	/**
	 * Called if the game is a tie
	 */
	public void tie(){
		board.display();
		System.out.print("\n=========================================\n"
				+ "Tie game!\n"
				+ "=========================================");
	}
	
	/** 
	 * @return Mark of the player
	 */
	public char getMark() {
		return mark;
	}
}
