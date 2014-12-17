package mvc.observer;


public interface Observable {
	
	public void addObserver(Observer observer);
	
	public void removeObserver();
	
	public void notifyObservers();
}
