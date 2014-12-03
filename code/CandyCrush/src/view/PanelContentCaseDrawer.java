package view;

import java.awt.Color;
import java.awt.Graphics2D;

import model.Circle;

public class PanelContentCaseDrawer implements ContentCaseVisitor {

	private Graphics2D g;
	private int height;
	private int width;
	private int i;
	private int j;
	private int border;

	public void setDimensions(int width, int height){
		this.height = height;
		this.width = width;
	}
	
	public void setPosition(int i, int j){
		this.i = i;
		this.j = j;
	}


	public PanelContentCaseDrawer() {
		border = 3;
	}

	@Override
	public void visit(Circle c) {
		if(c == null) //TODO gérer le null
			g.setColor(Color.WHITE);
		else
			g.setColor(c.getColor());
		g.fillOval(width * i + border, height * j + border, width - (border+2), height - (border+2));
	}


	public void setGraphics2D(Graphics2D g) {
		this.g = g;
	}



}
