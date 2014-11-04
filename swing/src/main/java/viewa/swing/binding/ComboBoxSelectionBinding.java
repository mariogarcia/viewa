package viewa.swing.binding;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import viewa.swing.binding.collection.EventList;
import viewa.swing.binding.combo.ComboBoxBindingModel;
import viewa.swing.binding.core.AbstractSelectionBinding;
import viewa.swing.binding.core.BasicBeanAdapter;
import viewa.swing.binding.core.BeanAdapter;
import viewa.swing.binding.list.ListBindingModelListener;

/**
 * @author Mario Garcia
 *
 * @param <TS>
 */
public class ComboBoxSelectionBinding<SS extends JComboBox,TS> extends
AbstractSelectionBinding<SS,Object,TS,Object> {

	private ComboBoxBindingModel<TS> model;
	private EventList<TS> modelList;
	/**
	 * @param source
	 * @param target
	 */
	public ComboBoxSelectionBinding(SS source, BeanAdapter<TS> target) {
		super(new BasicBeanAdapter<SS>(source),null, target,null);
		this.setSourceListener(new ListBindingModelListener<TS,Object,Object>(this.getTarget()));
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#bind()
	 */
	public void bind() {
		this.model = getWorkingComboBoxAdapter(this.getSource().getSource(), modelList);
		if (this.model.contains(getTarget().getSource())){
			this.model.setSelectedItem(getTarget().getSource());
		}
		this.getSource().getSource().setModel(model);		
		this.model.addPropertyChangeListener(this.getSourceListener());
	}

	/**
	 * @param combo
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ComboBoxBindingModel<TS> getWorkingComboBoxAdapter(
			JComboBox combo, EventList<TS> list) {
		ComboBoxModel model = combo.getModel();
		ComboBoxBindingModel<TS> adapter = null;

		if (!(model instanceof ComboBoxBindingModel)) {
			adapter = new ComboBoxBindingModel<TS>();
		} else {
			adapter = (ComboBoxBindingModel) model;
		}
		if (list != null) {
			adapter.setModelList(list);
		}
		return adapter;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#unbind()
	 */
	public void unbind() {}
}
