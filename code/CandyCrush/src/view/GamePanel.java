package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.ContentCase;
import view.state.GamePanelContext;
import controller.GameController;


public class GamePanel extends Panel implements Observer{
	private static final long serialVersionUID = 1L;
	
	private GamePanelContext context;
	private GameController controller;
	private PanelContentCaseDrawer visitor;
	private ContentCase grid[][];
	private Image buffer;
	private int selectedI;
	private int selectedJ;
	private int swappedI;
	private int swappedJ;
	private int caseHeight;
	private int caseWidth;
	private int nbRows;
	private int nbColumns;
	private int score;

	public GamePanel(){
		caseHeight = 32;
		caseWidth = 32;
		nbRows = 8;
		nbColumns = 8;
		visitor = new PanelContentCaseDrawer();
		
		MouseEventManager ma = new MouseEventManager();
		addMouseListener(ma);
		addMouseMotionListener(ma);
	}

	public void paint(Graphics g2){
		if(buffer == null) 
			buffer = createImage(800, 600);
		Graphics2D g = (Graphics2D) buffer.getGraphics();
		visitor.setGraphics2D(g);
		visitor.setDimensions(caseWidth, caseHeight);
		// fond
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		// afficher la grille vide
		g.setColor(Color.BLACK);
		for(int i = 0; i <= nbColumns; i++) {
			for (int j = 0; j <= nbRows; j++){
				g.drawLine(caseHeight * i, 0, caseHeight * i, nbRows * caseHeight + 1); 
				g.drawLine(0, caseWidth * j, nbColumns * caseWidth + 1, caseWidth * j); 
			}
		}

		// afficher la première case sélectionnée
		if(selectedI != -1 && selectedJ != -1) {
			g.setColor(Color.ORANGE);
			g.fillRect(selectedI * caseWidth + 1, selectedJ * caseHeight + 1, caseWidth - 1, caseHeight - 1);
		}

		// afficher la deuxième case sélectionnée
		if(swappedI != -1 && swappedJ != -1) {
			g.setColor(Color.YELLOW);
			g.fillRect(swappedI * caseWidth + 1, swappedJ * caseHeight + 1, caseWidth - 1, caseHeight - 1);
		}

		// afficher le contenu de la grille
		for(int i = 0; i < nbColumns; i++) {
			for(int j = 0; j < nbRows; j++) {
				visitor.setPosition(i,j);
				grid[i][j].accept(visitor);
			}
		}

		// copier l'image à l'écran
		g2.drawImage(buffer, 0, 0, null);
	}

	public GameController getController() {
		return controller;
	}
	
	public void setSelectedCase(int x, int y){
		selectedI = x/caseWidth;
		selectedJ = y/caseHeight;
	}
	
	public void setSwappedCase(int x, int y){
		swappedI = x/caseWidth;
		swappedJ = y/caseHeight;
	}

	@Override
	public void update(ContentCase[][] grid, int score) {
		this.grid = grid;
		this.score = score;
		repaint();
	}
	
	public void validateSwap(int swappedX, int swappedY){
		int swappedI = swappedX / caseWidth;
		int swappedJ = swappedY / caseHeight;

		if( controller.isValidSwap(selectedI, selectedJ, swappedI, swappedJ) )
			setSwappedCase(swappedX, swappedY);
	}
	
	private class MouseEventManager extends MouseAdapter{
	    // gestion des événements souris
	    public void mousePressed(MouseEvent e) { 
	        context.mousePressed(e);
	        repaint();
	    }
	    
	    public void mouseMoved(MouseEvent e) { 
	       context.mouseMoved(e);
	       repaint();
	    }
	    
	    public void mouseReleased(MouseEvent e) {
	        context.mouseReleased(e);
	        repaint();
	    }
	}

}
