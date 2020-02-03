import java.util.ArrayList;
import java.util.Collections;


public class Player implements Comparable<Player> {
	private static int numberOfPlayers = 0;
	
	private String name;
	/**
	 * 0 = 계급 없음
	 * 1 = 달무티 DALMUTI
	 * 2 = 대주교 BISHOP
	 * 3 = 광부 STONECUTTER
	 * 4 = 농노 PEASANT
	 * 5이상은 사용할 순 있지만 이름이 주어지진 않음
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

	public Card findMinCard() {
		
		Card minCard = Collections.min(this.hand);
		return minCard;
		
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
