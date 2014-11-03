package org.viewaframework.swing.groovy

import org.viewaframework.swing.*;
import groovy.util.FactoryBuilderSupport

import java.util.Map

import javax.swing.UIManager

import org.viewaframework.swing.util.ResourceLocator

class NiceDatePickerFactory extends AbstractFactory{

	def datePicker
	def constraints
	
	public Object newInstance(FactoryBuilderSupport builder, Object name,
			Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {
			
		def iconPath = "org/viewaframework/swing/datepicker/date.png"
		def defaultImage = ResourceLocator.getImageIcon(DatePicker,iconPath)
		def loadedImage = UIManager.getIcon("JXDatePicker.arrowIcon")
		if (loadedImage != defaultImage){
			UIManager.put("JXDatePicker.arrowIcon",defaultImage)
		}
		datePicker = new DatePicker(name:attributes.id)
		return datePicker;
	}

	@Override
	public boolean onHandleNodeAttributes(FactoryBuilderSupport builder,Object node, Map attributes) {
		return false
	}

	@Override
	public void setParent(FactoryBuilderSupport builder, Object parent,Object child) {
		if (constraints){
			parent.add(child,constraints)
		}
	}
}
