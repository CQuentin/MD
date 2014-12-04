package view.state;

import java.awt.event.MouseEvent;

public class PlayState implements GameState{

	@Override
	public void mousePressed(GamePanelContext context, MouseEvent e) {
		context.getGamePanel().setSelectedCase(e.getX(), e.getY());		
	}

	@Override
	public void mouseMoved(GamePanelContext context, MouseEvent e) {
		context.getGamePanel().validateSwap(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(GamePanelContext context, MouseEvent e) {
		context.getGamePanel().swap();
	}

	@Override
	public void mouseDragged(GamePanelContext context, MouseEvent e) {
		context.getGamePanel().validateSwap(e.getX(), e.getY());		
	}

}
