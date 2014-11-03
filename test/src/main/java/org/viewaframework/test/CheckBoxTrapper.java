package org.viewaframework.test;

import javax.swing.JCheckBox;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.viewaframework.test.component.AbstractComponentTrapper;
import org.viewaframework.test.util.TrapperUtils;

/**
 * @author mario
 * 
 */
public class CheckBoxTrapper extends AbstractComponentTrapper<JCheckBox> {

	private static final Log logger = LogFactory.getLog(CheckBoxTrapper.class);
	
	/**
	 * @param applicationTrapper
	 * @param componentName
	 */
	public CheckBoxTrapper(ApplicationTrapper applicationTrapper,String componentName) {
		// TODO CheckBoxTrapper should extend AbstractButtonTrapper
		super(applicationTrapper, componentName);
	}

	/**
	 * @return
	 */
	public CheckBoxTrapper check() {
		return this.check(this.applicationTrapper().getSettings()
				.getTimeBeforeNextEvent());
	}

	/**
	 * @param millis
	 * @return
	 */
	public CheckBoxTrapper check(long millis) {
		return CheckBoxTrapper.class.cast(TrapperUtils.processRunnableAndWait(
				this, millis, new Runnable() {
					public void run() {
						getTarget().setSelected(true);
					}
				}));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.viewaframework.test.Trapper#getType()
	 */
	public Class<JCheckBox> getType() {
		return JCheckBox.class;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.Trapper#log(java.lang.String)
	 */
	public Trapper<JCheckBox> log(String message) {
		logger.info(message);
		return this;
	}

	/**
	 * @return
	 */
	public CheckBoxTrapper requireChecked() {
		TestCase.assertTrue(this.getTarget().isSelected());
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
	public CheckBoxTrapper requireEnabled() {
		return this.requireEnabled(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.viewaframework.test.component.AbstractComponentTrapper#requireEnabled
	 * (boolean)
	 */
	@Override
	public CheckBoxTrapper requireEnabled(boolean enabled) {
		TestCase.assertTrue(this.getTarget().isEnabled() == enabled);
		return this;
	}

	/**
	 * @param text
	 * @return
	 */
	public CheckBoxTrapper requireText(String text) {
		TestCase.assertEquals(text,getTarget().getText());		
		return this;
	}

	/**
	 * @param text
	 * @return
	 */
	public CheckBoxTrapper requireToolTipText(String text) {
		TestCase.assertEquals(text,getTarget().getToolTipText());		
		return this;
	}

	/**
	 * @return
	 */
	public CheckBoxTrapper requireUnchecked() {
		TestCase.assertFalse(this.getTarget().isSelected());
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
	public CheckBoxTrapper requireVisible() {
		return this.requireVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.viewaframework.test.component.AbstractComponentTrapper#requireVisible
	 * (boolean)
	 */
	@Override
	public CheckBoxTrapper requireVisible(boolean visible) {
		TestCase.assertTrue(this.getTarget().isVisible() == visible);
		return this;
	}
	
	/**
	 * @return
	 */
	public CheckBoxTrapper uncheck() {
		return this.uncheck(this.applicationTrapper().getSettings()
				.getTimeBeforeNextEvent());
	}

	/**
	 * @param millis
	 * @return
	 */
	public CheckBoxTrapper uncheck(long millis) {
		return CheckBoxTrapper.class.cast(TrapperUtils.processRunnableAndWait(
				this, millis, new Runnable() {
					public void run() {
						getTarget().setSelected(false);
					}
				}));
	}
}
