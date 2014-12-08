package model;

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
	public boolean isGameOver(GameEvent gEvent) {
		return gEvent.getTime() <= 0;
	}

}
