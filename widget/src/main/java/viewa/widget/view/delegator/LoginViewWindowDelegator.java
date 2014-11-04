package viewa.widget.view.delegator;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

import viewa.controller.ViewController;
import viewa.view.ViewContainer;
import viewa.view.ViewException;
import viewa.view.delegator.Delegator;
import viewa.widget.controller.LoginViewController;

public class LoginViewWindowDelegator implements Delegator{

	private static final String POINT = ".";
	private String componentName;
	
	/**
	 * @param componentName
	 */
	public LoginViewWindowDelegator(String componentName){
		this.componentName = componentName;
	}

	/* (non-Javadoc)
	 * @see viewa.view.delegator.Delegator#clean(viewa.view.ViewContainer)
	 */
	public void clean(ViewContainer arg0) throws ViewException {
		arg0.getViewControllerMap().get(arg0.getId() + POINT + getComponentName()).clear();		
	}

	/**
	 * @return
	 */
	public String getComponentName() {
		return componentName;
	}

	/* (non-Javadoc)
	 * @see viewa.view.delegator.Delegator#inject(viewa.view.ViewContainer)
	 */
	public void inject(ViewContainer arg0) throws ViewException {
		Object list = arg0.getViewControllerMap().get(arg0.getId() + POINT + getComponentName());
		if (list == null){
			List<ViewController<? extends EventListener,? extends EventObject>> listilla =
				new ArrayList<ViewController<? extends EventListener,? extends EventObject>>();
			listilla.add(new LoginViewController());
			arg0.getViewControllerMap().put(
					arg0.getId() + POINT + getComponentName(),
					listilla);
		}
	}
}