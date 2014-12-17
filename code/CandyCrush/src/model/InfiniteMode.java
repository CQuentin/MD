package model;

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
	public boolean isGameOver(GameEvent gEvent) {
		return false;
	}
}
