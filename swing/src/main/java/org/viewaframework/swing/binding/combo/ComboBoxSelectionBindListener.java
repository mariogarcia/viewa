package org.viewaframework.swing.binding.combo;

import java.beans.PropertyChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.viewaframework.swing.binding.core.AbstractBindingListener;
import org.viewaframework.swing.binding.core.BasicBeanAdapter;
import org.viewaframework.swing.binding.core.BeanAdapter;
import org.viewaframework.swing.binding.core.Property;

/**
 * @author Mario Garcia
 *
 * @param <T>
 * @param <TV>
 * @param <SV>
 */
public class ComboBoxSelectionBindListener<T,TV,SV> extends AbstractBindingListener<T, TV, SV>{

	private static final Log logger = LogFactory.getLog(ComboBoxSelectionBindListener.class);
	
	/**
	 * @param target
	 * @param targetProperty
	 * @param sourceProperty
	 */
	public ComboBoxSelectionBindListener(BeanAdapter<T> target,Property<TV> targetProperty, Property<SV> sourceProperty) {
		super(target, targetProperty, sourceProperty);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.SynchronizedListener#getNewValue(java.beans.PropertyChangeEvent)
	 */
	@SuppressWarnings("unchecked")
	public Object getNewValue(PropertyChangeEvent evt) {
		if (logger.isDebugEnabled()){
			logger.debug("newValue: "+evt.getNewValue()+"| sourceProperty: "+
					this.getSourceProperty());
		}
		return new BasicBeanAdapter(evt.getNewValue()).
			getValue(this.getSourceProperty().getType(),this.getSourceProperty().getExpression());
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.AbstractBindingListener#isPropertyToHandle(java.beans.PropertyChangeEvent)
	 */
	@Override
	public boolean isPropertyToHandle(PropertyChangeEvent evt) {
		return  evt.getPropertyName().equals("selectedItem");
	}
}
