package org.viewaframework.swing.treetable.single.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.tree.TreeNode;

import org.viewaframework.swing.DynamicTreeTable;
import org.viewaframework.swing.table.DynamicTableColumn;
import org.viewaframework.swing.treetable.single.DynamicTreeTableModel;
import org.viewaframework.swing.treetable.single.ui.SingleGroupingCheckboxItem;
import org.viewaframework.swing.treetable.single.util.SwingGrouper;

/**
 * When a grouping item has been clicked this listener changes the whole
 * table structure to fit the selected aggregation property.
 * 
 * @author mgg
 *
 * @param <T>
 */
public class GroupingListener<T> implements ActionListener{

	private DynamicTreeTable<T> table;
	
	/**
	 * @param table
	 */
	public GroupingListener(DynamicTreeTable<T> table){
		this.table = table;		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (SingleGroupingCheckboxItem.class.isInstance(e.getSource())){
			SingleGroupingCheckboxItem item = SingleGroupingCheckboxItem.class.cast(e.getSource());
			List<DynamicTableColumn> filteredColumns = 
				getFilteredColumns(table.getTreeTableColumnInfoList(),item.getTreeTableColumnInfo());
			List<T> elements = table.getNotAdaptedModel().getRawObjects();
			TreeNode node = new SwingGrouper().
				groupElementsByPropertyName(elements, filteredColumns);			
			
			table.getNotAdaptedModel().setRoot(node);
			table.setTreeTableModel(new DynamicTreeTableModel<T>(elements,filteredColumns));			
			table.repaint();
		}	
	}
	
	/**
	 * This method just changes the previous grouping property to set it to false and the selected to
	 * true.
	 * 
	 * @param all
	 * @param grouped
	 * @return
	 */
	private List<DynamicTableColumn> getFilteredColumns(
			List<DynamicTableColumn> all,DynamicTableColumn grouped){
		for (DynamicTableColumn info : all){
			info.setGrouping(info.equals(grouped));
		}		
		return all;
	}
}
