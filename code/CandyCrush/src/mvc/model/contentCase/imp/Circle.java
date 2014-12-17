package mvc.model.contentCase.imp;

import java.awt.Color;

import mvc.model.contentcase.ContentCase;
import mvc.view.visitor.ContentCaseVisitor;

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
	
	public ContentCase clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch(CloneNotSupportedException cnse) {
			cnse.printStackTrace(System.err);
		}

		return (ContentCase) o;
	}

}
