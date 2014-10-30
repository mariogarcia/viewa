package org.viewaframework.widget.view.ui;

import javax.swing.table.TableCellRenderer;

import org.viewaframework.view.ViewContainer;

public interface MasterViewCellRenderer extends TableCellRenderer{

	public void setViewContainer(ViewContainer viewContainer);
	public ViewContainer getViewContainer();
}
