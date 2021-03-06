import java.util.Random;

import se.lth.cs.pt.window.SimpleWindow;

public class TurtleU5 {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "TurtleDraw1000");
		Turtle t = new Turtle(w, 300, 300);
		Random rand = new Random();
		t.penDown();
		for (int i = 0; i < 1000; i++) {
			t.forward(rand.nextInt(10)+1);
			SimpleWindow.delay(10);
			int ss = rand.nextInt(361)-180;
			t.left(ss);
		}
	}
}
