package org.viewaframework.test.component;

import javax.swing.AbstractButton;

import junit.framework.TestCase;

import org.viewaframework.test.ApplicationTrapper;
import org.viewaframework.test.util.TrapperUtils;

/**
 * @author Mario Garcia
 *
 * @param <T>
 */
public abstract class AbstractButtonTrapper<T extends AbstractButton> 
	extends AbstractComponentTrapper<T> 
	{

	/**
	 * @param applicationTrapper
	 * @param componentName
	 */
	public AbstractButtonTrapper(ApplicationTrapper applicationTrapper,String componentName) {
		super(applicationTrapper, componentName);
	}
	
	/**
	 * @param applicationTrapper
	 * @param viewId
	 * @param componentName
	 */
	public AbstractButtonTrapper(ApplicationTrapper applicationTrapper, String viewId,String componentName) {
		super(applicationTrapper, viewId, componentName);
	}

	/**
	 * @return
	 */
	public AbstractButtonTrapper<T> click(){
		return this.click(this.applicationTrapper().getSettings().getTimeBeforeNextEvent());
	}
	
	/**
	 * @param millis
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public AbstractButtonTrapper<T> click(long millis){
		return AbstractButtonTrapper.class.cast(TrapperUtils.processRunnableAndWait(this,
				millis, new Runnable() {
					public void run() {
						getTarget().doClick();
					}
				}));	
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractComponentTrapper#requireEnabled()
	 */
	@Override
	public AbstractComponentTrapper<T> requireEnabled() {
		return this.requireEnabled(true);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractComponentTrapper#requireEnabled(boolean)
	 */
	@Override
	public AbstractComponentTrapper<T> requireEnabled(boolean enabled) {
		TestCase.assertEquals(this.getTarget().isEnabled(),enabled);
		return this;
	}

	/**
	 * @param text
	 * @return
	 */
	public AbstractComponentTrapper<T> requireText(String text){
		TestCase.assertEquals(text,this.getTarget().getText());
		return this;
	}

	/**
	 * @param text
	 * @return
	 */
	public AbstractComponentTrapper<T> requireToolTipText(String text){
		TestCase.assertEquals(text,this.getTarget().getToolTipText());
		return this;
	}	
	
	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractComponentTrapper#requireVisible()
	 */
	@Override
	public AbstractComponentTrapper<T> requireVisible() {
		return this.requireVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractComponentTrapper#requireVisible(boolean)
	 */
	@Override
	public AbstractComponentTrapper<T> requireVisible(boolean visible) {
		TestCase.assertEquals(this.getTarget().isVisible(),visible);
		return this;
	}
}
