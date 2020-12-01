import se.lth.cs.pt.square.Square;
import se.lth.cs.pt.window.SimpleWindow;

public class Uppgift7 {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "DrawSquare");
		Square sq = new Square(0, 0, 100);
		int oldX = 0; // x-koordinaten för "förra punkten"
		int oldY = 0; // y-koordinaten
		int nbrSteps = 10; // antal steg till ny position
		while (true) {
			w.waitForMouseClick();
			int x = w.getClickedX();
			int y = w.getClickedY();
			int xDist = x - oldX;
			int yDist = y - oldY;
			// sq.erase(w); tar bara bort den senaste
			for (int i = 0; i < nbrSteps; i++) {
				sq.move(xDist / nbrSteps, yDist / nbrSteps);
				sq.draw(w);
				System.out.println("square " + i);
			}
			oldX = x;
			oldY = y;
		}
	}
}
