package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import view.Observer;

public class Game implements Observable {

	private List<Observer> observers;
	private Timer timeTimer;
	private double time;
	private int timeSign;
	private Grid grid;
	private Score score;

	public Game() {
		grid = new Grid();
		score = new Score();
		observers = new ArrayList<Observer>();
		timeTimer = createTimer(100);
	}
	
	private Timer createTimer(final int timeTic) {
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				incrTimer(timeTic);
				notifyObservers();
			}
		};
		return new Timer(timeTic, action);
	}

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver() {
		observers.clear();
	}
	
	public void notifyObservers() {
		GameEvent e = new GameEvent(grid.getContentGrid(), score.getValue(), time);
		for (Observer obs : observers)
			obs.update(e);
	}
	
	public void start(){
		timeTimer.start();
	}
	
	public void setScore(Score score) {
		this.score = score;
	}

	public Score getScore() {
		return score;
	}

	public void setFactory(ContentCaseFactory factory) {
		grid.setList(factory.createContentCase());
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void setTimeSign(int timeSign) {
		this.timeSign = timeSign;
	}

	public void incrTimer(int timeTic) {
		time += timeSign * timeTic;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public boolean fillGrid() {
		boolean res = grid.fill();
		notifyObservers();
		return res;
	}
	
	public void flush(int i, int j) {
		grid.removed(i, j);
		notifyObservers();
	}
	
	public void swap(int selectedI, int selectedJ, int swappedI, int swappedJ) {
		grid.swap(selectedI, selectedJ, swappedI, swappedJ);
		notifyObservers();
	}
}
