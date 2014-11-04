package viewa.swing.binding;

import javax.swing.JComboBox;

import viewa.swing.binding.collection.EventList;
import viewa.swing.binding.combo.ComboBoxBindingModel;
import viewa.swing.binding.core.AbstractListBinding;

/**
 * @author Mario Garcia
 * 
 * @param <TS>
 */
public class ComboBoxListBinding<TS> extends AbstractListBinding<JComboBox, TS> {

	/**
	 * 
	 */
	private ComboBoxBindingModel<TS> comboModel;

	/**
	 * @param source
	 * @param list
	 */
	public ComboBoxListBinding(JComboBox source, EventList<TS> list) {
		super(source, list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.viewaframework.binding.core.Binding#bind()
	 */
	public void bind() {
		this.comboModel = new ComboBoxBindingModel<TS>(this.getTarget());
		this.getSource().getSource().setModel(comboModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.viewaframework.binding.core.Binding#unbind()
	 */
	public void unbind() {
		// There is no need to "unbind" the model from the component
	}
}