package viewa.test;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.test.application.ApplicationTrapperAware;
import viewa.test.application.PropertyAware;
import viewa.test.component.ComponentAware;
import viewa.view.ViewContainer;

/**
 * @author Mario Garcia
 *
 */
public class ViewTrapper implements 
	Trapper<ViewContainer>,ApplicationTrapperAware,ComponentAware,PropertyAware{

	private static final Log logger = LogFactory.getLog(ViewTrapper.class);
	private ApplicationTrapper applicationTraper;
	private ViewContainer target;
	
	/**
	 * @param applicationTrapper
	 * @param viewId
	 */
	public ViewTrapper(ApplicationTrapper applicationTrapper,String viewId){
		this.applicationTraper = applicationTrapper;
		this.target = this.applicationTraper.
			getTarget().getViewManager().getViews().get(viewId);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ApplicationTrapperAware#applicationTrapper()
	 */
	public ApplicationTrapper applicationTrapper() {
		return this.applicationTraper;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ComponentAware#button(java.lang.String)
	 */
	public ButtonTrapper button(String name) {
		return new ButtonTrapper(this.applicationTrapper(),name);
	}

	/* (non-Javadoc)
	 * @see viewa.test.component.ComponentAware#checkBox(java.lang.String)
	 */
	public CheckBoxTrapper checkBox(String name) {
		return new CheckBoxTrapper(this.applicationTraper,name);
	}

	/* (non-Javadoc)
	 * @see viewa.test.component.ComponentAware#combo(java.lang.String)
	 */
	public ComboTrapper combo(String name) {
		return new ComboTrapper(this.applicationTraper,name);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getTarget()
	 */
	public ViewContainer getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getType()
	 */
	public Class<ViewContainer> getType() {
		return ViewContainer.class;
	}

	/* (non-Javadoc)
	 * @see viewa.test.component.ComponentAware#list(java.lang.String)
	 */
	public ListTrapper list(String name) {
		return new ListTrapper(this.applicationTraper,name);
	}

	/* (non-Javadoc)
	 * @see viewa.test.Trapper#log(java.lang.String)
	 */
	public ViewTrapper log(String message) {
		logger.info(message);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.ComponentAware#menuItem(java.lang.String)
	 */
	public MenuItemTrapper menuItem(String name) {
		return new MenuItemTrapper(this.applicationTraper, name);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.PropertyAware#property(java.lang.String)
	 */
	public PropertyTrapper property(String name) {
		return new PropertyTrapper(this.applicationTrapper(),name);
	}
	
	/**
	 * @return
	 */
	public ViewTrapper requireOpened(){
		return this.requireOpened(true);
	}

	/**
	 * @param openedOrClosed
	 * @return
	 */
	public ViewTrapper requireOpened(boolean openedOrClosed){
		TestCase.assertEquals(this.getTarget() == null,!openedOrClosed);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#setTarget(java.lang.Object)
	 */
	public void setTarget(ViewContainer target) {
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ComponentAware#text(java.lang.String)
	 */
	public TextTrapper text(String name) {
		return new TextTrapper(this.applicationTrapper(), name);
	}
}
