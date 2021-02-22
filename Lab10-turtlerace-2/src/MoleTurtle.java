public class MoleTurtle extends RaceTurtle {
	
	public MoleTurtle(RaceWindow w, int nbr) {
		super(w,nbr);
	}
	
	public void raceStep() {
		if(rnd.nextBoolean()) {
			penDown();
		} else {
			penUp();
		}
		super.raceStep();
	}
	public String toString() {
		return super.toString() + " - MoleTurtle";
	}
}
