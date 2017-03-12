package exercise4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player{
	/** Used for counting how deep the recursion goes for getting an input in the case they choose a spot that already has an entry*/
	protected int counter = 10;
	/**Constructs a human player
	 * @param s Name
	 * @param x	Mark
	 */
	public HumanPlayer(String s, char x){
		super(s,x);
		setHuman();
	}
	/**
	 * Player is able to make a move. Takes input from keyboard and check whether it is a proper input or not
	 * @throws IOException Catches errors
	 */
	public void makeMove() throws IOException{
		counter--;
		if (counter<0) {
			out.print("Stop inputing bad things\nExiting...\n");
			System.exit(1);
		}
		int row=0, col=0;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		out.print(name + ", what row should your next " + mark + " be placed in? ");
		String in= stdin.readLine();
		do {
			if (in!=null && in.length()>0) row = in.charAt(0)-48;
			while (in == null || row<0 || row>2 || in.length()<1) {
				out.print("Please try again: ");
				in = stdin.readLine();
				if (isNumeric(in)){
					row = in.charAt(0)-48;
				}
				else in=null;
			}
			out.print(name + ", what column should your next " + mark + " be placed in? ");
			in= stdin.readLine();
			if (in!=null && in.length()>0) col = in.charAt(0)-48;
			while (in == null || col<0 || col>2 || in.length()<1) {
				out.print("Please try again: ");
				in = stdin.readLine();
				if (isNumeric(in)){
					col = in.charAt(0)-48;
				}
				else in=null;
			}
		} while (board.getMark(row,col) != ' ');
		out.print("Space is already in use: \n");
		board.addMark(row, col, mark);
	}
}
