import java.util.ArrayList;

public class TurtleRace {
	private static ArrayList<RaceTurtle> turtles;
	private static ArrayList<RaceTurtle> finished;

	public static void main(String[] args) {
		RaceWindow w = new RaceWindow();
		turtles = new ArrayList<RaceTurtle>();
		finished = new ArrayList<RaceTurtle>();
		for(int i = 0 ; i < 8 ; i++) {
			turtles.add(new RaceTurtle(w,i+1));
		}
		race();
	}
	
	private static void race() {
		while(!turtles.isEmpty()) {
			for(int i = 0 ; i < turtles.size() ; i++) {
				RaceTurtle r = turtles.get(i);
				r.raceStep();
				RaceWindow.delay(10);
				if(r.getX() > RaceWindow.X_END_POS) {
					finished.add(r);
					turtles.remove(i);
				}
			}
		}
		System.out.println("På plats ett: " + finished.get(0));
		System.out.println("På plats två: " + finished.get(1));
		System.out.println("På plats tre: " + finished.get(2));
	}
}
