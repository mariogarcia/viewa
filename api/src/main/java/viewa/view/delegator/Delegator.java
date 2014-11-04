package viewa.view.delegator;

import viewa.view.ViewContainer;
import viewa.view.ViewException;

public interface Delegator {
	
	public void inject(ViewContainer viewContainer) throws ViewException;
	public void clean(ViewContainer viewContainer)throws ViewException;

}
