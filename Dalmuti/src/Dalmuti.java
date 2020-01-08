
public class Dalmuti {

	public Dalmuti() {
		System.out.println("Welcome to Dalmuti.");
		
		//카드 생성
		Card cards[] = new Card[57];
		int index = 0;
		for(int i = 1; i <= 10; i++) {			
			for(int j = 0; j < i; j++) {
				
				cards[index] = new Card(i);
				//System.out.printf("%d,%d/", index,i);
				index++;	
			}
			System.out.println();
		}
		cards[55] = new Card(13);
		cards[56] = new Card(13);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dalmuti game = new Dalmuti();
	}

}
