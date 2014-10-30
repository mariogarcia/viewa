package org.viewaframework.swing.binding.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class helps bindings to keep synchronized.
 * 
 * @author Mario Garcia
 *
 * @param <T> Target type
 * @param <TV> Target property type
 * @param <SV> Source property type
 */
public abstract class AbstractBindingListener<T,TV,SV> implements
		PropertyChangeListener, SynchronizedListener {

	private Property<SV> sourceProperty;
	private BeanAdapter<T> target;
	private Property<TV> targetProperty;
	private static final Log logger = LogFactory.getLog(AbstractBindingListener.class);

	/**
	 * Constructor
	 * 
	 * @param target The target object this listener is going to populate
	 * @param sourceProperty The source property from data is retrieved
	 * @param targetProperty The target property where data is going to be copied
	 */
	public AbstractBindingListener(BeanAdapter<T> target,Property<TV> targetProperty,Property<SV> sourceProperty){
		this.target = target;
		this.sourceProperty = sourceProperty;
		this.targetProperty = targetProperty;
	}


	/**
	 * The source property from data is retrieved
	 * 
	 * @return
	 */
	public Property<SV> getSourceProperty() {
		return sourceProperty;
	}


	/**
	 * The target object this listener is going to populate
	 * 
	 * @return
	 */
	public BeanAdapter<T> getTarget() {
		return target;
	}


	/**
	 * The target property where data is going to be copied
	 * 
	 * @return
	 */
	public Property<TV> getTargetProperty() {
		return targetProperty;
	}


	/**
	 * Once we know that source and target are not synchronized and also that
	 * the right property is at hand we make the value transfer within this method
	 * 
	 * @param evt event
	 */
	public void handleProperty(final PropertyChangeEvent evt){
		target.setValue(
				targetProperty.getType(),
				targetProperty.getExpression(),
				targetProperty.getType().cast(
						ConvertUtils.convert(
								getNewValue(evt).toString(),
								targetProperty.getType())														
				));
	}

	/**
	 * Whether the property triggered is the one to handle
	 * 
	 * @param evt
	 * @return
	 */
	public boolean isPropertyToHandle(PropertyChangeEvent evt) {
		return evt.getPropertyName().
			equals(sourceProperty.getExpression());
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.SynchronizedListener#isSynchronized(java.beans.PropertyChangeEvent)
	 */
	public boolean isSynchronized(PropertyChangeEvent evt) {
		Boolean areSynchronized = Boolean.TRUE;
		Object newValue = getNewValue(evt);
		newValue = newValue != null ? ConvertUtils.convert(
				getNewValue(evt).toString(),
				targetProperty.getType()) : null;
		Object targetValue = target.getValue(
				targetProperty.getType(),
				targetProperty.getExpression());
		if (newValue!= null && !newValue.equals(targetValue)){
			areSynchronized = Boolean.FALSE;
		}
		if (logger.isDebugEnabled()){
			logger.debug("isSynchronized? = "+areSynchronized);
		}	
		return areSynchronized;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seejava.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (isPropertyToHandle(evt) &&  !isSynchronized(evt)) {
			handleProperty(evt);
		}
	}

	/**
	 * The source property from data is retrieved
	 * 
	 * @param sourceProperty
	 */
	public void setSourceProperty(Property<SV> sourceProperty) {
		this.sourceProperty = sourceProperty;
	}
	
	/**
	 * The target object this listener is going to populate
	 * 
	 * @param target
	 */
	public void setTarget(BeanAdapter<T> target) {
		this.target = target;
	}

	/**
	 * The target property where data is going to be copied
	 * 
	 * @param targetProperty
	 */
	public void setTargetProperty(Property<TV> targetProperty) {
		this.targetProperty = targetProperty;
	}
}