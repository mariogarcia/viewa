package org.viewaframework.swing.builder.component;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;

import org.viewaframework.swing.builder.SwingBuilder;

/**
 * @author mgg
 *
 */
public class TextFieldBuilder extends ComponentBuilderAbstract<JTextField>{

	private JTextField textField = new JTextField();;
	
	/**
	 * 
	 */
	public TextFieldBuilder(SwingBuilder swingBuilder){
		super(swingBuilder);
		this.textField.setPreferredSize(new Dimension(150,25));
	}
	
	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.Builder#getTarget()
	 */
	public JTextField getTarget() {
		return this.textField;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.Builder#getType()
	 */
	public Class<JTextField> getType() {
		return JTextField.class;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilder#setName(java.lang.String)
	 */
	public TextFieldBuilder setName(String name){
		this.textField.setName(name);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.Builder#setTarget(java.lang.Object)
	 */
	public void setTarget(JTextField target) {
		this.textField = target;
	}
	
	/**
	 * @param text
	 * @return
	 */
	public TextFieldBuilder setText(String text){
		this.textField.setText(text);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilder#setFont(java.awt.Font)
	 */
	public TextFieldBuilder setFont(Font font) {
		this.textField.setFont(font);
		return this;
	}

	public TextFieldBuilder setEnabled(boolean enabled) {
		this.textField.setEnabled(enabled);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilder#setPreferredSize(java.awt.Dimension)
	 */
	public TextFieldBuilder setPreferredSize(Dimension dimension) {
		this.textField.setPreferredSize(dimension);
		return this;
	}
}
