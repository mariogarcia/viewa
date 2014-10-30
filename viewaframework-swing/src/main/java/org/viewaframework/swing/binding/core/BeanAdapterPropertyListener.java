package org.viewaframework.swing.binding.core;

import java.beans.PropertyChangeEvent;


/**
 * @author Mario Garcia
 *
 * @param <S>
 * @param <SV>
 * @param <TV>
 */
public class BeanAdapterPropertyListener<T,TV,SV> extends AbstractBindingListener<T, TV, SV>{

	/**
	 * @param target
	 * @param targetProperty
	 * @param sourceProperty
	 */
	public BeanAdapterPropertyListener(BeanAdapter<T> target,Property<TV> targetProperty, Property<SV> sourceProperty) {
		super(target, targetProperty, sourceProperty);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.SynchronizedListener#getNewValue(java.beans.PropertyChangeEvent)
	 */
	public Object getNewValue(PropertyChangeEvent evt) {
		return evt.getNewValue();
	}	
}

