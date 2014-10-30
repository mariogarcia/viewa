package org.viewaframework.swing.binding.core;

import org.viewaframework.swing.binding.collection.EventList;

/**
 * Abstract implementation of the ListableBinding interface
 * 
 * @author Mario Garcia
 *
 * @param <SS> Source
 * @param <SP> Source property
 * @param <TS> Target
 * @param <TP> Target property
 */
public abstract class AbstractListBinding<SS,TS> implements ListableBinding<SS,TS>{

	private BeanAdapter<SS> source;
	private EventList<TS> target;
	
	/**
	 * Constructor
	 * 
	 * @param source The source component
	 * @param list The list used by the underline model
	 */
	public AbstractListBinding(SS source,EventList<TS> list){
		this.source = new BasicBeanAdapter<SS>(source);
		this.target = list;
	}	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#getSource()
	 */
	public BeanAdapter<SS> getSource() {
		return source;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ListBinding#getTarget()
	 */
	public EventList<TS> getTarget() {
		return target;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#setSource(org.viewaframework.binding.core.BeanAdapter)
	 */
	public void setSource(BeanAdapter<SS> source) {
		this.source = source;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ListBinding#setTarget(org.viewaframework.binding.collection.ListAdapter)
	 */
	public void setTarget(EventList<TS> target) {
		this.target = target;
	}
}