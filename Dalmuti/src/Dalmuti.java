import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Dalmuti {
	Player[] players;
	ArrayList<Card> cards;

	public Dalmuti() {
		System.out.println("Welcome to Dalmuti.");

		createCards();
		createPlayers();
		designateRanks();

	}

	private void designateRanks() {
		// »Ì±â
		ArrayList<int[]> firstRank = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
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
		for (int i = 0; i < 4; i++) {
			System.out.printf("%d %d\n", firstRank.get(i)[0], firstRank.get(i)[1]);
		}

		players[firstRank.get(0)[0]].rank = Rank.dalmuti;
		players[firstRank.get(1)[0]].rank = Rank.bishop;
		players[firstRank.get(2)[0]].rank = Rank.flush;
		players[firstRank.get(3)[0]].rank = Rank.peasant;
	}

	private void createPlayers() {
		players = new Player[4];
		for (int i = 0; i < 4; i++) {
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
