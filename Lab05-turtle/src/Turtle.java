import se.lth.cs.pt.window.SimpleWindow;

public class Turtle {
	private boolean isPenDown;
	private SimpleWindow w;
	private double x;
	private double y;
	private int alpha; // vinkel i grader
	
	/** Skapar en sköldpadda som ritar i ritfönstret w. Från början 
	    befinner sig sköldpaddan i punkten x, y med pennan lyft och 
	    huvudet pekande rakt uppåt i fönstret (i negativ y-riktning). */
	public Turtle(SimpleWindow w, int x, int y) {
		this.w = w;
		this.x = x;
		this.y = y;
		isPenDown = false;
		this.alpha = 90;
		w.moveTo(x,y);
	}

	/** Sänker pennan. */
	public void penDown() {
		isPenDown = true;
	}
	
	/** Lyfter pennan. */
	public void penUp() {
		isPenDown = false;
	}
	
	/** Går rakt framåt n pixlar i den riktning huvudet pekar. */
	public void forward(int n) {
		w.moveTo((int) Math.round(x), (int) Math.round(y));
		x += n * Math.cos(Math.toRadians(getDirection()));
		y -= n * Math.sin(Math.toRadians(getDirection()));
		if( isPenDown ) {
			w.lineTo((int) Math.round(x), (int) Math.round(y));
		} else {
			w.moveTo((int) Math.round(x), (int) Math.round(y));
		}
		
	}

	/** Vrider beta grader åt vänster runt pennan. */
	public void left(int beta) {
		alpha += beta;
	}

	/** Går till punkten newX, newY utan att rita. Pennans läge (sänkt
	    eller lyft) och huvudets riktning påverkas inte. */
	public void jumpTo(int newX, int newY) {
		x = newX;
		y = newY;
	}

	/** Återställer huvudriktningen till den ursprungliga. */
	public void turnNorth() {
		alpha = 90;
	}

	/** Tar reda på x-koordinaten för sköldpaddans aktuella position. */
	public int getX() {
		return (int) Math.round(x);
	}

 	/** Tar reda på y-koordinaten för sköldpaddans aktuella position. */
	public int getY() {
		return (int) Math.round(y);
	}
  
	/** Tar reda på sköldpaddans riktning, i grader från den positiva X-axeln. */
 	public int getDirection() {
 		return alpha;
	}
}
