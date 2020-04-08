package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import controller.Controller;

abstract public class Player implements Comparable<Player> {
	private static int numberOfPlayers = 0;

	private String name;
	/**
	 * 0 = 계급 없음 1 = 달무티 DALMUTI 2 = 대주교 BISHOP 3 = 광부 STONECUTTER 4 = 농노 PEASANT
	 * 5이상은 사용할 순 있지만 이름이 주어지진 않음
	 */
	protected int rank;
	protected ArrayList<Card> hand;
	private int score;
	
	public Controller controller;

	public Player(String name) {
		this.name = name;
		this.rank = 0;
		this.hand = new ArrayList<Card>();
		this.score = 0;

//		System.out.println("Created player " + name);
		numberOfPlayers++;
	}

	public int getScore() {
		return this.score;
	}
	
	public void addScore(int gameScore) {
		this.score += gameScore;
	}
	
	abstract public boolean wantsRevolution();

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
			
//			System.out.println(tax);
			this.giveCard(tax, toPlayer);
			countOfCardsForTax--;
		}
	}

	abstract public Card findTaxCard();

	abstract public int[] selectCards(int exCardNum, int exCardsCount); 

	abstract public boolean wantsPass();

	// 이전 카드에 대한 정보를 받아서 내 카드 중 어느 걸 얼마나 낼지 정한 후, 카드 내기
	public int[] playCards(int exCardNum, int exCardsCount) {
		// cardsInfo에 무슨 카드를 몇장 냈는지 저장
		int[] cardsInfo = new int[2];
		if (!wantsPass()) {
			cardsInfo = selectCards(exCardNum, exCardsCount);

			if (cardsInfo[0] != 0 && cardsInfo[1] != 0) {
				Card card = new Card(cardsInfo[0]);
				for (int i = 0; i < cardsInfo[1]; i++) {
					this.getHand().remove(card);
				}
			}
		}
		
//		if (cardsInfo[0] != 0 && cardsInfo[1] != 0) {
//			System.out.println(cardsInfo[0] + "을" + cardsInfo[1] + "장 냈습니다.\n");
//		}
//		else {
//			System.out.println("패스했습니다.\n");
//		}
		return cardsInfo;
	}

	public boolean handIsEmpty() {
		return hand.size() == 0;
	}

	public void delay() {
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getName() {
		return name;
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
