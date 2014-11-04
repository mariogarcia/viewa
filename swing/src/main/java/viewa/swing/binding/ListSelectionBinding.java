package viewa.swing.binding;

import javax.swing.JList;
import javax.swing.ListModel;

import viewa.swing.binding.core.AbstractSelectionBinding;
import viewa.swing.binding.core.BasicBeanAdapter;
import viewa.swing.binding.core.BeanAdapter;
import viewa.swing.binding.core.NotBindableObjectException;
import viewa.swing.binding.list.ListBindingModel;
import viewa.swing.binding.list.ListBindingModelListener;

/**
 * @author Mario Garcia
 *
 * @param <SS>
 * @param <TS>
 */
public class ListSelectionBinding<SS extends JList,TS> extends
	AbstractSelectionBinding<SS,Object,TS,Object> {

	private ListBindingModel<TS> model;
	private JList sourceList;
	
	/**
	 * @param source
	 * @param target
	 */
	public ListSelectionBinding(SS source,BeanAdapter<TS> target) {
		super(new BasicBeanAdapter<SS>(source),null,target,null);
		this.sourceList = source;
		this.setSourceListener(new ListBindingModelListener<TS,Object,Object>(this.getTarget()));
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#bind()
	 */
	@SuppressWarnings("unchecked")
	public void bind() {
		ListModel model = this.sourceList.getModel();
			if (!ListBindingModel.class.isInstance(model)){
				throw new NotBindableObjectException("JList should be bind to a ListBinding first");
			} 		
		this.model = ListBindingModel.class.cast(model);
		this.model.addPropertyChangeListener(getSourceListener());
		if (this.model.contains(getTarget().getSource())){
			this.model.setSelectedItem(getTarget().getSource());
		}	
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#unbind()
	 */
	public void unbind() {
		this.model.removePropertyChangeListener(getSourceListener());
	}
}
