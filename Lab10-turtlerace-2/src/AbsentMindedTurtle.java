
public class AbsentMindedTurtle extends RaceTurtle {
	private final int degree;
	
	public AbsentMindedTurtle(RaceWindow w, int nbr, int degree) {
		super(w,nbr);
		this.degree = degree;
	}
	
	public void raceStep() {
		if(rnd.nextInt(100) > degree) {
			super.raceStep();
		}
	
	}
	public String toString() {
		return super.toString() + " - AbsentMindedTurtle (" + degree + "% frånvarande)";
	}
}
