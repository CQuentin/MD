package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Game;
import model.Grid;
import view.GamePanel;
import view.state.GameContext;
import view.state.PlayState;

public class GameController implements GameActionListener {
	private Game game;
	private GamePanel panel;
	private GameContext context;
	private Grid grid;
	private Timer animationTimer;

	public GameController(GamePanel panel, Game game) {
		this.panel = panel;
		this.game = game;
		this.grid = game.getGrid();
		animationTimer = createTimer(100);
		context = new GameContext(this, new PlayState());
		init();
	}
	
	private Timer createTimer(int timeTic) {
		ActionListener action = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!grid.fill() && !removeAlignments())
					animationTimer.stop();
				triggerNotify();
			}
		};
		return new Timer(timeTic, action);
	}

	private void init() {
		// remplir une première fois la grille
		while (grid.fill());
		
		// enlever les alignements existants
		while (removeAlignments()) {
			while (grid.fill());
		}
		triggerNotify();
	}

	public void swap(int selectedI, int selectedJ, int swappedI, int swappedJ) {
		grid.swap(selectedI, selectedJ, swappedI, swappedJ);
		animationTimer.start();
	}

	private void triggerNotify() {
		game.notifyObservers();
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

		// puis on annule l'échange
		grid.swap(selectedI, selectedJ, swappedI, swappedJ);
		
		return newAlignment;
	}

	// est-ce qu'on a trois cases de la même couleur vers le droite depuis (i,
	// j) ?
	private boolean horizontalAligned(int i, int j) {
		int height = grid.getHeight();
		int width = grid.getWidth();
		if (i < 0 || j < 0 || i >= height-2 || j >= width) 
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
		if (i < 0 || j < 0 || i >= height || j >= width-2)
			return false;
		if (grid.casesEqual(grid.getCase(i, j), grid.getCase(i, j + 1),
				grid.getCase(i, j + 2)))
			return true;
		return false;
	}

	// supprimer les alignements
	private boolean removeAlignments() {
		int height = grid.getHeight();
		int width = grid.getWidth();
		boolean marked[][] = new boolean[height][width];

		boolean markedAlignements = markeAlignements(marked);
		
		if (markedAlignements)
			removeMarkedAlignements(marked);
		
		return markedAlignements;
	}
	
	private boolean markeAlignements(boolean[][] marked) {
		boolean markedAlignements = false;
		
		for (int i = 0; i < marked.length; i++) {
			for (int j = 0; j < marked[i].length; j++) {
				if (horizontalAligned(i, j)) {
					marked[i][j] = marked[i + 1][j] = marked[i + 2][j] = true;
					markedAlignements = true;
				}
				if (verticalAligned(i, j)) {
					marked[i][j] = marked[i][j + 1] = marked[i][j + 2] = true;
					markedAlignements = true;
				}
			}
		}
		
		return markedAlignements;
	}
	
	private void removeMarkedAlignements(boolean[][] marked) {
		for (int i = 0; i < marked.length; i++) {
			for (int j = 0; j < marked[i].length; j++) {
				if (marked[i][j]) {
					grid.removed(i, j);
					marked[i][j] = false;
				}
			}
		}
	}
	
	public GamePanel getGamePanel() {
		return panel;
	}

	@Override
	public void swappedCaseSelectedChanged(int selectedI, int selectedJ,
			int swappedI, int swappedJ) {
		context.swappedCaseSelectedChanged(selectedI, selectedJ, swappedI,
				swappedJ);
	}

	@Override
	public void swappedCaseConfirmedChanged(int selectedI, int selectedJ,
			int swappedI, int swappedJ) {
		context.swappedCaseConfirmedChanged(selectedI, selectedJ, swappedI,
				swappedJ);
	}
	
}
