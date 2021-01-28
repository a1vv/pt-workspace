import java.util.Random;

public class RaceTurtle extends Turtle {
	private final int number;
	
	public RaceTurtle(RaceWindow w, int nbr) {
		super(w,RaceWindow.getStartXPos(nbr),RaceWindow.getStartYPos(nbr));
		number = nbr;
		penDown();
		this.left(-90);
	}
	
	public void raceStep() {
		Random rnd = new Random();
		forward(rnd.nextInt(6));
	}
	public String toString() {
		return "Nummer " + number;
	}
}
