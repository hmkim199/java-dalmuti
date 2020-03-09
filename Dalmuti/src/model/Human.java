package model;

import java.util.Scanner;

import controller.Controller;
import model.Card;
import model.Player;
import view.MainView;

public class Human extends AI {

	public Human(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean wantsRevolution() {
		// TODO Auto-generated method stub
		controller.updateView();
		System.out.println("혁명을 원하시나요? (Y/N)");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		
		System.out.println(input);
		
		if (input.equals("Y")) {
			return true;
		}
		
		return false;
	}

//
//	@Override
//	public Card findTaxCard() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int[] selectCards(int exCardNum, int exCardsCount) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean wantsPass() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
