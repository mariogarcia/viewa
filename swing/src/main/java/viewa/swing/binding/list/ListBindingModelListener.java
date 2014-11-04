package viewa.swing.binding.list;

import java.beans.PropertyChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.swing.binding.core.AbstractBindingListener;
import viewa.swing.binding.core.Adapter;
import viewa.swing.binding.core.BasicBeanAdapter;
import viewa.swing.binding.core.BeanAdapter;

/**
 * @author Mario Garcia
 *
 * @param <T>
 * @param <TV>
 * @param <SV>
 */
public class ListBindingModelListener<T,TV,SV> extends AbstractBindingListener<T,TV,SV>{

	private static final Log logger = LogFactory.getLog(ListBindingModelListener.class);
	
	/**
	 * @param target
	 */
	public ListBindingModelListener(BeanAdapter<T> target) {
		super(target, null, null);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.SynchronizedListener#getNewValue(java.beans.PropertyChangeEvent)
	 */
	public Object getNewValue(PropertyChangeEvent evt) {
		if (logger.isDebugEnabled()){
			logger.debug(evt.getNewValue());
		}
		return evt.getNewValue();
	}
	

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.AbstractBindingListener#handleProperty(java.beans.PropertyChangeEvent)
	 */
	@Override
	@SuppressWarnings("unchecked")	
	public void handleProperty(PropertyChangeEvent evt) {
		if (logger.isDebugEnabled()){
			logger.debug(getTarget().getClass().getName()+" || "+evt.getNewValue().getClass().getName());
		}		
		this.getTarget().sync(Adapter.class.cast(evt.getNewValue()));
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.AbstractBindingListener#isPropertyToHandle(java.beans.PropertyChangeEvent)
	 */
	@Override
	public boolean isPropertyToHandle(PropertyChangeEvent evt) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.AbstractBindingListener#isSynchronized(java.beans.PropertyChangeEvent)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean isSynchronized(PropertyChangeEvent evt) {
		if (logger.isDebugEnabled()){
			logger.debug(getTarget().getClass().getName()+" || "+evt.getNewValue().getClass().getName());
		}			
		return this.getTarget().isSync(Adapter.class.cast(evt.getNewValue()));
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.AbstractBindingListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		PropertyChangeEvent evt2 = transformToBeanAdapters(evt);
		super.propertyChange(evt2);
	}

	/**
	 * @param evt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private PropertyChangeEvent transformToBeanAdapters(PropertyChangeEvent evt) {
		BeanAdapter<?> newValue = evt.getNewValue() != null ? new BasicBeanAdapter(evt.getNewValue()) : null;
		BeanAdapter<?> oldValue = evt.getOldValue() != null ? new BasicBeanAdapter(evt.getOldValue()) : null;
		return new PropertyChangeEvent(evt.getSource(),evt.getPropertyName(), oldValue, newValue);
	}
}
