package view;

import model.ContentCase;

public interface Observer {

	public void update(ContentCase grid[][], int score);
	
}
