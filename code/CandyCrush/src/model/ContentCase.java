package model;

import view.ContentCaseVisitor;

public interface ContentCase {

	public int getGain();
	public void accept(ContentCaseVisitor visitor);
	public boolean isEqual(ContentCase content);
	public Object getValue();
	
}
