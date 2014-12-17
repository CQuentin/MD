package gamemanager.level.mode.imp;

import gamemanager.level.mode.Mode;
import mvc.model.event.GameEvent;

public class InfiniteMode implements Mode {
	
	private double timeStart;

	public InfiniteMode() {
		timeStart = 0;
	}

	@Override
	public int getSign() {
		return 0;
	}

	@Override
	public double getTimeStart() {
		return timeStart;
	}

	@Override
	public boolean isGameOver(GameEvent e) {
		return false;
	}
}
