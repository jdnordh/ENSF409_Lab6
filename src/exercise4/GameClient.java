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
	
	public void communicate()  {
		System.out.println("Starting...");
		String input = "";
		String response = "";
		while (true) {
			try {
				response = socketIn.readLine();
				System.out.println(response);
				input = stdIn.readLine();
				if (!input.toUpperCase().equals("QUIT")){
					socketOut.println(input);	
				}else{
					break;
				}
				
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}
		System.out.println("Closing...");
	}

	public static void main(String[] args) throws IOException  {
		GameClient a = new GameClient("localhost", 9090);
		a.communicate();
	}
}

