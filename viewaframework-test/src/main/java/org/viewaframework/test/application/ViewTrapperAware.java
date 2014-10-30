package org.viewaframework.test.application;

import org.viewaframework.test.ViewTrapper;

public interface ViewTrapperAware {

	/**
	 * @param viewId
	 * @return
	 */
	public ViewTrapper view(String viewId);
	
	/**
	 * @return
	 */
	public ViewTrapper view();
}
