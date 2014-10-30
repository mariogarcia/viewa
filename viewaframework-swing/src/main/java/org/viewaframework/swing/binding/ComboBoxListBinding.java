package org.viewaframework.swing.binding;

import javax.swing.JComboBox;

import org.viewaframework.swing.binding.collection.EventList;
import org.viewaframework.swing.binding.combo.ComboBoxBindingModel;
import org.viewaframework.swing.binding.core.AbstractListBinding;

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