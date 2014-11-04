package viewa.swing.binding;

import javax.swing.JButton;

import viewa.swing.binding.core.AbstractSelectionBinding;
import viewa.swing.binding.core.BasicBeanAdapter;
import viewa.swing.binding.core.BeanAdapter;
import viewa.swing.binding.core.BeanAdapterPropertyListener;
import viewa.swing.binding.core.Property;

/**
 * @author Mario Garcia
 *
 * @param <SS>
 * @param <SP>
 * @param <TS>
 * @param <TP>
 */
public class ButtonBinding<SS extends JButton, SP, TS, TP> extends
	AbstractSelectionBinding<SS, SP, TS, TP> {

	/**
	 * @param source
	 * @param sourceProperty
	 * @param target
	 * @param targetProperty
	 */
	public ButtonBinding(SS source, Property<SP> sourceProperty,BeanAdapter<TS> target, Property<TP> targetProperty) {
		super(new BasicBeanAdapter<SS>(source), sourceProperty, target, targetProperty);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#bind()
	 */
	public void bind() {
		this.setSourceListener(new BeanAdapterPropertyListener<TS, TP, SP>(this.getTarget(),this.getTargetProperty(),this.getSourceProperty()));
		this.setTargetListener(new BeanAdapterPropertyListener<SS, SP, TP>(this.getSource(),this.getSourceProperty(),this.getTargetProperty()));
		this.getSource().getSource().addPropertyChangeListener(getSourceListener());
		this.getTarget().addPropertyChangeListener(getTargetListener());
		 /* Synchronizes both sides */
		this.getTarget().setSource(this.getTarget().getSource());
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#unbind()
	 */
	public void unbind() {
		this.getSource().getSource().removePropertyChangeListener(getSourceListener());
		this.getTarget().removePropertyChangeListener(getTargetListener());
	}
}
