package viewa.controller;

/**
 * This exception can be thrown by a {@link ViewController#handleView(viewa.view.ViewContainer, java.util.EventObject)} method.
 * This will allow programmers to handle this exception in a 
 * {@link ViewController#postHandlingViewOnError(viewa.view.ViewContainer, java.util.EventObject, Throwable)} method.
 * 
 * 
 * @author mgg
 *
 */
public class BackgroundException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public BackgroundException() {
		super();
	}

	/**
	 * @param message
	 */
	public BackgroundException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BackgroundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public BackgroundException(Throwable cause) {
		super(cause);
	}
}
