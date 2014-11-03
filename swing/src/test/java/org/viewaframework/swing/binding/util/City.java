package org.viewaframework.swing.binding.util;


/**
 * @author Mario Garcia
 *
 */
public class City {

	private String name;
	
	/**
	 * 
	 */
	public City(){
		super();
	}
	
	/**
	 * @param name
	 */
	public City(String name){
		this();
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
