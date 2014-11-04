package viewa.annotation.processor;

import viewa.view.ViewContainer;
import viewa.view.perspective.PerspectiveConstraint;

/**
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public class ViewsProcessorWrapper {

	private PerspectiveConstraint constraint;
	private boolean rootView;
	private boolean trayView;
	private ViewContainer view;

	public ViewsProcessorWrapper(ViewContainer view,PerspectiveConstraint constraint,boolean rootView,boolean trayView){
		this.view =view;
		this.constraint = constraint;
		this.rootView = rootView;
		this.trayView = trayView;
	}

	public PerspectiveConstraint getConstraint() {
		return constraint;
	}

	public ViewContainer getView() {
		return view;
	}

	public boolean isRootView() {
		return rootView;
	}

	public boolean isTrayView() {
		return trayView;
	}

	public void setConstraint(PerspectiveConstraint constraint) {
		this.constraint = constraint;
	}

	public void setRootView(boolean rootView) {
		this.rootView = rootView;
	}

	public void setTrayView(boolean trayView) {
		this.trayView = trayView;
	}
	
	public void setView(ViewContainer view) {
		this.view = view;
	}
	
}
