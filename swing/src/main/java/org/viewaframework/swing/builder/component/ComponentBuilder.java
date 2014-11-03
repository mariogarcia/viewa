package org.viewaframework.swing.builder.component;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import org.viewaframework.swing.builder.Builder;
import org.viewaframework.swing.builder.SwingBuilderAware;

/**
 * @author mgg
 *
 * @param <T>
 */
public interface ComponentBuilder<T extends Component> extends Builder<T>,SwingBuilderAware {

	/**
	 * @param name
	 * @return
	 */
	public ComponentBuilder<T> setName(String name);
	/**
	 * @param font
	 * @return
	 */
	public ComponentBuilder<T> setFont(Font font);
	/**
	 * @param enabled
	 * @return
	 */
	public ComponentBuilder<T> setEnabled(boolean enabled);
	/**
	 * @param dimension
	 * @return
	 */
	public ComponentBuilder<T> setPreferredSize(Dimension dimension);
}
