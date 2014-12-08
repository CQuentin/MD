package model;

import java.awt.Component;

import controller.GameController;
import view.GamePanel;
import view.Observer;

public class Level implements Observer{

	private Mode mode;
	private Game game;
	private GamePanel gPanel;
	private GameController gController;
	
	public Level(Mode mode){
		this.mode = mode;

		game = new Game();
		game.getGameEvent().setTime(mode.getTimeStart());
		game.getGameEvent().setTimeSign(mode.getSign());

		gPanel = new GamePanel();
		game.addObserver(gPanel);
		game.addObserver(this);

		gController = new GameController(gPanel, game);
		gPanel.addGameActionListener(gController);
	}

	@Override
	public void update(GameEvent gEvent ) {
		if (mode.isGameOver(gEvent)){
			System.out.println("fini !! Hahahaha");
			System.exit(0);
		}
	}

	public Component getGPanel() {
		return gPanel;
	}
	
	public void startGame(){
		game.start();
	}


}