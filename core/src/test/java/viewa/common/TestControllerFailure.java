package viewa.common;

import static viewa.util.ComponentFinder.find;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import viewa.controller.AbstractViewController;
import viewa.controller.BackgroundException;
import viewa.controller.ViewController;
import viewa.view.ViewContainer;
import viewa.view.ViewException;

/**
 * This controller fails in the handleView method and should pass control to the
 * {@link ViewController#postHandlingViewOnError(ViewContainer, java.util.EventObject, Throwable)} 
 * postHandlingViewOnError method. This way you can use the UI to inform user 
 * why the background method failed.
 * 
 * @author mgg
 *
 */
public class TestControllerFailure extends AbstractViewController<ActionListener, ActionEvent> {

	/* (non-Javadoc)
	 * @see viewa.controller.ViewController#getSupportedClass()
	 */
	public Class<ActionListener> getSupportedClass() {
		return ActionListener.class;
	}

	/* (non-Javadoc)
	 * @see viewa.controller.AbstractViewController#handleView(viewa.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	public void handleView(ViewContainer view, ActionEvent eventObject) throws BackgroundException{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// No matter what happens here the method will throw an exception
			}
			throw new BackgroundException("Bad situation!");
	}

	/* (non-Javadoc)
	 * @see viewa.controller.AbstractViewController#postHandlingViewOnError(viewa.view.ViewContainer, java.util.EventObject, java.lang.Throwable)
	 */
	@Override
	public void postHandlingViewOnError(ViewContainer view,ActionEvent eventObject, BackgroundException th) throws ViewException {
		JTextField field = find(JTextField.class).in(view).named("text");
		field.setText(th.getMessage());
	}
}
