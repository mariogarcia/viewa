package org.viewaframework.swing.builder.component;

import java.awt.Dimension;
import java.awt.Font;

import org.viewaframework.swing.DatePicker;
import org.viewaframework.swing.builder.SwingBuilder;

/**
 * 
 * @author mgg
 *
 */
public class DatePickerBuilder extends ComponentBuilderAbstract<DatePicker>{

	private DatePicker datePicker = new DatePicker();
	
	/**
	 * @param swingBuilder
	 */
	public DatePickerBuilder(SwingBuilder swingBuilder) {
		super(swingBuilder);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.Builder#getTarget()
	 */
	public DatePicker getTarget() {		
		return this.datePicker;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.Builder#getType()
	 */
	public Class<DatePicker> getType() {
		return DatePicker.class;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setEnabled(boolean)
	 */
	public ComponentBuilder<DatePicker> setEnabled(boolean enabled) {
		this.datePicker.setEnabled(enabled);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setFont(java.awt.Font)
	 */
	public ComponentBuilder<DatePicker> setFont(Font font) {
		this.datePicker.setFont(font);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setName(java.lang.String)
	 */
	public ComponentBuilder<DatePicker> setName(String name) {
		this.datePicker.setName(name);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setPreferredSize(java.awt.Dimension)
	 */
	public ComponentBuilder<DatePicker> setPreferredSize(Dimension dimension) {
		this.datePicker.setPreferredSize(dimension);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.Builder#setTarget(java.lang.Object)
	 */
	public void setTarget(DatePicker target) {
		this.datePicker = target;
	}
}
