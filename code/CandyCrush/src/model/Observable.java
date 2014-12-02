package model;

import java.util.ArrayList;
import java.util.List;

public class Observable {

	private List<Observer> observers; 
	
	public Observable(){
		observers = new ArrayList<Observer>();
	}
	
	public void addObserver(Observer observer){
		observers.add(observer);
	}
	
	public void removeObserver(){
		observers.clear();
	}
	
	public void notify(ContentCase grid[][], int score){
		for(Observer obs : observers)
		      obs.update(grid, score);
	}
}
