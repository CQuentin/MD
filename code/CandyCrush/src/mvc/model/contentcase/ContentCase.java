package mvc.model.contentcase;

import mvc.view.visitor.ContentCaseVisitor;

public interface ContentCase extends Cloneable {

	public int getGain();
	public void accept(ContentCaseVisitor visitor);
	public boolean isEqual(ContentCase content);
	public Object getValue();
	public ContentCase clone();
	
}
