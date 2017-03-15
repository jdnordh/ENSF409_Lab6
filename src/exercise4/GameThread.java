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
	
	public void setGame(Game g){
		game = g;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void run(){
		running = true;
		System.out.println("Thread " + this.getName() + " started");
		String temp = "Starting the game...";
		out.println(temp);
		game.getBoard().display(out);
		try {
			while (running){
				game.setPlayer(in, out, this.getName());
				if (game.bothSet()) game.play(in, out, this.getName());
				sleep(1);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + this.getName() + " interrupted");
		}
		
	}
}
