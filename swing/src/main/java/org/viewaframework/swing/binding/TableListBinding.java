package org.viewaframework.swing.binding;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JTable;

import org.viewaframework.swing.binding.collection.EventList;
import org.viewaframework.swing.binding.core.AbstractListBinding;
import org.viewaframework.swing.binding.table.ColumnInfo;
import org.viewaframework.swing.binding.table.TableBindingModel;

/**
 * @author Mario Garcia
 *
 * @param <TS>
 */
public class TableListBinding<TS> extends AbstractListBinding<JTable, TS> {

	private List<ColumnInfo> tableColumns;
	private TableBindingModel<TS> tableModel;
	private JTable table;
	/**
	 * @param source
	 * @param list
	 * @param tableColumns
	 */
	public TableListBinding(JTable source, EventList<TS> list,List<ColumnInfo> tableColumns) {
		super(source, list);
		this.tableColumns = tableColumns;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#bind()
	 */
	public void bind() {
		this.tableModel = new TableBindingModel<TS>(this.getSource().getSource(),this.getTarget(),this.tableColumns);	
		this.table = this.getSource().getSource();
		this.table.setModel(this.tableModel);
		Collections.sort(tableColumns,new Comparator<ColumnInfo>() {
			public int compare(ColumnInfo o1, ColumnInfo o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});
		for (int i=0;i<this.tableColumns.size();i++){
			this.table.getColumnModel().getColumn(i).setPreferredWidth(tableColumns.get(i).getPreferredWith());
		}
		
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#unbind()
	 */
	public void unbind() {}
	
}
