package mvc.controller.state;

import mvc.controller.GameContext;

public interface GameState {

	public void swappedCaseSelectedChanged(GameContext context, int selectedI,
			int selectedJ, int swappedI, int swappedJ);

	public void swappedCaseConfirmedChanged(GameContext context, int selectedI,
			int selectedJ, int swappedI, int swappedJ);

}
