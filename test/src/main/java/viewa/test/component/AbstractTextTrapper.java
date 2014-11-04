package viewa.test.component;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import junit.framework.TestCase;

import viewa.test.ApplicationTrapper;
import viewa.test.util.TrapperUtils;

/**
 * @author Mario Garcia
 * 
 * @param <T>
 */
public abstract class AbstractTextTrapper<T extends JTextComponent> extends
		AbstractComponentTrapper<T> {

	/**
	 * @param applicationTrapper
	 * @param componentName
	 */
	public AbstractTextTrapper(ApplicationTrapper applicationTrapper,
			String componentName) {
		super(applicationTrapper, componentName);
	}

	/**
	 * @param applicationTrapper
	 * @param viewId
	 * @param componentName
	 */
	public AbstractTextTrapper(ApplicationTrapper applicationTrapper,
			String viewId, String componentName) {
		super(applicationTrapper, viewId, componentName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.test.component.AbstractComponentTrapper#requireEnabled
	 * ()
	 */
	@Override
	public AbstractTextTrapper<T> requireEnabled() {
		TestCase.assertTrue(this.getTarget().isEnabled());
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.test.component.AbstractComponentTrapper#requireEnabled
	 * (boolean)
	 */
	@Override
	public AbstractTextTrapper<T> requireEnabled(boolean enabled) {
		TestCase.assertEquals(this.getTarget().isEnabled(), enabled);
		return this;
	}

	/**
	 * @param textRequired
	 */
	public AbstractTextTrapper<T> requireText(String textRequired) {
		TestCase.assertEquals(this.getTarget().getText(), textRequired);
		return this;
	}

	/**
	 * @param textRequired
	 */
	public AbstractTextTrapper<T> requireText(String textRequired, int offs,
			int len) {
		try {
			TestCase.assertEquals(this.getTarget().getText(offs, len),
					textRequired);
		} catch (BadLocationException e) {
			TestCase.fail(e.getMessage());
		}
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
	public AbstractTextTrapper<T> requireVisible() {
		TestCase.assertTrue(this.getTarget().isVisible());
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.test.component.AbstractComponentTrapper#requireVisible
	 * (boolean)
	 */
	@Override
	public AbstractTextTrapper<T> requireVisible(boolean visible) {
		TestCase.assertEquals(this.getTarget().isVisible(), visible);
		return this;
	}

	/**
	 * @param text
	 * @return
	 */
	public AbstractTextTrapper<T> setText(String text) {
		this.setText(text, this.applicationTrapper().getSettings()
				.getTimeBeforeNextEvent());
		return this;
	}

	/**
	 * @param text
	 * @param millis
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public AbstractTextTrapper<T> setText(final String text, long millis) {
		return AbstractTextTrapper.class.cast(TrapperUtils
				.processRunnableAndWait(this, millis, new Runnable() {
					public void run() {
						getTarget().setText(text);
					}
				}));
	}
}
