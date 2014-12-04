package view.state;

import java.awt.event.MouseEvent;

import view.GamePanel;

public class GamePanelContext {

	private GameState currentState;
	private GamePanel panel;

	public GamePanelContext(GamePanel panel, GameState initialState) {
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
		currentState.mousePressed(this, e);

	}

	public void mouseMoved(MouseEvent e) {
		currentState.mouseMoved(this, e);
	}

	public void mouseReleased(MouseEvent e) {
		currentState.mouseReleased(this, e);
	}

	public void mouseDragged(MouseEvent e) {
		currentState.mouseDragged(this, e);
		
	}

}
