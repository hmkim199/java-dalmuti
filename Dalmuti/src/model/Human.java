package model;

import java.util.Scanner;

import model.Card;
import model.Player;

public class Human extends AI {

	public Human(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean wantsRevolution() {
		// TODO Auto-generated method stub
		System.out.println("혁명을 원하시나요? (Y/N)");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		
		System.out.println(input);
		
		if (input == "Y") {
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
