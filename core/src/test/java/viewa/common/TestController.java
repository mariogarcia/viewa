package viewa.common;

import static viewa.util.ComponentFinder.find;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import viewa.controller.AbstractViewController;
import viewa.view.ViewContainer;
import viewa.view.ViewException;

/**
 * @author Mario Garcia
 * @since 1.0.2
 * 
 */
public class TestController extends
		AbstractViewController<ActionListener, ActionEvent> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see viewa.controller.ViewController#getSupportedClass()
	 */
	public Class<ActionListener> getSupportedClass() {
		return ActionListener.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.controller.AbstractViewController#postHandlingView
	 * (viewa.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	public void postHandlingView(ViewContainer view, ActionEvent eventObject)
			throws ViewException {
		JTextField field = find(JTextField.class).in(view).named("text");
		field.setText("Hey it worked");
	}
}
