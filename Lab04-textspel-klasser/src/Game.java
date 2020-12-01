import java.util.Scanner;
import se.lth.cs.pt.timer.Timer;

// handles game functionality and user interaction
public class Game {
	private Tiles tiles;
	private Player currentPlayer;
	private Player p1;
	private Player p2;
	private int columnChoice;
	Scanner scan;

	public Game() {
		scan = new Scanner(System.in);
	}

	public void setup() {
		print("C O N N E C T   F O U R");
		// ask for player names and create player objects
		print("Player One: Enter your name");
		String p1n = scan.nextLine();
		p1 = new Player(p1n, "\u25cf");
		print("Player Two: Enter your name");
		String p2n = scan.nextLine();
		p2 = new Player(p2n, "\u25cb");
		// start game?
		print(p1n + " vs " + p2n + "!");
		print("Start game? y/n");
		start();
	}
	
	public void start() {
		tiles = new Tiles();
		while (true) {
			char answer = scan.next().charAt(0);
			if (answer == 'y') {
				runGame();
			} else if (answer == 'n') {
				setup();
			} else {
				this.print("Invalid input. Enter y or n");
			}
		}
	}

	public void runGame() {
		//main game loop
		while (true) {
			nextPlayer();
			printBoard();
			turn();
			if(isGameOver()) {
				break;
			}
		}
		//game ended, play again?
		print("Current score: " + p1.name() + " - " + p1.score() + " : " + p2.score() + " - " + p2.name());
		print("Play again? y/n");
		start();
	}

	private void turn() {
		print(currentPlayer.name() + "'s turn (" + currentPlayer.symbol() + "), pick a column.");
		columnChoice = scan.nextInt();
		if (columnChoice >= 1 && columnChoice <= 9) {
			if (tiles.placeTile(currentPlayer, columnChoice)) {
				print("This column is already full! Pick another.");
				turn();
			}
		} else {
			print("Invalid choice! First column is 1 and last is 9.");
			turn();
		}
	}

	private boolean isGameOver() {
		if(tiles.gameOverCheck(currentPlayer)) {
			for (int i = 0 ; i < 10 ; i++) {print("");}
			print("Game Over! " + currentPlayer.name() + " won!");
			return true;
		} else {
			return false;
		}
	}

	private void printBoard() {
		//print("1 2 3 4 5 6 7 8 9");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(tiles.board()[8 - i][j] + "\u0020");
			}
			print("");
		}
		print("1 2 3 4 5 6 7 8 9");
		//print("\uff11\u0020\uff12\u0020\uff13\u0020\uff14\u0020\uff15\u0020\uff16\u0020\uff17\u0020\uff18\u0020\uff19");
	}

	private void nextPlayer() {
		if (currentPlayer == p1) {
			currentPlayer = p2;
		} else {
			currentPlayer = p1;
		}
	}

	private void print(String s) {
		System.out.println(s);
	}
}