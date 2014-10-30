package org.viewaframework.view;

import java.awt.Container;
import java.util.Map;

import org.viewaframework.core.ApplicationAware;
import org.viewaframework.view.perspective.Perspective;
import org.viewaframework.view.perspective.PerspectiveConstraint;

/**
 * This interface should be implemented by classes used for managing
 * several views.<br/><br/>
 * Views can be added to the manager and then re-arranged before it can
 * be shown.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */
public interface ViewManager extends ApplicationAware
{
	public static final String ROOT_VIEW_ID = "rootViewId";

	/**
	 * Adds a view to the manager
	 * 
	 * @param view
	 * @throws
	 */
	public void addView(ViewContainer view) throws ViewException;
	
	/**
	 * Adds a view to the manager given it a specific view constraint
	 * 
	 * @param view
	 * @param constraint
	 * @throws ViewException
	 */
	public void addView(ViewContainer view,PerspectiveConstraint constraint) throws ViewException;
	
	/**
	 * Arranges the views contained in the manager. It would be
	 * called by the application before the views can be viewed.
	 * 
	 * @return
	 */
	public Container arrangeViews() throws ViewManagerException;
	
	/**
	 * @return
	 */
	public Perspective getPerspective();
	
	//[ID:2895658]: getRootView() from ViewManager not to throw and Exception
	/**
	 * @return
	 */
	public ViewContainerFrame getRootView();
	
	/**
	 * @return
	 */
	public Map<Object,ViewContainer> getViews();
	
	/**
	 * It removes the view passed as parameter from the manager.
	 * 
	 * @param view
	 */
	public void removeView(ViewContainer view) throws ViewException;
	
	/**
	 * @param viewLayout
	 */
	public void setPerspective(Perspective perspective) throws ViewException;
	
	
	/**
	 * Sets the main view
	 * 
	 * @param view
	 * @throws ViewException
	 */
	public void setRootView(ViewContainerFrame view) throws ViewException;
	
	
}
