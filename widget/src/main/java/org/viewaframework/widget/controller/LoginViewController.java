package org.viewaframework.widget.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.viewaframework.controller.AbstractViewController;
import org.viewaframework.view.ViewContainer;

public class LoginViewController extends AbstractViewController<WindowListener,WindowEvent>{
	
	
	/* (non-Javadoc)
	 * @see org.viewaframework.controller.ViewController#getSupportedClass()
	 */
	public Class<WindowListener> getSupportedClass() {
		return WindowListener.class;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.controller.AbstractViewController#handleView(org.viewaframework.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	public void handleView(ViewContainer view, WindowEvent eventObject) {
			if (eventObject.getID() == WindowEvent.WINDOW_CLOSING){
				view.getApplication().close();
			}

	}		
}