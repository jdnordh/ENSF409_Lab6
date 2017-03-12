package exercise4;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Runs the game, assigns players and a board
 * @author Jordan Nordh
 *
 */
public class Referee {
	private PrintWriter out1;
	private PrintWriter out2;
	
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

	public Referee(PrintWriter o1){
		out1 = o1;
		out2 = null;
	}
	
	public Referee(PrintWriter o1, PrintWriter o2){
		out1 = o1;
		out2 = o2;
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
		if (out2 == null){
			out1.print("Started the game...\n");
		}
		else {
			out1.print("Started the game...\n");
			out2.print("Started the game...\n");
		}
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
			if (out2 == null){
				if (board.xWins()) winsOne(xPlayer);
				else if (board.oWins()) winsOne(oPlayer);
				else tieOne();
			}
			else {
				if (board.xWins()) wins(xPlayer);
				else if (board.oWins()) wins(oPlayer);
				else tie();
			}
		}
		else if (bothBot){
			out1.print("Both players are bots, so delaying move time...\n");
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
			if (board.xWins()) winsOne(xPlayer);
			else if (board.oWins()) winsOne(oPlayer);
			else tieOne();
		}
	}
	
	public void wins(Player p){
		board.display();
		out1.print("\n=========================================\n"
				+ p.getName() +" wins!\n"
				+ "=========================================");
		out2.print("\n=========================================\n"
				+ p.getName() +" wins!\n"
				+ "=========================================");
	}

	public void tie(){
		board.display();
		out1.print("\n=========================================\n"
				+ "Tie game!\n"
				+ "=========================================");
		out2.print("\n=========================================\n"
				+ "Tie game!\n"
				+ "=========================================");
	}
	
	public void winsOne(Player p){
		board.displayOne();
		out1.print("\n=========================================\n"
				+ p.getName() +" wins!\n"
				+ "=========================================");
	}

	public void tieOne(){
		board.displayOne();
		out1.print("\n=========================================\n"
				+ "Tie game!\n"
				+ "=========================================");
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

