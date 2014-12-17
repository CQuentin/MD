package mvc.model.event;

import mvc.model.contentcase.ContentCase;

/**
 * An event which indicates that game data have been changed.
 *
 */
public class GameEvent {

	private ContentCase[][] grid;
	private int score;
	private double time;
	
	public GameEvent(ContentCase[][] grid, int score, double time) {
		this.time = time;
		this.score = score;
		copyGrid(grid);
	}
	
	/**
	 * Copy content of the grid which is given in parameter.
	 * This allows to return a copy to the observer(s) and not a reference
	 * to Grid model. 
	 * @param grid 	Content of the grid
	 */
	private void copyGrid(ContentCase grid[][]){
        if(this.grid == null){
            this.grid = new ContentCase [grid.length][grid[0].length];
        }
        for (int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[0].length; j++)
                this.grid[i][j] = grid[i][j];
    }

	/**
	 * Return a copy of grid model.
	 * @return ContentCase[][]	Table containing grid content case
	 */
	public ContentCase[][] getGrid() {
		return grid;
	}

	/**
	 * Return current score of game.
	 * This value will be initialized by Game model just before notify observers.
	 * @return int score	Current score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Return current time of game.
	 * This value will be initialized by Game model just before notify observers.
	 * @return double time	Current time
	 */
	public double getTime() {
		return time;
	}
}
