// handles gamedata
public class Tiles {
	private String[][] board = new String[9][9];
	private int[] lastTop = new int[9];
	private int lastCol;

	public Tiles() {
		for (int i = 0; i < 9; i++) {
			lastTop[i] = -1; // blir sedan satt till 0 (rad 1) när första har placerats
			for (int j = 0; j < 9; j++) {
				board[i][j] = " ";
			}
		}
	}

	// changes the board data, returns **true** if the selected column **is full**.
	public boolean placeTile(Player p, int column) {
		column -= 1;
		if (lastTop[column] > 8) {
			return true;
		} else {
			lastTop[column]++;
			board[lastTop[column]][column] = p.symbol();
			lastCol = column;
			return false;
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
		for (int i = 0; i < 9; i++) {
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
		for (int i = 1; i < 9; i++) {
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

	// fortfarande problem med out of bounds! Exception när man är
	// för nära kanterna och ett av värdena som kollas är utanför
//	private boolean diagonalCheck(Player p, boolean inverted) {
//		int count = 1;
//		int inv = 1;
//		// if inverted - checks the other diagonal
//		if (inverted) {
//			inv = -1;
//		}
//		boolean up = true;
//		boolean down = true;
//		boolean win = false;
//		try {
//			String chosen = board[lastTop[lastCol]][lastCol];
//			String upper;
//			String downer;
//			for (int i = 1; i < 9; i++) {
//				upper = board[lastTop[lastCol] + i][lastCol + i * inv];
//				if (chosen.equals(upper)) {
//					count++;
//				} else {
//					up = false;
//				}
//				downer = board[lastTop[lastCol] - i][lastCol - i * inv];
//				if (chosen.equals(downer)) {
//					count++;
//				} else {
//					down = false;
//				}
//				if (count == 4) {
//					win = true;
//					p.win();
//					break;
//				}
//				if (!up && !down) {
//					win = false;
//					break;
//				}
//			}
//		} catch (Exception e) {
//			if (count == 4) {
//				win = true;
//				p.win();
//			}
//		}
//		return win;
//	}


//	private boolean diagonalCheck(Player p) {
//		int count = 0;
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				try {
//					if(board[i+j][j].equals(p.symbol())) {
//					count++;
//					if (count == 4) {
//						p.win();
//						return true;
//					}
//				} else {
//					count = 0;
//				}
//				}
//				catch(Exception e) {
//					
//				}
//			} 
//		}
//		return false;
//	}
//
//	private boolean diagonalBackCheck(Player p) {
//		int count = 0;
//		for (int i = 8; i > 0; i++) {
//			for (int j = 0; j < 9; j++) {
//				try {
//					if (board[i + j][j].equals(p.symbol())) {
//						count++;
//						if (count == 4) {
//							p.win();
//							return true;
//						}
//					} else {
//						count = 0;
//					}
//				}
//				catch(Exception e) {
//					
//				}
//				
//			}
//		}
//		return false;
//	}

	public String[][] board() {
		return this.board;
	}
}
