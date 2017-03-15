package exercise4;


import java.io.*;


public class Game implements Constants {
	private Board board;

	private boolean p1;	
	private boolean p2;
	private boolean p1turn;
	private boolean play;
	
	private HumanPlayer xPlayer;
	private HumanPlayer oPlayer;
	
	public Game() {
        board  = new Board();
        p1 = false;
        p2 = false;
        p1turn = true;
        play = true;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public static void main(String[] args) throws IOException {
		Referee theRef;
		Player xPlayer, oPlayer;
		BufferedReader stdin;
		Game theGame = new Game();
		stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nPlease enter the name of the \'X\' player: ");
		String name= stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}

		xPlayer = new HumanPlayer(name, LETTER_X);
		xPlayer.setBoard(theGame.board);
		
		System.out.print("\nPlease enter the name of the \'O\' player: ");
		name = stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}
		
		oPlayer = new HumanPlayer(name, LETTER_O);
		oPlayer.setBoard(theGame.board);
		
		theRef = new Referee();
		theRef.setBoard(theGame.board);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
        
	}
	
	public void setPlayer(BufferedReader in, PrintWriter out, String s){
		while (!p1 && s.equals("Player 1")){
			try{
				out.println("Please enter the name of the \'X\' player: ");
				out.flush();
				String name= in.readLine();
				while (name == null) {
					out.println("Please try again: ");
					out.flush();
					name = in.readLine();
				}
				xPlayer = new HumanPlayer(name, LETTER_X);
				xPlayer.setBoard(board);
				p1 = true;
			} catch (IOException e) {
				out.println("Error: " + e.getMessage());
				out.flush();
			}
			
		}
		while (!p2 & s.equals("Player 2")){
			try{
				out.println("Please enter the name of the \'O\' player: ");
				out.flush();
				String name = in.readLine();
				while (name == null) {
					out.println("Please try again: ");
					out.flush();
					name = in.readLine();
				}
			
				oPlayer = new HumanPlayer(name, LETTER_O);
				oPlayer.setBoard(board);
				p2 = true;
			} catch (IOException e) {
				out.println("Error: " + e.getMessage());
				out.flush();
			}
		}
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
	}
	
	synchronized public void play(BufferedReader in, PrintWriter out, String s){		
		if (p1 && p2 && play){
			if (!board.oWins() && !board.xWins() && !board.isFull()){
				if (p1turn && s.equals("Player 1")) {
					try {
						xPlayer.makeMove(in, out);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (!p1turn && s.equals("Player 2")) {
					try {
						oPlayer.makeMove(in, out);
					} catch (IOException e) {
						out.println("Error: " + e.getMessage());
					}
				}
				else {
					out.println("Waiting for opponent...");
					out.flush();
				}
			}
			else {
				if (board.xWins()) wins(out, xPlayer);
				else if (board.oWins()) wins(out, oPlayer);
				else tie(out);
			}
		}
		if (board.isFull() || board.xWins() || board.oWins()){
			try {
				play = playAgain(in, out);
			} catch (IOException e) {
				out.println("Error: " + e.getMessage());
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
	
	synchronized private static Boolean playAgain(BufferedReader in, PrintWriter out) throws IOException{
		out.print("\nWould you like to play again? (Y|N)\n");
		out.flush();
		String input= in.readLine();
		while (input == null || input.length()<1){
			out.print("Please try again: ");
			out.flush();
			input = in.readLine();
		}
		if (input.toUpperCase().charAt(0)=='Y'){
			return true;
		}
		else return false;
	}
}
