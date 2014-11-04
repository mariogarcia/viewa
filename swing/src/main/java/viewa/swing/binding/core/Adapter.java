package viewa.swing.binding.core;


/**
 * Very important interface. Here started everything.
 * 
 * @author Mario Garcia
 *
 * @param <T> Type of elements this adapter is going to take care of.
 */
public interface Adapter<T> extends Observable{
	
	/**
	 * Returns the name of the adapter
	 * 
	 * @return The name of the adapter
	 */
	public String getName();
	
	/**
	 * Returns the source this adapter is taken care of
	 * 
	 * @return
	 */
	public T getSource();

	/**
	 * Returns true if the current object is synchronized with the object passed as parameter
	 * 
	 * @param source The reference object
	 * @return true if both are synchronized
	 */
	public boolean isSync(Adapter<T> source);
	
	/**
	 * Sets the name of the adapter
	 * 
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * Sets the source object
	 * 
	 * @param source
	 */
	public void setSource(T source);
	
	/**
	 * Synchronizes all values of the current bean with the bean passed as parameter.
	 * 
	 * @param value
	 */
	public void sync(Adapter<T> target);

}
