package model;

public class GameEvent {

	private double time;
	private int timeSign;
	private Grid grid;
	private Score score;
	private ContentCaseFactory factory;

	public GameEvent() {
		grid = new Grid();
		score = new Score();
		factory = new BubbleFactory();
		grid.setList(factory.createContentCase());
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public Score getScore() {
		return score;
	}

	public void setFactory(ContentCaseFactory factory) {
		this.factory = factory;
		grid.setList(factory.createContentCase());
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void setTimeSign(int timeSign) {
		this.timeSign = timeSign;
	}

	public void incrTimer(int timeTic) {
		time += timeSign * timeTic;
	}

}
