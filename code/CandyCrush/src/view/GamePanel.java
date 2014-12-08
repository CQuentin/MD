package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.EventListenerList;

import model.ContentCase;
import model.GameEvent;
import controller.GameActionListener;

public class GamePanel extends Panel implements Observer {
	private static final long serialVersionUID = 1L;

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
	
	@SuppressWarnings("unused")
	private int score;
	@SuppressWarnings("unused")
	private double time;

    private final EventListenerList listeners = new EventListenerList();
	
	public GamePanel() {
		caseHeight = 32;
		caseWidth = 32;

		selectedI = -1;
		selectedJ = -1;
		swappedI = -1;
		swappedJ = -1;

		visitor = new PanelContentCaseDrawer();		

		MouseEventManager ma = new MouseEventManager();
		addMouseListener(ma);
		addMouseMotionListener(ma);
	}
	
	@Override
	public void update(GameEvent gEvent) {
		this.grid = gEvent.getGrid().getContentGrid();
		nbRows = grid.length;
		nbColumns = grid[0].length;
		this.score = gEvent.getScore().getValue();
		this.time = gEvent.getTime();
		repaint();
	}
	
	public void addGameActionListener(GameActionListener listener) {
		listeners.add(GameActionListener.class, listener);
	}
	
	public void removeGameActionListener(GameActionListener listener) {
		listeners.remove(GameActionListener.class, listener);
	}

	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g2) {
		if (buffer == null)
			buffer = createImage(800, 600);
		Graphics2D g = (Graphics2D) buffer.getGraphics();
		visitor.setGraphics2D(g);
		visitor.setDimensions(caseWidth, caseHeight);
		
		// fond
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		// afficher la grille vide
		g.setColor(Color.BLACK);
		for (int i = 0; i <= nbRows; i++) {
			for (int j = 0; j <= nbColumns; j++) {
				g.drawLine(caseHeight * i, 0, caseHeight * i, nbColumns
						* caseHeight + 1);
				g.drawLine(0, caseWidth * j, nbRows * caseWidth + 1,
						caseWidth * j);
			}
		}

		// afficher la première case sélectionnée
		if (selectedI != -1 && selectedJ != -1) {
			g.setColor(Color.ORANGE);
			g.fillRect(selectedI * caseWidth + 1, selectedJ * caseHeight + 1,
					caseWidth - 1, caseHeight - 1);
		}

		// afficher la deuxième case sélectionnée
		if (swappedI != -1 && swappedJ != -1) {
			g.setColor(Color.YELLOW);
			g.fillRect(swappedI * caseWidth + 1, swappedJ * caseHeight + 1,
					caseWidth - 1, caseHeight - 1);
		}

		// afficher le contenu de la grille
		for (int i = 0; i < nbRows; i++) {
			for (int j = 0; j < nbColumns; j++) {
				visitor.setPosition(i, j);
				grid[i][j].accept(visitor);
			}
		}

		// copier l'image à l'écran
		g2.drawImage(buffer, 0, 0, null);
	}

	public Dimension getPreferredSize() {
		return new Dimension(caseHeight * nbRows + 1, caseWidth * nbColumns + 1);
	}
	
	public void setSelectedCase(int i, int j) {
		selectedI = i;
		selectedJ = j;
	}

	public void setSelectedCaseFromPixel(int x, int y) {
		selectedI = x / caseWidth;
		selectedJ = y / caseHeight;
	}
	
	public void setSwappedCase(int i, int j) {
		swappedI = i;
		swappedJ = j;
	}
	
	public void swappedCaseSelectedChanged(int swappedX, int swappedY) {
		int swappedI = swappedX / caseWidth;
		int swappedJ = swappedY / caseHeight;
		
		for(GameActionListener listener : getGameActionListener())
			listener.swappedCaseSelectedChanged(selectedI, selectedJ, swappedI, swappedJ);
	}
	
	public void swappedCaseConfirmedChanged(int swappedX, int swappedY) {
		int swappedI = swappedX / caseWidth;
		int swappedJ = swappedY / caseHeight;
		
		for(GameActionListener listener : getGameActionListener())
			listener.swappedCaseConfirmedChanged(selectedI, selectedJ, swappedI, swappedJ);
	}
	
	private GameActionListener[] getGameActionListener() {
		return listeners.getListeners(GameActionListener.class);
	}
	
	private class MouseEventManager extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			setSelectedCaseFromPixel(e.getX(), e.getY());
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			swappedCaseSelectedChanged(e.getX(), e.getY());
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			swappedCaseConfirmedChanged(e.getX(), e.getY());
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			swappedCaseSelectedChanged(e.getX(), e.getY());
			repaint();
		}
	}
}
