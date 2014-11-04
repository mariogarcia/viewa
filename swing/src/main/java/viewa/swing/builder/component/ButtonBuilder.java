package viewa.swing.builder.component;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import viewa.swing.builder.SwingBuilder;

/**
 * @author mgg
 *
 */
public class ButtonBuilder extends ComponentBuilderAbstract<JButton>{

	private JButton button;
	
	/**
	 * @param builder
	 */
	public ButtonBuilder(SwingBuilder builder){
		super(builder);
		this.button = new JButton();
	}
	
	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.Builder#getTarget()
	 */
	public JButton getTarget() {
		return this.button;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.Builder#getType()
	 */
	public Class<JButton> getType() {
		return JButton.class;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilder#setName(java.lang.String)
	 */
	public ButtonBuilder setName(String name) {
		this.button.setName(name);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.Builder#setTarget(java.lang.Object)
	 */
	public void setTarget(JButton target) {
		this.button = target;
	}

	/**
	 * @param text
	 * @return
	 */
	public ButtonBuilder setText(String text){
		this.button.setText(text);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilder#setFont(java.awt.Font)
	 */
	public ButtonBuilder setFont(Font font) {
		this.button.setFont(font);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilder#setEnabled(boolean)
	 */
	public ButtonBuilder setEnabled(boolean enabled) {
		this.button.setEnabled(enabled);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilder#setPreferredSize(java.awt.Dimension)
	 */
	public ButtonBuilder setPreferredSize(Dimension dimension) {
		this.button.setPreferredSize(dimension);
		return this;
	}
}
