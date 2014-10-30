package org.viewaframework.swing.binding;

import javax.swing.JTable;

import org.viewaframework.swing.binding.core.AbstractSelectionBinding;
import org.viewaframework.swing.binding.core.BasicBeanAdapter;
import org.viewaframework.swing.binding.core.BeanAdapter;
import org.viewaframework.swing.binding.core.NotBindableObjectException;
import org.viewaframework.swing.binding.list.ListBindingModelListener;
import org.viewaframework.swing.binding.table.TableBindingModel;

/**
 * @author Mario Garcia
 *
 * @param <SS>
 * @param <TS>
 */
public class TableSelectionBinding<SS extends JTable,TS> extends
AbstractSelectionBinding<SS,Object,TS,Object> {

	private TableBindingModel<TS> model;
	
	/**
	 * @param source
	 * @param target
	 */
	public TableSelectionBinding(SS source,BeanAdapter<TS> target) {
		super(new BasicBeanAdapter<SS>(source),null, target,null);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#bind()
	 */
	@SuppressWarnings("unchecked")
	public void bind() {		
		JTable table = this.getSource().getSource();
		Object o = table.getModel();
			if (!TableBindingModel.class.isInstance(o)){
				throw new NotBindableObjectException("JTable should be bind to a ListAdapter first");
			}		
	 /* When selected the target is synchronized */		
		this.model = TableBindingModel.class.cast(this.getSource().getSource().getModel());			
		this.model.addPropertyChangeListener(new ListBindingModelListener<TS,Object,Object>(this.getTarget()));
	 /* If target is in table then select it */
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
