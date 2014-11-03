package org.viewaframework.test;

/**
 * @author Mario Garcia
 *
 * @param <T>
 */
public interface Trapper<T> {

	/**
	 * @return
	 */
	public T getTarget();
	
	/**
	 * @return
	 */
	public Class<T> getType();
	
	/**
	 * @param target
	 */
	public void setTarget(T target);
	
	/**
	 * @param message
	 * @return
	 */
	public Trapper<T> log(String message);
	
}
