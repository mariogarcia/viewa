package org.viewaframework.swing.binding.core;

import java.beans.PropertyChangeEvent;

/**
 * @author Mario Garcia
 *
 */
public interface SynchronizedListener {

	/**
	 * @param evt
	 * @return
	 */
	public Object getNewValue(PropertyChangeEvent evt);

	/**
	 * @param evt
	 * @return
	 */
	public boolean isSynchronized(PropertyChangeEvent evt);
	
}
