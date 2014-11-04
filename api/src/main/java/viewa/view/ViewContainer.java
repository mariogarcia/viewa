package viewa.view;

import java.awt.Image;
import java.util.List;

import javax.swing.JToolBar;
import javax.swing.RootPaneContainer;

import viewa.controller.ViewControllerAware;
import viewa.core.ApplicationAware;
import viewa.core.MessageAware;
import viewa.model.ViewModelAware;
import viewa.view.delegator.DelegatorAware;
import viewa.view.event.ViewContainerEventAware;
import viewa.view.event.ViewContainerEventControllerAware;

/**
 * This class it is the container of all graphical components.
 * 
 * @author Mario Garcia
 *
 */
public interface ViewContainer extends ViewContainerEventAware,ViewContainerEventControllerAware,ApplicationAware,ComponentsAware,ViewControllerAware,ViewModelAware,DelegatorAware,RootPaneContainer,MessageAware 
{
	public static final String CONTENTPANE 	= "contentPane";
	public static final String FRAME		= "frame";
	public static final String MENUBAR		= "menuBar";
	public static final String ROOTPANE 	= "rootPane";
	public static final String TOOLBAR 		= "toolBar";
	
	
	/**
	 * @return
	 */
	public List<ViewActionDescriptor> getActionDescriptors();
	
	/**
	 * Returns the image that represents this view
	 * 
	 * @return
	 */
	public Image getIconImage();
	
	/**
	 * Returns the id of the view
	 * 
	 * @return the name
	 */
	public abstract String getId();
	
	/**
	 * @return
	 */
	public JToolBar getJToolBar();
	
	/**
	 * Returns the title of the view
	 * 
	 * @return
	 */
	public abstract String getTitle();
	
	/**
	 * @param descriptors
	 */
	public void setActionDescriptors(List<ViewActionDescriptor> descriptors);
	
	/**
	 * Sets a representative image for this view
	 * 
	 * @param image
	 */
	public void setIconImage(Image image);
	
	/**
	 * Sets the name view.
	 * 
	 * @param name the id to set
	 */
	public abstract void setId(String name);		
	
	/**
	 * @param toolBar
	 */
	public void setJToolbar(JToolBar toolBar);

	/**
	 * Sets the title of the view
	 * 
	 * @param title
	 */
	public abstract void setTitle(String title);

	/**
	 * Closes the view
	 */
	public abstract void viewClose() throws ViewException;

	/**
	 * Once background actions has been performed we can establish the 
	 * final aspect of the view within this method.
	 * 
	 * @throws ViewException
	 */
	public abstract void viewFinalUIState() throws ViewException;
	
	/**
	 * Initializes the view. It should be implemented by an
	 * abstract class and it should call sequentially to the
	 * following methods.<br/><br/>
	 * 
	 * viewInitUIState();<br/>
	 * viewInitBackActions();<br/>
	 * viewFinalUIState();<br/>
	 * 
	 */
	public abstract void viewInit() throws ViewException;
	
	/**
	 * Background actions affecting the final view.
	 * Actions that could freeze the UI, should be done here.
	 * 
	 * @throws ViewException
	 */
	public abstract void viewInitBackActions() throws ViewException;
	
	/**
	 * How the view is visualized before background actions are performed.
	 * For example, if we want to keep disabled the UI until some
	 * database actions finished we should do it here.
	 * 
	 * @throws ViewException
	 */
	public abstract void viewInitUIState() throws ViewException;

}