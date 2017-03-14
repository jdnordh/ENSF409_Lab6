package exercise4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer implements Constants {
	private PrintWriter out;
	private Socket socket;
	private ServerSocket serverSocket;
	private BufferedReader in;
	
	private Game game;
	
	private int threads;
	private GameThread p1;
	private GameThread p2;
	
	public GameServer(String s, int port){
		try{
			serverSocket = new ServerSocket(port);
			System.out.println("Server is now Running....");
			socket = serverSocket.accept();
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter((socket.getOutputStream()), true);
		} catch (IOException e){
			System.out.println("Server error");
		}
	}

	public void play(){
		while (true){
			
			break;
		}
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
		GameServer ser = new GameServer("localhost", 8099);
		ser.play();
	}
}

