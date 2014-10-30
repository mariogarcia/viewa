package org.viewaframework.test;

import javax.swing.JMenuItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.viewaframework.test.component.AbstractButtonTrapper;

/**
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public class MenuItemTrapper extends AbstractButtonTrapper<JMenuItem>{

	private static final Log logger = LogFactory.getLog(MenuItemTrapper.class);
	
	/**
	 * @param applicationTrapper
	 * @param componentName
	 */
	public MenuItemTrapper(ApplicationTrapper applicationTrapper,String componentName) {
		super(applicationTrapper, componentName);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.Trapper#getType()
	 */
	public Class<JMenuItem> getType() {
		return JMenuItem.class;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.Trapper#log(java.lang.String)
	 */
	public MenuItemTrapper log(String message) {
		logger.info(message);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractComponentTrapper#requireEnabled()
	 */
	@Override
	public MenuItemTrapper requireEnabled() {
		return MenuItemTrapper.class.cast(super.requireEnabled());
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractButtonTrapper#requireText(java.lang.String)
	 */
	@Override
	public MenuItemTrapper requireText(String text) {
		return MenuItemTrapper.class.cast(super.requireText(text));
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractButtonTrapper#requireToolTipText(java.lang.String)
	 */
	@Override
	public MenuItemTrapper requireToolTipText(String text) {
		return MenuItemTrapper.class.cast(super.requireToolTipText(text));
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractComponentTrapper#requireVisible()
	 */
	@Override
	public MenuItemTrapper requireVisible() {
		return MenuItemTrapper.class.cast(super.requireVisible());
	}
}
