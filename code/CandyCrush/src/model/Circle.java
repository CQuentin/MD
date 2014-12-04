package model;

import java.awt.Color;

import view.ContentCaseVisitor;

public class Circle implements ContentCase {

	private Color c;

	public Circle(Color c) {
		this.c = c;
	}

	public Color getValue() {
		return c;
	}
	
	public Color getColor(){
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	@Override
	public int getGain() {
		return 1;
	}

	@Override
	public void accept(ContentCaseVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean isEqual(ContentCase content) {
		if (!(content instanceof Circle)) {
			return false;
		}
		return c == (Color) content.getValue();
	}

}
