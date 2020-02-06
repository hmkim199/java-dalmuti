import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Dalmuti {
	private final int NUMBER_OF_PLAYERS = 4;
	private Player[] players;
	private ArrayList<Card> cards;
	
	private int round = 1;
	private int currentPlayer = 1;
	private Card currentCard = null;
	private int currentCount = 0;
	private boolean[] skipped;

	public Dalmuti() {
		System.out.println("Welcome to Dalmuti.");

		createCards();
		createPlayers();
		designateRanks();
		handOutCards();
		if (someoneWantsRevolution()) {
			revolution();
		} else {
			collectTaxes();
		}
		playGame();
	}

	private void playGame() {
		while (!hasGameEnded()/* 모두가 카드를 소진함 */) {

			System.out.println("Round " + round);
			currentCard = null;
			currentCount = 0;
			skipped = new boolean[players.length];
			
			while (true/* 아무도 낼 카드가 없음 */) {
				System.out.println();
				System.out.println(players[currentPlayer] + "'s turn");
				ArrayList<Card> playedCards = players[currentPlayer].playCards(currentCard, currentCount);
				
				if (playedCards.size() == 0) {
					skipped[currentPlayer] = true;
					System.out.println("PASS");
				} else {
					skipped[currentPlayer] = false;
					currentCard = playedCards.get(0);
					currentCount = playedCards.size();
					System.out.println(players[currentPlayer] + " played [" + currentCard + "] X " + currentCount);
				}
				
				if (hasRoundEnded()) {
					currentPlayer = ((currentPlayer - 1) % 4 + 4 ) % 4;
					break;
				} else {
					currentPlayer = (currentPlayer + 1) % 4;
				}
			}
			round++;
		}
	}
	
	private boolean hasGameEnded() {
		int nPeopleWithCards = 0;
		
		for (int i = 0; i < players.length; i++) {
			int nCards = players[i].getHand().size();
			if (nCards > 0) {
				nPeopleWithCards++;
			}
		}
		System.out.println("카드가 남아 있는 사람 수: " + nPeopleWithCards);
		
		return nPeopleWithCards < 1;
	}
	
	private boolean hasRoundEnded() {
		boolean isEnd = true;
		for (int i = 0; i < players.length; i++) {
//			System.out.print(skipped[i] + " ");
			isEnd = isEnd && skipped[i];
		}
//		System.out.println();
		return isEnd;
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
		ArrayList<int[]> firstRank = new ArrayList<>();
		for (int i = 0; i < players.length; i++) {
			int tuple[] = new int[2];
			tuple[0] = i;
			tuple[1] = cards.get(i).getNumber();
			firstRank.add(tuple);
		}
		// TODO 계급정하기
		Collections.sort(firstRank, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[1] - o2[1];
			}
		});
		for (int i = 0; i < players.length; i++) {
//			System.out.printf("%d %d\n", firstRank.get(i)[0], firstRank.get(i)[1]);
		}

		for (int i = 0; i < players.length; i++) {
			players[firstRank.get(i)[0]].setRank(i + 1);
		}
		// sort players based on rank

		Arrays.sort(players);
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
