package exercise4;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class GameThread extends Thread{
	/** The game that the thread is connected to */
	private Game game;
	/** The player that is inputing to the thread */
	private Player player;
	/** input from the client */
	private BufferedReader in;
	/** Output to the client */
	private PrintWriter out;
	
	/**
	 * Constructor for the Thread
	 * @param g The game to be played
	 * @param p The player using this thread
	 * @param i The input from the client
	 * @param o The output to the client
	 */
	public GameThread(Game g, Player p, BufferedReader i, PrintWriter o){
		game = g;
		player = p;
		in = i;
		out = o;
	}
	
	public void run(){
		try {
			while (true){
				sleep(1);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + this.getName() + " interrupted");
		}
		
	}
	
}
