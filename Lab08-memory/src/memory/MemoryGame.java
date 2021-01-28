package memory;

import java.util.Scanner;

public class MemoryGame {
	private static MemoryBoard mb;
	private static MemoryWindow mw;
	private static Scanner scan;
	private static String[] fronts;
	
	public static void main(String[] args) {
		String[] frontFileNames = {"can.jpg", "flopsy_mopsy_cottontail.jpg",
				"friends.jpg", "mother_ladybird.jpg", "mr_mcgregor.jpg",
				"mrs_rabbit.jpg", "mrs_tittlemouse.jpg", "radishes.jpg", "radishes.jpg" };
		scan = new Scanner(System.in);
		fronts = frontFileNames;
		newGame();
		
	}
	private static void newGame() {
		mb = new MemoryBoard(4,"back.jpg",fronts);
		mw = new MemoryWindow(mb);
		gameLoop();
	}
	
	private static void gameLoop() {
		mw.drawBoard();
		int moves = 0;
		while(true) {
			System.out.println("Pick two cards.");
			turnCards();
			moves++;
			if(mb.hasWon()) {
				mw.close();
				System.out.println("GAME FINISHED WITH " + moves + " MOVES!");
				System.out.println("Play again? (y/n)");
				if(scan.next().equals("y")) {
					newGame();
				}
			}
		}
		
	}
	private static void turnCards() {
		int r, c;
		int r1 = -1;
		int c1 = -1;
		int i = 0;
		while(true) {
			mw.waitForMouseClick();
			r = mw.getMouseRow();
			c = mw.getMouseCol();
			if(!mb.frontUp(r, c)) {
				turn(r,c);
				i++;
			}
			if(i == 2) {
				if(!mb.same(r1, c1, r, c)) {
					mw.delay(500);
					turn(r1,c1);
					turn(r,c);
				} else {
					System.out.println("Pair found!");
				}
				break;
			}
			r1 = r;
			c1 = c;
		}
	}
	private static void turn(int r, int c) {
		//System.out.println("turning card: " + r + ", " + c );
		mb.turnCard(r, c);
		mw.drawCard(r, c);
	}
}
