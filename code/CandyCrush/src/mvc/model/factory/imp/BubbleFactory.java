package mvc.model.factory.imp;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import mvc.model.contentcase.ContentCase;
import mvc.model.contentcase.imp.Circle;
import mvc.model.factory.ContentCaseFactory;

/**
 * Factory which using Circle to create a list of contents.
 *
 */
public class BubbleFactory implements ContentCaseFactory {

	/**
	 * Create a set of contents which will be used to fill grid.
	 */
	@Override
	public List<ContentCase> createContentCase() {

	    Color colors[] = {Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, Color.PINK, Color.CYAN};
		List<ContentCase> cases = new ArrayList<ContentCase>();
		
		for (Color c : colors){
			cases.add(new Circle (c));
		}
		return cases;
	}

}
