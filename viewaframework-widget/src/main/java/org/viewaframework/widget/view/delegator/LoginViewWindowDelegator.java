package org.viewaframework.widget.view.delegator;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

import org.viewaframework.controller.ViewController;
import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.ViewException;
import org.viewaframework.view.delegator.Delegator;
import org.viewaframework.widget.controller.LoginViewController;

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
	 * @see org.viewaframework.view.delegator.Delegator#clean(org.viewaframework.view.ViewContainer)
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
	 * @see org.viewaframework.view.delegator.Delegator#inject(org.viewaframework.view.ViewContainer)
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