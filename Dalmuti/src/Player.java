import java.util.ArrayList;
import java.util.Collections;

public class Player implements Comparable<Player> {
	private static int numberOfPlayers = 0;

	private String name;
	/**
	 * 0 = 계급 없음 1 = 달무티 2 = 대주교 3 = 광부 4 = 농노 5이상은 사용할 순 있지만 이름이 주어지진 않음
	 */
	private int rank;
	private ArrayList<Card> hand;

	public Player(String name) {
		this.name = name;
		this.rank = 0;
		this.hand = new ArrayList<Card>();

		System.out.println("Created player " + name);
		numberOfPlayers++;
	}

	public boolean wantsRevolution() {
		return Math.random() < 0.5;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void giveCard(Card card, Player toPlayer) {
		this.hand.remove(card);
		toPlayer.receiveCard(card);
	}

	public void receiveCard(Card card) {
		hand.add(card);
	}

	public void payTaxTo(Player toPlayer) {
		int nExchange = 0;
		if (rank == 1 || rank == numberOfPlayers) {
			nExchange = 2;
		} else if (rank == 2 || rank == numberOfPlayers - 1) {
			nExchange = 1;
		} else {
			System.out.println("세금을 낼 대상이 아닙니다.\n");
			return;
		}

		System.out.println("===== Rank " + rank + " pays tax to rank " + toPlayer.rank + " =====\n");

		for (int i = 0; i < nExchange; i++) {
			Card selectedCard = selectCard();
			giveCard(selectedCard, toPlayer);

//			System.out.println("Selected card: " + selectedCard);
//			
//			System.out.println(hand);
//			System.out.println(toPlayer.hand);
//			System.out.println();
		}
	}

	private Card selectCard() {
		Card selectedCard = null;

		if (rank <= 2) {
			int randomIndex = (int) (Math.random() * hand.size());
			selectedCard = hand.get(randomIndex);
		} else if (rank > numberOfPlayers - 2) {
			selectedCard = Collections.min(hand);
		}

		return selectedCard;
	}

	@Override
	public String toString() {
		return "Player " + name;
	}

	@Override
	public int compareTo(Player o) {
		return this.rank - o.rank;
	}
}
