package exercise4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
	private PrintWriter out;
	private Socket socket;
	private ServerSocket serverSocket;
	private BufferedReader in;
	
	private Game game;
	
	private GameThread p1;
	private GameThread p2;
	
	public GameServer(String s, int port){
		try{
			InetAddress a = InetAddress.getByName("localhost");
			serverSocket = new ServerSocket(port, 50, a);
			System.out.println("Server is now running....");
			socket = serverSocket.accept();
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter((socket.getOutputStream()), true);
		} catch (IOException e){
			System.out.println("Server error: " + e.getMessage());
		}
	}

	public void play(){	
		game = new Game();
		try {
			System.out.println("Connecting P1");
			p1 = new GameThread("Player 1", socket);
			p1.setGame(game);
			p1.start();
			socket = serverSocket.accept();
			System.out.println("Connecting P2");
			p2 = new GameThread("Player 2", socket);
			p2.setGame(game);
			p2.start();
			
		} catch (IOException e){
			System.out.println("Error... " + e.getMessage());
		}
		while (p1.isRunning() || p2.isRunning());
		
		System.out.println("Exiting...");
		try{
			out.close();
			socket.close();
			serverSocket.close();
			in.close();
		} catch (IOException e) {
			System.exit(1);
			e.printStackTrace();
		}
		System.exit(0);
	}

	
	public static void main(String [] args){
		GameServer ser;
		ser = new GameServer("localhost", 9090);
		ser.play();
	}
}
