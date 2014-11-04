package viewa.test;

import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.test.component.AbstractTextTrapper;

/**
 * @author Mario Garcia
 *
 */
public class TextTrapper extends AbstractTextTrapper<JTextField>{

	private static final Log logger = LogFactory.getLog(TextTrapper.class);
	
	/**
	 * @param applicationTrapper
	 * @param componentName
	 */
	public TextTrapper(ApplicationTrapper applicationTrapper,String componentName) {
		super(applicationTrapper, componentName);
	}

	/**
	 * @param applicationTrapper
	 * @param viewId
	 * @param componentName
	 */
	public TextTrapper(ApplicationTrapper applicationTrapper,String viewId, String componentName) {
		super(applicationTrapper, viewId, componentName);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getType()
	 */
	public Class<JTextField> getType() {
		return JTextField.class;
	}

	/* (non-Javadoc)
	 * @see viewa.test.Trapper#log(java.lang.String)
	 */
	public TextTrapper log(String message) {
		logger.info(message);
		return this;
	}

	/* (non-Javadoc)
	 * @see viewa.test.component.AbstractComponentTrapper#requireEnabled()
	 */
	@Override
	public TextTrapper requireEnabled() {
		return TextTrapper.class.cast(super.requireEnabled());
	}

	/* (non-Javadoc)
	 * @see viewa.test.component.AbstractComponentTrapper#requireEnabled(boolean)
	 */
	@Override
	public TextTrapper requireEnabled(boolean enabled) {
		return TextTrapper.class.cast(super.requireEnabled(enabled));
	}

	/* (non-Javadoc)
	 * @see viewa.test.component.AbstractComponentTrapper#requireVisible()
	 */
	@Override
	public TextTrapper requireVisible() {
		return TextTrapper.class.cast(super.requireVisible());
	}

	/* (non-Javadoc)
	 * @see viewa.test.component.AbstractComponentTrapper#requireVisible(boolean)
	 */
	@Override
	public TextTrapper requireVisible(boolean visible) {
		return TextTrapper.class.cast(super.requireVisible(visible));
	}
}
