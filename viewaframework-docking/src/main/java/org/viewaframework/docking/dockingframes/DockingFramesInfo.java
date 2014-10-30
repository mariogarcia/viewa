package org.viewaframework.docking.dockingframes;

import java.io.Serializable;

import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.perspective.PerspectiveConstraint;

import bibliothek.gui.dock.common.SingleCDockable;

/**
 * This class wraps all the information about a dockable and its related ViewContainer within
 * a grid
 * 
 * @author mgg
 *
 */
public class DockingFramesInfo implements Serializable{

	private static final long serialVersionUID = -8767013760977078868L;
	
	private PerspectiveConstraint constraint;
	private SingleCDockable dockable;
	private ViewContainer viewContainer;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o){
		boolean result = o == this;
		if (!result && DockingFramesInfo.class.isInstance(o)){
			DockingFramesInfo thisClass = this;
			DockingFramesInfo otherClass= DockingFramesInfo.class.cast(o);
			
			result = thisClass.dockable.getUniqueId().
				equalsIgnoreCase(otherClass.getDockable().getUniqueId());
		}
		return result;
	}
	/**
	 * @return
	 */
	public PerspectiveConstraint getConstraint() {
		return constraint;
	}
	/**
	 * @return
	 */
	public SingleCDockable getDockable() {
		return dockable;
	}
	public ViewContainer getViewContainer() {
		return viewContainer;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		return this.getDockable().getUniqueId().hashCode();
	}
	/**
	 * @param constraint
	 */
	public void setConstraint(PerspectiveConstraint constraint) {
		this.constraint = constraint;
	}
	/**
	 * @param dockable
	 */
	public void setDockable(SingleCDockable dockable) {
		this.dockable = dockable;
	}
	public void setViewContainer(ViewContainer viewContainer) {
		this.viewContainer = viewContainer;
	}
}
