package viewa.swing.treetable.single;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableNode;
import viewa.swing.binding.collection.EventList;
import viewa.swing.table.DynamicTableColumn;
import viewa.swing.treetable.single.util.SwingGrouper;

/**
 * This TreeTableModel only can group by one propery at a time.
 * 
 * @author mgg
 *
 * @param <T>
 */
public class DynamicTreeTableModel<T>  extends AbstractTreeTableModel implements ListDataListener{

	private EventList<T> rawObjects = new EventList<T>();
	private TreeNode root;
	private EventList<DynamicTableColumn> treeTableColumnInfoList = new EventList<DynamicTableColumn>();

	/**
	 * To create a new SingleGroupingModel you have to provide the list of elements and 
	 * the information of all columns.
	 * 
	 * @param rawObjects The list of elements shown by the table
	 * @param columns The column information.
	 */
	public DynamicTreeTableModel(List<T> rawObjects,List<? extends DynamicTableColumn> columns){
		this.treeTableColumnInfoList.addAll(columns);		
		this.rawObjects.addAll(rawObjects);
		this.rawObjects.addListDataListener(this);
		this.root = new SwingGrouper().groupElementsByPropertyName(rawObjects, this.treeTableColumnInfoList);
	}	
	
	/**
	 * @param columns
	 */
	public DynamicTreeTableModel(List<? extends DynamicTableColumn> columns){
		super();
		this.rawObjects.addListDataListener(this);
		this.treeTableColumnInfoList.addAll(columns);		
	}

	  /* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	public Object getChild(Object parent, int index) {
		return DefaultMutableTreeTableNode.class.cast(parent).getChildAt(index);
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	public int getChildCount(Object parent) {
		return DefaultMutableTreeTableNode.class.cast(parent).getChildCount();
	}

	/* (non-Javadoc)
	 * @see org.jdesktop.swingx.treetable.TreeTableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return this.treeTableColumnInfoList.size();
	}
	
	/**
	 * @param column
	 * @return
	 */
	public String getColumnName(int column) {
		Collections.sort(this.treeTableColumnInfoList);		
		return this.treeTableColumnInfoList.get(column).getTitle();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object, java.lang.Object)
	 */
	public int getIndexOfChild(Object parent, Object child) {
		DefaultMutableTreeTableNode parentNode = DefaultMutableTreeTableNode.class.cast(parent);
		DefaultMutableTreeTableNode childNode = DefaultMutableTreeTableNode.class.cast(child);
		Object childUserObject = childNode.getUserObject();
		int result = -1;
		boolean isChild = false;
		for (int i=0;i<parentNode.getChildCount() && !isChild;i++){
			Object unknown = parentNode.getChildAt(i).getUserObject();
			isChild = unknown != null && unknown.equals(childUserObject);
			if (isChild){
				result = i;
			}
		}
		return result;
	}

	/**
     * Gets the path from the root to the specified node.
     * 
     * @param aNode
     *            the node to query
     * @return an array of {@code TreeTableNode}s, where
     *         {@code arr[0].equals(getRoot())} and
     *         {@code arr[arr.length - 1].equals(aNode)}, or an empty array if
     *         the node is not found.
     * @throws NullPointerException
     *             if {@code aNode} is {@code null}
     */
    public TreeTableNode[] getPathToRoot(TreeTableNode aNode) {
        List<TreeTableNode> path = new ArrayList<TreeTableNode>();
        TreeTableNode node = aNode;

        while (node != root) {
            path.add(0, node);

            node = node.getParent();
        }

        if (node == root) {
            path.add(0, node);
        }

        return path.toArray(new TreeTableNode[0]);
    }

	/**
	 * @return
	 */
	public EventList<T> getRawObjects() {
		return rawObjects;
	}
	
	/* (non-Javadoc)
	 * @see org.jdesktop.swingx.treetable.AbstractTreeTableModel#getRoot()
	 */
	@Override
	public Object getRoot() {
		return root;
	}
	
	/**
	 * @return
	 */
	public EventList<DynamicTableColumn> getTreeTableColumnInfoList() {
		return treeTableColumnInfoList;
	}

	/* (non-Javadoc)
	 * @see org.jdesktop.swingx.treetable.TreeTableModel#getValueAt(java.lang.Object, int)
	 */
	public Object getValueAt(Object node, int column) {
		Collections.sort(this.treeTableColumnInfoList);
		DynamicTableColumn columnInfo =  this.treeTableColumnInfoList.get(column);
		DefaultMutableTreeTableNode mutableNode = DefaultMutableTreeTableNode.class.cast(node);
		Object userObject = mutableNode.getUserObject();
		String propertyName = columnInfo.getPropertyName();
		Object value = null;
		try{
			if (mutableNode.isLeaf() || (!mutableNode.isLeaf() && column == 0)){
				if (column == 0 && !mutableNode.isLeaf()){
					boolean found = false;
					for (Iterator<DynamicTableColumn> it = this.treeTableColumnInfoList.iterator();it.hasNext() && !found;){
						DynamicTableColumn possibleGroupingColumn = it.next();
						if (possibleGroupingColumn.isGrouping()){
							propertyName = possibleGroupingColumn.getPropertyName();
							found = true;
						}
					}
				}
				if (userObject != null){
					value = new PropertyUtilsBean().getProperty(userObject,propertyName);
				}
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return value;
	}

	/**
     * Invoked this to insert newChild at location index in parents children.
     * This will then message nodesWereInserted to create the appropriate event.
     * This is the preferred way to add children as it will create the
     * appropriate event.
     */
    public void insertNodeInto(MutableTreeTableNode newChild,
            MutableTreeTableNode parent, int index) {
        parent.insert(newChild, index);

        modelSupport.fireChildAdded(new TreePath(getPathToRoot(parent)), index,
                newChild);
    }

	/* (non-Javadoc)
	 * @see org.jdesktop.swingx.treetable.AbstractTreeTableModel#isCellEditable(java.lang.Object, int)
	 */
	@Override
    public boolean isCellEditable(Object node, int column) {
		return TreeTableNode.class.cast(node).isEditable(column);
    }

	/* (non-Javadoc)
     * @see org.jdesktop.swingx.treetable.AbstractTreeTableModel#isLeaf(java.lang.Object)
     */
    @Override
    public boolean isLeaf(Object node) {
    	return ((TreeTableNode) node).isLeaf();
    }

	/**
     * Message this to remove node from its parent. This will message
     * nodesWereRemoved to create the appropriate event. This is the preferred
     * way to remove a node as it handles the event creation for you.
     */
    public void removeNodeFromParent(MutableTreeTableNode node) {
        MutableTreeTableNode parent = (MutableTreeTableNode) node.getParent();

        if (parent == null) {
            throw new IllegalArgumentException("node does not have a parent.");
        }

        int index = parent.getIndex(node);
        node.removeFromParent();

        modelSupport.fireChildRemoved(new TreePath(getPathToRoot(parent)),
                index, node);
    }
	
	  /**
	 * @param rawObjects
	 */
	public void setRawObjects(EventList<T> rawObjects) {
		this.rawObjects = rawObjects;
	}
    
    /**
	 * @param root
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
		modelSupport.fireNewRoot();
	}
    
	/**
	 * @param treeTableColumnInfoList
	 */
	public void setTreeTableColumnInfoList(
			EventList<DynamicTableColumn> treeTableColumnInfoList) {
		this.treeTableColumnInfoList = treeTableColumnInfoList;
	}
	
    /**
     * Sets the user object for a node. Client code must use this method, so
     * that the model can notify listeners that a change has occurred.
     * <p>
     * This method is a convenient cover for
     * {@link #valueForPathChanged(TreePath, Object)}.
     * 
     * @param node
     *            the node to modify
     * @param userObject
     *            the new user object to set
     * @throws NullPointerException
     *             if {@code node} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code node} is not a node managed by this model
     */
    public void setUserObject(TreeTableNode node, Object userObject) {
        valueForPathChanged(new TreePath(getPathToRoot(node)), userObject);
    }

    /* (non-Javadoc)
	 * @see org.jdesktop.swingx.treetable.AbstractTreeTableModel#setValueAt(java.lang.Object, java.lang.Object, int)
	 */
	@Override
	public void setValueAt(Object value, Object node, int column) {		
		Collections.sort(this.treeTableColumnInfoList);
		DynamicTableColumn info = this.treeTableColumnInfoList.get(column);
		DefaultMutableTreeTableNode realNode = DefaultMutableTreeTableNode.class.cast(node);		
		try{
			PropertyUtilsBean beanUtil = new PropertyUtilsBean();
			Object userObject = realNode.getUserObject();
			String propertyName = info.getPropertyName();			
			Class<?> clazz = beanUtil.getPropertyDescriptor(userObject, propertyName).getPropertyType();
			beanUtil.getPropertyDescriptor(userObject, propertyName);
			beanUtil.setProperty(userObject,propertyName,ConvertUtils.convert(value!=null ? value.toString() : null, clazz));
		} catch (Exception ex){
			ex.printStackTrace();
		}		
		modelSupport.firePathChanged(new TreePath(getPathToRoot(realNode)));
	}
    
    /* (non-Javadoc)
     * @see org.jdesktop.swingx.treetable.AbstractTreeTableModel#valueForPathChanged(javax.swing.tree.TreePath, java.lang.Object)
     */
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        if (path.getPathComponent(0) != root) {
            throw new IllegalArgumentException("invalid path");
        }
        
        TreeTableNode node = (TreeTableNode) path.getLastPathComponent();
        node.setUserObject(newValue);
        
        modelSupport.firePathChanged(path);
    }

	/* (non-Javadoc)
	 * @see javax.swing.event.ListDataListener#intervalAdded(javax.swing.event.ListDataEvent)
	 */
	public void intervalAdded(ListDataEvent e) {		
		this.setRoot(new SwingGrouper().groupElementsByPropertyName(getRawObjects(), getTreeTableColumnInfoList()));
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListDataListener#intervalRemoved(javax.swing.event.ListDataEvent)
	 */
	public void intervalRemoved(ListDataEvent e) {
		this.setRoot(new SwingGrouper().groupElementsByPropertyName(getRawObjects(), getTreeTableColumnInfoList()));
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListDataListener#contentsChanged(javax.swing.event.ListDataEvent)
	 */
	public void contentsChanged(ListDataEvent e) {
		this.setRoot(new SwingGrouper().groupElementsByPropertyName(getRawObjects(), getTreeTableColumnInfoList()));
	}
}
