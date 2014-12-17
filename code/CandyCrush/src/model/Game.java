package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import view.Observer;

public class Game implements Observable {

	private GameEvent gameEvent;
	private List<Observer> observers;
	private Timer timeTimer;

	public Game() {
		gameEvent = new GameEvent();
		observers = new ArrayList<Observer>();
		timeTimer = createTimer(100);
	}
	
	private Timer createTimer(final int timeTic) {
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameEvent.incrTimer(timeTic);
				notifyObservers();
			}
		};
		return new Timer(timeTic, action);
	}

	public void setGrid(Grid grid) {
		gameEvent.setGrid(grid);
	}

	public Grid getGrid() {
		return gameEvent.getGrid();
	}

	public void setScore(Score score) {
		gameEvent.setScore(score);
	}

	public Score getScore() {
		return gameEvent.getScore();
	}

	public void setFactory(ContentCaseFactory factory) {
		gameEvent.setFactory(factory);
	}

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver() {
		observers.clear();
	}

	public GameEvent getGameEvent(){
		return gameEvent;
	}
	
	public void notifyObservers() {
		for (Observer obs : observers)
			obs.update(gameEvent);
	}
	
	public void start(){
		timeTimer.start();
	}
}
