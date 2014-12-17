package gamemanager.level.mode;

import mvc.model.event.GameEvent;

public interface Mode {

	public int getSign();
	public double getTimeStart();
	public boolean isGameOver(GameEvent e);

}
