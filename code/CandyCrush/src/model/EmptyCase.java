package model;

import view.ContentCaseVisitor;

public class EmptyCase implements ContentCase {

	@Override
	public int getGain() {
		return 0;
	}

	@Override
	public void accept(ContentCaseVisitor visitor) {
		
	}

	@Override
	public boolean isEqual(ContentCase content) {
		return false;
	}

	@Override
	public Object getValue() {
		return null;
	}

}
