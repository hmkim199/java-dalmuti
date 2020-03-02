import java.util.Arrays;

public class Controller {
	Dalmuti model = new Dalmuti();
	MainView view = new MainView();
	
	public Controller() {
		model.createCards();
		model.createPlayers();
		model.designateRanks();

		Player[] players = model.getPlayers();
		int[] ranks = new int[players.length];
		String[] names = new String[players.length];
		
		for (int i = 0; i < players.length; i++) {
			ranks[i] = players[i].getRank();
			names[i] = players[i].toString();
		}
		
		view.updateView(ranks, names);
		
//		for (int nGames = 0; nGames < NUMBER_OF_GAMES; nGames++) {
//			Arrays.sort(players);
//			handOutCards();
//			if (someoneWantsRevolution()) {
//				revolution();
//			} else {
//				collectTaxes();
//			}
//			System.out.println(nGames+"번째 게임입니다.");
//			playGame();
//			aggregateScore();
//		}
//		
//		for(int i = 0; i < players.length; i++) {
//			System.out.println("점수 집계!");
//			System.out.println((i+1)+"등은 "+players[i]+" 점수는 "+players[i].getScore());
//		}
//		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller controller = new Controller();
	}

}
