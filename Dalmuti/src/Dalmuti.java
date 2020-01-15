import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Dalmuti {
	private final int NUMBER_OF_PLAYERS = 7;
	Player[] players;
	ArrayList<Card> cards;

	public Dalmuti() {
		System.out.println("Welcome to Dalmuti.");

		createCards();
		createPlayers();
		designateRanks();
		handOutCards();
		
		
	}

	private void handOutCards() {

		Collections.shuffle(cards);
		int handCount;
		int extraHand = cards.size() % players.length;
		int index = 0;

		for (int i = 0; i < players.length; i++) {
			handCount = cards.size() / players.length;			
			if(extraHand != 0) {
				handCount++;
				extraHand--;
			}
			
			for (int j = 0; j < handCount; j++) {
				players[i].receiveCard(cards.get(index));
				index++;
			}
			
			System.out.println(players[i].getHand());
			System.out.println(players[i].getHand().size());
		}

	}

	private void designateRanks() {
		// »Ì±â
		ArrayList<int[]> firstRank = new ArrayList<>();
		for (int i = 0; i < players.length; i++) {
			int tuple[] = new int[2];
			tuple[0] = i;
			tuple[1] = cards.get(i).getNumber();
			firstRank.add(tuple);
			System.out.printf("%d %d\n", firstRank.get(i)[0], firstRank.get(i)[1]);

		}
		// TODO °è±ÞÁ¤ÇÏ±â
		Collections.sort(firstRank, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[1] - o2[1];
			}
		});
		for (int i = 0; i < players.length; i++) {
			System.out.printf("%d %d\n", firstRank.get(i)[0], firstRank.get(i)[1]);
		}

		for (int i = 0; i < players.length; i++) {
			players[firstRank.get(i)[0]].setRank(i + 1);
		}
		// sort players based on rank
		
		Arrays.sort(players);
		for(int i = 0; i < players.length; i++) {
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
		// ¼¯±â
		Collections.shuffle(cards);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dalmuti game = new Dalmuti();
	}

}
