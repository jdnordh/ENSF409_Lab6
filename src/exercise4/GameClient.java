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
	
	/**
	 * Client will quit from either server telling it to, or the client telling it to
	 */
	public void communicate()  {
		System.out.println("Starting...");
		String input = "";
		String response = "";
		while (true) {
			try {
				response = socketIn.readLine();
				if (response.equals("QUIT")) break;
				System.out.println(response);
				input = stdIn.readLine();
				if (!input.equalsIgnoreCase("QUIT")){
					socketOut.println(input);	
					socketOut.flush();
				}else{
					break;
				}
				
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
				break;
			}
		}
		socketOut.println("QUIT");
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
