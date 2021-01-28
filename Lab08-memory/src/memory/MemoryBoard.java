package memory;

import java.util.Random;

public class MemoryBoard {
	private int size;
	private MemoryCardImage[][] board;
	private boolean[][] bools;

	/** Skapar ett memorybräde med size * size kort. backFileName är filnamnet 
	    för filen med baksidesbilden. Vektorn frontFileNames innehåller filnamnen 
	    för frontbilderna. */
	public MemoryBoard(int size, String backFileName, String[] frontFileNames) {
		this.size = size;
		board = new MemoryCardImage[size][size];
		bools = new boolean[size][size];
		createCards(backFileName, frontFileNames);
	}

	/* Skapar size * size / 2 st memorykortbilder.
	   Placerar ut varje kort på två slumpmässiga ställen på spelplanen. */
	private void createCards(String backFileName, String[] frontFileNames) {
		int i = 0;
		MemoryCardImage[] cards = new MemoryCardImage[size*size/2];
		for(String c : frontFileNames) {
			cards[i] = new MemoryCardImage(c,backFileName);
			//varje kort ska placeras två gånger
			placeCard(cards[i]);
			placeCard(cards[i]);
			i++;
			if(i == cards.length) {
				break;
			}
		}
	}
	// hittar en plats för kortet
	private void placeCard(MemoryCardImage card) {
		Random rnd = new Random();
		int r = rnd.nextInt(getSize());
		int c = rnd.nextInt(getSize());
		if(getCard(r,c)==null) {
			board[r][c] = card;
			bools[r][c] = false;
		} else {
			placeCard(card);
		}
	}

	/** Tar reda på brädets storlek. */
	public int getSize() {
		return size;
	}
	
	/** Hämtar den tvåsidiga bilden av kortet på rad r, kolonn c.
	    Raderna och kolonnerna numreras från 0 och uppåt. */
	public MemoryCardImage getCard(int r, int c) {
		return board[r][c];
	}

	/** Vänder kortet på rad r, kolonn c. */
	public void turnCard(int r, int c) {
		bools[r][c] = !bools[r][c];
	}
	
	/** Returnerar true om kortet r, c har framsidan upp. */
	public boolean frontUp(int r, int c) {
		return bools[r][c];
	}
	
	/** Returnerar true om det är samma kort på rad r1, kolonn c2 som på rad r2, 
	    kolonn c2. */
	public boolean same(int r1, int c1, int r2, int c2) {
		return board[r1][c1] == board[r2][c2];
	}

	/** Returnerar true om alla kort har framsidan upp. */
	public boolean hasWon() {
		for(int r = 0 ; r < size ; r++) {
			for(int c = 0 ; c < size ; c++) {
				if(!bools[r][c]) {
					return false;
				}
			}
		}
		return true;
	}	
}
