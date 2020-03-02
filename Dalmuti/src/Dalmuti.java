import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Dalmuti {
	private final int NUMBER_OF_PLAYERS = 4;
	private final int NUMBER_OF_GAMES = 5;
	private Player[] players;
	private ArrayList<Card> cards;
	private int newRank;
	
	public Dalmuti() {
		System.out.println("Welcome to Dalmuti.");

//		createCards();
//		createPlayers();
//		designateRanks();
//		
//		for (int nGames = 0; nGames < NUMBER_OF_GAMES; nGames++) {
//			Arrays.sort(players);
//			handOutCards();
//			if (someoneWantsRevolution()) {
//				revolution();
//			} else {
//				collectTaxes();
//			}
//			System.out.println(nGames+"번째 게임입니다.");
//			playGame();
//			aggregateScore();
//		}
//		
//		for(int i = 0; i < players.length; i++) {
//			System.out.println("점수 집계!");
//			System.out.println((i+1)+"등은 "+players[i]+" 점수는 "+players[i].getScore());
//		}
//		
	}
	
	Player[] getPlayers() {
		return players;
	}

	private void playGame() {
		// TODO 달무티가 카드를 내면 그 다음 사람들은 더 낮은 숫자의 카드를 해당개수만큼 내야함. 모두가 못내면 한 라운드 끝
		// 이전 라운드에 마지막으로 낸 사람이 다음 라운드의 선 플레이어가 됨.
		// 한 게임에 여러 라운드가 있다. 한명 제외 모두가 카드를 소진했을 때 한 게임 끝.
		
		int firstPlayer = 0;
		int thisRound = 0;
		newRank = 1;
//		System.out.println("\n게임 시작!!\n");
		while (true) {
//			System.out.println("\n**********" + thisRound + "번째 라운드 입니다.*************\n");
//			System.out.println("선플레이어는 " + firstPlayer + "입니다.");
			firstPlayer = playRound(firstPlayer);

			thisRound++;
			if(firstPlayer == -1) {
				break;
			}
		}
		
//		for (Player player: players) {
//			System.out.print(player + "   ");
//		}
//		System.out.println();
	}

	private int playRound(int firstPlayer) {

		int totalTurn = firstPlayer;
		int turn = 0;
		int[] play = new int[2];
		int passCount = 0;
		int exCardNum = 0;
		int exCardsCount = 0;
		

		while (true) {
			turn = totalTurn % players.length;

			if (players[turn].handIsEmpty()) {
				passCount++;
			}
			else {
//				System.out.println(turn + "번째 player 차례입니다.");
//				System.out.println(players[turn].getHand());
				
				play = players[turn].playCards(exCardNum, exCardsCount);

				if (play[0] == 0 && play[1] == 0) {
					passCount++;
				} else {
					exCardNum = play[0];
					exCardsCount = play[1];
					passCount = 0;
				}
				if(players[turn].handIsEmpty()) {
					players[turn].setRank(newRank);
					newRank++;
					
//					System.out.println("#######" + players[turn] + "끝!!!!!!!났고");
//					System.out.println("########새 랭크는"+players[turn].getRank());	
				}
			}
			
			if (passCount == players.length - 1) {
//				System.out.println("라운드 끝");
				turn = (turn + 1) % players.length;
				break;
			}
			totalTurn++;
			
			int donePlayers = 0;
			for (int i = 0; i < players.length; i++) {

				if (players[i].handIsEmpty()) {
					donePlayers++;
				}
			}
			
//			System.out.println(donePlayers + "명이 패를 모두 소진했습니다.");
			
			if (donePlayers == players.length - 1) {
				for (int i = 0; i < players.length; i++) {
					if(!players[i].handIsEmpty()) {
						players[i].setRank(newRank);
						break;
					}
				}
				turn = -1;
				break;
			}
		}
		return turn;
	}
	
	private void aggregateScore() {
		
		for(int i = 0; i<players.length; i++) {
			players[i].addScore(players.length - players[i].getRank());
		}
		
		Arrays.sort(players, new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				// TODO Auto-generated method stub
				return o2.getScore() - o1.getScore();
			}
		});
	}

	private void collectTaxes() {
		players[players.length - 1].payTax(2, players[0]);
		players[players.length - 2].payTax(1, players[1]);
		players[1].payTax(1, players[players.length - 2]);
		players[0].payTax(2, players[players.length - 2]);

//		System.out.println("세금 교환 완료");
		for (int i = 0; i < 4; i++) {
//			System.out.println(players[i].getHand());
		}
	}

	private void revolution() {
		// 세금 없고 계급 반대
//		System.out.println("혁명! 계급순서 변경!!!!!!!!!!");
		for (int j = 0; j < players.length; j++) {
			int newRank = players.length - j;
			players[j].setRank(newRank);
		}
		Arrays.sort(players);
//		for (int k = 0; k < players.length; k++) {
//			System.out.println(players[k]);
//		}
	}

	private boolean someoneWantsRevolution() {
		// 혁명 가능 체크
		for (int i = 0; i < players.length; i++) {
			ArrayList<Card> hand = players[i].getHand();
			int indexFirst13 = hand.indexOf(new Card(13));
			int indexLast13 = hand.lastIndexOf(new Card(13));
			if (indexFirst13 != indexLast13) {
//				System.out.println(players[i] + "가 13을 2개 가졌다");
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

//		for (int i = 0; i < players.length; i++) {
//			System.out.println(players[i].getHand());
//			// System.out.println(players[i].getHand().size());
//		}

	}

	void designateRanks() {
		// 뽑기
		int rank = 0;
		for(int i = 0; i < players.length; i++) {
			rank = cards.get(i).getNumber();
			players[i].setRank(rank);
			
//			System.out.println(rank);
		}

		Arrays.sort(players);
		for (int i = 0; i < players.length; i++) {
			players[i].setRank(i + 1);
//			System.out.println(players[i]);
//			System.out.println("랭크는"+players[i].getRank()+"입니다!!!");
		}
	}

	void createPlayers() {
		players = new Player[NUMBER_OF_PLAYERS];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player("Me" + i);
		}
	}

	void createCards() {
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
		Dalmuti game = new Dalmuti();

	}

}
