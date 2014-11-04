package viewa.swing.builder.component;

import java.awt.Dimension;
import java.awt.Font;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import viewa.swing.DynamicTable;
import viewa.swing.builder.SwingBuilder;
import viewa.swing.table.DynamicTableColumn;
import viewa.swing.table.DynamicTableModel;

public class DynamicTableBuilder<T> extends ComponentBuilderAbstract<DynamicTable<T>>{

	private DynamicTable<T> target = new DynamicTable<T>();
	/**
	 * @param swBuilder
	 */
	public DynamicTableBuilder(SwingBuilder swBuilder,Class<T> clazz){
		super(swBuilder);
		List<DynamicTableColumn> columns = 
			new ArrayList<DynamicTableColumn>();
		for (Field field : clazz.getDeclaredFields()){
			columns.add(new DynamicTableColumn(field.getName(),0,100));
		}
		DynamicTableModel<T> model = new DynamicTableModel<T>(columns);
		target = new DynamicTable<T>(model);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.Builder#getTarget()
	 */
	public DynamicTable<T> getTarget() {
		return this.target;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.Builder#getType()
	 */
	@SuppressWarnings("unchecked")
	public Class<DynamicTable<T>> getType() {
		return (Class<DynamicTable<T>>) this.target.getClass();
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setEnabled(boolean)
	 */
	public DynamicTableBuilder<T> setEnabled(boolean enabled) {
		this.target.setEnabled(enabled);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setFont(java.awt.Font)
	 */
	public DynamicTableBuilder<T> setFont(Font font) {
		this.getTarget().setFont(font);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setName(java.lang.String)
	 */
	public DynamicTableBuilder<T> setName(String name) {
		this.getTarget().setName(name);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public DynamicTableBuilder<T> rows(List<T> beans){
		DynamicTableModel<T> model =  (DynamicTableModel<T>)this.getTarget().getModel();
		for (T bean : beans){
			model.addRow(bean);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.component.ComponentBuilder#setPreferredSize(java.awt.Dimension)
	 */
	public DynamicTableBuilder<T> setPreferredSize(
			Dimension dimension) {
		this.target.setPreferredSize(dimension);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.swing.builder.Builder#setTarget(java.lang.Object)
	 */
	public void setTarget(DynamicTable<T> target) {
		this.target = target;
	}
}
