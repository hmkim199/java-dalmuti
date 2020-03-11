package model;

import java.util.Collections;
import java.util.Scanner;

public class AI extends Player {

	public AI(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean wantsRevolution() {
		super.updateAndDelay();
		return Math.random() < 0.5;
	}

	@Override
	public Card findTaxCard() {
		super.updateAndDelay();

		Card tax = Collections.min(this.hand);

		if (this.rank <= 2) {
			int taxIndex = (int) (Math.random() * this.getHand().size());
			tax = this.getHand().get(taxIndex);
		}
		return tax;

	}

	@Override
	public int[] selectCards(int exCardNum, int exCardsCount) {
		super.updateAndDelay();

		int[] cardsInfo = new int[2];
		int[] handCount = new int[14];

		for (int i = 0; i < hand.size(); i++) {
			handCount[hand.get(i).getNumber()] += 1;
		}

		if (exCardNum == 0) {
			for (int i = 13; i > 0; i--) {
				if (handCount[i] > 0) {
					cardsInfo[0] = i;
					cardsInfo[1] = handCount[i];
					break;
				}
			}

		} else {
			for (int i = exCardNum - 1; i > 0; i--) {
				if (handCount[i] >= exCardsCount) {
					cardsInfo[0] = i;
					cardsInfo[1] = exCardsCount;
					break;
				}
			}
		}

		return cardsInfo;
	}

	@Override
	public boolean wantsPass() {
		super.updateAndDelay();

		return Math.random() < 0.2;
	}

}
