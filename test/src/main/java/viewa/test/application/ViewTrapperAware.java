package viewa.test.application;

import viewa.test.ViewTrapper;

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
