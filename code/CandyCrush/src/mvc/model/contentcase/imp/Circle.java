package mvc.model.contentcase.imp;

import java.awt.Color;

import mvc.model.contentcase.ContentCase;
import mvc.view.visitor.ContentCaseVisitor;

/**
 * This class is used to represent a circle content case.
 * Prototype pattern => cloneable object
 */
public class Circle implements ContentCase {

	private Color c;

	public Circle(Color c) {
		this.c = c;
	}

	/**
	 * Return the value which is specific to this content.
	 */
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

	/**
	 * Visitor pattern
	 * Allows to add operations without change content case interface.
	 * For example to draw content.
	 */
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
