package viewa.swing.treetable.single.ui;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import viewa.swing.DynamicTreeTable;
import viewa.swing.table.DynamicTableColumn;
import viewa.swing.treetable.single.listener.GroupingListener;
import viewa.swing.treetable.single.listener.OrderListener;
import viewa.swing.util.ResourceLocator;

/**
 * This is the popup installed in a SingleGroupingTable header by default. This pop up allows the user
 * to change the table aggregation on-the-fly depending on the selected column.
 * 
 * @author mgg
 *
 */
public class SingleGroupingPopup<T> extends JPopupMenu{

	private static final String GROUP ="org/viewaframework/swing/treetable/sitemap.png";
	
	public static final String NAME_GROUP_BY_MENU = "gropuByColumn";
	public static final String NAME_ORDER_BY_MENU = "orderByColumn";
	public static final String NAME_ORDER_BY_ITEM = "orderByColumn";
	public static final String NAME_GROUP_BY_ITEM = "groupByColumn";
	
	private static final String ORDER ="org/viewaframework/swing/treetable/table_sort.png";
	private static final long serialVersionUID = 4436818255574960821L;
	
	private JMenu groupByColumn;
	private ActionListener groupListener;
	private JMenu orderbyColumn;
	private ActionListener orderListener;
	private DynamicTreeTable<?> table;
	
	/**
	 * @param table
	 */
	public SingleGroupingPopup(DynamicTreeTable<T> table){
		super();
		this.table = table;
		this.orderListener = new OrderListener<T>(table);
		this.groupListener = new GroupingListener<T>(table);
		this.initComponents();
	}

	/**
	 * 
	 */
	private void initComponents() {
	 /* Building Group and Order menues */
		this.orderbyColumn = new JMenu("Order by");
		this.orderbyColumn.setName(NAME_ORDER_BY_MENU);
		this.orderbyColumn.setIcon(ResourceLocator.getImageIcon(SingleGroupingPopup.class,ORDER));
		this.groupByColumn = new JMenu("Group By");
		this.groupByColumn.setName(NAME_GROUP_BY_MENU);
		this.groupByColumn.setIcon(ResourceLocator.getImageIcon(SingleGroupingPopup.class,GROUP));
	 /* Looping over the table column info to add the check box items */
		List<DynamicTableColumn> columns = table.getTreeTableColumnInfoList();
		ButtonGroup groupingGroup = new ButtonGroup();
		ButtonGroup orderGroup = new ButtonGroup();		
		for (DynamicTableColumn c : columns){
			JCheckBoxMenuItem orderItem = new SingleGroupingCheckboxItem(c);
			JCheckBoxMenuItem groupItem = new SingleGroupingCheckboxItem(c);
			orderItem.setName(NAME_ORDER_BY_ITEM);
			groupItem.setName(NAME_GROUP_BY_ITEM);	
			orderItem.addActionListener(orderListener);
			groupItem.addActionListener(groupListener);
		 /* As long as the model can aggregate only by one column at a time multiple
		  * selection is not allowed */
			groupingGroup.add(groupItem);
			orderGroup.add(orderItem);
		 /* Finally we add the items */
			this.groupByColumn.add(groupItem);
			this.orderbyColumn.add(orderItem);
		}
	 /* Adding both menus to the popup */
		this.add(this.orderbyColumn);
		this.addSeparator();
		this.add(this.groupByColumn);
	}
}
