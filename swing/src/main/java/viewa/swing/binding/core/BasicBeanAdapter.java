package viewa.swing.binding.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Default implementation of BeanAdapter<T>
 * 
 * @author Mario Garcia
 *
 * @param <T>
 */
public class BasicBeanAdapter<T> implements BeanAdapter<T>{
	
	private static final Log logger = LogFactory.getLog(BasicBeanAdapter.class);
	private String name;
	private List<PropertyChangeListener> propertyChangeListenerList;
	private T source;
	private DynaBean targetWrapper;

	/**
	 *  Default constructor
	 */
	public BasicBeanAdapter(){
		super();
		this.setName(this.getTimeStampName());
	}
	
	/**
	 * Constructor that receives a JavaBean object as parameter
	 * 
	 * @param target
	 */
	public BasicBeanAdapter(T source){
		this();
		this.source = source;
		this.targetWrapper = new WrapDynaBean(this.source);
		this.propertyChangeListenerList = new ArrayList<PropertyChangeListener>();
	}

	/**
	 * Constructor that receives a JavaBean object as parameter, and a
	 * name for monitoring purposes
	 * 
	 * @param source
	 * @param name
	 */
	public BasicBeanAdapter(T source,String name){
		this(source);
		this.setName(name);
	}
	

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeListenerList.add(listener);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object){
		return this.getSource().equals(object);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Adapter#firePropertyChange(java.beans.PropertyChangeEvent)
	 */
	public void firePropertyChange(PropertyChangeEvent evt) {
		if (propertyChangeListenerList!= null){
			for (PropertyChangeListener listener : this.propertyChangeListenerList){
				listener.propertyChange(evt);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Adapter#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Adapter#getPropertyChangeListeners()
	 */
	public List<PropertyChangeListener> getPropertyChangeListeners() {
		return this.propertyChangeListenerList;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ModelAdapter#getPropertyClass(java.lang.String)
	 */
	public Class<?> getPropertyClass(String propertyName) {
		return this.targetWrapper.getDynaClass().getDynaProperty(propertyName).getType();
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Model#getTarget()
	 */
	public T getSource(){
		return this.source;
	}

	/**
	 * @return
	 */
	private String getTimeStampName(){
		return String.valueOf(System.nanoTime());
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ModelProxy#getValue(java.lang.Class, java.lang.String)
	 */
	public <U> U getValue(Class<U> clazz,String propertyName) {	
		Object o = null;
		try {
			o = this.targetWrapper.get(propertyName); 
		} catch (IllegalArgumentException ex){
			logger.error(ex.getMessage()+" ["+targetWrapper.getDynaClass().getName()+"|"+propertyName+"]");
		}
		if (o!= null && !clazz.isAssignableFrom(o.getClass())){
			throw new ClassCastException("expected "+o.getClass().getSimpleName()+" found "+clazz.getSimpleName());
		}
		return clazz.cast(o);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ModelAdapter#getValueList(java.lang.Class, java.lang.String)
	 */
	public <U> List<U> getValueList(Class<U> clazz, String propertyName){
		List<?> o = List.class.cast(this.targetWrapper.get(propertyName));
		List<U> list = new ArrayList<U>();
		for (Object ob : o){
			list.add(clazz.cast(ob));
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.BeanAdapter#getValueMap(java.lang.Class, java.lang.Class, java.lang.String)
	 */
	public <K, V> Map<K, V> getValueMap(Class<K> clazzKey, Class<V> clazzValue,String propertyName) {
		// TODO implementation
		throw new RuntimeException("Not implemented yet");
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ModelAdapter#getValueSet(java.lang.Class, java.lang.String)
	 */
	public <U> Set<U> getValueSet(Class<U> clazz, String propertyName){
		Set<?> o = Set.class.cast(this.targetWrapper.get(propertyName));
		Set<U> list = new HashSet<U>();
		for (Object ob : o){
			list.add(clazz.cast(ob));
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ModelAdapter#getValueString(java.lang.String)
	 */
	public String getValueString(String propertyName){
		return String.valueOf(this.targetWrapper.get(propertyName));
	}	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		return this.getSource().hashCode();
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Adapter#isSync(org.viewaframework.binding.core.Adapter)
	 */
	@SuppressWarnings("unchecked")
	public boolean isSync(Adapter<T> src) {
		Boolean isSynchronized = true;
		BeanAdapter adapter = BasicBeanAdapter.class.cast(src);
		DynaClass clazz = this.targetWrapper.getDynaClass();
		DynaProperty[] properties =clazz.getDynaProperties();	
		for (DynaProperty property : properties){
			Class<?> type = property.getType();
			String name = property.getName();
			try{
				Object target = this.getValue(type,name);
				Object source = adapter.getValue(type, name);
				isSynchronized = source == null && target == null ? 
							true : source == null || target == null ? 
									false : target.equals(source);
			} catch (Exception ex){
				isSynchronized = false;
			}
			if (!isSynchronized){
				break;
			}
		}
		return isSynchronized;
	}
	
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeListenerList.remove(listener);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Adapter#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.BeanAdapter#setSource(java.lang.Object)
	 */
	public void setSource(T source) {
		this.sync(new BasicBeanAdapter<T>(source));
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.BeanAdapter#setValue(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	public <V> BasicBeanAdapter<T> setValue(Class<V> clazz,String propertyName,V value){
		Object oldValue = this.targetWrapper.get(propertyName);
		Class<?> targetClazz = this.targetWrapper.getDynaClass().getDynaProperty(propertyName).getType();
		if (!clazz.equals(targetClazz)){
			throw new ClassCastException("");
		}		
		this.targetWrapper.set(propertyName, value);
		firePropertyChange(new PropertyChangeEvent(this,propertyName, oldValue, value));
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ModelProxy#setValue(java.lang.String, java.lang.Object)
	 */
	public BasicBeanAdapter<T> setValue(String propertyName,Object value){
		Object oldValue = this.targetWrapper.get(propertyName);
		this.targetWrapper.set(propertyName, value);
		firePropertyChange(new PropertyChangeEvent(this,propertyName, oldValue, value));
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.BeanAdapter#sync(org.viewaframework.binding.core.BeanAdapter)
	 */
	@SuppressWarnings("unchecked")
	public void sync(Adapter<T> value) {
		BeanAdapter adapter = BasicBeanAdapter.class.cast(value);
		DynaProperty[] properties =  this.targetWrapper.getDynaClass().getDynaProperties();
		for (DynaProperty property : properties){
			Class clazzType = property.getType();
			String name = property.getName();
			if (!name.equals("class")){			
				Object v = adapter.getValue(clazzType,name);
				if (logger.isDebugEnabled()){
					logger.debug("Setting value ["+name+"="+v+"] as ["+clazzType+"]");
				}
				this.setValue(clazzType,name,v);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Adapter#sync(org.viewaframework.binding.core.Adapter, org.viewaframework.binding.core.Property)
	 */
	@SuppressWarnings("unchecked")
	public void sync(Adapter<T> src, Property srcProperty,Property dstProperty) {
		BeanAdapter adapter = BasicBeanAdapter.class.cast(src);
		DynaProperty property =  this.targetWrapper.getDynaClass().getDynaProperty(srcProperty.getExpression());
		Object v = adapter.getValue(property.getType(),property.getName());
		String expression = dstProperty.getExpression();
		if (logger.isDebugEnabled()){
			logger.debug("Setting value ["+expression+"="+v+"]");
		}
		this.setValue(expression,v);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getSource().toString();
	}	
}
