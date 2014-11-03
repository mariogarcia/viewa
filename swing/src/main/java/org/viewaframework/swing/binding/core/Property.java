package org.viewaframework.swing.binding.core;


/**
 * @author Mario Garcia
 *
 * @param <T>
 */
public class Property<T> {
	
	private String expression;
	private Class<T> type;

	/**
	 * @param clazz
	 * @param expression
	 */
	public Property(Class<T> clazz,String expression){
		this.expression = expression;
		this.type = clazz;
	}

	/**
	 * @return
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @return
	 */
	public Class<T> getType() {
		return type;
	}

	/**
	 * @param expression
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}
}
