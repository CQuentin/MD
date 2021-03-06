package gamemanager.level.mode.imp;

import gamemanager.level.mode.Mode;
import mvc.model.event.GameEvent;

public class ClassicMode implements Mode {

	private double timeStart;
	
	public ClassicMode() {
		timeStart = 30000;
	}
	
	@Override
	public int getSign() {
		return -1;
	}

	@Override
	public double getTimeStart() {
		return timeStart;
	}

	@Override
	public boolean isGameOver(GameEvent e) {
		return e.getTime() <= 0;
	}

}
