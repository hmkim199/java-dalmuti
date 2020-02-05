import java.util.ArrayList;
import java.util.Collections;

public class Player implements Comparable<Player> {
	private static int numberOfPlayers = 0;

	private String name;
	/**
	 * 0 = 계급 없음 1 = 달무티 DALMUTI 2 = 대주교 BISHOP 3 = 광부 STONECUTTER 4 = 농노 PEASANT
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

	public void payTax(int countOfCardsForTax, Player toPlayer) {

		while (countOfCardsForTax != 0) {
			Card tax = this.findTaxCard();

			System.out.println(tax);
			this.giveCard(tax, toPlayer);
			countOfCardsForTax--;
		}
	}

	
	public Card findTaxCard() {

		Card tax = Collections.min(this.hand);

		if (this.rank <= 2) {
			int taxIndex = (int) (Math.random() * this.getHand().size());
			tax = this.getHand().get(taxIndex);
		}
		return tax;

	}

	// 라운드 시작 때 달무티가 카드 고르기
	public Card dalSelectCard() {
		Card selectedCard = Collections.max(this.hand);
		return selectedCard;
	}
	
	public Card selectCard(int exCardNum) {
		
		ArrayList<Integer> handInfo = new ArrayList<Integer>();		
		for(int i = 0; i < hand.size(); i++) {
			handInfo.add(hand.get(i).getNumber());
		}
		
		int myMaxCardIndex = Collections.max(handInfo);
		while(myMaxCardIndex >= exCardNum) {
			handInfo.remove(myMaxCardIndex);
			myMaxCardIndex = Collections.max(handInfo);
		}
		int selectedCardNum = handInfo.get(myMaxCardIndex);
		selectedCardNum = hand.indexOf(selectedCardNum);
		
		return hand.get(selectedCardNum);
		
	}
	
	// 라운드 시작 때 달무티가 골랐던 카드 몇 개 낼 지 정하기
	public int dalCardsCountToPlay() {
		Card cardToPlay = dalSelectCard();
		int count = 0;
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i) == cardToPlay) {
				count++;
			}
		}
		return count;
	}
	
	public int[] dalPlayCards() {
		Card card = dalSelectCard();
		int nCard = dalCardsCountToPlay();
		int[] cardInfo = new int[2];
		for(int i = 0; i < nCard; i++) {
			card =  dalSelectCard();
			hand.remove(card);
		}
		
		cardInfo[0] = card.getNumber();
		cardInfo[1] = nCard;
		
		return cardInfo;
		
	}
	
	public void playCards(int cardNum, int nCard) {
		Card card = new Card(cardNum);
		for (int i = 0; i < nCard; i++) {
			this.getHand().remove(card);
		}
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
