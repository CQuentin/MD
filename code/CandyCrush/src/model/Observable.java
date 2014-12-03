package model;

import java.util.ArrayList;
import java.util.List;

import view.Observer;

public interface Observable {
	
	public void addObserver(Observer observer);
	
	public void removeObserver();
	
	public void notifyObservers();
}
