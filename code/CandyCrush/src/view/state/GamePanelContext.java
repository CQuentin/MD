package view.state;

import java.awt.event.MouseEvent;

import view.GamePanel;

public class GamePanelContext {

	private GameState currentState;
	private GamePanel panel;

	public void GameContext(GamePanel panel, GameState initialState) {
		this.panel = panel;
		currentState = initialState;
	}

	public void setState(GameState state) {
		currentState = state;
	}

	public GamePanel getGamePanel() {
		return panel;
	}

	// gestion des événements souris
	public void mousePressed(MouseEvent e) {
		// on appuie sur le bouton de la souris : récupérer les coordonnées de
		// la première case

	}

	public void mouseMoved(MouseEvent e) {
		// on bouge la souris : récupérer les coordonnées de la deuxième case

	}

	public void mouseReleased(MouseEvent e) {
		// lorsque l'on relâche la souris il faut faire l'échange et cacher les
		// cases

	}

}
