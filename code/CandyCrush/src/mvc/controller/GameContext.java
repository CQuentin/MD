package mvc.controller;

import mvc.controller.state.GameState;

public class GameContext {

	private GameState currentState;
	private GameController controller;

	public GameContext(GameController controller, GameState initialState) {
		this.controller = controller;
		currentState = initialState;
	}

	public void setState(GameState state) {
		currentState = state;
	}

	public GameController getGameController() {
		return controller;
	}

	public void swappedCaseSelectedChanged(int selectedI, int selectedJ,
			int swappedI, int swappedJ) {
		currentState.swappedCaseSelectedChanged(this, selectedI, selectedJ,
				swappedI, swappedJ);
	}

	public void swappedCaseConfirmedChanged(int selectedI, int selectedJ,
			int swappedI, int swappedJ) {
		currentState.swappedCaseConfirmedChanged(this, selectedI, selectedJ,
				swappedI, swappedJ);
	}

}
