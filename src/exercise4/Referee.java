package exercise4;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Runs the game, assigns players and a board
 * @author Jordan Nordh
 *
 */
public class Referee {
	/**
	 * Specifies player that uses x's as marks
	 */
	private Player xPlayer;
	/**
	 * Specifies the player that uses o'x as marks
	 */
	private Player oPlayer;
	/**
	 * monitors the board and assigns players to it
	 */
	private Board board;

	public Referee(){
	}
	
	/**
	 * Play the game
	 * @throws IOException 
	 */


	/**
	 * Assigns board to be played on
	 * @param b Board used to play game
	 */
	public void setBoard(Board b){
		board = b;
	}
	
	/**
	 * Sets this player to oPlayer
	 * @param op Player to be assigned
	 */
	public void setoPlayer(Player op){
		oPlayer = op;
	}
	
	/**
	 * Sets this player to xPlayer
	 * @param x Player to be assigned
	 */
	public void setxPlayer(Player x){
		xPlayer = x;
	}
}

