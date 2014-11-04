package viewa.swing.treetable.single.util;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.tree.TreeNode;

import org.apache.commons.beanutils.BeanComparator;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import viewa.swing.table.DynamicTableColumn;

/**
 * @author mgg
 *
 */
public class SwingGrouper {

	/**
	 * @param <T>
	 * @param rawObjects
	 * @param columns
	 * @return
	 */
	public <T> TreeNode groupElementsByPropertyName(List<T> rawObjects,List<DynamicTableColumn> columns){
		boolean isGroupByProperty = false;
		String propertyName = "";		
		for (ListIterator<DynamicTableColumn> it = columns.listIterator();
			it.hasNext() && !isGroupByProperty;){
			DynamicTableColumn column = it.next();
				if (column.isGrouping()){
					propertyName = column.getPropertyName();
					isGroupByProperty = true;
				}
		}		
		DefaultMutableTreeTableNode root = new DefaultMutableTreeTableNode();
		@SuppressWarnings("unchecked")
		Comparator<T> comparator = new BeanComparator(propertyName);
		TreeMap<T,Set<T>> companyMap = new TreeMap<T,Set<T>>(comparator);
		for (ListIterator<T> ci = rawObjects.listIterator();ci.hasNext();){
			T c1 = ci.next();
			for (ListIterator<T> ic = rawObjects.listIterator(rawObjects.size());ic.hasPrevious();){
				T c2 = ic.previous();
				int result = comparator.compare(c1, c2);
				if (result == 0){
					Set<T> companiesSameGroup = companyMap.get(c1);
					if (companiesSameGroup == null){
						companiesSameGroup = new HashSet<T>();
						companyMap.put(c1, companiesSameGroup);
					}
					companiesSameGroup.add(c1);
				}
			}
		}		
		for (T key : companyMap.keySet()){
			DefaultMutableTreeTableNode aggregation = new DefaultMutableTreeTableNode(key);
			aggregation.setParent(root);
			aggregation.setAllowsChildren(true);
			Set<T> children = companyMap.get(key);
			if (children != null){
				for (T child : children){
					DefaultMutableTreeTableNode childNode = new DefaultMutableTreeTableNode(child);
					childNode.setParent(aggregation);
					childNode.setAllowsChildren(false);
				}
			}
		}
		return root;
	}	
}
