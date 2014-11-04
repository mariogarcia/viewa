package viewa.swing.binding.core;

/**
 * @author Mario Garcia
 *
 */
public class NotBindableObjectException extends RuntimeException{

	private static final long serialVersionUID = 6419984007718223324L;

	/**
	 * @param message
	 */
	public NotBindableObjectException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NotBindableObjectException(Throwable cause) {
		super(cause);
	}


}
