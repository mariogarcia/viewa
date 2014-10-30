package org.viewaframework.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.ViewException;

/**
 * This controller just closes the view when the user push the x button of the window. It is 
 * usually injected in dialog or root views by {@org.viewaframework.view.delegator.DialogViewClosingWindowDelegator}.
 * 
 * @author Mario Garcia
 *
 */
public class DefaultWindowController extends AbstractViewController<WindowListener,WindowEvent>{
	
	private static final Log logger = LogFactory.getLog(DefaultWindowController.class);
	
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
		try {
			if (eventObject.getID() == WindowEvent.WINDOW_CLOSING){
				view.getApplication().getViewManager().removeView(view);
			}
		} catch (ViewException e) {
			logger.fatal("Exception while closing dialog: "+e.getMessage());
		}
	}		
}