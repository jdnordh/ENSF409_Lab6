package exercise4;


import java.io.*;


public class Game implements Constants {
	private Board board;

	private boolean p1;	
	private boolean p2;
	private boolean p1turn;
	private boolean play;
	private boolean opSet;
	
	private boolean p1P;
	private boolean p2P;
	
	private HumanPlayer xPlayer;
	private HumanPlayer oPlayer;
	/** Default constructor */
	public Game() {
        board  = new Board();
        p1 = false;
        p2 = false;
        p1turn = true;
        play = true;
        opSet = false;
        p1P = false;
        p2P = false;
	}
	/** Returns play */
	public boolean play(){
		return play;
	}
	/** return the board */
	public Board getBoard(){
		return board;
	}
	
	/** Returns true if both players are set */
	public boolean bothSet(){
		return p1 && p2;
	}
	
	/**
	 * Each thread assigns a player to the game if it is not already initialized
	 * @param in Reader
	 * @param out Writer
	 * @param s Thread Name
	 */
	public void setPlayer(BufferedReader in, PrintWriter out, String s){
		while (!p1 && s.equals("Player 1")){
			try{
				System.out.println("Setting X player...");
				out.println("Please enter the name of the \'X\' player: ");
				out.println("GIVE");
				out.flush();
				String name= in.readLine();
				while (name == null) {
					out.println("Please try again: ");
					out.println("GIVE");
					out.flush();
					name = in.readLine();
				}
				xPlayer = new HumanPlayer(name, LETTER_X);
				xPlayer.setBoard(board);
				p1 = true;
				System.out.println("P1 set");
				out.println("Waiting for opponent...");
				out.flush();
			} catch (IOException e) {
				out.println("Error: " + e.getMessage());
			}
			
		}
		while (!p2 & s.equals("Player 2")){
			try{
				System.out.println("Setting O player...");
				out.println("Please enter the name of the \'O\' player: ");
				out.println("GIVE");
				out.flush();
				String name = in.readLine();
				while (name == null) {
					out.println("Please try again: ");
					out.println("GIVE");
					out.flush();
					name = in.readLine();
				}
			
				oPlayer = new HumanPlayer(name, LETTER_O);
				oPlayer.setBoard(board);
				p2 = true;
				System.out.println("P2 set");
				out.println("Waiting for opponent...");
				out.flush();
			} catch (IOException e) {
				out.println("Error: " + e.getMessage());
			}
		}
		if(p1 && p2 && !opSet){
			xPlayer.setOpponent(oPlayer);
			oPlayer.setOpponent(xPlayer);
			opSet = true;
			System.out.println("Opponents set");
		}
	}
	
	/**
	 * Check if the game is finished
	 * @return True if the game is over
	 */
	public boolean isFin(){
		return board.isFull() || board.xWins() || board.oWins();
	}
	
	/**
	 * Both threads will try to play, but are only allowed to if it is their turn
	 * @param in Reader from the the thread
	 * @param out Writer from the thread
	 * @param s Name of the thread
	 */
	synchronized public void play(BufferedReader in, PrintWriter out, String s){
		if (p1 && p2 && play){
			if (!isFin()){
				if (p1turn && s.equals("Player 1")) {
					try {
						board.display(out);
						xPlayer.makeMove(in, out);
						if (!isFin()) board.display(out);
						out.println("Waiting for opponent...");
						out.flush();
						p1turn = false;
					} catch (IOException e) {
						out.println("Error: " + e.getMessage());
					}
				}
				else if (!p1turn && s.equals("Player 2")) {
					try {
						board.display(out);
						oPlayer.makeMove(in, out);
						if (!isFin()) board.display(out);
						out.println("Waiting for opponent...");
						out.flush();
						p1turn = true;
					} catch (IOException e) {
						out.println("Error: " + e.getMessage());
					}
				}
			}
		}
	}
	
	/**
	 * Prints the winner to the clients
	 * @param out Writer from the thread
	 * @param p player 
	 */
	public void wins(PrintWriter out, Player p){
		board.display(out);
		out.print("\n=========================================\n"
				+ p.getName() +" wins!\n"
				+ "=========================================\n");
		out.flush();
		out.println("QUIT");
		out.flush();
	}

	/**
	 * Prints that game was a tie to both clients 
	 * @param out Writer from thread
	 */
	public void tie(PrintWriter out){
		board.display(out);
		out.print("\n=========================================\n"
				+ "Tie game!\n"
				+ "=========================================\n");
		out.flush();
		out.println("QUIT");
		out.flush();
	}
	
	/**
	 * Prints the winner or tie to the clients
	 * @param out Writer from the thread
	 * @param name thread name 
	 */
	public void printWinner(PrintWriter out, String name){
		if ((name.equals("Player 1") && !p1P) || (name.equals("Player 2") && !p2P)){
			if (board.xWins()) wins(out, xPlayer);
			else if (board.oWins()) wins(out, oPlayer);
			else tie(out);
		}
		if (name.equals("Player 1")) p1P =true;
		else p2P = true;
		if (p1P && p2P){
			System.exit(0);
		}
	}
	
	/**
	 * For testing purposes only
	 */
	public void setFull(){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				board.addMark(i, j, LETTER_O);
			}
		}
	}
}
