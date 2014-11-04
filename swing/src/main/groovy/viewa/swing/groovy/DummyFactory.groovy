package viewa.swing.groovy

import groovy.util.FactoryBuilderSupport

import java.util.Map

class DummyFactory extends AbstractFactory{

	Class clazz
	
	public DummyFactory(Class clazz){
			this.clazz = clazz
	}
	
	@Override
	public Object newInstance(FactoryBuilderSupport arg0, Object arg1,
			Object arg2, Map arg3) throws InstantiationException,
			IllegalAccessException {
		
		return clazz.newInstance();
	}
}
