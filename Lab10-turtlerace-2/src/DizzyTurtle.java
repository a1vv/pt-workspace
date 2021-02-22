
public class DizzyTurtle extends RaceTurtle {
	private final int dizzyness;
	
	public DizzyTurtle(RaceWindow w, int nbr, int dizzyness) {
		super(w,nbr);
		this.dizzyness = dizzyness;
	}
	
	public void raceStep() {
		super.raceStep();
		int angle = (rnd.nextInt(20)-9)*dizzyness/10;
		if(Math.abs(getDirection())>10) {
			angle = -getDirection();
		}
		left(angle);
	}     
	
	public String toString() {
		return super.toString() + " - DizzyTurtle (Yrsel : " + dizzyness + ")";
	}
}
