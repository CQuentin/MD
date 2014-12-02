package controller;

import model.Game;
import model.Grid;

public class GameController {
	private Game game;

	public void swap(int x1, int y1, int x2, int y2) {

	}

	public Game getGame() {
		return game;
	}

	// détermine si l'échange entre deux cases est valide
	public boolean isValidSwap(int selectedI, int selectedJ, int swappedI,
			int swappedJ) {
		// il faut que les cases soient dans la grille
		if (selectedI == -1 || swappedI == -1 || selectedJ == -1
				|| swappedJ == -1)
			return false;
		// que les cases soient à côté l'une de l'autre
		if (Math.abs(swappedI - selectedI) + Math.abs(swappedJ - selectedJ) != 1)
			return false;
		// et que les couleurs soient différentes
		Grid grid = game.getGrid();
		if (grid.casesEqual(grid.getCase(selectedI, selectedJ), grid.getCase(swappedI, swappedJ)))
			return false;

		// alors on effectue l'échange
		grid.swap(selectedI, selectedJ, swappedI, swappedJ);

		// et on vérifie que ça créé un nouvel alignement
		boolean newAlignment = false;
		for (int i = 0; i < 3; i++) {
			newAlignment |= horizontalAligned(selectedI - i, swappedI);
			newAlignment |= horizontalAligned(selectedJ - i, swappedJ);
			newAlignment |= verticalAligned(selectedI, swappedI - i);
			newAlignment |= verticalAligned(selectedJ, swappedJ - i);
		}

		// puis on annule l'échange
		grid.swap(selectedI, selectedJ, swappedI, swappedJ);
		return newAlignment;
	}
	
	// est-ce qu'on a trois cases de la même couleur vers le droite depuis (i,
	// j) ?
	private boolean horizontalAligned(int i, int j) {
		Grid grid = game.getGrid();
		if (i < 0 || j < 0 || i >= 6 || j >= 8) //TODO
			return false;
		if ( grid.casesEqual(grid.getCase(i, j), grid.getCase(i+1, j), grid.getCase(i+2, j)) )
			return true;
		return false;
	}

	// est-ce qu'on a trois cases de la même couleur vers le bas depuis (i, j) ?
	private boolean verticalAligned(int i, int j) {
		Grid grid = game.getGrid();
		if (i < 0 || j < 0 || i >= 8 || j >= 6)
			return false;
		if ( grid.casesEqual(grid.getCase(i, j), grid.getCase(i, j+1), grid.getCase(i, j+2)) )
			return true;
		return false;
	}
	
}
