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
	
	public int selectCard(int exCardNum) {
		// 라운드 시작 때 선플레이어가 카드 고르기
		int selectedCardNum = 0;
		if (exCardNum == 0) {
			Card selectedCard = Collections.max(this.hand);
			selectedCardNum = selectedCard.getNumber();
			return selectedCardNum;
		}
		
		// 후 플레이어들 카드 고르기
		ArrayList<Integer> handInfo = new ArrayList<Integer>();		
		for(int i = 0; i < hand.size(); i++) {
			handInfo.add(hand.get(i).getNumber());
		}
		
		selectedCardNum = Collections.max(handInfo);
		while(selectedCardNum >= exCardNum) {
			handInfo.remove((Integer)selectedCardNum);
		
			selectedCardNum = Collections.max(handInfo);
		}
		
		return selectedCardNum;
		
	}
	
	// 라운드 시작 때 선플레이어가 해당 카드 몇 개 낼 지 정하기
	public int cardsCountToPlay(int selectedCardNum, int exCardsCount) {
		int cardsCount = 0;
		
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getNumber() == selectedCardNum) {
				cardsCount++;
			}
		}
			//return cardsCount;
		if(exCardsCount == 0) {
			return cardsCount;
		}
		else if(exCardsCount <= cardsCount) {
			cardsCount = exCardsCount;
		}
		else {
			//pass 해야 하는 경우
			cardsCount = -1;
		}
		return cardsCount;
		
	}
	
	public boolean wantsPass() {
		return Math.random() < 0.2;
	}
	
	// 이전 카드에 대한 정보를 받아서 내 카드 중 어느 걸 얼마나 낼지 정한 후, 카드 내기
	public int[] playCards(int exCardNum, int exCardsCount) {
		//cardsInfo에 무슨 카드를 몇장 냈는지 저장
		int[] cardsInfo = new int[2];
		if(!wantsPass()) {
			int selectedCardNum = selectCard(exCardNum);
			int cardsCount = cardsCountToPlay(selectedCardNum, exCardsCount);
			if(cardsCount != -1) {
				Card card = new Card(selectedCardNum);
				for (int i = 0; i < cardsCount; i++) {
					this.getHand().remove(card);
				}
				System.out.println(selectedCardNum + "을" + cardsCount + "장 냈습니다.\n");
				cardsInfo[0] = selectedCardNum;
				cardsInfo[1] = cardsCount;
				return cardsInfo;
			}
			
		}
		System.out.println("패스했습니다.\n");
		return cardsInfo;
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
