package org.viewaframework.swing.groovy


import groovy.util.FactoryBuilderSupport
import java.util.Map
import org.viewaframework.swing.table.DynamicTableColumn

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
