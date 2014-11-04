package viewa.test;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.core.Application;
import viewa.test.application.PropertyAware;
import viewa.test.application.ViewTrapperAware;
import viewa.test.component.ComponentAware;
import viewa.test.util.TrapperSettings;
import viewa.view.ViewManager;

/**
 * @author Mario Garcia
 *
 */
public class ApplicationTrapper implements 
	Trapper<Application>,ViewTrapperAware,ComponentAware,PropertyAware{

	private static final Log logger = LogFactory.getLog(ApplicationTrapper.class);
	private TrapperSettings settings;
	private Application target;
	private ViewTrapper viewTrapper;
	
	
	
	/**
	 * 
	 */
	public ApplicationTrapper(){
		super();
		this.settings = new TrapperSettings();
	}
	
	/**
	 * @param application
	 */
	public ApplicationTrapper(Application application){
		this();
		this.target = application;
		this.viewTrapper = new ViewTrapper(this, ViewManager.ROOT_VIEW_ID);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ComponentAware#button(java.lang.String)
	 */
	public ButtonTrapper button(String name) {
		return this.viewTrapper.button(name);
	}
	
	/**
	 * @param viewID
	 * @param name
	 * @return
	 */
	public ButtonTrapper button(String viewID,String name){
		this.viewTrapper = new ViewTrapper(this, viewID);
		return this.viewTrapper.button(name);
	}
	
	/* (non-Javadoc)
	 * @see viewa.test.component.ComponentAware#checkBox(java.lang.String)
	 */
	public CheckBoxTrapper checkBox(String name) {
		return this.viewTrapper.checkBox(name);
	}

	/**
	 * 
	 */
	public void close(){
		this.target.close();
	}

	/* (non-Javadoc)
	 * @see viewa.test.component.ComponentAware#combo(java.lang.String)
	 */
	public ComboTrapper combo(String name) {
		return this.viewTrapper.combo(name);
	}

	/**
	 * @return
	 */
	public TrapperSettings getSettings() {
		return settings;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getTarget()
	 */
	public Application getTarget() {
		return target;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getType()
	 */
	public Class<Application> getType() {
		return Application.class;
	}
	
	/* (non-Javadoc)
	 * @see viewa.test.component.ComponentAware#list(java.lang.String)
	 */
	public ListTrapper list(String name) {
		return this.viewTrapper.list(name);
	}

	/* (non-Javadoc)
	 * @see viewa.test.Trapper#log(java.lang.String)
	 */
	public ApplicationTrapper log(String message) {
		logger.info(message);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.test.ComponentAware#menuItem(java.lang.String)
	 */
	public MenuItemTrapper menuItem(String name) {
		return this.viewTrapper.menuItem(name);
	}

	/**
	 * @param viewID
	 * @param name
	 * @return
	 */
	public MenuItemTrapper menuItem(String viewID,String name) {
		this.viewTrapper = new ViewTrapper(this, viewID);		
		return this.viewTrapper.menuItem(name);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.PropertyAware#property(java.lang.String)
	 */
	public PropertyTrapper property(String name) {
		return this.viewTrapper.property(name);
	}

	/**
	 * @param viewID
	 * @param name
	 * @return
	 */
	public PropertyTrapper property(String viewID,String name) {
		this.viewTrapper = new ViewTrapper(this,viewID);
		return this.viewTrapper.property(name);
	}
	
	/**
	 * Checks that the application is visible
	 * 
	 * @return
	 */
	public ApplicationTrapper requireVisible(){
		return this.requireVisible(true);
	}
	
	/**
	 * Checks whether the application is visible or not
	 * 
	 * @param visible
	 * @return
	 */
	public ApplicationTrapper requireVisible(boolean visible){
		TestCase.assertEquals(this.getTarget().getViewManager().getRootView().getFrame().isVisible(),visible);
		return this;		
	}
	
	/**
	 * @param settings
	 */
	public void setSettings(TrapperSettings settings) {
		this.settings = settings;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#setTarget(java.lang.Object)
	 */
	public void setTarget(Application target) {
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ComponentAware#text(java.lang.String)
	 */
	public TextTrapper text(String name) {
		return this.viewTrapper.text(name);
	}

	/**
	 * @param viewID
	 * @param name
	 * @return
	 */
	public TextTrapper text(String viewID,String name){
		this.viewTrapper = new ViewTrapper(this, viewID);
		return this.viewTrapper.text(name);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ViewTrapperAware#view()
	 */
	public ViewTrapper view() {
	 /* The view musn't be cached. The viewTrapper always must have a fresh reference of the view */
		this.viewTrapper = new ViewTrapper(this,this.viewTrapper.getTarget() != null ? 
				this.viewTrapper.getTarget().getId() : null);
		return this.viewTrapper;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ViewTrapperAware#viewTrapper(java.lang.String)
	 */
	public ViewTrapper view(String viewId) {
		this.viewTrapper =  new ViewTrapper(this, viewId);
		return this.viewTrapper;
	}
}
