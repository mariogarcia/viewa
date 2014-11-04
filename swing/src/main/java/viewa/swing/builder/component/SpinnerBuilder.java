package viewa.swing.builder.component;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JSpinner;

import viewa.swing.builder.SwingBuilder;

/**
 * @author mgg
 *
 */
public class SpinnerBuilder extends ComponentBuilderAbstract<JSpinner>{

	private JSpinner target = new JSpinner();
	
	/**
	 * @param builder
	 */
	public SpinnerBuilder(SwingBuilder builder){
		super(builder);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.Builder#getTarget()
	 */
	public JSpinner getTarget() {
		return this.target;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.Builder#getType()
	 */
	public Class<JSpinner> getType() {
		return JSpinner.class;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setEnabled(boolean)
	 */
	public SpinnerBuilder setEnabled(boolean enabled) {
		this.getTarget().setEnabled(enabled);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setFont(java.awt.Font)
	 */
	public SpinnerBuilder setFont(Font font) {
		this.getTarget().setFont(font);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setName(java.lang.String)
	 */
	public SpinnerBuilder setName(String name) {
		this.getTarget().setName(name);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setPreferredSize(java.awt.Dimension)
	 */
	public SpinnerBuilder setPreferredSize(Dimension dimension) {
		this.getTarget().setPreferredSize(dimension);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.Builder#setTarget(java.lang.Object)
	 */
	public void setTarget(JSpinner target) {
		this.target = target;
	}
}
