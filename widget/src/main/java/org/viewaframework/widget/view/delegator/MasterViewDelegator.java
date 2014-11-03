package org.viewaframework.widget.view.delegator;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.ViewException;
import org.viewaframework.view.delegator.Delegator;
import org.viewaframework.widget.view.MasterView;
import org.viewaframework.widget.view.ui.MasterViewColumn;

/**
 * 
 * @author Mario Garcia
 *
 */
public class MasterViewDelegator implements Delegator {

	private static final String TABLE_COMPONENT_NAME = "table";
	private static final String POINT = ".";
	private static final String COLUMN = "column";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.viewaframework.view.delegator.Delegator#clean(org.viewaframework.
	 * view.ViewContainer)
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public void clean(ViewContainer viewContainer) throws ViewException {
		MasterView masterView = MasterView.class.cast(viewContainer);
		List<MasterViewColumn> columns = masterView.getColumns();
		JTable table = JTable.class.cast(viewContainer
				.getComponentByName(TABLE_COMPONENT_NAME));
		for (MasterViewColumn column : columns) {
			TableColumnModel columnModel = table.getColumnModel();
			Integer columnIndex = columnModel.getColumnIndex(column.getPropertyName());
			TableColumn tableColumn = columnModel.getColumn(columnIndex);
			tableColumn.setHeaderValue(column.getPropertyName());
			if (column.getRenderer() != null){
				tableColumn.setCellRenderer(column.getRenderer());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.viewaframework.view.delegator.Delegator#inject(org.viewaframework
	 * .view.ViewContainer)
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public void inject(ViewContainer viewContainer) throws ViewException {
		MasterView masterView = MasterView.class.cast(viewContainer);
		List<MasterViewColumn> columns = masterView.getColumns();
		JTable table = JTable.class.
			cast(viewContainer.
					getComponentByName(TABLE_COMPONENT_NAME));
		for (MasterViewColumn column : columns) {			
			Integer width = column.getWidth();
			TableColumnModel columnModel = table.getColumnModel();
			Integer columnIndex = columnModel.getColumnIndex(column.getPropertyName());
			TableColumn tableColumn = columnModel.getColumn(columnIndex);
			String tableColumnName = String.valueOf(tableColumn.getHeaderValue());
		 /* Setting the preferred with */
			tableColumn.setPreferredWidth(width);
		 /* Setting the i18n name */
			tableColumn.setHeaderValue(viewContainer.
					getMessage(COLUMN + POINT + tableColumnName));
			tableColumn.setIdentifier(column.getPropertyName());
			if (column.getRenderer() != null){
				column.getRenderer().setViewContainer(viewContainer);
				tableColumn.setCellRenderer(column.getRenderer());
			}
		}
	}
}
