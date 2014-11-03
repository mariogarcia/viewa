package org.viewaframework.view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JRootPane;

import org.viewaframework.core.Application;
import org.viewaframework.view.perspective.Perspective;

/**
 * The default implementation of the AbstractViewManager. It has a JFrame as its
 * principal component. It also has a main perspective where all the views are
 * going to be arranged.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */
public class DefaultViewManager extends AbstractViewManager
{
	private Perspective perspective;
	private ViewContainerFrame rootView;

	/**
	 * Default Constructor
	 */
	public DefaultViewManager(){
		super();
	}

	/**
	 * This constructor gives the manager the application instance as well as
	 * the initial perspective.
	 * 
	 * @param app The application instance
	 * @param perspective The current perspective
	 */
	public DefaultViewManager(Application app,Perspective perspective){
		this.setApplication(app);
		try {
			this.setPerspective(perspective);
		} catch (ViewException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.viewa.view.AbstractViewManager#arrangeViews()
	 */
	@Override
	public Container arrangeViews() throws ViewManagerException 
	{
		JFrame 				rootContainer 	= null;
		Container 			container 		= null;
		ViewContainerFrame 	rootView 		= null; 	

		try 
		{
			rootView 		= this.getRootView();
			rootContainer 	= rootView.getFrame();
			container 		= super.arrangeViews();
			rootContainer.setContentPane(rootView.getRootPane());
			((JRootPane)rootContainer.getContentPane()).getContentPane().add(container);			
			this.addView(rootView);			
		} catch (ViewException e) {
			throw new ViewManagerException();
		}
			
		return rootContainer;
	}

	/* (non-Javadoc)
	 * @see org.viewa.view.ViewManager#getPerspective()
	 */
	public Perspective getPerspective() {
		return this.perspective;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewManager#getRootView()
	 * 
	 * [ID:2895658]: getRootView() from ViewManager not to throw and Exception
	 */
	public ViewContainerFrame getRootView() {
		if (rootView == null){
			rootView = new AbstractViewContainerFrame(){
				//Just for instancing it
			};
			rootView.setApplication(this.getApplication());
		}
		return rootView;
	}

	/* (non-Javadoc)
	 * @see org.viewa.view.ViewManager#setViewLayout(org.viewa.view.ViewLayout)
	 */
	public void setPerspective(Perspective viewLayout) throws ViewException {
		if (this.getPerspective() == null){
			this.perspective = viewLayout;
		} else {
			this.getPerspective().clear();
			this.perspective = viewLayout;
		}
	}

	/**
	 * @param rootView the rootView to set
	 */
	public void setRootView(ViewContainerFrame rootView) {
		this.rootView = rootView;
	}
}
