package viewa.core;

import viewa.view.DefaultViewContainerFrame;

/**
 * A default application implementation has a DefaultViewContainerFrame as a ROOT_VIEW
 * 
 * @author Mario Garcia
 *
 */
public class DefaultApplication extends AbstractApplication {

	public DefaultApplication(){
		this.setRootView(new DefaultViewContainerFrame());
	}
	
}
