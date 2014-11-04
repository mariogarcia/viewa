package viewa.core;

/**
 * This exception means the abnormal termination of the application
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public class ApplicationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2374434276758301788L;

	/**
	 * @param throwable
	 */
	public ApplicationException(Throwable throwable){
		super(throwable);
	}
	
	/**
	 * @param message
	 */
	public ApplicationException(String message){
		super(message);
	}
	
}
