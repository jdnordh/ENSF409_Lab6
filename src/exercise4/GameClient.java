package exercise4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
	private PrintWriter socketOut;
	private Socket palinSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;

	private ClientThread p1;
	private ClientThread p2;
	
	public GameClient(String serverName, int portNumber) {
		try {
			palinSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(
					palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	private void startOp(){
		
	}
	
	public void getPlayers(){
		p1 = new ClientThread();
		p1.start();
		
		System.out.println("Would you like a human opponent? (Y/N)");
		try{
			String input = stdIn.readLine();
			while(true){
				if (input.toUpperCase().equals("Y")) {
					startOp();
					break;
				}
				else if (input.toUpperCase().equals("N")) break;
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void communicate()  {

		String line = "";
		String response = "";
		boolean running = true;
		while (running) {
			try {
				System.out.println("Please enter a word: ");
				line = stdIn.readLine();
				if (!line.toUpperCase().equals("QUIT")){
					System.out.println(line);
					socketOut.println(line);
					response = socketIn.readLine();
					System.out.println(response);	
				}else{
					running = false;
				}
				
			} catch (IOException e) {
				System.out.println("Sending error: " + e.getMessage());
			}
		}
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("closing error: " + e.getMessage());
		}

	}

	public static void main(String[] args) throws IOException  {
		GameClient a = new GameClient("localhost", 9090);
		a.getPlayers();
	}
}

