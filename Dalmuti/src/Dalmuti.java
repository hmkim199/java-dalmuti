import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Dalmuti {
	private final int NUMBER_OF_PLAYERS = 4;
	private final int NUMBER_OF_GAMES = 5; 
	
	private Player[] players;
	private ArrayList<Card> cards;

	private int game = 0;
	private int round = 1;
	private int firstPlayer = 0;
	private Card currentCard = null;
	private int currentCount = 0;
	private boolean[] skipped;

	private int newRankCounter = 1;

	public Dalmuti() {
		System.out.println("Welcome to Dalmuti.");

		createCards();
		createPlayers();
		designateRanks();
		
		for (game = 0; game < NUMBER_OF_GAMES; game++) {
			handOutCards();
			if (someoneWantsRevolution()) {
				revolution();
			} else {
				collectTaxes();
			}
			playGame();
			aggregateScores();
		}
		
		Arrays.sort(players, new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				// TODO Auto-generated method stub
				return o2.getScore() - o1.getScore();
			}
			
		});
		
		System.out.println(players[0] + " is the winner!!!");
	}

	private void aggregateScores() {
		Arrays.sort(players);
		
		System.out.println("Result of Game " + game + ": ");
		
		for (Player player: players) {
			player.addScore(players.length - player.getRank());
		}

		for (Player player: players) {
			System.out.println(player + "\t" + player.getScore() + " ( +" + (players.length - player.getRank()) + ")");
		}
		System.out.println();
		
	}
	
	private void playGame() {
		round = 1;
		newRankCounter = 1;
		
		while (!hasGameEnded()/* 모두가 카드를 소진함 */) {
//			System.out.println("Round " + round);
			
			firstPlayer = playRound(firstPlayer);
			round++;
		}
	}

	private int playRound(int firstPlayer) {
		int currentPlayer = firstPlayer;
		currentCard = null;
		currentCount = 0;
		skipped = new boolean[players.length];

		ArrayList<Card> playedCards = null;

		while (true/* 아무도 낼 카드가 없음 */) {
//			System.out.println();

			skipped[currentPlayer] = true;

			if (!players[currentPlayer].handIsEmpty()) {
//				System.out.println(players[currentPlayer] + "'s turn");

				playedCards = players[currentPlayer].playCards(currentCard, currentCount);

				if (playedCards.size() > 0) {
					skipped = new boolean[players.length];
					currentCard = playedCards.get(0);
					currentCount = playedCards.size();
				}

				if (players[currentPlayer].handIsEmpty()) {
					players[currentPlayer].setRank(newRankCounter);
					newRankCounter++;
//					System.out.println(players[currentPlayer]+ " is done!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
			}

			currentPlayer = (currentPlayer + 1) % players.length;

			if (hasRoundEnded()) {
				for (Player player : players) {
					if (player.handIsEmpty() == false) {
						player.setRank(newRankCounter);
					}
				}
				return currentPlayer;
			}
		}
	}

	private boolean hasGameEnded() {
		int nDonePeople = 0;

		for (int i = 0; i < players.length; i++) {
			if (players[i].handIsEmpty()) {
				nDonePeople++;
			}
		}
//		System.out.println("카드를 다 사용한 사람 수: " + nDonePeople);

		return nDonePeople >= players.length - 1;
	}

	private boolean hasRoundEnded() {
		int skippedCount = 0;
		for (int i = 0; i < players.length; i++) {
//			System.out.print(skipped[i] + " ");
			if (skipped[i] == true) {
				skippedCount++;
			}
		}
//		System.out.println();
		return skippedCount == players.length - 1;
	}

	private void collectTaxes() {
		for (int i = players.length - 1; i >= 0; i--) {
			int rank = players[i].getRank();
			players[i].payTaxTo(players[players.length - rank]);
		}
	}

	private void revolution() {
		// 세금 없고 계급 반대
		System.out.println("혁명! 계급순서 변경!!!!!!!!!!");
		for (int j = 0; j < players.length; j++) {
			int newRank = players.length - j;
			players[j].setRank(newRank);
		}
		Arrays.sort(players);
		for (int k = 0; k < players.length; k++) {
//			System.out.println(players[k]);
		}
	}

	private boolean someoneWantsRevolution() {
		// 혁명 가능 체크
		for (int i = 0; i < players.length; i++) {
			ArrayList<Card> hand = players[i].getHand();
			int indexFirst13 = hand.indexOf(new Card(13));
			int indexLast13 = hand.lastIndexOf(new Card(13));
			if (indexFirst13 != indexLast13) {
//				System.out.println(players[i] + "가 13을 2개 가졌다");
				if (players[i].wantsRevolution()) {
					return true;
				}
			}
		}

		return false;
	}

	private void handOutCards() {

		Collections.shuffle(cards);

		for (int i = 0; i < cards.size(); i++) {
			int receiver = i % players.length;
			players[receiver].receiveCard(cards.get(i));
		}

		for (int i = 0; i < players.length; i++) {
//			System.out.println(players[i].getHand());
			// System.out.println(players[i].getHand().size());
		}

	}

	private void designateRanks() {
		// 뽑기
		for (int i = 0; i < players.length; i++) {
			players[i].setRank(cards.get(i).getNumber());
		}

//		for (Player player: players) {
//			System.out.println(player + " picked Card " + player.getRank());
//		}
		
		// sort players based on rank
		Arrays.sort(players);
		
		int rank = 1;
		for (Player player: players) {
			player.setRank(rank);
		}

		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i]);
		}
	}

	private void createPlayers() {
		players = new Player[NUMBER_OF_PLAYERS];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player("Me" + i);
		}
	}

	private void createCards() {
		cards = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			for (int j = 0; j < i; j++) {

				cards.add(new Card(i));

			}
		}
		cards.add(new Card(13));
		cards.add(new Card(13));
		// 섞기
		Collections.shuffle(cards);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dalmuti game = new Dalmuti();

	}

}
