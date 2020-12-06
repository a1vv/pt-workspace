import se.lth.cs.pt.maze.Maze;
import se.lth.cs.pt.window.SimpleWindow;

public class MazeWalker {
	private Turtle turtle;

	public MazeWalker(Turtle turtle) {
		this.turtle = turtle;
		this.turtle.penDown();
	}

	public int[] walk(Maze maze) {
		boolean wallLeft, wallFront;
		turtle.jumpTo(maze.getXEntry(), maze.getYEntry());
		int[] walk = new int[2];
		walk[0] = 0;
		walk[1] = 0;
		while (!maze.atExit(turtle.getX(), turtle.getY())) {
			wallLeft = maze.wallAtLeft(turtle.getDirection(), turtle.getX(), turtle.getY());
			wallFront = maze.wallInFront(turtle.getDirection(), turtle.getX(), turtle.getY());
			if (wallLeft && !wallFront) {
				turtle.forward(1);
				walk[0]++;
			} else if (wallLeft && wallFront) {
				turtle.left(-90);
				walk[1]++;
			} else if (!wallLeft) {
				turtle.left(90);
				walk[1]++;
				turtle.forward(1);
				walk[0]++;
			}
			SimpleWindow.delay(2);
		}
		return walk;
	}
}
