package org.viewaframework.view;

import java.awt.Container;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.viewaframework.annotation.processor.ControllersProcessor;
import org.viewaframework.annotation.processor.ListenersProcessor;
import org.viewaframework.controller.ViewController;
import org.viewaframework.controller.ViewControllerDispatcher;
import org.viewaframework.core.Application;
import org.viewaframework.model.ViewModel;
import org.viewaframework.model.ViewModelManager;
import org.viewaframework.view.perspective.PerspectiveConstraint;

/**
 * A default implementation of View Manager. It manages the views added to the application
 * and those which are removed from the application. It also is resposible of launching
 * the views lifecycle and add them to the current perspective. 
 * 
 * It's also responsible for keeping the visual part of the application stable.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */
public abstract class AbstractViewManager implements ViewManager
{
	private Application 				application;
	private Map<Object,ViewContainer> 	views;

	/**
	 * Default constructor. It creates a new List where the views are
	 * added.
	 */
	public AbstractViewManager(){
		this.views = new HashMap<Object,ViewContainer>();		
	}


	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewManager#addView(org.viewaframework.view.ViewContainer, org.viewaframework.view.perspective.PerspectiveConstraint)
	 */
	public void addView(ViewContainer view,PerspectiveConstraint constraint) throws ViewException 
	{	 
		Map<Object,ViewContainer> 				viewContainerCollection = this.getViews();
		ViewContainer 							viewContainer 			= viewContainerCollection.get(view.getId());		
		Application								app						= this.getApplication();
		ViewModelManager						modelManager			= app.getViewModelManager();
		ViewControllerDispatcher				controllerDispatcher	= app.getControllerDispatcher();
		Map<String,ViewModel>					model					= null;
		Map<String,ViewModel>					viewModel				= null;
		Map<String,List<ViewController<?,?>>>	controllers 			= null;
		String									viewId					= view.getId();
	 /* Views must have their ids and these ids must not be repeated */		
		if (viewId!=null && viewContainer == null)
		{
			model 		= modelManager.getViewModelMap(viewId);
			viewModel	= view.getViewModelMap();
			controllers = controllerDispatcher.getViewControllers(view);
		 /* Then application instance is injected in the view */
			view.setApplication(app);
		 /* The view is added to the application holder */
			this.getViews().put(viewId,view);
		 /* The view lifecycle begins */
			if (viewModel!=null){
				model.putAll(viewModel);
			}
			view.setViewModelMap(model);
		 /* This view can already have some controllers, if so the manager adds the dispatcher controllers*/
			if (view.getViewControllerMap()!=null){
				view.getViewControllerMap().putAll(controllers);
		 /* Otherwise the dispatcher sets the controllers */
			} else {
				view.setViewControllerMap(controllers);
			}
			// TODO refactoring
			try{
				view.getViewControllerMap().putAll(
						new ControllersProcessor(
								view,
								view.getApplication().getApplicationContext()).process());
				view.setViewContainerListeners(
					new ListenersProcessor(view).getResult()
				);
			} catch(Exception ex){
				throw new ViewException(ex.getMessage());
			}
			view.viewInit();
		 /* And finally the view is added to the current perspective */
			if (!view.getId().equals(ViewManager.ROOT_VIEW_ID)){
				if (view instanceof ViewContainerDialog){
					JDialog dialog 		= ((ViewContainerDialog)view).getDialog();
					JFrame 	rootFrame 	= this.getRootView().getFrame();
					dialog.setLocationRelativeTo(rootFrame);
					dialog.setTitle(view.getTitle());
					dialog.setIconImage(view.getIconImage());
					dialog.setVisible(true);			
			 /* Otherwise if it is not an AbstractViewContainerTray is added to the current perspective */
				} else if (!(view instanceof AbstractViewContainerTray)){
					this.getPerspective().addView(view,constraint);
				 /* Now the new view has the focus. Not needed in the other cases
				  * because in one hand dialogs catch the focus and on the other
				  * the frame has the focus when is made visible */
					view.getRootPane().requestFocusInWindow();
				}
			} else {
				ViewContainerFrame f = (ViewContainerFrame) view;
				f.getFrame().setTitle(f.getTitle());
			}
		} else {
			throw new ViewException(viewId == null ? "view must have an id" : "View is already added");
		}
	}
	
	public void addView(ViewContainer view) throws ViewException {
		this.addView(view,null);
	}
	
	/* (non-Javadoc)
	 * @see org.viewa.view.ViewManager#arrangeView()
	 */
	public Container arrangeViews() throws ViewManagerException
	{
		Map<Object,ViewContainer> cviews 					= new HashMap<Object, ViewContainer>();
		Collection<ViewContainer> viewContainerCollection 	= this.getViews().values();
	 /* ViewManager and Perspectives can make different decisions about its views so
	  * it is mandatory to create different view collections. */
		for (ViewContainer view : viewContainerCollection){
			cviews.put(view.getId(), view);
		}
		
		this.getPerspective().setViews(cviews);
		return this.getPerspective().arrange();
	}
	
	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationAware#getApplication()
	 */
	public Application getApplication() {
		return this.application;
	}
	
	/* (non-Javadoc)
	 * @see org.viewa.view.ViewManager#getViews()
	 */
	public Map<Object, ViewContainer> getViews() {
		return this.views;
	}

	/* (non-Javadoc)
	 * @see org.viewa.view.ViewManager#removeView(org.viewa.view.View)
	 */
	public void removeView(ViewContainer view) throws ViewException{
		if (view.getId()!=null){
		/* First the view is removed from the application holder */
			ViewContainer view2Close = this.getViews().remove(view.getId());
		/* If the view still exists is removed from the current perspective */
			if (view2Close!=null){
				view2Close.viewClose();			
			 /* Floatable views like dialogs and frames are not added to any perspective so theys couldnt be removed from any perspective */
				if (!(view2Close instanceof ViewContainerDialog) && !(view2Close instanceof ViewContainerFrame)){
					this.getPerspective().removeView(view2Close);
				} else if ((view2Close instanceof ViewContainerDialog) && !(view2Close instanceof ViewContainerFrame)){
					this.getRootView().getFrame().toFront();
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationAware#setApplication(org.viewa.core.Application)
	 */
	public void setApplication(Application application) {
		this.application = application;
	}
		
}
