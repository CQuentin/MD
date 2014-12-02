package model;

import java.util.List;
import java.util.Random;

public class Grid {

	private List<ContentCase> listOfContents;
	private ContentCase contentGrid[][];
	private int height;
	private int width;

	public Grid(int height, int width) {
		this.height = height;
		this.width = width;
	}
	
	public Grid(){
		this(8,8);
	}

	public boolean fill() {
		Random rand = new Random();
		boolean modified = false;
		for (int i = 0; i < height; i++) {
			for (int j = width - 1; j >= 0; j--) {
				if (contentGrid[i][j] == null) { // TODO
					if (j == 0) {
						contentGrid[i][j] = listOfContents.get(1 + rand
								.nextInt(listOfContents.size() - 1));
					} else {
						contentGrid[i][j] = contentGrid[i][j - 1];
						contentGrid[i][j - 1] = null; // TODO
					}
					modified = true;
				}
			}
		}
		return modified;
	}

	public void swap(int x1, int y1, int x2, int y2) {
		ContentCase tmp = contentGrid[x1][y1];
		contentGrid[x1][y1] = contentGrid[x2][y2];
		contentGrid[x2][y2] = tmp;
	}

	public void removed(int i, int j) {
		contentGrid[i][j] = null; // TODO
	}

	public void setList(List<ContentCase> listOfContents) {
		this.listOfContents = listOfContents;
	}

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
	
	public ContentCase getCase(int i, int j){
		return contentGrid[i][j];
	}

}
