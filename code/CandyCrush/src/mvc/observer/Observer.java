package mvc.observer;

import mvc.model.event.GameEvent;

public interface Observer {
	public void update(GameEvent e);
}
