import java.util.ArrayList;

enum Rank{
	none, dalmuti, bishop, flush, peasant
}
public class Player implements Comparable {
	private String name;
	private Rank rank;
	private ArrayList<Card> hand;
	
	public Player(String name) {
		this.name = name;
		this.rank = Rank.none;
		this.hand = new ArrayList<Card>();
		
		System.out.println("Created player " + name);
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void receiveCard(Card card) {
		hand.add(card);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Player that = (Player)o;
		return this.rank.ordinal() - that.rank.ordinal();
	}
	@Override
	public String toString() {
		return "Player " + name; 
	}
}
