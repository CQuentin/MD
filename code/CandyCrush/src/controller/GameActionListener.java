package controller;

import java.util.EventListener;

public interface GameActionListener extends EventListener {
	public void swappedCaseSelectedChanged(int selectedI, int selectedJ, int swappedI, int swappedJ);
	public void swappedCaseConfirmedChanged(int selectedI, int selectedJ, int swappedI, int swappedJ);
}
