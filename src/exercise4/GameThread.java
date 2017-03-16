package exercise4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class GameThread extends Thread{
	private Game game;
	private Socket socket;
		
	private PrintWriter out;
	private BufferedReader in;
	
	private boolean running;
	/** construct a new thread */
	public GameThread(String name, Socket s){
		super(name);
		socket = s;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			System.out.println("Error... " + e.getMessage());
		}
	}
	/** set the threads game */
	public void setGame(Game g){
		game = g;
	}
	/** check if the thread is running */
	public boolean isRunning(){
		return running;
	}
	/** The thread will call to try to play the game */
	public void run(){
		running = true;
		System.out.println("Thread " + this.getName() + " started");
		String temp = "Starting the game...";
		out.println(temp);
		game.getBoard().display(out);
		try {
			while (running){
				if (game.isFin()) {
					game.printWinner(out, this.getName());
					running = false;
				}
				game.setPlayer(in, out, this.getName());
				if (game.bothSet() && !game.isFin()) 
					game.play(in, out, this.getName());
				sleep(1);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + this.getName() + " interrupted");
		}
		
	}
}
