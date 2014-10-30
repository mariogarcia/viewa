package org.viewaframework.swing.binding.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;


/**
 * This kind of bean adapter can delay the events until the commit() 
 * method is called.
 * 
 * @author Mario Garcia
 *
 * @param <T>
 */
public class LazyBeanAdapter<T> extends BasicBeanAdapter<T>{

	private static final Boolean COMMITED = Boolean.TRUE;
	private Boolean autoCommit;
	private Boolean commited;
	private List<PropertyChangeEvent> propertyChangeEvents;	
	private DynaBean targetWrapper;
	
	/**
	 * @param source
	 */
	public LazyBeanAdapter(T source) {
		super(source);
		this.autoCommit = Boolean.FALSE;
		this.commited = Boolean.FALSE;
		this.targetWrapper = new WrapDynaBean(this.getSource());		
		this.propertyChangeEvents = new ArrayList<PropertyChangeEvent>();
	}
	
	/**
	 * @param source
	 * @param autoCommit
	 */
	public LazyBeanAdapter(T source,Boolean autoCommit) {
		this(source);
		this.autoCommit = autoCommit;
	}
	
	/**
	 * @param source
	 * @param name
	 */
	public LazyBeanAdapter(T source,String name){
		this(source);
		this.setName(name);
	}

	/**
	 * 
	 */
	public void commit(){
		if (!isAutoCommit()){
			for (PropertyChangeListener listener : this.getPropertyChangeListeners()){
				for (PropertyChangeEvent event : this.propertyChangeEvents){
					listener.propertyChange(event);
				}
			}
			this.commited = COMMITED;
		}		
	}
	
	/**
	 * @return
	 */
	public Boolean isAutoCommit(){
		return autoCommit;
	}
	
	/**
	 * @return
	 */
	public Boolean isCommited(){
		return this.commited;
	}

	/**
	 * @param autoCommit
	 */
	public void setAutoCommit(Boolean autoCommit) {
		this.autoCommit = autoCommit;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.BasicModelAdapter#setValue(java.lang.String, java.lang.Object)
	 */
	@Override
	public BasicBeanAdapter<T> setValue(String propertyName, Object value) {
		Object oldValue = this.targetWrapper.get(propertyName);
		this.targetWrapper.set(propertyName, value);
		PropertyChangeEvent event = new PropertyChangeEvent(this.getSource(),propertyName, oldValue, value);
		if (isAutoCommit()){
			this.firePropertyChange(event);
			this.commited = COMMITED;
		} else {
			this.propertyChangeEvents.add(event);
			this.commited = !COMMITED;
		}
		return this;
	}
}
