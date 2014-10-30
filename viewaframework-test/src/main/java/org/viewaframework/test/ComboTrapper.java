package org.viewaframework.test;

import javax.swing.JComboBox;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.viewaframework.test.component.AbstractComponentTrapper;
import org.viewaframework.test.util.TrapperUtils;

public class ComboTrapper extends AbstractComponentTrapper<JComboBox> {

	private static final Log logger = LogFactory.getLog(ComboTrapper.class);
	
	/**
	 * @param applicationTrapper
	 * @param componentName
	 */
	public ComboTrapper(ApplicationTrapper applicationTrapper,
			String componentName) {
		super(applicationTrapper, componentName);
	}

	/**
	 * @param index
	 * @return
	 */
	public ComboTrapper clickOnIndex(int index) {
		return this.clickOnIndex(index, this.applicationTrapper().getSettings()
				.getTimeBeforeNextEvent());
	}

	/**
	 * @param index
	 * @param millis
	 * @return
	 */
	public ComboTrapper clickOnIndex(final int index, long millis) {
		return ComboTrapper.class.cast(TrapperUtils.processRunnableAndWait(
				this, millis, new Runnable() {
					public void run() {
						getTarget().setSelectedIndex(index);
					}
				}));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.viewaframework.test.Trapper#getType()
	 */
	public Class<JComboBox> getType() {
		return JComboBox.class;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.Trapper#log(java.lang.String)
	 */
	public ComboTrapper log(String message) {
		logger.info(message);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.viewaframework.test.component.AbstractComponentTrapper#requireEnabled
	 * ()
	 */
	@Override
	public ComboTrapper requireEnabled() {
		TestCase.assertTrue(this.getTarget().isEnabled());
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.viewaframework.test.component.AbstractComponentTrapper#requireEnabled
	 * (boolean)
	 */
	@Override
	public ComboTrapper requireEnabled(boolean enabled) {
		TestCase.assertTrue(this.getTarget().isEditable() == enabled);
		return this;
	}

	/**
	 * @param object
	 * @return
	 */
	public ComboTrapper requireSelectedValue(Object object) {
		TestCase.assertEquals(this.getTarget().getSelectedItem(), object);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.viewaframework.test.component.AbstractComponentTrapper#requireVisible
	 * ()
	 */
	@Override
	public ComboTrapper requireVisible() {
		TestCase.assertTrue(this.getTarget().isVisible());
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.viewaframework.test.component.AbstractComponentTrapper#requireVisible
	 * (boolean)
	 */
	@Override
	public ComboTrapper requireVisible(boolean visible) {
		TestCase.assertTrue(this.getTarget().isVisible() == visible);
		return this;
	}

}
