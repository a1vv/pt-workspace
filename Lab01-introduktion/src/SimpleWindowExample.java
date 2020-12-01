import se.lth.cs.pt.window.SimpleWindow;
import java.awt.Color;

public class SimpleWindowExample {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500,500,"Drawing Window");
		w.setLineColor(Color.BLUE);
		w.moveTo(100,100);
		w.lineTo(150,100);
	}
}
