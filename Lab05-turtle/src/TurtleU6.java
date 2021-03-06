import java.util.Random;

import se.lth.cs.pt.window.SimpleWindow;

public class TurtleU6 {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "TurtleFight");
		Turtle t1 = new Turtle(w, 350, 350);
		Turtle t2 = new Turtle(w, 250, 250);
		Random rand = new Random();
		t1.penDown();
		t2.penDown(); 
		while (distance(t1, t2) >= 50) {
			t1.forward(rand.nextInt(10)+1);
			t1.left(rand.nextInt(361)-180);
			t2.forward(rand.nextInt(10)+1);
			t2.left(rand.nextInt(361)-180);
			SimpleWindow.delay(10);
		}
	}
	// beräkna avståndet
	private static double distance(Turtle t1, Turtle t2) {
		double distance = Math.sqrt(Math.pow((t1.getX() - t2.getX()),2)+Math.pow((t1.getY()-t2.getY()),2));
		return distance;
	}
}
