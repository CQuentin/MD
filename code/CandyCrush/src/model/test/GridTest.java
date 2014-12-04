package model.test;

import java.awt.Color;

import model.Circle;
import model.ContentCase;
import model.Grid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GridTest {
	public Grid grid;
	Circle cBlack;
	Circle cWhite;
	
	public GridTest() {
		grid = new Grid(2,1);
		cBlack= new Circle(Color.BLACK);
		cWhite = new Circle(Color.WHITE);
	}
	
	@Before
	public void startUp(){
		grid.getContentGrid()[0][0] = cBlack;
		grid.getContentGrid()[1][0] = cWhite;
	}
	
	@Test
	public void test() {
		grid.swap(0, 0, 1, 0);
		
		ContentCase[][] expected = new ContentCase[2][1];
		expected[0][0] = cWhite;
		expected[1][0] = cBlack;
		
		Assert.assertArrayEquals(grid.getContentGrid(),expected);
	}

}
