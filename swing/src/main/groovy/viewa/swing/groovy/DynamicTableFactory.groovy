package viewa.swing.groovy

import viewa.swing.DynamicTable
import viewa.swing.table.DynamicTableModel;

class DynamicTableFactory extends AbstractFactory{

	def dynamicTable
	def dynamicTableModel
	def dynamicTableColumns = []
	def masterPanel
	def constraints
	
	public Object newInstance(FactoryBuilderSupport builder, Object name,
			Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {
		dynamicTable = new DynamicTable()
		dynamicTable.name = attributes.id
		constraints = attributes.constraints
		return dynamicTable;
	}

	@Override
	public boolean onHandleNodeAttributes(FactoryBuilderSupport builder,
			Object node, Map attributes) {
		return false
	}

	@Override
	public void onNodeCompleted(FactoryBuilderSupport builder, Object parent,Object node) {
		dynamicTableModel = new DynamicTableModel(dynamicTableColumns)
		dynamicTable.model = dynamicTableModel		
	}

	@Override
	public void setParent(FactoryBuilderSupport builder, Object parent,Object child) {
		if (constraints){
			parent.add(child,constraints)
		}
	}

	@Override
	public void setChild(FactoryBuilderSupport builder, Object parent,Object child) {
		dynamicTableColumns << child
	}
}
