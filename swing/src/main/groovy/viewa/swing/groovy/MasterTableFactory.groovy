package viewa.swing.groovy

import viewa.swing.DynamicTable
import viewa.swing.MasterViewPanel
import viewa.swing.pagination.PaginationListener
import viewa.swing.table.DynamicTableColumn
import viewa.swing.table.DynamicTableModel

class MasterTableFactory extends AbstractFactory{
	
	def dynamicTable
	def dynamicTableModel
	def dynamicTableColumns
	def masterPanel
	def masterPanelPaginationListener
	def constraints
	
	public Object newInstance(FactoryBuilderSupport builder, Object name,
			Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {
				
		masterPanel = new MasterViewPanel()
		dynamicTableColumns = []
		masterPanel.name = attributes.id
		masterPanel.title = attributes.title
		masterPanel.paginationPanel.enabled = attributes.pagination
		constraints = attributes.constraints
		masterPanel
	}

	@Override
	public void onNodeCompleted(FactoryBuilderSupport builder, Object parent,Object node) {
		dynamicTableModel = new DynamicTableModel(dynamicTableColumns)
		dynamicTable = new DynamicTable(dynamicTableModel)
		masterPanel.table = dynamicTable
		
		def paginationListener = (masterPanelPaginationListener.closure) as PaginationListener
		masterPanel.paginationPanel.addPaginationListener(paginationListener)
	}
	
	@Override
	public void setParent(FactoryBuilderSupport builder, Object parent,Object child) {
		if (constraints){
			parent.add(masterPanel,constraints)
		}
	}

	@Override
	public void setChild(FactoryBuilderSupport builder, Object parent,
			Object child) {
		if (child instanceof DynamicTableColumn){
			dynamicTableColumns << child
		} else if (child instanceof MasterPaginationControllerFactory.PaginationListenerWrapper){
			masterPanelPaginationListener = child
		}
	}

	@Override
	public boolean onHandleNodeAttributes(FactoryBuilderSupport builder,Object node, Map attributes) {
		return false;
	}		
}
