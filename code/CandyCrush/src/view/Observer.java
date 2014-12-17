package view;

import model.GameEvent;

public interface Observer {
	public void update(GameEvent e);
}
