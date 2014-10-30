package org.viewaframework.widget.view.ui;


public class MasterViewColumn{

	private String propertyName;
	private MasterViewCellRenderer renderer;
	private Integer width;

	/**
	 * 
	 */
	public MasterViewColumn(){
		super();
	}

	/**
	 * @param propertyName
	 * @param width
	 */
	public MasterViewColumn(String propertyName,Integer width) {
		super();
		this.propertyName = propertyName;
		this.width = width;
	}

	/**
	 * @param propertyName
	 * @param width
	 * @param renderer
	 */
	public MasterViewColumn(String propertyName,Integer width,MasterViewCellRenderer renderer){
		this(propertyName,width);
		this.renderer = renderer;
	}

	/**
	 * @return
	 */
	public String getPropertyName() {
		return propertyName;
	}
	
	/**
	 * @return
	 */
	public MasterViewCellRenderer getRenderer() {
		return renderer;
	}

	/**
	 * @return
	 */
	public Integer getWidth() {
		return width;
	}
	
	/**
	 * @param propertyName
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * @param renderer
	 */
	public void setRenderer(MasterViewCellRenderer renderer) {
		this.renderer = renderer;
	}

	/**
	 * @param width
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

}
