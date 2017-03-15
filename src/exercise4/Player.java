package exercise4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Defines the input as certain player
 * @author Jordan Nordh
 *
 */
public abstract class Player{
	abstract public void makeMove(BufferedReader in, PrintWriter out) throws IOException;
	
	protected PrintWriter out;
	
	/** Name of the player */
	protected String name;
	/**Board being used to play game*/
	protected Board board;
	/** Who the player is playing against */
	protected Player opponent;
	/** Which mark the player is using */
	protected char mark;

	public void setPrinter(PrintWriter p){
		out = p;
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
	
	public String getName(){
		return name;
	}
	
	/**
	 * If the game is still playable, make a move, unused
	 * @throws IOException
	 */
	public void play(BufferedReader in, PrintWriter out) throws IOException{
		if (!board.isFull() && !board.oWins() && !board.xWins()){
			makeMove(in, out);
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
	 * @return Mark of the player
	 */
	public char getMark() {
		return mark;
	}
}
