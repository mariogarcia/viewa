package viewa.widget.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import viewa.controller.AbstractViewController;
import viewa.view.ViewContainer;

public class LoginViewController extends AbstractViewController<WindowListener,WindowEvent>{
	
	
	/* (non-Javadoc)
	 * @see viewa.controller.ViewController#getSupportedClass()
	 */
	public Class<WindowListener> getSupportedClass() {
		return WindowListener.class;
	}
	/* (non-Javadoc)
	 * @see viewa.controller.AbstractViewController#handleView(viewa.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	public void handleView(ViewContainer view, WindowEvent eventObject) {
			if (eventObject.getID() == WindowEvent.WINDOW_CLOSING){
				view.getApplication().close();
			}

	}		
}