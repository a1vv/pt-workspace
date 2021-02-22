import java.util.ArrayList;
import java.util.Random;

public class TurtleRace {
	private static ArrayList<RaceTurtle> turtles;
	private static ArrayList<RaceTurtle> finished;
	private static RaceWindow w;

	public static void main(String[] args) {
		w = new RaceWindow();
		turtles = new ArrayList<RaceTurtle>();
		finished = new ArrayList<RaceTurtle>();
		for(int i = 0 ; i < 8 ; i++) {
			turtles.add(newTurtle(i+1));
			System.out.println(turtles.get(i));
		}
		race();
	}  
	
	private static void race() {
		while(!turtles.isEmpty()) {
			for(int i = 0 ; i < turtles.size() ; i++) {
				RaceTurtle r = turtles.get(i);
				r.raceStep();
				RaceWindow.delay(2);
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
	
	private static RaceTurtle newTurtle(int nbr) {
		Random rnd = new Random();
		switch(rnd.nextInt(3)+1) {
			case 1 : return new DizzyTurtle(w,nbr,rnd.nextInt(5)+1);
			case 2 : return new MoleTurtle(w,nbr);
			case 3 : return new AbsentMindedTurtle(w,nbr,rnd.nextInt(101));
		}
		return null;
	}
}
