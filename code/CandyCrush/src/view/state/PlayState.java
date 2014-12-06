package view.state;

import java.awt.event.MouseEvent;

public class PlayState implements GameState{

	@Override
	public void mousePressed(GamePanelContext context, MouseEvent e) {
		context.getGamePanel().setSelectedCaseFromPixel(e.getX(), e.getY());		
	}

	@Override
	public void mouseMoved(GamePanelContext context, MouseEvent e) {
		context.getGamePanel().swappedCaseSelectedChanged(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(GamePanelContext context, MouseEvent e) {
		context.getGamePanel().swappedCaseConfirmedChanged(e.getX(), e.getY());
	}

	@Override
	public void mouseDragged(GamePanelContext context, MouseEvent e) {
		context.getGamePanel().swappedCaseSelectedChanged(e.getX(), e.getY());
	}

}
