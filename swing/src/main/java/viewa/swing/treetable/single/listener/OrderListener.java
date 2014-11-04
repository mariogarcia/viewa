package viewa.swing.treetable.single.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.tree.TreePath;

import org.apache.commons.beanutils.BeanComparator;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import viewa.swing.DynamicTreeTable;
import viewa.swing.treetable.single.ui.SingleGroupingCheckboxItem;

public class OrderListener<T> implements ActionListener{

	private DynamicTreeTable<T> table;
	
	public OrderListener(DynamicTreeTable<T> table){
		this.table = table;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (SingleGroupingCheckboxItem.class.isInstance(e.getSource())){
			SingleGroupingCheckboxItem item = SingleGroupingCheckboxItem.class.cast(e.getSource());
			DefaultMutableTreeTableNode root = 
				DefaultMutableTreeTableNode.class.cast(table.getNotAdaptedModel().getRoot());
			orderBy(root, item.getTreeTableColumnInfo().getPropertyName());
			
			
		}				
	}
	
	/**
	 * @param root
	 * @param property
	 */
	public void orderBy(DefaultMutableTreeTableNode root,final String property){
		List<DefaultMutableTreeTableNode> children = new ArrayList<DefaultMutableTreeTableNode>();
		List<TreePath> expandedAggregations = new ArrayList<TreePath>();
		for (int i=0;i<root.getChildCount();i++){
			DefaultMutableTreeTableNode aggregation = DefaultMutableTreeTableNode.class.cast(root.getChildAt(i));
			TreePath treePath = new TreePath(table.getNotAdaptedModel().getPathToRoot(aggregation));
			boolean expanded = table.isExpanded(table.getRowForPath(treePath));
			if (expanded){
				expandedAggregations.add(treePath);
			}
			children.clear();
			for (int j=0;j<aggregation.getChildCount();j++){
				DefaultMutableTreeTableNode leaf = DefaultMutableTreeTableNode.class.cast(aggregation.getChildAt(j));
				children.add(leaf);				
			}
			for (DefaultMutableTreeTableNode n2r : children){
				table.getNotAdaptedModel().removeNodeFromParent(n2r);
			}
			Collections.sort(children,Collections.reverseOrder(new Comparator<DefaultMutableTreeTableNode>(){
				public int compare(DefaultMutableTreeTableNode o1,DefaultMutableTreeTableNode o2) {
					Object userObject1 = o1.getUserObject();
					Object userObject2 = o2.getUserObject();
					return new BeanComparator(property).compare(userObject1, userObject2);
				}			
			}));
			for (DefaultMutableTreeTableNode c : children){
				table.getNotAdaptedModel().insertNodeInto(c, aggregation, 0);
			}	
		}
		table.getNotAdaptedModel().setRoot(root);
		for (TreePath exp : expandedAggregations){
			table.expandPath(exp);
		}
	}
}
