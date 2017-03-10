package Excercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private PrintWriter out;
	private Socket socket;
	private ServerSocket serverSocket;
	private BufferedReader in;
	
	public Server(String s, int port){
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
	
	public void communicate(){
		while (true){
			String input, output = "Error reading from socket";
			try {
				input = in.readLine();
				if (input == null) break;
				if (input.toUpperCase().equals("QUIT")) break;
				if (input.equals(reverse(input))) output = input + " is a palindrome";
				else output = input + " is not a palindrome";
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.println(output);
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
	
	public static String reverse(String s){
		char [] temp = new char[s.length()];
		for (int i = 0, j = s.length() - 1 ; i < s.length(); i++, j--){
			temp[i] = s.charAt(j);
		}
		return new String(temp);
	}
	
	public static void main(String [] args){
		Server ser = new Server("localhost", 8099);
		ser.communicate();
	}
}
