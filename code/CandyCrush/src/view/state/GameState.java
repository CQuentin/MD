package view.state;

import java.awt.event.MouseEvent;

public interface GameState {

	public void mousePressed(GamePanelContext context, MouseEvent e);
	
	public void mouseMoved(GamePanelContext context, MouseEvent e);

	public void mouseReleased(GamePanelContext context, MouseEvent e) ;

	public void mouseDragged(GamePanelContext gamePanelContext, MouseEvent e);
}
