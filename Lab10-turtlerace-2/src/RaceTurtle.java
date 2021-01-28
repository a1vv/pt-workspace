import java.util.Random;

public class RaceTurtle extends Turtle {
	protected final int number;
	protected final Random rnd;
	
	public RaceTurtle(RaceWindow w, int nbr) {
		super(w,RaceWindow.getStartXPos(nbr),RaceWindow.getStartYPos(nbr));
		number = nbr;
		rnd = new Random();
		penDown();
		this.left(-90);
	}
	
	public void raceStep() {
		forward(rnd.nextInt(6));
	}
	public String toString() {
		return "Nummer " + number;
	}
}
