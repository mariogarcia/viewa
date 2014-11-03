package org.viewaframework.test.application;

import org.viewaframework.test.PropertyTrapper;

public interface NumberAware {

	/**
	 * @return
	 */
	public PropertyTrapper asByte();
	/**
	 * @return
	 */
	public PropertyTrapper asDouble();
	/**
	 * @return
	 */
	public PropertyTrapper asFloat();
	/**
	 * @return
	 */
	public PropertyTrapper asInteger();
	/**
	 * @return
	 */
	public PropertyTrapper asLong();
	/**
	 * @return
	 */
	public PropertyTrapper asNumber();
	/**
	 * @return
	 */
	public PropertyTrapper asShort();
	/**
	 * @return
	 */
	public PropertyTrapper requireByte(Byte number);
	/**
	 * @return
	 */
	public PropertyTrapper requireDouble(Double number);
	/**
	 * @return
	 */
	public PropertyTrapper requireFloat(Float number);
	/**
	 * @return
	 */
	public PropertyTrapper requireInteger(Integer number);
	/**
	 * @return
	 */
	public PropertyTrapper requireLong(Long number);
	/**
	 * @return
	 */
	public PropertyTrapper requireNumber(Number number);
	/**
	 * @return
	 */
	public PropertyTrapper requireShort(Short number);
	
	
}
