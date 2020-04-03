
public class Controller {
	Player player = new Player();
	
	public Controller() {
		System.out.println("dddd");

		player.start();
		player.findTaxCard();
		
		synchronized (player) {
			try {
				player.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("doneeee");
			
		}
	}
	
	public static void main(String[] args) {
		
		new Controller();
		
	}

}
