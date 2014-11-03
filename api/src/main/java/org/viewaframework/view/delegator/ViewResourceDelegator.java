package org.viewaframework.view.delegator;

import java.util.Map;


public interface ViewResourceDelegator extends Delegator {

	/**
	 * @param converters
	 */
	public abstract void setViewResourceConverters(Map<Class<? extends Object>,ViewResourceConverter> converters);

}