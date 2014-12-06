package model;

import java.util.ArrayList;
import java.util.List;

import view.Observer;

public class Game implements Observable {

	private Grid grid;
	private Score score;
	private ContentCaseFactory factory;
	private List<Observer> observers;
	
	public Game() {
		grid = new Grid();
		score = new Score();
		observers = new ArrayList<Observer>();
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
	
	public void addObserver(Observer observer){
		observers.add(observer);
	}
	
	public void removeObserver(){
		observers.clear();
	}
	
	public void notifyObservers(){
		for(Observer obs : observers)
		      obs.update(grid.getContentGrid(), score.getValue());
	}
}
