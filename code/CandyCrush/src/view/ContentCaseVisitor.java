package view;

import java.awt.Graphics2D;

import model.Circle;

public interface ContentCaseVisitor {

	public void visit(Circle c);	
}
