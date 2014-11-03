package org.viewaframework.swing.binding.collection;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * This class is a regular ArrayList which post events each time an element
 * is added, removed or updated.
 * 
 * @author Mario Garcia
 * 
 * @param <U> Type of the elements this list contains
 */
public class EventList<E> extends ArrayList<E> implements ListModel {

	public static final String ELEMENTS = "elements";
	private static final long serialVersionUID = 6136119261534765824L;
	private List<ListDataListener> listenerList = new ArrayList<ListDataListener>();
	private String name;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	/**
	 * Default constructor
	 */
	public EventList() {
		super();
	}

	/**
	 * Initializes the adapter with a collection of items
	 * 
	 * @param c
	 */
	public EventList(Collection<E> c) {
		super(c);
	}

	/**
	 * Initializes the adapter with a collection of items and a 
	 * list of listeners which are going to listen the list data changes.
	 * 
	 * @param c collection
	 * @param listeners
	 */
	public EventList(Collection<E> c,
			List<ListDataListener> listeners) {
		super(c);
		this.fireIntervalAdded(this, 0, c.size());
		this.firePropertyChange(new PropertyChangeEvent(this,ELEMENTS,null,this));
	}

	/**
	 * Constructor establishing the initial capacity of the adapter
	 * 
	 * @param initialCapacity
	 */
	public EventList(int initialCapacity) {
		super(initialCapacity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		boolean added = super.add(e);
		int index = getCurrentIndex();
		if (added) {
			this.fireIntervalAdded(this, index, index);
			this.firePropertyChange(new PropertyChangeEvent(this,ELEMENTS,null,this));
		}
		return added;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		super.add(index, element);
		this.fireIntervalAdded(this, index, index);
		this.firePropertyChange(new PropertyChangeEvent(this,ELEMENTS,null,this));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		int start = getCurrentIndex();
		boolean added = super.addAll(c);
		if (added) {
			int end = getCurrentIndex();
			this.fireIntervalAdded(this, start, end);
			this.firePropertyChange(new PropertyChangeEvent(this,ELEMENTS,null,this));
		}
		return added;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean added = false;
		if (!c.isEmpty()) {
			added = super.addAll(c);
			if (added) {
				int end = getCurrentIndex() + c.size();
				this.fireIntervalAdded(this, index, end);
				this.firePropertyChange(new PropertyChangeEvent(this,ELEMENTS,null,this));
			}
		}
		return added;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.ListModel#addListDataListener(javax.swing.event.ListDataListener
	 * )
	 */
	public void addListDataListener(ListDataListener l) {
		listenerList.add(l);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener){
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#clear()
	 */
	@Override
	public void clear() {
		int formerSize = this.size();
		super.clear();
		this.fireIntervalRemoved(this, 0, formerSize);
		this.firePropertyChange(new PropertyChangeEvent(this,ELEMENTS,null,this));
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return super.contains(o);
	}

	/**
	 * This method keeps informed listeners of list data changes. Updates.
	 * 
	 * @param source It will usually be this
	 * @param index0 The start
	 * @param index1 The end
	 */
	protected void fireContentsChanged(Object source, int index0, int index1) {
		for (ListDataListener listener : this.listenerList) {
			listener.contentsChanged(new ListDataEvent(source,
					ListDataEvent.CONTENTS_CHANGED, index0, index1));
			updateUI(listener);
		}
	}

	/**
	 * This method keeps informed listeners of list data changes. Insertions.
	 * 
	 * @param source It will usually be this
	 * @param index0 The start
	 * @param index1 The end
	 */
	protected void fireIntervalAdded(Object source, int index0, int index1) {
		for (ListDataListener listener : this.listenerList) {
			listener.intervalAdded(new ListDataEvent(source,
					ListDataEvent.CONTENTS_CHANGED, index0, index1));
			updateUI(listener);
		}
	}

	/**
	 * This method keeps informed listeners of list data changes. Deletions.
	 * 
	 * @param source It will usually be this
	 * @param index0 The start
	 * @param index1 The end
	 */
	protected void fireIntervalRemoved(Object source, int index0, int index1) {
		for (ListDataListener listener : this.listenerList) {
			listener.intervalRemoved(new ListDataEvent(source,
					ListDataEvent.CONTENTS_CHANGED, index0, index1));
			updateUI(listener);
		}
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Adapter#firePropertyChange(java.beans.PropertyChangeEvent)
	 */
	public void firePropertyChange(PropertyChangeEvent evt) {
		for (PropertyChangeListener listener : this.getPropertyChangeListeners()){
			listener.propertyChange(evt);
		}		
	}

	/**
	 * Returns the current index of the list
	 * 
	 * @return index
	 */
	private int getCurrentIndex() {
		return this.size() <= 0 ? 0 : this.size() - 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Object getElementAt(int index) {
		return this.get(index);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Adapter#getName()
	 */
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Adapter#getPropertyChangeListeners()
	 */
	public List<PropertyChangeListener> getPropertyChangeListeners() {
		return Arrays.asList(this.propertyChangeSupport.getPropertyChangeListeners());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return this.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#remove(int)
	 */
	@Override
	public E remove(int index) {
		E elementRemoved = super.remove(index);
		this.fireIntervalRemoved(this, index, index);
		this.firePropertyChange(new PropertyChangeEvent(this,ELEMENTS,null,this));
		return elementRemoved;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		int index = this.indexOf(o);
		boolean removable = index != -1;
		if (removable) {
			removable = this.remove(index) != null ? removable : false;
		}
		return removable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seejavax.swing.ListModel#removeListDataListener(javax.swing.event.
	 * ListDataListener)
	 */
	public void removeListDataListener(ListDataListener l) {
		listenerList.remove(l);
	}


	public void removePropertyChangeListener(PropertyChangeListener listener){
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#removeRange(int, int)
	 */
	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		super.removeRange(fromIndex, toIndex);
		this.fireIntervalRemoved(this, fromIndex, toIndex);
		this.firePropertyChange(new PropertyChangeEvent(this,ELEMENTS,null,this));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		E elementReplaced = super.set(index, element);
		this.fireContentsChanged(this, index, index);
		this.firePropertyChange(new PropertyChangeEvent(this,ELEMENTS,null,this));
		return elementReplaced;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Adapter#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method helps the list to update the component using it to
	 * refresh its view.
	 * 
	 * @param listener
	 */
	private void updateUI(final ListDataListener listener) {
		if (listener instanceof JComponent){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JComponent.class.cast(listener).updateUI();	
				}
			});			
		}		
	}
}
