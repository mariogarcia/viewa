package org.viewaframework.widget.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.viewaframework.controller.AbstractOpenerController;
import org.viewaframework.widget.view.AboutView;

/**
 * @author mario
 *
 */
public class AboutActionController extends AbstractOpenerController<ActionListener,ActionEvent>{

	public static final String HELP_PATTERN = "about";
	
	/**
	 * 
	 */
	public AboutActionController(){
		super(new AboutView());
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.controller.ViewController#getSupportedClass()
	 */
	public Class<ActionListener> getSupportedClass() {
		return ActionListener.class;
	}
}
