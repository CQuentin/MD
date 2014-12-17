package mvc.model.test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import mvc.model.Grid;
import mvc.model.contentcase.ContentCase;
import mvc.model.contentcase.imp.Circle;
import mvc.model.contentcase.imp.EmptyCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GridTest {
	public Grid grid;
	private Circle cBlack;
	private Circle cWhite;
	private EmptyCase emptyCase;
	private List<ContentCase> listOfContents;
	
	public GridTest() {
		grid = new Grid(1,2);
		cBlack= new Circle(Color.BLACK);
		cWhite = new Circle(Color.WHITE);
		emptyCase = new EmptyCase();
		listOfContents = new ArrayList<ContentCase>();
		listOfContents.add(cBlack);
		listOfContents.add(cWhite);
		grid.setList(listOfContents);
	}
	
	@Before
	public void startUp(){
		grid.getContentGrid()[0][0] = cBlack;
		grid.getContentGrid()[0][1] = cWhite;
	}
	
	@Test
	public void testSwap() {
		grid.swap(0, 0, 0, 1);
		
		ContentCase[][] expected = new ContentCase[1][2];
		expected[0][0] = cWhite;
		expected[0][1] = cBlack;
		
		Assert.assertArrayEquals(grid.getContentGrid(),expected);
	}
	
	@Test
	public void testRemoved(){
		grid.removed(0, 0);
		Assert.assertTrue(grid.getContentGrid()[0][0] instanceof EmptyCase);
	}
	
	@Test
	public void testCasesEqual1(){
		Assert.assertFalse(grid.casesEqual(grid.getContentGrid()[0][0],grid.getContentGrid()[0][1]));
	}
	
	@Test
	public void testCasesEqual2(){
		grid.getContentGrid()[0][1] = cBlack;
		Assert.assertTrue(grid.casesEqual(grid.getContentGrid()[0][0],grid.getContentGrid()[0][1]));
	}
	
	@Test
	public void testFill1() {
		Assert.assertFalse(grid.fill());
	}
	
	/* Test non modification dans le cas d'une grille pleine */
	@Test
	public void testFill2() {
		ContentCase[][] expected = grid.getContentGrid();
		grid.fill();
		ContentCase[][] actual = grid.getContentGrid();
		
		Assert.assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testFill3() {
		grid.getContentGrid()[0][0] = emptyCase.clone();
		Assert.assertTrue(grid.fill());
	}
	
	@Test
	public void testFill4() {
		grid.getContentGrid()[0][0] = emptyCase.clone();
		grid.fill();
		ContentCase[][] actual = grid.getContentGrid();
		
		Assert.assertFalse(actual[0][0] instanceof EmptyCase);
	}
	
	@Test
	public void testFill5() {
		grid.getContentGrid()[0][1] = emptyCase.clone();
		ContentCase expected = grid.getContentGrid()[0][0];
		grid.fill();
		ContentCase actual = grid.getContentGrid()[0][1];
		
		Assert.assertEquals(expected, actual);
	}
	
}
