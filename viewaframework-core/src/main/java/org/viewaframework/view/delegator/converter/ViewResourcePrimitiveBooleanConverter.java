package org.viewaframework.view.delegator.converter;

import org.viewaframework.view.delegator.ViewResourceConverter;


public class ViewResourcePrimitiveBooleanConverter implements ViewResourceConverter{

	public Object convert(Object arg0, Class<? extends Object> arg1) {
		Boolean b = null;
		if (arg0!= null && arg1.equals(this.getDestinationClass())){
			b = Boolean.valueOf(arg0.toString());
		}
		return b;
	}

	public Class<? extends Object> getDestinationClass() {
		return boolean.class;
	}

}
