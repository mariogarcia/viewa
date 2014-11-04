package viewa.test.component;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.test.ApplicationTrapper;
import viewa.test.Trapper;
import viewa.test.ViewTrapper;
import viewa.test.application.ApplicationTrapperAware;
import viewa.test.application.ViewTrapperAware;
import viewa.view.ViewContainer;

/**
 * @author Mario Garcia
 * 
 * @param <T>
 */
public abstract class AbstractComponentTrapper<T extends Component>
		implements Trapper<T>, ApplicationTrapperAware, ViewTrapperAware{

	private static final Log logger = LogFactory.getLog(AbstractComponentTrapper.class);
	private ApplicationTrapper applicationTrapper;
	private T target;

	/**
	 * @param applicationTrapper
	 * @param componentName
	 */
	public AbstractComponentTrapper(ApplicationTrapper applicationTrapper,String componentName) {
		this.applicationTrapper = applicationTrapper;
		this.target = this.getType().cast(getComponentByName(this.applicationTrapper.view().getTarget(),componentName));
	}
		
	/**
	 * @param applicationTrapper
	 * @param viewId
	 * @param componentName
	 */
	public AbstractComponentTrapper(ApplicationTrapper applicationTrapper,String viewId, String componentName) {
		this.applicationTrapper = applicationTrapper;
		this.target = this.getType().cast(getComponentByName(this.applicationTrapper.getTarget().getViewManager().getViews().get(viewId),componentName));
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.viewaframework.widget.test.ApplicationTrapperAware#applicationTrapper
	 * ()
	 */
	public ApplicationTrapper applicationTrapper() {
		return this.applicationTrapper;
	}

	/**
	 * ViewContainer.getComponentsByName() doesn't distinguishes between different
	 * types of components.
	 * 
	 * @param name The name of the component
	 * @since 1.0.2
	 * 
	 * @return The right typed component
	 */
	private Component getComponentByName(ViewContainer view,String name){
		Class<T> type = this.getType();
		List<Component> finalists = new ArrayList<Component>();
		List<Component> candidates = view.getComponentsByName(name);
		if (candidates != null){
			for (Component component : candidates){
				if (type.isAssignableFrom(component.getClass())){
					finalists.add(component);
				}
			}
		} else {
			logger.warn("No component found for name: "+name+" at "+view.getId()+" view");
		}
		return finalists.size() > 0 ? finalists.get(0) : null;
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.viewaframework.widget.test.Trapper#getTarget()
	 */
	public T getTarget() {
		return target;
	}

	/**
	 * Checks that the component is enabled otherwise the test will fail
	 * 
	 * @return
	 */
	public abstract AbstractComponentTrapper<T> requireEnabled();

	/**
	 * Checks the component is visible otherwise the test will fail
	 * 
	 * @return
	 */
	public abstract AbstractComponentTrapper<T> requireVisible();
	
	/**
	 * Checks whether the component is enabled or not
	 * 
	 * @return
	 */
	public abstract AbstractComponentTrapper<T> requireEnabled(boolean enabled);

	/**
	 * 
	 * Checks whether the component is visible or not
	 * 
	 * @return
	 */
	public abstract AbstractComponentTrapper<T> requireVisible(boolean visible);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.viewaframework.widget.test.Trapper#setTarget(java.lang.Object)
	 */
	public void setTarget(T target) {
		this.target = target;
	}

	/**
	 * @return
	 */
	public ViewTrapper view() {
		return this.applicationTrapper().view();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.viewaframework.widget.test.ViewTrapperAware#view(java.lang.String)
	 */
	public ViewTrapper view(String viewId) {
		return this.applicationTrapper().view(viewId);
	}
}
