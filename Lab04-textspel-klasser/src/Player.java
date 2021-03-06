// keep track of player scores and names
public class Player {
	private String symbol;
	private String name;
	private int score;

	public Player(String name, String symbol) {
		this.symbol = symbol;
		this.name = name;
		this.score = 0;
	}

	public String symbol() {
		return this.symbol;
	}

	public String name() {
		return this.name;
	}
	
	public void win() {
		this.score++;
	}
	public int score() {
		return this.score;
	}
}