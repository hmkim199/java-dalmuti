import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Dalmuti {
	private final int NUMBER_OF_PLAYERS = 4;
	private Player[] players;
	private ArrayList<Card> cards;

	public Dalmuti() {
		System.out.println("Welcome to Dalmuti.");

		createCards();
		createPlayers();
		designateRanks();
		handOutCards();
		if (someoneWantsRevolution()) {
			revolution();
		} else {
			collectTaxes();
		}
	}

	private void collectTaxes() {
		// shallow copy. deep copy
		ArrayList hand = (ArrayList) players[0].getHand().clone();
		// ((ArrayList<Card>)hand).add(new Card(9999999));

		
		// 플레이어들의 핸드를

		System.out.println(hand);
		System.out.println(players[0].getHand());
//		Arrays.sort(hand);
	}

	private void revolution() {
		// 세금 없고 계급 반대
		System.out.println("혁명! 계급순서 변경!!!!!!!!!!");
		for (int j = 0; j < players.length; j++) {
			int newRank = players.length - j;
			players[j].setRank(newRank);
		}
		Arrays.sort(players);
		for (int k = 0; k < players.length; k++) {
			System.out.println(players[k]);
		}
	}

	private boolean someoneWantsRevolution() {
		// 혁명 가능 체크
		for (int i = 0; i < players.length; i++) {
			ArrayList<Card> hand = players[i].getHand();
			int indexFirst13 = hand.indexOf(new Card(13));
			int indexLast13 = hand.lastIndexOf(new Card(13));
			if (indexFirst13 != indexLast13) {
				System.out.println(players[i] + "가 13을 2개 가졌다");
				if (players[i].wantsRevolution()) {
					return true;
				}
			}
		}

		return false;
	}

	private void handOutCards() {

		Collections.shuffle(cards);

		for (int i = 0; i < cards.size(); i++) {
			int receiver = i % players.length;
			players[receiver].receiveCard(cards.get(i));
		}

		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i].getHand());
			// System.out.println(players[i].getHand().size());
		}

	}

	private void designateRanks() {
		// 뽑기
		ArrayList<int[]> firstRank = new ArrayList<>();
		for (int i = 0; i < players.length; i++) {
			int tuple[] = new int[2];
			tuple[0] = i;
			tuple[1] = cards.get(i).getNumber();
			firstRank.add(tuple);
			System.out.printf("%d %d\n", firstRank.get(i)[0], firstRank.get(i)[1]);

		}
		// TODO 계급정하기
		Collections.sort(firstRank, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[1] - o2[1];
			}
		});
		for (int i = 0; i < players.length; i++) {
			System.out.printf("%d %d\n", firstRank.get(i)[0], firstRank.get(i)[1]);
		}

		for (int i = 0; i < players.length; i++) {
			players[firstRank.get(i)[0]].setRank(i + 1);
		}
		// sort players based on rank

		Arrays.sort(players);
		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i]);
		}
	}

	private void createPlayers() {
		players = new Player[NUMBER_OF_PLAYERS];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player("Me" + i);
		}
	}

	private void createCards() {
		cards = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			for (int j = 0; j < i; j++) {

				cards.add(new Card(i));

			}
		}
		cards.add(new Card(13));
		cards.add(new Card(13));
		// 섞기
		Collections.shuffle(cards);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Dalmuti game = new Dalmuti();
		Car car1 = new Car();
		car1.setBrand("Hyundai Motors");
		 
		Car car2 = new Car();
		car2.setBrand("Mercedes Benz");
		 
		// shallow copy
		Car car3 = car1;
		Car car4 = new Car();
		try {
		    // example of deep copy
		    car4 = (Car) car2.clone();
		} catch(CloneNotSupportedException e) {
		    System.out.println(e.getMessage());
		}
		
		//shallow copy는 객체 = 객체 형태로 직접 대입, 같은 주소를 가짐. car1의 값을 바꾸든 3을 바꾸든 1,3 둘 다 같이 변함.
		car3.setBrand("changed");
		System.out.println(car1.getBrand());
		System.out.println(car3.getBrand());
		
		//deep copy는 객체.clone()의 형태로 복제하는 해당 클래스가 인터페이스 cloneable을 implements해야함. 예외처리도 해주어야 한다. 
		//다른 주소에 복사된 객체를 저장. 둘 중 어느것을 바꾸어도 다른 한쪽에는 영향을 주지 않음.
		car4.setBrand("changed");
		System.out.println(car2.getBrand());
		System.out.println(car4.getBrand());

		System.out.println();
		System.out.println(car1.hashCode());
		System.out.println(car2.hashCode());
		System.out.println(car3.hashCode());
		System.out.println(car4.hashCode());

	}

}
