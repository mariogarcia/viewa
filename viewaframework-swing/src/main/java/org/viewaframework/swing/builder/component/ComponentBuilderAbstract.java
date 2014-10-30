package org.viewaframework.swing.builder.component;

import javax.swing.JComponent;

import org.viewaframework.swing.builder.SwingBuilder;

/**
 * @author mgg
 *
 * @param <T>
 */
public abstract class ComponentBuilderAbstract<T extends JComponent> implements ComponentBuilder<T>{

	private SwingBuilder rootBuilder;
	
	/**
	 * @param swingBuilder
	 */
	public ComponentBuilderAbstract(SwingBuilder swingBuilder){
		this.rootBuilder = swingBuilder;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.SwingBuilderAware#swingBuilder()
	 */
	public SwingBuilder swingBuilder() {
		return this.rootBuilder;
	}	
}
