package exercise4;

public class ClientThread extends Thread{
	private boolean isFirst;
	
	public void setFirst(boolean t){
		isFirst = t;
	}
	
	public void run(){
		boolean running = true;
		while (running){
			try {
				
				sleep(1);
				
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	
}
