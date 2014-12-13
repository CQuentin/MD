package model.test;

import java.awt.Color;

import model.Circle;
import model.ContentCase;
import model.EmptyCase;
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
	public void testSwap() {
		grid.swap(0, 0, 1, 0);
		
		ContentCase[][] expected = new ContentCase[2][1];
		expected[0][0] = cWhite;
		expected[1][0] = cBlack;
		
		Assert.assertArrayEquals(grid.getContentGrid(),expected);
	}
	
	@Test
	public void testRemoved(){
		grid.removed(0, 0);
		Assert.assertTrue(grid.getContentGrid()[0][0] instanceof EmptyCase);
	}
	
	@Test
	public void testCasesEqual1(){
		Assert.assertFalse(grid.casesEqual(grid.getContentGrid()[0][0],grid.getContentGrid()[1][0]));
	}
	
	@Test
	public void testCasesEqual2(){
		grid.getContentGrid()[1][0] = cBlack;
		Assert.assertTrue(grid.casesEqual(grid.getContentGrid()[0][0],grid.getContentGrid()[1][0]));
	}
}
