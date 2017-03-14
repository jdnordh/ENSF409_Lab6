package exercise4;


public class GameThread extends Thread{
	private Game game;
	private Player player;
	
	public void run(){
		try {
			sleep(1);
		} catch (InterruptedException e) {
			System.out.println("Thread " + this.getName() + " interrupted");
		}
		
	}
	
}
