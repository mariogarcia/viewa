package viewa.swing.binding.util;


/**
 * @author Mario Garcia
 *
 */
public class Author {

	private Integer age;
	private String city;
	private String name;
	
	/**
	 * 
	 */
	public Author(){
		super();
	}
	
	/**
	 * @param name
	 * @param age
	 */
	public Author(String name,Integer age) {
		super();
		this.age = age;
		this.name = name;
	}
	
	/**
	 * @param name
	 * @param age
	 * @param city
	 */
	public Author(String name,Integer age,String city){
		this(name,age);
		this.city = city;
	}
	
	/**
	 * @return
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.name;
	}
}
