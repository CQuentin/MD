package model;

import view.ContentCaseVisitor;

public interface ContentCase extends Cloneable {

	public int getGain();
	public void accept(ContentCaseVisitor visitor);
	public boolean isEqual(ContentCase content);
	public Object getValue();
	public ContentCase clone();
	
}
