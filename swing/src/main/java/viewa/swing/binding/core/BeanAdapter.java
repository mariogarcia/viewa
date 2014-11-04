package viewa.swing.binding.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Adapter for encapsulate accessing JavaBeans
 * 
 * @author Mario Garcia
 *
 * @param <T>
 */
public interface BeanAdapter<T> extends Adapter<T>{

	/**
	 * Returns the class of the property name passed as parameter
	 * 
	 * @param propertyName The name of the property
	 * @return the type of the property
	 */
	public Class<?> getPropertyClass(String propertyName);
	
	/**
	 * Returns the value of the desired property with the type
	 * passed as parameter
	 * 
	 * @param <U> returned object
	 * @param clazz the desired type
	 * @param propertyName the property name
	 * @return the property value
	 * @throws Exception
	 */
	public <U> U getValue(Class<U> clazz,String propertyName);
	
	/**
	 * Returs the property value as a List
	 * 
	 * @param <U>
	 * @param clazz
	 * @param propertyName
	 * @return
	 * @throws Exception
	 */
	public <U> List<U> getValueList(Class<U> clazz,String propertyName);
	
	/**
	 * Returns the value as a java.util.Map
	 * 
	 * @param <K>
	 * @param <V>
	 * @param clazzKey
	 * @param clazzValue
	 * @param propertyName
	 * @return
	 * @throws Exception
	 */
	public <K,V> Map<K,V> getValueMap(Class<K> clazzKey,Class<V> clazzValue,String propertyName);
	
	/**
	 * Returns the value as a java.util.Set
	 * 
	 * @param <U>
	 * @param clazz
	 * @param property
	 * @return
	 * @throws Exception
	 */
	public <U> Set<U> getValueSet(Class<U> clazz,String property);

	/**
	 * Returns the string representation of the property
	 * 
	 * @param propertyName
	 * @return
	 * @throws Exception
	 */
	public String getValueString(String propertyName);	

	/**
	 * Sets the property value
	 * 
	 * @param <V>
	 * @param clazz
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <V> BasicBeanAdapter<T> setValue(Class<V> clazz,String propertyName,V value);
	
	/**
	 * Sets the property value
	 * 
	 * @param propertyName
	 * @param value
	 */
	public BeanAdapter<T> setValue(String propertyName,Object value);	
}
