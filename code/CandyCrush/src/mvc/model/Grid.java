package mvc.model;

import java.util.List;
import java.util.Random;

import mvc.model.contentcase.ContentCase;
import mvc.model.contentcase.imp.EmptyCase;

/**
 * Represents grid of the game.
 *
 */
public class Grid {

	private List<ContentCase> listOfContents;
	private ContentCase contentGrid[][];
	private int height;
	private int width;
	private EmptyCase emptyCase;

	public Grid(int height, int width) {
		this.height = height;
		this.width = width;
		contentGrid = new ContentCase[height][width];
		emptyCase = new EmptyCase();
		
		for (int i = 0 ; i < height ; i++)
			for (int j = 0 ; j < width ; j++)
				contentGrid[i][j] = emptyCase.clone();
	}
	
	public Grid(){
		this(8,8);
	}

	/**
	 * Fill grid with random contents cases which are selected from 
	 * the list of contents.
	 * If the grid is already full, nothing is performed.
	 * If the grid contains empty case, it will be filled by gravity step by step.
	 * So, to completely fill the grid, several call may be necessary.
	 * @return true if the grid has been modified and false otherwise.
	 */
	public boolean fill() {
		Random rand = new Random();
		boolean modified = false;
		for (int i = 0; i < height; i++) {
			for (int j = width - 1; j >= 0; j--) {
				if (contentGrid[i][j] instanceof EmptyCase) {
					if (j == 0) {
						contentGrid[i][j] = listOfContents.get(rand
								.nextInt(listOfContents.size())).clone();
					} else {
						contentGrid[i][j] = contentGrid[i][j - 1];
						contentGrid[i][j - 1] = emptyCase.clone();
					}
					modified = true;
				}
			}
		}
		return modified;
	}

	/**
	 * Swap two cases.
	 * @param i1	Horizontal position of the first case
	 * @param j1	Vertical position of the first case
	 * @param i2	Horizontal position of the second case
	 * @param j2	Vertical position of the second case
	 */
	public void swap(int i1, int j1, int i2, int j2) {
		ContentCase tmp = contentGrid[i1][j1];
		contentGrid[i1][j1] = contentGrid[i2][j2];
		contentGrid[i2][j2] = tmp;
	}

	/**
	 * Flush content case whose position is given in parameter.
	 * @param i		Horizontal position
	 * @param j		Vertical position
	 */
	public void removed(int i, int j) {
		contentGrid[i][j] = emptyCase;
	}

	/**
	 * Test if content cases are equals or not.
	 * @param cases		Content cases to test
	 * @return true if cases are equals, false otherwise
	 */
	public boolean casesEqual(ContentCase... cases) {
		ContentCase c0 = cases[0];

		for (ContentCase c : cases) {
			if (c.isEqual(c0)) {
				c0 = c;
			} else
				return false;
		}
		return true;
	}
	
	public void setList(List<ContentCase> listOfContents) {
		this.listOfContents = listOfContents;
	}
	
	public ContentCase getCase(int i, int j){
		return contentGrid[i][j];
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public ContentCase[][] getContentGrid() {
		return contentGrid;
	}

}
