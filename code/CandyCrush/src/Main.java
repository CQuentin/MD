import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Game;
import controller.GameController;
import view.GamePanel;


public class Main {

	public static void main(String[] args) {

		Game g = new Game();	
		GameController gController = new GameController();
		GamePanel gPanel = new GamePanel();
		g.addObserver(gPanel);
		
		gController.init(g);
		gPanel.init(gController);
		
		 Frame frame = new Frame("Miam, des bonbons !");
	        frame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent event) {
	                System.exit(0);
	            }
	        });
	        frame.add(gPanel);
	        frame.pack();
	        frame.setVisible(true);
		
	}

}
