package viewa.swing.binding.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.swing.binding.collection.EventList;
import viewa.swing.binding.core.BasicBeanAdapter;
import viewa.swing.binding.core.BeanAdapter;
import viewa.swing.binding.core.ObservableModel;

/**
 * @author Mario Garcia
 *
 * @param <U>
 */
public class TableBindingModel<U> extends AbstractTableModel implements ObservableModel<U> {
	/**
	 * @author Mario Garcia
	 *
	 */
	private class ListAdapterTableModelListener implements ListDataListener{
		private TableBindingModel<U> model;
		private JTable table;
		/**
		 * @param model
		 */
		public ListAdapterTableModelListener(JTable table,TableBindingModel<U> model){
			this.model = model;
			this.table = table;
		}
		/* (non-Javadoc)
		 * @see javax.swing.event.ListDataListener#contentsChanged(javax.swing.event.ListDataEvent)
		 */
		public void contentsChanged(ListDataEvent e) {
			Integer actual = this.table.getSelectedRow();
			model.fireTableRowsUpdated(e.getIndex0(),e.getIndex1());
			model.fireTableDataChanged();
			this.maintainSelection(actual);
		}
		
		/* (non-Javadoc)
		 * @see javax.swing.event.ListDataListener#intervalAdded(javax.swing.event.ListDataEvent)
		 */
		public void intervalAdded(ListDataEvent e) {
			Integer actual = this.table.getSelectedRow();
			model.fireTableRowsInserted(e.getIndex0(),e.getIndex1());
			model.fireTableDataChanged();
			this.maintainSelection(actual);
		}
		
		/* (non-Javadoc)
		 * @see javax.swing.event.ListDataListener#intervalRemoved(javax.swing.event.ListDataEvent)
		 */
		public void intervalRemoved(ListDataEvent e) {
			Integer actual = this.table.getSelectedRow();
			model.fireTableRowsDeleted(e.getIndex0(),e.getIndex1());
			model.fireTableDataChanged();
			this.maintainSelection(actual);
		}
		/**
		 * @param actual
		 */
		public void maintainSelection(Integer actual){
			Integer selectedRow = actual != -1 ? 
					actual : this.table.getModel().getRowCount() > 0 ? 
							0 : null;
			if (selectedRow != null){
				this.table.setRowSelectionInterval(selectedRow,selectedRow);
			}
		}
		
	}
	/**
	 * @author Mario Garcia
	 *
	 */
	private class SelectionListener implements ListSelectionListener{
		private JTable table;
		/**
		 * @param table
		 */
		public SelectionListener(JTable table){
			this.table = table;
		}
		/* (non-Javadoc)
		 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()){				
				Integer selectionIndex = table.getSelectionModel().getMinSelectionIndex();
				if (logger.isDebugEnabled()){
					logger.debug("Selected index is:"+selectionIndex);
				}
				try {
					if (selectionIndex >= 0){
						int realRowIndex = table.convertRowIndexToModel(selectionIndex); 						
						Object o = TableBindingModel.this.getRow(realRowIndex);
						if (logger.isDebugEnabled()){
							logger.debug("Realindex: "+realRowIndex);
							logger.debug("The selected object is: "+o);
							logger.debug("[M:"+realRowIndex+" | V:"+selectionIndex+"] "+
									TableBindingModel.this.getRow(realRowIndex));
						}
						TableBindingModel.this.setSelectedItem(o);
					}
				} catch (Exception e1) {
					logger.error(e1.getMessage());
				} 
			}
		}	
	}
	
	private static final Log logger = LogFactory.getLog(TableBindingModel.class);
	public static final String SELECTED_ITEM = "selectedItem";
	private static final long serialVersionUID = 2327107603858041801L;
	private List<ColumnInfo> columns;
	private EventList<U> modelList;
	private PropertyChangeSupport propertyChangeSupport;
	private U selectedElement;
	private JTable table;
	
	/**
	 * @param elementList
	 * @param visibleProperties
	 */
	public TableBindingModel(JTable table,EventList<U> elementList,List<ColumnInfo> columns) {
		super();
		this.table = table;
		this.modelList = elementList;
		this.columns = columns;
		this.modelList.addListDataListener(new ListAdapterTableModelListener(this.table,this));
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		table.getSelectionModel().addListSelectionListener(new SelectionListener(table));
	}
	
	/**
	 * @param visibleProperties
	 */
	public TableBindingModel(JTable table,List<ColumnInfo> columns) {
		this(table,new EventList<U>(),columns);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener){
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#contains(java.lang.Object)
	 */
	public boolean contains(U element) {
		return this.modelList.contains(element);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#firePropertyChange(java.beans.PropertyChangeEvent)
	 */
	public void firePropertyChange(PropertyChangeEvent evt) {
		this.propertyChangeSupport.firePropertyChange(evt);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class<?> clazz = super.getColumnClass(columnIndex);
		if (!this.modelList.isEmpty()){
			clazz = new BasicBeanAdapter<U>(this.modelList.get(0)).
				getPropertyClass(this.columns.get(columnIndex).getPropertyName());
		}		
		return clazz;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return this.columns.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		return this.columns.get(column).getColumnName();
	}
	
	/**
	 * @param column
	 * @return
	 */
	public String getColumnPropertyName(int column){
		return this.columns.get(column).getPropertyName();
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#getModelList()
	 */
	public EventList<U> getModelList() {
		return modelList;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#getPropertyChangeListeners()
	 */
	public List<PropertyChangeListener> getPropertyChangeListeners() {
		return Arrays.asList(this.propertyChangeSupport.getPropertyChangeListeners());
	}
	
	/**
	 * @param rowIndex
	 * @return
	 */
	public U getRow(int rowIndex){
		return modelList.get(rowIndex);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return this.modelList.size();
	}
	
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#getSelectedElementAdapter()
	 */
	public BeanAdapter<U> getSelectedElementAdapter() {
		return new BasicBeanAdapter<U>(this.selectedElement);
	}

	/**
	 * @return
	 */
	public U getSelectedItem() {
		return selectedElement;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		BeanAdapter<U> adapter = new BasicBeanAdapter<U>(modelList.get(rowIndex));
		return adapter.getValue(
				this.getColumnClass(columnIndex),
				this.getColumnPropertyName(columnIndex));
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener){
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#setModelList(org.viewaframework.binding.collection.EventList)
	 */
	public void setModelList(EventList<U> modelList) {
		this.modelList = modelList;
	}

	/**
	 * @param selectedObject
	 */
	@SuppressWarnings("unchecked")
	public void setSelectedItem(Object anItem) {
		if (this.modelList.contains(anItem)){
			U oldValue = this.selectedElement;
			U newValue = (U) anItem;
			this.selectedElement = newValue;
			this.firePropertyChange(
					new PropertyChangeEvent(
							this,SELECTED_ITEM,oldValue,newValue));		
		}
	}
	
}
