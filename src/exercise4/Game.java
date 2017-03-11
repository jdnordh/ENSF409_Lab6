package exercise4;

//Game.java
import java.io.*;

public class Game implements Constants {

	private Board theBoard;
	private Referee theRef;
	
	/**
	 * creates a board for the game
	 */
    public Game( ) {
        theBoard  = new Board();

	}
    /** Allows for more than one play through*/
	private static Boolean play = true;
    
    /**
     * calls the referee method runTheGame
     * @param r refers to the appointed referee for the game 
     * @throws IOException
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }
    
	
	public static void main(String[] args) throws IOException {
		do {
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

		xPlayer = create_player (name, LETTER_X, theGame.theBoard, stdin);
		
		System.out.print("\nPlease enter the name of the \'O\' player: ");
		name = stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}
		
		oPlayer = create_player (name, LETTER_O, theGame.theBoard, stdin);
		
		theRef = new Referee();
		theRef.setBoard(theGame.theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
        
        theGame.appointReferee(theRef);
        play = playAgain();
		} while (play);
		System.out.print("Exiting...\n");
	}
	
	/**
	 * Asks if the player would like to play again
	 * @return True if they want to play again
	 * @throws IOException
	 */
	private static Boolean playAgain() throws IOException{
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nWould you like to play again? (Y/N)\n");
		String in= stdin.readLine();
		while (in == null || in.length()<1){
			System.out.print("Please try again: ");
			in = stdin.readLine();
		}
		if (in.toUpperCase().charAt(0)=='Y'){
			return true;
		}
		else return false;
	}
	
	/**
	 * Creates the specified type of player indicated by the user. 
	 * 
	 * @param name player's name
	 * @param mark player's mark (X or O)
	 * @param board refers to the game board
	 * @param stdin refers to an input stream
	 * @return a newly created player
	 * @throws IOException
	 */
	static public Player  create_player(String name, char mark, Board board,
			BufferedReader stdin)throws IOException {
		// Get the player type.
		final int NUMBER_OF_TYPES = 4;
		System.out.print ( "\nWhat type of player is " + name + "?\n");
		System.out.print("  1: Human\n" + "  2: Random Player\n"
		+ "  3: Blocking Player\n" + "  4: Smart Player\n");
		System.out.print( "Please enter a number in the range 1-" + NUMBER_OF_TYPES + ": ");
		int player_type = 0;

		String input;
		stdin = new BufferedReader(new InputStreamReader(System.in));
		input= stdin.readLine();
		if (isNumeric(input)) player_type = Integer.parseInt(input);
		else player_type = -1;
		while (player_type < 1 || player_type > NUMBER_OF_TYPES) {
			System.out.print( "Please try again.\n");
			System.out.print ( "Enter a number in the range 1-" +NUMBER_OF_TYPES + ": ");
			input= stdin.readLine();
			if (isNumeric(input)) player_type = Integer.parseInt(input);
		}
		
		// Create a specific type of Player 
		Player result = null;
		switch(player_type) {
			case 1:
				result = new HumanPlayer(name, mark);
				break;
			case 2:
				result = new RandomPlayer(name, mark);
				break;
			case 3:
				result = new BlockingPlayer(name, mark);
				break;
			case 4:
				result = new SmartPlayer(name, mark);
				break;
			default:
				System.out.print ( "\nDefault case in switch should not be reached.\n"
				+ "  Program terminated.\n");
				System.exit(0);
		}
		result.setBoard(board);
		return result;
	}
	/** Check if string is numeric*/
	public static boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}
}





/*
import java.io.*;

//STUDENTS SHOULD ADD CLASS COMMENT, METHOD COMMENTS, FIELD COMMENTS 

*//**
 * 
 * @author Jordan Nordh
 *
 *//*
public class Game implements Constants {

	*//**
	 * Board used to play the game
	 *//*
	private Board theBoard;
	*//**
	 * Referee of the game
	 *//*
	private Referee theRef;
	
	*//**
	 * Default constructor for game
	 * Sets board to default board
	 *//*
    public Game( ) {
        theBoard  = new Board();
	}
    
    *//**
     * Sets the games referee
     * @param r Referee of the game
     * @throws IOException Yes
     *//*
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }
	
    *//**
     * Main function
     * @param args Command line args
     * @throws IOException Throws
     *//*
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

		xPlayer = new Player(name, LETTER_X);
		xPlayer.setBoard(theGame.theBoard);
		
		System.out.print("\nPlease enter the name of the \'O\' player: ");
		name = stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}
		
		oPlayer = new Player(name, LETTER_O);
		oPlayer.setBoard(theGame.theBoard);
		
		theRef = new Referee();
		theRef.setBoard(theGame.theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
        
        theGame.appointReferee(theRef);
	}
	

}
*/