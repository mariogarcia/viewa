package org.viewaframework.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a default blank implementation of an AbstractViewController for listening
 * ActionEvent events.
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public abstract class AbstractActionController extends AbstractViewController<ActionListener,ActionEvent>{

	/* (non-Javadoc)
	 * @see org.viewaframework.controller.ViewController#getSupportedClass()
	 */
	public Class<ActionListener> getSupportedClass() {
		return ActionListener.class;
	}
}
