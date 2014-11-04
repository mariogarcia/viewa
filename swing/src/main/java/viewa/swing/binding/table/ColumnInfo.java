package viewa.swing.binding.table;

/**
 * @author Mario Garcia
 *
 */
public class ColumnInfo {

	private String columnName;
	private Integer order;
	private Integer preferredWith;
	private String propertyName;
	
	/**
	 * @param name
	 */
	public ColumnInfo(Integer order,String name){
		this(order,name,name);
	}

	/**
	 * @param propertyName
	 * @param columnName
	 */
	public ColumnInfo(Integer order,String propertyName, String columnName) {
		this(order,propertyName,columnName,0);
	}

	/**
	 * @param propertyName
	 * @param columnName
	 */
	public ColumnInfo(Integer order,String propertyName, String columnName,Integer preferredWidth) {
		super();
		this.order = order;
		this.propertyName = propertyName;
		this.columnName = columnName;
		this.preferredWith = preferredWidth;
	}

	/**
	 * @return
	 */
	public String getColumnName() {
		return columnName;
	}
	
	/**
	 * @return
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @return
	 */
	public Integer getPreferredWith() {
		return preferredWith;
	}
	
	/**
	 * @return
	 */
	public String getPropertyName() {
		return propertyName;
	}
	
	/**
	 * @param columnName
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @param order
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * @param preferredWith
	 */
	public void setPreferredWith(Integer preferredWith) {
		this.preferredWith = preferredWith;
	}
	/**
	 * @param propertyName
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
}
