package mvc.view.visitor.imp;

import java.awt.Graphics2D;

import mvc.model.contentcase.imp.Circle;
import mvc.view.visitor.ContentCaseVisitor;

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
	
	public void setDimensions(int width, int height){
		this.height = height;
		this.width = width;
	}
	
	public void setPosition(int i, int j){
		this.i = i;
		this.j = j;
	}

	public void setGraphics2D(Graphics2D g) {
		this.g = g;
	}
	
	@Override
	public void visit(Circle c) {
		g.setColor(c.getColor());
		g.fillOval(width * i + border, height * j + border, width - (border+2), height - (border+2));
	}

}
