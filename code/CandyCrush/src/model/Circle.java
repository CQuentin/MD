package model;

import java.awt.Color;

public class Circle implements ContentCase {
	
	private Color c;

	public Circle (Color c){
		this.c = c;
	}
	
	public Color getValue() {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEqual(ContentCase content) {
		return c == (Color)content.getValue();
	}

}
