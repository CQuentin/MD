package model;

public class Game extends Observable {

	private Grid grid;
	private Score score;
	private ContentCaseFactory factory;

	public Game() {
		grid = new Grid();
	}

	public Score getScore() {
		return score;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public void setFactory(ContentCaseFactory factory) {
		this.factory = factory;
		grid.setList(factory.createContentCase());
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public Grid getGrid() {
		return grid;
	}
}
