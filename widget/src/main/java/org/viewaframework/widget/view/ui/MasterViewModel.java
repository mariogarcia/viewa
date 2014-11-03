package org.viewaframework.widget.view.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Mario Garcia
 *
 * @param <T>
 */
public class MasterViewModel<T> extends AbstractTableModel 
	implements ListSelectionListener{

	private static final Log logger = LogFactory.getLog(MasterViewModel.class);
	private static final long serialVersionUID = 1L;
	private List<MasterViewColumn> columns;
	private T selectedObject;
	private List<T> selectedObjects;
	private List<T> sourceList;
	private JTable table;

	/**
	 * @param columns
	 * @param table
	 */
	public MasterViewModel(List<MasterViewColumn> columns,JTable table){
		super();
		this.sourceList = new ArrayList<T>();
		this.columns = columns;
		this.table = table;
		this.selectedObjects = new ArrayList<T>();
		table.getSelectionModel().
			addListSelectionListener(this);
	}
	/**
	 * @param srcList
	 */
	public synchronized void addAll(Collection<T> srcList){
		this.sourceList.clear();
		this.sourceList.addAll(srcList);
		this.fireTableDataChanged();
		if (this.getRowCount() > 0){
			this.table.setRowSelectionInterval(0,0);
		}
	}

	/**
	 * @param e
	 */
	public synchronized void addRow(T e){
		this.sourceList.add(e);
		this.fireTableDataChanged();
		int modelIndex = this.sourceList.indexOf(e);
		int visualIndex = this.table.convertRowIndexToView(modelIndex);		
		this.table.setRowSelectionInterval(visualIndex,visualIndex);
	}

	/**
	 * 
	 */
	public synchronized void clear(){
		this.sourceList.clear();
		this.fireTableDataChanged();
	}	
	
	/**
	 * @param e
	 * @return
	 */
	public boolean contains(T e){
		return this.sourceList.contains(e);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Comparable.class;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columns.size();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		return columns.get(column).getPropertyName();
	}
		
	/**
	 * @param rowIndex
	 * @return
	 */
	public T getRow(Integer rowIndex){
		return this.sourceList.get(rowIndex);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return sourceList!= null ? sourceList.size() : 0;
	}
	
	/**
	 * @return
	 */
	public T getSelectedObject() {
		return selectedObject;
	}
	
	/**
	 * @return
	 */
	public List<T> getSelectedObjects() {
		return selectedObjects;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		T currentObject = this.sourceList.get(rowIndex);
		Object valueObject = null;
		try {
			valueObject = new PropertyUtilsBean().
					getProperty(
						currentObject,
						columns.get(columnIndex).getPropertyName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueObject;
	}
	
	/**
	 * @param e
	 */
	public synchronized void removeRow(T e){
		int modelIndex = this.sourceList.indexOf(e);
		this.sourceList.remove(e);
		int visualIndex = this.table.convertRowIndexToView(modelIndex);
		this.fireTableRowsDeleted(visualIndex, visualIndex);		
		this.fireTableDataChanged();
	}

	/**
	 * @param e
	 */
	public void setSelectedObject(T e) {
		this.selectedObject = e;
	}

	/**
	 * @param selectedObjects
	 */
	public void setSelectedObjects(List<T> selectedObjects) {
		this.selectedObjects = selectedObjects;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()){
			int minIndex = table.getSelectionModel().getMinSelectionIndex();
			int maxIndex = table.getSelectionModel().getMaxSelectionIndex();
			try {
				if (minIndex >= 0){
					this.selectedObjects.clear();
					for (int i = minIndex ; i<=maxIndex;i++){
						int realIndex = table.convertRowIndexToModel(i);
						T value = getRow(realIndex);
                        if (table.getSelectionModel().isSelectedIndex(i)) {
                            this.selectedObjects.add(value);
                         /* Always the first occurrence is the result of calling getSelectedObject() */
                            if (i == minIndex){
                                this.selectedObject = value;
                            }
                            if (logger.isDebugEnabled()){
                                logger.debug(
                                    new StringBuilder().
                                        append("[M:").append(realIndex).
                                        append(" | V:").append(minIndex ++).
                                        append("] ").append(value).toString());
                            }
                        }
					}
				}
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}
		}
	}
}
