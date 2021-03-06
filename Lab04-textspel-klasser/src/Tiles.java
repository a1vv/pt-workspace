// handles gamedata
public class Tiles {
	private String[][] board; //playing board
	private int[] lastTop; // height of the latest placed tile, each column has it's own value
	private int lastCol; // column of the latest placed tile
	private int boardSize;

	public Tiles() {
		boardSize = 9;
		lastTop = new int[boardSize];
		board = new String[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			lastTop[i] = -1; // no tiles have been placed yet, gets incremented with every placement
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = " ";
			}
		}
	}
	
	// checks the chosen column
	public boolean turn(int c) {
		if(c > 0 && c <= boardSize) {
			return true;
		} else {
			return false;
		}
	}

	// changes the board data when a player places a tile
	public boolean placeTile(Player p, int column) {
		column -= 1; //user input is 1 higher than real number
		if (lastTop[column] >= boardSize-1) { //index = boardSize-1 (ex boardSize 9 => lastTop[boardSize]: 0,1,2,..,7,8)
			return false; //column is full
		} else {
			lastTop[column]++;
			board[lastTop[column]][column] = p.symbol();
			lastCol = column;
			return true;
		}
	}

	public boolean gameOverCheck(Player p) {
		if (verticalCheck(p) || horizontalCheck(p) || diagonalCheck(p, false) || diagonalCheck(p, true)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean verticalCheck(Player p) {
		int count = 0;
		for (int i = lastTop[lastCol]; i >= 0; i--) {
			if (board[i][lastCol].equals(p.symbol())) {
				count++;
				if (count == 4) {
					p.win();
					return true;
				}
			} else {
				count = 0;
			}
		}
		return false;
	}

	private boolean horizontalCheck(Player p) {
		int count = 0;
		for (int i = 0; i < boardSize; i++) {
			if (board[lastTop[lastCol]][i].equals(p.symbol())) {
				count++;
				if (count == 4) {
					p.win();
					return true;
				}
			} else {
				count = 0;
			}
		}
		return false;
	}

	private boolean diagonalCheck(Player p, boolean inverted) {
		int count = 1;
		int inv = 1;
		// if inverted - checks the other diagonal
		if (inverted) {
			inv = -1;
		}
		boolean up = true;
		boolean down = true;
		boolean win = false;
		String chosen = board[lastTop[lastCol]][lastCol];
		String upper;
		String downer;
		for (int i = 1; i < boardSize; i++) {
			try{
				upper = board[lastTop[lastCol] + i][lastCol + i * inv];
				if (chosen.equals(upper)) {
					count++;
				} else {
					up = false;
				}
			}
			catch (Exception e) {
				up = false;
			}
			try {
				downer = board[lastTop[lastCol] - i][lastCol - i * inv];
				if (chosen.equals(downer)) {
					count++;
				} else {
					down = false;
				}
			}
			catch (Exception e) {
				down = false;
			}
			
			if (count == 4) {
				win = true;
				p.win();
				break;
			}
			if (!up && !down) {
				win = false;
				break;
			}
		}
		return win;
	}

	public String[][] board() {
		return this.board;
	}
	
	public int boardSize() {
		return boardSize;
	}
}
