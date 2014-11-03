package org.viewaframework.model;

import java.util.Map;

/**
 * @author Mario Garcia
 * @since 1.0
 *
 */
public class AbstractViewModel implements ViewModel
{
	private Map<String,ViewModelConverter<?,?>> converterMap;
	private ViewModelStrategy 					defaultStrategy;
	private Class<?> 							modelClass;
	private Map<String,ViewModelStrategy> 		propertyStrategyMap;
	
	/* (non-Javadoc)
	 * @see org.viewaframework.model.ViewModel#addModelConverter(java.lang.String, org.viewaframework.model.ViewModelConverter)
	 */
	public void addModelConverter(String property,ViewModelConverter<?, ?> converter) {
		this.converterMap.put(property, converter);
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.model.ViewModel#addModelStrategy(java.lang.String, org.viewaframework.model.ViewModelStrategy)
	 */
	public void addModelStrategy(String property, ViewModelStrategy strategy) {
		this.propertyStrategyMap.put(property, strategy);
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.model.ViewModel#addModelStrategy(org.viewaframework.model.ViewModelStrategy)
	 */
	public void addModelStrategy(ViewModelStrategy strategy) {
		this.defaultStrategy = strategy;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.model.ViewModel#getModelClass()
	 */
	public Class<?> getModelClass() {
		return this.modelClass;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.model.ViewModel#getModelConverterMap()
	 */
	public Map<String, ViewModelConverter<?, ?>> getModelConverterMap() {
		return this.converterMap;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.model.ViewModel#getModelStrategy()
	 */
	public ViewModelStrategy getModelStrategy() {
		return this.defaultStrategy;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.model.ViewModel#getModelStrategy(java.lang.String)
	 */
	public ViewModelStrategy getModelStrategy(String property) {
		return this.propertyStrategyMap.get(property);
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.model.ViewModel#getModelStrategyMap()
	 */
	public Map<String, ViewModelStrategy> getModelStrategyMap() {
		return this.propertyStrategyMap;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.model.ViewModel#setModelClass(java.lang.Class)
	 */
	public void setModelClass(Class<?> classType) {
		this.modelClass = classType;
	}
}
