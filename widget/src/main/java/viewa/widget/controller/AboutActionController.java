package viewa.widget.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import viewa.controller.AbstractOpenerController;
import viewa.widget.view.AboutView;

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
	 * @see viewa.controller.ViewController#getSupportedClass()
	 */
	public Class<ActionListener> getSupportedClass() {
		return ActionListener.class;
	}
}
