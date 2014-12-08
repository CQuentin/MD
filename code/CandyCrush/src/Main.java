import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.ClassicMode;
import model.Level;
import model.Mode;

public class Main {

	public static void main(String[] args) {

		Mode mode = new ClassicMode();
		Level level = new Level(mode);
		
		
		

		Frame frame = new Frame("Miam, des bonbons !");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		frame.add(level.getGPanel());
		frame.pack();
		frame.setVisible(true);
		level.startGame();
	}
}
