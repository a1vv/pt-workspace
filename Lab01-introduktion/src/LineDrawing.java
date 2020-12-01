import se.lth.cs.pt.window.SimpleWindow;

public class LineDrawing {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "LineDrawing");
		w.moveTo(0, 0);
		while (true) {
			w.waitForMouseClick();
			//getMouse verkar ju inte vara rätt så
			int mx = w.getClickedX();
			int my = w.getClickedY();
			w.lineTo(mx,my);
		}
	}
}
