import se.lth.cs.pt.window.SimpleWindow;

public class Mole {
	private int width = 30;
	private int height = 50;
	private Graphics g = new Graphics(width, height, 10);

	public static void main(String[] args) {
		Mole m = new Mole();
		// lite förvirrad över varför man måste skapa ett nytt mole-objekt inuti
		// mole-klassen
		m.drawWorld();
		m.dig();

	}

	public void drawWorld() {
		g.rectangle(0, 0, g.getWidth(), g.getHeight(), Colors.SOIL);
	}

	public void dig() {
		int x = g.getWidth() / 2;
		int y = g.getHeight() / 2;
		while (true) {
			if(x<0) {
				x=0;
			} else if (x>=width) {
				x=width-1;
			}
			if(y<0) {
				y=0;
			} else if (y>=height) {
				y=height-1;
			}
			
			g.block(x, y, Colors.MOLE);
			char key = g.waitForKey();
			g.block(x,y,Colors.TUNNEL);
			if(key == 'w') {
				y -= 1;
			} else if (key == 'a') {
				x -= 1;
			} else if (key == 's') {
				y += 1;
			} else if (key == 'd') {
				x += 1;
				
			}
		}
	}
}
