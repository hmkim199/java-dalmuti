
public class Player extends Thread {
	public Player() {
		
	}
	
	public void run() {
	
	}
	
	public synchronized void findTaxCard () {
		synchronized(this) {
			for (int i = 0; i < 5; i++) {
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			notify();
		}
	}
}
