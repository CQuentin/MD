package view.state;

import view.GamePanel;
import controller.GameController;


public class PlayState implements GameState{

	@Override
	public void swappedCaseSelectedChanged(GameContext context, int selectedI,
			int selectedJ, int swappedI, int swappedJ) {
		
		GameController controller = context.getGameController();
		GamePanel panel = controller.getGamePanel();
		
		if(controller.isValidSwap(selectedI, selectedJ, swappedI, swappedJ)) {
			panel.setSwappedCase(swappedI, swappedJ);
			return;
		}
		
		panel.setSwappedCase(-1, -1);
	}

	@Override
	public void swappedCaseConfirmedChanged(GameContext context, int selectedI,
			int selectedJ, int swappedI, int swappedJ) {
		
		GameController controller = context.getGameController();
		GamePanel panel = controller.getGamePanel();

		if(controller.isValidSwap(selectedI, selectedJ, swappedI, swappedJ)) {
			controller.swap(selectedI, selectedJ, swappedI, swappedJ);
		}
		
		panel.setSelectedCase(-1, -1);
		panel.setSwappedCase(-1, -1);	
	}

}
