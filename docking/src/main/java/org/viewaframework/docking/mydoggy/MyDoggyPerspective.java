package org.viewaframework.docking.mydoggy;

import info.clearthought.layout.TableLayout;

import java.awt.Container;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.noos.xing.mydoggy.Content;
import org.noos.xing.mydoggy.ContentManagerListener;
import org.noos.xing.mydoggy.TabbedContentManagerUI;
import org.noos.xing.mydoggy.ToolWindow;
import org.noos.xing.mydoggy.ToolWindowAnchor;
import org.noos.xing.mydoggy.ToolWindowType;
import org.noos.xing.mydoggy.ToolWindowTypeDescriptor;
import org.noos.xing.mydoggy.event.ContentManagerEvent;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager;
import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.ViewContainerEditor;
import org.viewaframework.view.ViewException;
import org.viewaframework.view.ViewManagerException;
import org.viewaframework.view.perspective.Perspective;
import org.viewaframework.view.perspective.PerspectiveConstraint;

/**
 * This perspective uses the MyDoggy docking framework.
 * 
 * @author Mario Garcia
 *
 */
public class MyDoggyPerspective implements Perspective{

	private static final Log logger = LogFactory.getLog(MyDoggyPerspective.class);
	private String id = "myDoggyPerspective";
	private MyDoggyToolWindowManager myDoggyToolWindowManager;
	private JPanel panel;
	private Map<Object,ViewContainer> views;
	
	/**
	 * Default Constructor
	 */
	public MyDoggyPerspective(){
		this.panel = new JPanel();
		this.panel.setLayout(new TableLayout(new double[][]{{0, -1, 0}, {0, -1, 0}}));
		this.panel.add(this.getMyDoggyToolWindowManager(),"1,1,");
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewAware#addView(org.viewaframework.view.ViewContainer)
	 */
	public void addView(ViewContainer view) {
		this.addView(view, null);
	}

	
	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewAware#addView(org.viewaframework.view.ViewContainer, org.viewaframework.view.perspective.PerspectiveConstraint)
	 */
	public void addView(ViewContainer view, PerspectiveConstraint perspectiveConstraint) {
	 /* Since version 1.0.2 unifying perspective constraints */
		Object constraint = translatePerspectiveConstraint(perspectiveConstraint);
		if (logger.isDebugEnabled()){
			logger.debug("Adding view: "+view.getId());
		}
		Image 					 viewIconImage 	= view.getIconImage();
		ImageIcon 				 viewIcon 		= viewIconImage != null ? new ImageIcon(viewIconImage) : null;	
		String					 viewID			= view.getId();
		String					 title			= view.getTitle();
		Container				 viewComponent	= view.getRootPane();
		MyDoggyToolWindowManager manager 		= this.getMyDoggyToolWindowManager();
		if (ViewContainerEditor.class.isInstance(view)){
			if (logger.isDebugEnabled()){
				logger.debug("Adding an editor view");
			}
			manager.getContentManager().
				addContent(viewID,title,viewIcon,viewComponent).
				setSelected(true);
		}else{
			if (logger.isDebugEnabled()){
				logger.debug("Adding a normal view");
			}
			ToolWindowAnchor anchor = 
				constraint != null && 
				constraint instanceof ToolWindowAnchor ? 
						ToolWindowAnchor.class.cast(constraint): 
						ToolWindowAnchor.LEFT;	
			if (logger.isDebugEnabled()){
				logger.debug("Registering toolWindow ["+viewID+","+title+","+viewComponent.getName()+","+anchor.name()+"]");
			}
			ToolWindow toolWindow = manager.
				registerToolWindow(viewID,title,viewIcon,viewComponent,anchor);
			/* Id's are useless for users */			
			for (ToolWindowType type : ToolWindowType.values()){
				try{
					ToolWindowTypeDescriptor descriptor = manager.getTypeDescriptorTemplate(type);
					if (descriptor != null){
						descriptor.setIdVisibleOnTitleBar(false);		
					}
				} catch (java.lang.IllegalStateException e){
					logger.warn(e.getMessage());
				}
			}			
			toolWindow.setAvailable(true);
			toolWindow.setVisible(false);
		}
		this.getViews().put(viewID,view);
		if (this.panel.getRootPane()!= null){
			this.panel.getRootPane().validate();
			this.panel.getRootPane().repaint();
		}
	}
	
	/**
	 * @param perspectiveConstraint
	 * @return
	 */
	private Object translatePerspectiveConstraint(PerspectiveConstraint perspectiveConstraint) {
		ToolWindowAnchor anchor = null;
		anchor = PerspectiveConstraint.BOTTOM.equals(perspectiveConstraint) ? ToolWindowAnchor.BOTTOM : anchor;
		anchor = PerspectiveConstraint.LEFT.equals(perspectiveConstraint) ? ToolWindowAnchor.LEFT : anchor;
		anchor = PerspectiveConstraint.RIGHT.equals(perspectiveConstraint) ? ToolWindowAnchor.RIGHT : anchor;
		anchor = PerspectiveConstraint.TOP.equals(perspectiveConstraint) ? ToolWindowAnchor.TOP : anchor;
		return anchor;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.view.perspective.Perspective#arrange()
	 */
	public Container arrange() throws ViewManagerException {
		return this.panel;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.view.perspective.Perspective#clear()
	 */
	public void clear() {
		this.getViews().clear();
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.view.perspective.Perspective#getId()
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * @return
	 */
	@SuppressWarnings("all")
	public MyDoggyToolWindowManager getMyDoggyToolWindowManager() {
		if (this.myDoggyToolWindowManager == null){
			this.myDoggyToolWindowManager = new MyDoggyToolWindowManager();
			TabbedContentManagerUI tabbedContentManagerUI = (
					TabbedContentManagerUI.class.cast(
							this.myDoggyToolWindowManager.
								getContentManager().getContentManagerUI()));
			tabbedContentManagerUI.setShowAlwaysTab(true);
			tabbedContentManagerUI.setDetachable(false);
			tabbedContentManagerUI.setMinimizable(false);
			myDoggyToolWindowManager.
				getContentManager().
					addContentManagerListener(new ContentManagerListener(){
						public void contentAdded(ContentManagerEvent arg0) {}
						public void contentRemoved(ContentManagerEvent arg0) {
						 /* If x button clicked */
							ViewContainer view2Remove = getViews().get(arg0.getContent().getId());
							try {
								view2Remove.getApplication().getViewManager().removeView(view2Remove);
							} catch (ViewException e) {
								e.printStackTrace();
							}
						}
						public void contentSelected(ContentManagerEvent arg0) {}
					});
		}
		return myDoggyToolWindowManager;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewAware#getViews()
	 */
	public Map<Object, ViewContainer> getViews() {
		if (this.views == null){
			this.views = new HashMap<Object, ViewContainer>();
		}
		return this.views;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewAware#removeView(org.viewaframework.view.ViewContainer)
	 */
	public void removeView(ViewContainer view) {
			String viewId = view.getId();
			ToolWindow toolWindow = myDoggyToolWindowManager.getToolWindow(viewId);
			Content content = myDoggyToolWindowManager.getContentManager().getContent(viewId);
			if (content != null){
			 /* If it is removed by other than x tab button */
				myDoggyToolWindowManager.getContentManager().removeContent(content);
			}
		 /* If it is a tool window needs to be unregistered*/
			if (toolWindow != null){
				myDoggyToolWindowManager.unregisterToolWindow(viewId);
			}
			view.getApplication().getViewManager().getViews().remove(view.getId());
		this.views.remove(view.getId());
		if (this.panel.getRootPane()!= null){
			this.panel.getRootPane().validate();
			this.panel.getRootPane().repaint();
		}
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.view.perspective.Perspective#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param myDoggyToolWindowManager
	 */
	public void setMyDoggyToolWindowManager(MyDoggyToolWindowManager myDoggyToolWindowManager) {
		this.myDoggyToolWindowManager = myDoggyToolWindowManager;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewAware#setViews(java.util.Map)
	 */
	public void setViews(Map<Object, ViewContainer> views) {
		this.views = views;
	}
}
