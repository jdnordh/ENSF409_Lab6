package exercise4;
import java.io.IOException;

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
	/** true if both player are bots*/
	private Boolean bothBot = false;
	/**
	 * Default constructor 
	 */
	public Referee(){
	}
	
	/**
	 * Play the game
	 * @throws IOException 
	 */
	public void runTheGame() throws IOException{
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
		if (xPlayer.isHuman() || oPlayer.isHuman()) bothBot = false;
		else bothBot = true;
		System.out.print("Started the game...\n");
		board.display();
		if (!bothBot){
			while (!board.oWins() && !board.xWins() && !board.isFull()){
				xPlayer.makeMove();
				board.display();
				if (!board.oWins() && !board.xWins() && !board.isFull()){
					oPlayer.makeMove();
					board.display();
				}
			}
			if (board.xWins()) xPlayer.wins();
			else if (board.oWins()) oPlayer.wins();
			else oPlayer.tie();
		}
		else if (bothBot){
			System.out.print("Both players are bots, so delaying move time...\n");
			double time1, time2;
			while (!board.oWins() && !board.xWins() && !board.isFull()){
				xPlayer.makeMove();
				board.display();
				time1 = System.nanoTime()/1000000000;
				time2 = time1;
				time1 +=1;
				while (time2<time1){
					time2 = System.nanoTime()/1000000000;
				}
				if (!board.oWins() && !board.xWins() && !board.isFull()){
					time1 = System.nanoTime()/1000000000;
					time2 = time1;
					time1 +=1;
					while (time2<time1){
						time2 = System.nanoTime()/1000000000;
					}
					oPlayer.makeMove();
					board.display();
				}
			}
			if (board.xWins()) xPlayer.wins();
			else if (board.oWins()) oPlayer.wins();
			else oPlayer.tie();
		}
	}
	
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

