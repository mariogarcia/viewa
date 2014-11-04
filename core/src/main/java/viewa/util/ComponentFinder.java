package viewa.util;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.view.ViewContainer;

/**
 * @author mario
 *
 * @param <T>
 */
public class ComponentFinder<T> {
		
	private static final Log logger = LogFactory.getLog(ComponentFinder.class);
	
	/**
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> ComponentFinder<T> find(Class<T> clazz){
		return new ComponentFinder<T>(clazz);
	}
	
	/**
	 * @param clazz
	 */
	private ComponentFinder(Class<T> clazz){
		this.clazz = clazz;
	}

	private Class<T> clazz;
	private ViewContainer viewContainer;

	/**
	 * @param name
	 * @return
	 */
	public T named (String name){
	 /* Taking all components with that name and class */
		List<T> components = this.namedList(name);
	 /* If any we only want the first occurrence */
		return components.size() > 0 ? components.get(0) : null;
	}
	
	/**
	 * @param name
	 * @return
	 */
	public List<T> namedList (String name){
		List<T> components = new ArrayList<T>();
		List<Component> possibleComponents = this.viewContainer.getComponentsByName(name);
		if (possibleComponents != null){
			for (Component component : possibleComponents){
				/* Before adding any component be have to be sure is of that type */
				if (clazz.isAssignableFrom(component.getClass())){
					components.add(clazz.cast(component));
				}
			}
		}
		if (logger.isDebugEnabled()){
			if (components.size() == 0){
				logger.debug("No components found for name: '"+name+"'");
			}
		}
		return components;
	}
	
	/**
	 * @param view
	 * @return
	 */
	public ComponentFinder<T> in(ViewContainer view){
		this.viewContainer = view;
		return this;
	}
}
