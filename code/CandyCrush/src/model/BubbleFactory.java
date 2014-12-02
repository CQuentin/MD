package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
