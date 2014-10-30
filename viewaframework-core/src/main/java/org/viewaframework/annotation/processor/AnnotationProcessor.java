package org.viewaframework.annotation.processor;

public interface AnnotationProcessor<T> {

	/**
	 * @throws Exception
	 */
	public abstract void process() throws Exception;

	/**
	 * @return
	 */
	public abstract T getResult();

}