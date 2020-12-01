import se.lth.cs.pt.window.SimpleWindow;

public class Graphics {
	private int width;
	private int blockSize;
	private int height;

	private SimpleWindow w;

	public Graphics(int w, int height, int block) {
		width = w;
		blockSize = block;
		this.height = height;
		this.w = new SimpleWindow(width * blockSize, height * blockSize, "Diggin");

	}

	public void block(int x, int y, java.awt.Color color) {
		w.setLineColor(color);
		int left = x * blockSize; // x-koordinat för vänster sida
		int right = left + blockSize - 1; // blocket är $blockSize brett
		int top = y * blockSize; // y koordinat för översta raden
		int bottom = top + blockSize - 1; // blocket är blockSize högt
		// ritar en rad i taget tills den når sista raden och bildat ifylld kvadrat
		for (int row = top; row <= bottom; row++) {
			w.moveTo(left, row);
			w.lineTo(right, row);
		}
	}

	public void square() {
		w.moveTo(10, 10);
		w.lineTo(10, 20);
		w.lineTo(20, 20);
		w.lineTo(20, 10);
		w.lineTo(10, 10);
	}
	
	public void rectangle(int x, int y, int width, int height, java.awt.Color c) {
		for (int yy = y; yy < y + height; yy++) {
			for (int xx = x; xx < x + width; xx++) {
				block(xx, yy, c);
			} // blocken ritas rad för rad vänster-höger upp-ned
		}
	}
	
	public char waitForKey() {
		return w.waitForKey();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
