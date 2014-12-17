package mvc.model.contentCase.imp;

import mvc.model.contentcase.ContentCase;
import mvc.view.visitor.ContentCaseVisitor;

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
