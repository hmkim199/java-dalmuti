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

//		System.out.println("Created player " + name);
		numberOfPlayers++;
	}

	public ArrayList<Card> playCards(Card currentCard, int count) {
		ArrayList<Card> cards = new ArrayList<Card>();
		
		int[] assortedCards = sortCards();
		
		if (currentCard == null) {
			// 가진 카드 중 가장 높은 숫자 다 내기
			int maxCount = 0;
			int maxIndex = 0;
			
			for (int i = 1; i <= 13; i++) {
				if (assortedCards[i] >= maxCount) {
					maxCount = assortedCards[i];
					maxIndex = i;
				}
			}
			
//			System.out.println("Card " + maxIndex + "이 " + maxCount + "장으로 가장 많음.");
			
			Card card = new Card(maxIndex);
			for (int i = 0; i < maxCount; i++) {
				cards.add(card);
				hand.remove(card);
			}
			
			return cards;
		} else {
			// 가진 카드 중 조건에 맞는 가장 큰 숫자 20% 확률로 내기
			// 조건: currentCard보다 작은 수가 count만큼 있음
			int cardIndex = currentCard.getNumber();
			for (int i = cardIndex; i > 0; i--) {
				if (assortedCards[i] >= count) {
					int random = (int)(Math.random() * 100);
//					System.out.println("Card " + assortedCards[i] + " 낼 수 있음.");
					if (random < 20) {
						// 내지 않음
						return cards;
					} else {
						Card card = new Card(i);
						for (int j = 0; j < count; j++) {
							cards.add(card);
							hand.remove(card);
						}
						
						return cards;
					}
				}
			}
		}

		return cards;
	}
	
	private int[] sortCards() {
		int[] assortedCards = new int[14];
		for (int i = 0; i < hand.size(); i++) {
			int cardNumber = hand.get(i).getNumber();
			assortedCards[cardNumber]++;
		}

		System.out.println("-----------------------------------------------------------------");
		System.out.print("| ");
		for (int i = 1; i <= 13; i++) {
			System.out.printf("%2d | ", i);
		}
		System.out.println();
		System.out.println("-----------------------------------------------------------------");

		System.out.print("| ");
		for (int i = 1; i <= 13; i++) {
			System.out.printf("%2d | ", assortedCards[i]);
		}
		System.out.println();
		System.out.println("-----------------------------------------------------------------");
		
		return assortedCards;
	}

	public boolean wantsRevolution() {
		return Math.random() < 0.5;
	}

	private void giveCard(Card card, Player toPlayer) {
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
			System.out.println("세금을 낼 대상이 아닙니다.");
			return;
		}

		for (int i = 0; i < nExchange; i++) {
			Card selectedCard = selectCard();
			giveCard(selectedCard, toPlayer);

//			System.out.println("Rank " + rank + " pays " + selectedCard + " to rank " + toPlayer.rank);
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public ArrayList<Card> getHand() {
		return hand;
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
