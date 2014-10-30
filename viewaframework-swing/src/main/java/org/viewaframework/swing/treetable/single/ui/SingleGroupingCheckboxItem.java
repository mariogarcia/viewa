package org.viewaframework.swing.treetable.single.ui;

import javax.swing.JCheckBoxMenuItem;

import org.viewaframework.swing.table.DynamicTableColumn;

/**
 * This check box holds the table column information. This way the listener will be 
 * able to know what was the column selected and what to do then. 
 * 
 * @author mgg
 *
 */
public class SingleGroupingCheckboxItem extends JCheckBoxMenuItem{

	private static final long serialVersionUID = 7647862250083072285L;
	private DynamicTableColumn treeTableColumnInfo;
	
	/**
	 * The information hold by the check box
	 * 
	 * @param info
	 */
	public SingleGroupingCheckboxItem(DynamicTableColumn info){
		this.treeTableColumnInfo = info;
		this.setText(info.getTitle());
		this.setName("treeTableColumnInfo." + info.getPropertyName());
	}

	/**
	 * @return
	 */
	public DynamicTableColumn getTreeTableColumnInfo() {
		return treeTableColumnInfo;
	}

	/**
	 * @param treeTableColumnInfo
	 */
	public void setTreeTableColumnInfo(DynamicTableColumn treeTableColumnInfo) {
		this.treeTableColumnInfo = treeTableColumnInfo;
	}
}
