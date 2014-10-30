package org.viewaframework.swing.binding.core;

import org.viewaframework.swing.binding.collection.EventList;

public interface ObservableModel<U> extends Observable{

	public EventList<U> getModelList();
	public boolean contains(U element);
	public BeanAdapter<U> getSelectedElementAdapter();
	public void setModelList(EventList<U> modelList);
}
