package org.viewaframework.view.delegator.converter;

import org.viewaframework.view.delegator.ViewResourceConverter;



public class ViewResourceStringConverter implements ViewResourceConverter {

	public Object convert(Object arg0, Class<? extends Object> arg1) {
		return arg0.toString();
	}

	public Class<? extends Object> getDestinationClass() {
		return String.class;
	}

}
