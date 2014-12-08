package view.state;

public interface GameState {

	public void swappedCaseSelectedChanged(GameContext context, int selectedI,
			int selectedJ, int swappedI, int swappedJ);

	public void swappedCaseConfirmedChanged(GameContext context, int selectedI,
			int selectedJ, int swappedI, int swappedJ);

}
