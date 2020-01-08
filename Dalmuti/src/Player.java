enum Rank{
	none, dalmuti, bishop, flush, peasant
}
public class Player {
	String name;
	Rank rank;
	
	public Player(String name) {
		this.name = name;
		this.rank = Rank.none;
		
		System.out.println("Created player " + name);
	}
}
