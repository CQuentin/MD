package mvc.view.visitor.imp;

import java.awt.Graphics2D;

import mvc.model.contentcase.imp.Circle;
import mvc.view.visitor.ContentCaseVisitor;

/**
 * Specific panel drawer to draw grid content case.
 * @author
 *
 */
public class PanelContentCaseDrawer implements ContentCaseVisitor {

	private Graphics2D g;
	private int height;
	private int width;
	private int i;
	private int j;
	private int border;

	public PanelContentCaseDrawer() {
		border = 3;
	}
	
	/**
	 * Fix content case dimensions in pixels.
	 * @param width		Content case width in pixels
	 * @param height	Content case height in pixels
	 */
	public void setDimensions(int width, int height){
		this.height = height;
		this.width = width;
	}
	
	/**
	 * Fix content case position in the grid in order to draw it
	 * at the right place. 
	 * @param i		Horizontal position
	 * @param j		Vertical position
	 */
	public void setPosition(int i, int j){
		this.i = i;
		this.j = j;
	}

	/**
	 * Fix graphics which will be used to draw the content case.
	 * @param g		Panel Graphics2D
	 */
	public void setGraphics2D(Graphics2D g) {
		this.g = g;
	}
	
	/**
	 * Method which will be called to draw a Circle content case.
	 */
	@Override
	public void visit(Circle c) {
		g.setColor(c.getColor());
		g.fillOval(width * i + border, height * j + border, width
				- (border + 2), height - (border + 2));
	}

}
