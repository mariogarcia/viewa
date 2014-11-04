package viewa.test;

import java.util.Arrays;
import java.util.Collection;

import javax.swing.JList;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.test.component.AbstractComponentTrapper;
import viewa.test.util.TrapperUtils;

public class ListTrapper extends AbstractComponentTrapper<JList> {

	private static final Log logger = LogFactory.getLog(ListTrapper.class);
	/**
	 * @param applicationTrapper
	 * @param componentName
	 */
	public ListTrapper(ApplicationTrapper applicationTrapper,
			String componentName) {
		super(applicationTrapper, componentName);
	}

	/**
	 * @param index
	 * @return
	 */
	public ListTrapper clickOnIndex(int index) {
		return this.clickOnIndex(index, this.applicationTrapper().getSettings()
				.getTimeBeforeNextEvent());
	}

	/**
	 * @param index
	 * @return
	 */
	public ListTrapper clickOnIndex(final int index, long millis) {
		return ListTrapper.class.cast(TrapperUtils.processRunnableAndWait(this,
				millis, new Runnable() {
					public void run() {
						getTarget().setSelectedIndex(index);
					}
				}));
	}

	/**
	 * @param indexes
	 * @return
	 */
	public ListTrapper clickOnIndexes(int[] indexes) {
		return this.clickOnIndexes(indexes, this.applicationTrapper()
				.getSettings().getTimeBeforeNextEvent());
	}

	/**
	 * @param indexes
	 * @param millis
	 * @return
	 */
	public ListTrapper clickOnIndexes(final int[] indexes, long millis) {
		return ListTrapper.class.cast(TrapperUtils.processRunnableAndWait(this,
				millis, new Runnable() {
					public void run() {
						getTarget().setSelectedIndices(indexes);
					}
				}));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see viewa.test.Trapper#getType()
	 */
	public Class<JList> getType() {
		return JList.class;
	}

	/**
	 * @return
	 */
	public ListTrapper isEmpty() {
		return this.isEmpty(true);
	}

	/**
	 * @return
	 */
	public ListTrapper isEmpty(boolean empty) {
		TestCase
				.assertEquals(this.getTarget().getModel().getSize() == 0, empty);
		return this;
	}

	/* (non-Javadoc)
	 * @see viewa.test.Trapper#log(java.lang.String)
	 */
	public ListTrapper log(String message) {
		logger.info(message);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.test.component.AbstractComponentTrapper#requireEnabled
	 * ()
	 */
	@Override
	public ListTrapper requireEnabled() {
		return this.requireEnabled(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.test.component.AbstractComponentTrapper#requireEnabled
	 * (boolean)
	 */
	@Override
	public ListTrapper requireEnabled(boolean enabled) {
		TestCase.assertEquals(this.getTarget().isEnabled(), enabled);
		return this;
	}

	/**
	 * @param index
	 * @return
	 */
	public ListTrapper requireSelectedIndex(int index) {
		TestCase.assertEquals(this.getTarget().getSelectedIndex(), index);
		return this;
	}

	/**
	 * @param indexes
	 * @return
	 */
	public ListTrapper requireSelectedIndexes(int[] indexes) {
		TestCase.assertEquals(this.getTarget().getSelectedIndices(), indexes);
		return this;
	}

	/**
	 * @param value
	 * @return
	 */
	public ListTrapper requireSelectedValue(Object value) {
		TestCase.assertEquals(this.getTarget().getSelectedValue(), value);
		return this;
	}

	/**
	 * @param values
	 * @return
	 */
	public ListTrapper requireSelectedValues(Collection<?> values) {
		TestCase.assertTrue(Arrays.asList(this.getTarget().getSelectedValues())
				.containsAll(values));
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.test.component.AbstractComponentTrapper#requireVisible
	 * ()
	 */
	@Override
	public ListTrapper requireVisible() {
		return this.requireVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.test.component.AbstractComponentTrapper#requireVisible
	 * (boolean)
	 */
	@Override
	public ListTrapper requireVisible(boolean visible) {
		TestCase.assertEquals(this.getTarget().isVisible(), visible);
		return this;
	}

}
