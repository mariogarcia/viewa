package viewa.swing.binding.core;

import viewa.swing.binding.collection.EventList;

public interface ObservableModel<U> extends Observable{

	public EventList<U> getModelList();
	public boolean contains(U element);
	public BeanAdapter<U> getSelectedElementAdapter();
	public void setModelList(EventList<U> modelList);
}
