package exercise4;


import java.io.*;


public class Game implements Constants {
	private Board board;

	private boolean p1;	
	private boolean p2;
	private boolean p1turn;
	private boolean play;
	private boolean opSet;
	
	private boolean p1Again;
	private boolean p2Again;
	
	private boolean p1AgainT;
	private boolean p2AgainT;
	
	private HumanPlayer xPlayer;
	private HumanPlayer oPlayer;
	
	public Game() {
        board  = new Board();
        p1 = false;
        p2 = false;
        p1turn = true;
        play = true;
        opSet = false;
        p1Again = false;
        p2Again = false;
        p1AgainT = false;
        p2AgainT = false;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public boolean bothSet(){
		return p1 && p2;
	}
	
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
	
	public boolean isFin(){
		return board.isFull() || board.xWins() || board.oWins();
	}
	
	synchronized public void play(BufferedReader in, PrintWriter out, String s){
		if (p1 && p2 && play){
			if (!isFin()){
				if (p1turn && s.equals("Player 1")) {
					try {
						board.display(out);
						xPlayer.makeMove(in, out);
						board.display(out);
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
						board.display(out);
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
	
	public void wins(PrintWriter out, Player p){
		board.display(out);
		out.print("\n=========================================\n"
				+ p.getName() +" wins!\n"
				+ "=========================================");
		out.flush();
	}

	public void tie(PrintWriter out){
		board.display(out);
		out.print("\n=========================================\n"
				+ "Tie game!\n"
				+ "=========================================");
	}
	
	synchronized private void updatePlay(){
		play = p1Again && p2Again;
		if (play) {
			board = new Board();
			p1turn = true;
		}
	}
	
	public boolean getAgain(String name){
		if (name.equals("Player 1")) return p1AgainT;
		return p2AgainT;
	}
	
	public void playAgain(BufferedReader in, PrintWriter out, String name) throws IOException{
		//Print out the winner to both clients
		if (board.xWins()) wins(out, xPlayer);
		else if (board.oWins()) wins(out, oPlayer);
		else tie(out);
		
		out.print("\nWould you like to play again? (Y|N)\n");
		out.println("GIVE");
		out.flush();
		String input= in.readLine();
		while (input == null || input.length()<1){
			out.println("Please try again: ");
			out.println("GIVE");
			out.flush();
			input = in.readLine();
		}
		out.println("Waiting for opponent...");
		out.flush();
		if (input.toUpperCase().charAt(0)=='Y'){
			if (name.equals("Player 1")) {
				p1Again = true;
				p1AgainT = true;
			}
			else {
				p2Again = true;
				p2AgainT = true;
			}
		}
		else {
			if (name.equals("Player 1")) {
				p1Again = false;
				p1AgainT = true;
			}
			else {
				p2Again = false;
				p2AgainT = true;
			}
		}
		updatePlay();
	}
}
