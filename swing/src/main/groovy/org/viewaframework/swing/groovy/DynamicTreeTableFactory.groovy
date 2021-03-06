package org.viewaframework.swing.groovy

import org.viewaframework.swing.*;
import groovy.util.FactoryBuilderSupport

import java.util.Map

import org.viewaframework.swing.treetable.single.DynamicTreeTableModel

class DynamicTreeTableFactory extends AbstractFactory{
	
	def dynamicTable
	def dynamicTableModel
	def dynamicTableColumns = []
	def masterPanel
	def constraints
	
	public Object newInstance(FactoryBuilderSupport builder, Object name,
			Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {
		dynamicTable = new DynamicTreeTable()
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
		dynamicTableModel = new DynamicTreeTableModel(dynamicTableColumns)
		dynamicTable.treeTableModel = dynamicTableModel
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
