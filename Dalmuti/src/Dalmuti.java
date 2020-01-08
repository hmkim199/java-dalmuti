
public class Dalmuti {

	public Dalmuti() {
		System.out.println("Welcome to Dalmuti.");
		
		//카드 생성
		Card cards[] = new Card[57];
		int index = 0;
		for(int i = 1; i <= 10; i++) {			
			for(int j = 0; j < i; j++) {
				
				cards[index] = new Card(i);
				index++;	
			}
		}
		cards[55] = new Card(13);
		cards[56] = new Card(13);
		
		//player생성
		Player players[] = new Player[4];
		for(int i = 0; i<4; i++) {
			players[i] = new Player("Me" + i);
		}
		
		//card로 계급지정
		int first_rank[] = new int[4];
		for(int i = 0; i<4; i++) {
			first_rank[i] = (int) (Math.random()*56);
			System.out.println(first_rank[i]);
			System.out.println(cards[first_rank[i]]);
			
		}
		
			
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dalmuti game = new Dalmuti();
	}

}
