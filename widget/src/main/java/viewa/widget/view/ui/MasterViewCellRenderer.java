package viewa.widget.view.ui;

import javax.swing.table.TableCellRenderer;

import viewa.view.ViewContainer;

public interface MasterViewCellRenderer extends TableCellRenderer{

	public void setViewContainer(ViewContainer viewContainer);
	public ViewContainer getViewContainer();
}
