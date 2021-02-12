package model;

import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import controller.Controller;
import model.Card;
import model.Player;
import view.MainView;

public class Human extends AI {

	public Human(String name) {
		super(name);
	}

	@Override
	public boolean wantsRevolution() {
		super.delay();
		
		return controller.askRevolution();
	}

	
	
	@Override
	public Card findTaxCard() {
		super.delay();
		Card tax;
		while(true) {
			System.out.println("세금으로 낼 카드를 입력하시오.");
			Scanner sc = new Scanner(System.in);
			tax = new Card(sc.nextInt());
			
			if(!this.hand.contains(tax)) {
				System.out.println("해당 카드가 내 패에 없습니다.\n");
				continue;	
			}
			else if(this.rank > 2) {
				if(!tax.equals(Collections.min(hand))) {
					System.out.println("가장 낮은 수의 카드를 세금으로 내야 합니다.\n");
					continue;
				}
			}
			System.out.println("야호 나는야 세금 잘내는 모범시민~!");
			break;
		}
		
		return tax;
		
		
//		try {
//			wait();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		controller.askToChooseTaxCard();
	}
//
//	@Override
//	public int[] selectCards(int exCardNum, int exCardsCount) {
//		// TODO Auto-generated method stub
//		super.updateAndDelay();
//
//		return null;
//	}
//
//	@Override
//	public boolean wantsPass() {
//		// TODO Auto-generated method stub
//		super.updateAndDelay();
//
//		return false;
//	}
	
}
