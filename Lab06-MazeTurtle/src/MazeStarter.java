import java.util.Scanner;

import se.lth.cs.pt.maze.Maze;
import se.lth.cs.pt.window.SimpleWindow;

public class MazeStarter {
	private static Scanner scan;

	public static void main(String args[]) {
		scan = new Scanner(System.in);
		start();
	}

	private static void start() {
		System.out.println("Choose a maze (1-5)");
		int mazeNumber = scan.nextInt();
		SimpleWindow w = new SimpleWindow(600, 600, "TurtleMaze");
		Turtle turtle = new Turtle(w, 300, 300);
		MazeWalker mw = new MazeWalker(turtle);
		Maze m = new Maze(mazeNumber);
		m.draw(w);
		int[] walk = mw.walk(m);
		System.out.println("Maze Walked: " + walk[0] + " steps taken and " + walk[1] + " direction changes.");
		start();
	}
}
