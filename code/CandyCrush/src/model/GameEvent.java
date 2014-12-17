package model;

public class GameEvent {

	private ContentCase[][] grid;
	private int score;
	private double time;
	
	public GameEvent(ContentCase[][] grid, int score, double time) {
		this.time = time;
		this.score = score;
		copyGrid(grid);
	}
	
	private void copyGrid(ContentCase grid[][]){
        if(this.grid == null){
            this.grid = new ContentCase [grid.length][grid[0].length];
        }
        for (int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[0].length; j++)
                this.grid[i][j] = grid[i][j];
    }

	public ContentCase[][] getGrid() {
		return grid;
	}

	public int getScore() {
		return score;
	}

	public double getTime() {
		return time;
	}
}
