package exercise4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HumanPlayer extends Player{
	/**Constructs a human player
	 * @param s Name
	 * @param x	Mark
	 */
	public HumanPlayer(String s, char x){
		super(s,x);
	}
	/**
	 * Player is able to make a move. Takes input from keyboard and check whether it is a proper input or not
	 * @throws IOException Catches errors
	 */
	public void makeMove(BufferedReader in, PrintWriter out) throws IOException{
		int row=0, col=0;
		out.println(name + ", what row should your next " + mark + " be placed in? ");
		out.println("GIVE");
		out.flush();
		String input= in.readLine();
		do {
			if (input!=null && input.length()>0) row = input.charAt(0)-48;
			while (input == null || row<0 || row>2 || input.length()<1) {
				out.println("Please try again: ");
				out.println("GIVE");
				out.flush();
				input = in.readLine();
				if (isNumeric(input)){
					row = input.charAt(0)-48;
				}
				else in=null;
			}
			out.println(name + ", what column should your next " + mark + " be placed in? ");
			out.println("GIVE");
			out.flush();
			input = in.readLine();
			if (input!=null && input.length()>0) col = input.charAt(0)-48;
			while (input == null || col<0 || col>2 || input.length()<1) {
				out.print("Please try again: ");
				out.print("GIVE");
				out.flush();
				input = in.readLine();
				if (isNumeric(input)){
					col = input.charAt(0)-48;
				}
				else input=null;
			}
			if (board.getMark(row,col) != ' ') {
				out.print("Space is already in use: \n");
				out.flush();
			}
		} while (board.getMark(row,col) != ' ');
		board.addMark(row, col, mark);
	}
}
