package mvc.model.factory.imp;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import mvc.model.contentCase.imp.Circle;
import mvc.model.contentcase.ContentCase;
import mvc.model.factory.ContentCaseFactory;

public class BubbleFactory implements ContentCaseFactory {

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
