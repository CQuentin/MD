package controller;

import javax.swing.Box.Filler;

import model.Game;
import model.Grid;

public class GameController {
	private Game game;
	private Grid grid;

	public void setGame(Game game) {
		this.game = game;
	}

	public GameController() {

	}

	public void init(Game game) {
		this.game = game;
		grid = game.getGrid();

		// remplir une première fois la grille
		while (grid.fill()) {
			triggerNotify();
		}
		// enlever les alignements existants
		while (removeAlignments()) {
			triggerNotify();
			while (grid.fill()) {
				triggerNotify();
			}
		}
		triggerNotify();
	}

	public void swap(int selectedI, int selectedJ, int swappedI, int swappedJ) {
		grid.swap(selectedI, selectedJ, swappedI, swappedJ);
		do {
			triggerNotify();
			removeAlignments();
			triggerNotify();
		} while (grid.fill());
		triggerNotify();

	}

	private void triggerNotify() {
		game.notifyObservers();
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
		if (grid.casesEqual(grid.getCase(selectedI, selectedJ),
				grid.getCase(swappedI, swappedJ)))
			return false;
		// alors on effectue l'échange
		grid.swap(selectedI, selectedJ, swappedI, swappedJ);

		// et on vérifie que ça créé un nouvel alignement
		boolean newAlignment = false;
		for (int i = 0; i < 3; i++) {
			newAlignment |= horizontalAligned(selectedI - i, selectedJ);
			newAlignment |= horizontalAligned(swappedI - i, swappedJ);
			newAlignment |= verticalAligned(selectedI, selectedJ - i);
			newAlignment |= verticalAligned(swappedI, swappedJ - i);
		}

		// x1 = selectedI
		// y1 = selectedJ
		// x2 = swappedI
		// y2 = swappedJ
		//
		// newAlignment |= horizontalAligned(x1 - i, y1);
		// newAlignment |= horizontalAligned(x2 - i, y2);
		// newAlignment |= verticalAligned(x1, y1 - i);
		// newAlignment |= verticalAligned(x2, y2 - i);

		// puis on annule l'échange
		grid.swap(selectedI, selectedJ, swappedI, swappedJ);
		
		return newAlignment;
	}

	// est-ce qu'on a trois cases de la même couleur vers le droite depuis (i,
	// j) ?
	private boolean horizontalAligned(int i, int j) {
		int height = grid.getHeight();
		int width = grid.getWidth();
		if (i < 0 || j < 0 || i >= height-2 || j >= width) // TODO
			return false;
		if (grid.casesEqual(grid.getCase(i, j), grid.getCase(i + 1, j),
				grid.getCase(i + 2, j)))
			return true;
		return false;
	}

	// est-ce qu'on a trois cases de la même couleur vers le bas depuis (i, j) ?
	private boolean verticalAligned(int i, int j) {
		int height = grid.getHeight();
		int width = grid.getWidth();
		if (i < 0 || j < 0 || i >= height || j >= width-2) // TODO
			return false;
		if (grid.casesEqual(grid.getCase(i, j), grid.getCase(i, j + 1),
				grid.getCase(i, j + 2)))
			return true;
		return false;
	}

	// supprimer les alignements
	boolean removeAlignments() {
		boolean marked[][] = new boolean[grid.getHeight()][grid.getWidth()];
		int height = grid.getHeight();
		int width = grid.getWidth();
		// passe 1 : marquer tous les alignements
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (horizontalAligned(i, j)) {
					marked[i][j] = marked[i + 1][j] = marked[i + 2][j] = true;
				}
				if (verticalAligned(i, j)) {
					marked[i][j] = marked[i][j + 1] = marked[i][j + 2] = true;
				}
			}
		}
		// passe 2 : supprimer les cases marquées
		boolean modified = false;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (marked[i][j]) {
					grid.removed(i, j);
					marked[i][j] = false;
					modified = true;
				}
			}
		}
		return modified;
	}

}
