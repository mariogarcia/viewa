package viewa.swing.groovy

import viewa.swing.table.DynamicTableColumn

class DynamicTableColumnFactory extends AbstractFactory{

	public Object newInstance(FactoryBuilderSupport builder, Object name,
			Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {
				
		def order = builder.context.get("dynamicTableColumnOrder",0)
			attributes.order = order
		def column = new DynamicTableColumn(attributes)	
			builder.context.dynamicTableColumnOrder = order + 1
		return column
	}
}
