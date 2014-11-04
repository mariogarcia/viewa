package viewa.widget.controller;

import java.awt.event.ActionEvent;

import viewa.controller.AbstractActionController;
import viewa.view.ViewContainer;
import viewa.view.ViewException;
import viewa.view.ViewManager;

/**
 * This controller closes the view having the component that has triggered the event. If
 * that view is the root view, then the application ends.
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public class ExitActionController extends AbstractActionController{

	public static final String EXIT_PATTERN = "exit";

	/* (non-Javadoc)
	 * @see viewa.controller.AbstractViewController#postHandlingView(viewa.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	public void postHandlingView(ViewContainer view, ActionEvent eventObject)
			throws ViewException {
		if (view.getId().equals(ViewManager.ROOT_VIEW_ID)){
			view.getApplication().close();
		} else {
			this.getViewManager().removeView(view);
		}
	}
}
