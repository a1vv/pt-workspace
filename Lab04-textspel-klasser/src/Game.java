import java.util.Scanner;

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

	// new game setup
	// TODO: handle player symbols in player class? kanske onödigt
	public void setup() {
		println("C O N N E C T   F O U R");
		// ask for player names and create player objects
		println("Player One: Enter your name");
		String p1n = scan.nextLine();
		p1 = new Player(p1n, "\u25cf");
		println("Player Two: Enter your name");
		String p2n = scan.nextLine();
		p2 = new Player(p2n, "\u25cb");
		// start game?
		println(p1n + " vs " + p2n + "!");
		println("Start game? y/n");
		start();
	}
	
	// start game
	private void start() {
		tiles = new Tiles(); //create a new empty board
		while (true) {
			char answer = scan.next().charAt(0);
			if (answer == 'y') {
				runGame();
			} else if (answer == 'n') {
				setup();
			} else {
				this.println("Invalid input. Enter y or n");
			}
		}
	}

	private void runGame() {
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
		println("Current score: " + p1.name() + " - " + p1.score() + " : " + p2.score() + " - " + p2.name());
		println("Play again? y/n");
		start();
	}

	// handles player turns
	private void turn() {
		println(currentPlayer.name() + "'s turn (" + currentPlayer.symbol() + "), pick a column.");
		columnChoice = scan.nextInt();
		if(tiles.turn(columnChoice)) { //check if columnChoice is in bounds
			if(!tiles.placeTile(currentPlayer,columnChoice)) { //check if column is full
				println("This column is already full! Pick another.");
				turn();
			}
		} else {
			println("Invalid choice! First column is 1 and last is " + tiles.boardSize() + ".");
			turn();
		}
	}

	private boolean isGameOver() {
		if(tiles.gameOverCheck(currentPlayer)) {
			printBoard();
			println("Game Over! " + currentPlayer.name() + " won!");
			return true;
		} else {
			return false;
		}
	}
	
	// prints the game board
	private void printBoard() {
		int bS = tiles.boardSize();
		for (int i = 1 ; i <= bS ; i++) { // indicators at top
			print("*" + " ");
		}
		println(""); //line break
		for (int i = 0; i < bS; i++) {
			for (int j = 0; j < bS; j++) {
				print(tiles.board()[bS - 1 - i][j] + "\u0020"); // u0020 = blankspace
			}
			println("");
		}
		for (int i = 1 ; i <= bS ; i++) { // numbers at bottom
			print(i + " ");
		}
		println(""); //line break
	}

	private void nextPlayer() {
		if (currentPlayer == p1) {
			currentPlayer = p2;
		} else {
			currentPlayer = p1;
		}
	}

	private void println(String s) {
		System.out.println(s);
	}
	private void print(String s) {
		System.out.print(s);
	}
}