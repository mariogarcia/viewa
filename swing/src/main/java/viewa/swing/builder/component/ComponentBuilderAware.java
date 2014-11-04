package viewa.swing.builder.component;

import javax.swing.JComponent;


/**
 * @author mgg
 *
 */
public interface ComponentBuilderAware {

	/**
	 * @return
	 */
	public ButtonBuilder button();
	/**
	 * @param constraints
	 * @return
	 */
	public ButtonBuilder button(Object constraints);
	/**
	 * @param <T>
	 * @param component
	 * @param clazz
	 * @param constraints
	 * @return
	 */
	public <T extends JComponent> JComponentBuilder<T> component(T component,Class<T> clazz,Object constraints);
	/**
	 * @return
	 */
	public DatePickerBuilder datePicker();
	/**
	 * @param constraints
	 * @return
	 */
	public DatePickerBuilder datePicker(Object constraints);
	/**
	 * @return
	 */
	public LabelBuilder label();
	/**
	 * @param constraints
	 * @return
	 */
	public LabelBuilder label(Object constraints);
	/**
	 * @return
	 */
	public SpinnerBuilder spinner();
	/**
	 * @param constraints
	 * @return
	 */
	public SpinnerBuilder spinner(Object constraints);
	/**
	 * @return
	 */
	public TextFieldBuilder text();
	/**
	 * @param constraints
	 * @return
	 */
	public TextFieldBuilder text(Object constraints);
	/**
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public <T> DynamicTableBuilder<T> dynamicTable(Class<T> clazz);
	/**
	 * @param <T>
	 * @param clazz
	 * @param constraints
	 * @return
	 */
	public <T> DynamicTableBuilder<T> dynamicTable(Class<T> clazz,Object constraints);
}
