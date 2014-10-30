package org.viewaframework.swing.binding;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import org.viewaframework.swing.binding.collection.EventList;
import org.viewaframework.swing.binding.core.AbstractListBinding;
import org.viewaframework.swing.binding.list.ListBindingModel;

/**
 * @author Mario Garcia
 *
 * @param <TS>
 */
public class ListBinding<TS> extends AbstractListBinding<JList, TS> {

	private ListBindingModel<TS> model;
	private JList sourceList;
	
	/**
	 * @param source
	 * @param list
	 */
	public ListBinding(JList source, EventList<TS> list) {
		super(source, list);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#bind()
	 */
	public void bind() {		
		this.model = new ListBindingModel<TS>(this.getSource().getSource(),this.getTarget());
		this.sourceList = this.getSource().getSource();
		this.sourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.sourceList.setModel(model);
		this.sourceList.getSelectionModel().addListSelectionListener(model);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#unbind()
	 */
	public void unbind() {
		this.sourceList.getSelectionModel().removeListSelectionListener(model);
	}
}
