package org.viewaframework.swing.binding.combo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.List;

import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataListener;

import org.viewaframework.swing.binding.collection.EventList;
import org.viewaframework.swing.binding.core.BasicBeanAdapter;
import org.viewaframework.swing.binding.core.BeanAdapter;
import org.viewaframework.swing.binding.core.ObservableModel;

/**
 * @author Mario Garcia
 *
 * @param <U>
 */
public class ComboBoxBindingModel<U> implements MutableComboBoxModel,ObservableModel<U>{

	public static final String SELECTED_ITEM = "selectedItem";
	private static final long serialVersionUID = 4314491370525615140L;
	private EventList<U> listAdapter;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private U selectedElement;
	
	/**
	 * 
	 */
	public ComboBoxBindingModel(){
		this(new EventList<U>());
	}
	
	/**
	 * @param list
	 */
	public ComboBoxBindingModel(EventList<U> list){
		this.listAdapter = list;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.MutableComboBoxModel#addElement(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public void addElement(Object obj) {
		this.listAdapter.add((U)obj);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.ListModel#addListDataListener(javax.swing.event.ListDataListener)
	 */
	public void addListDataListener(ListDataListener l) {
		this.listAdapter.addListDataListener(l);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#contains(java.lang.Object)
	 */
	public boolean contains(U element){
		return this.listAdapter.contains(element);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#firePropertyChange(java.beans.PropertyChangeEvent)
	 */
	public void firePropertyChange(PropertyChangeEvent evt) {
		this.propertyChangeSupport.firePropertyChange(evt);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Object getElementAt(int index) {
		return (U)this.listAdapter.get(index);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#getModelList()
	 */
	public EventList<U> getModelList() {
		return listAdapter;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#getPropertyChangeListeners()
	 */
	public List<PropertyChangeListener> getPropertyChangeListeners() {
		return Arrays.asList(this.propertyChangeSupport.getPropertyChangeListeners());
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#getSelectedElementAdapter()
	 */
	public BeanAdapter<U> getSelectedElementAdapter() {
		return new BasicBeanAdapter<U>(this.selectedElement);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#getSelectedItem()
	 */
	public Object getSelectedItem() {
		return this.selectedElement != null ? 
				this.selectedElement : !this.listAdapter.isEmpty() ? 
						this.listAdapter.get(0) : null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return this.listAdapter.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.MutableComboBoxModel#insertElementAt(java.lang.Object, int)
	 */
	@SuppressWarnings("unchecked")
	public void insertElementAt(Object obj, int index) {
		this.listAdapter.add(index, (U)obj);
	}

	/* (non-Javadoc)
	 * @see javax.swing.MutableComboBoxModel#removeElement(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public void removeElement(Object obj) {
		this.listAdapter.remove((U)obj);
	}

	/* (non-Javadoc)
	 * @see javax.swing.MutableComboBoxModel#removeElementAt(int)
	 */
	public void removeElementAt(int index) {
		this.listAdapter.remove(index);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#removeListDataListener(javax.swing.event.ListDataListener)
	 */
	public void removeListDataListener(ListDataListener l) {
		this.listAdapter.removeListDataListener(l);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(
			PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#setModelList(org.viewaframework.binding.collection.EventList)
	 */
	public void setModelList(EventList<U> modelList) {
		this.listAdapter = modelList;
	}

	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public void setSelectedItem(Object anItem) {
		if (this.listAdapter.contains(anItem)){
			U oldValue = this.selectedElement;
			U newValue = (U) anItem;
			this.selectedElement = newValue;
			this.firePropertyChange(new PropertyChangeEvent(this,SELECTED_ITEM, oldValue, newValue));
		}
	}

}
