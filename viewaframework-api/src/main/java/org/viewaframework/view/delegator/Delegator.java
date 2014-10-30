package org.viewaframework.view.delegator;

import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.ViewException;

public interface Delegator {
	
	public void inject(ViewContainer viewContainer) throws ViewException;
	public void clean(ViewContainer viewContainer)throws ViewException;

}
