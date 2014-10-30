package org.viewaframework.swing.binding.text;

import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;

import org.viewaframework.swing.binding.core.AbstractBindingListener;
import org.viewaframework.swing.binding.core.BeanAdapter;
import org.viewaframework.swing.binding.core.Property;

/**
 * @author Mario Garcia
 *
 * @param <T>
 * @param <TV>
 * @param <SV>
 */
public class TextFieldBindingListener<T,TV,SV> extends AbstractBindingListener<T, TV, SV>{

	/**
	 * @param target
	 * @param targetProperty
	 * @param sourceProperty
	 */
	public TextFieldBindingListener(BeanAdapter<T> target,Property<TV> targetProperty, Property<SV> sourceProperty) {
		super(target, targetProperty, sourceProperty);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.SynchronizedListener#getNewValue(java.beans.PropertyChangeEvent)
	 */
	public Object getNewValue(PropertyChangeEvent evt) {
		JTextField.AccessibleJTextComponent accessible = JTextField.AccessibleJTextComponent.class.cast(evt.getSource());
		int length = accessible.getCharCount();
		String newValue = accessible.getTextRange(0,length);	
		return newValue;
	}
	
	/**
	 * @param evt
	 * @return
	 */
	public boolean isPropertyToHandle(PropertyChangeEvent evt) {
		return  this.getSourceProperty().getExpression().equals("text") ? 
					evt.getPropertyName().equals(JTextField.AccessibleJTextComponent.ACCESSIBLE_TEXT_PROPERTY) :
					evt.getPropertyName().equals(this.getSourceProperty().getExpression());
	}	
	
}
