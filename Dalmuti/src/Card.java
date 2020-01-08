
public class Card {
	private int number;
	
	public Card(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "Card " + number; 
	}
}
