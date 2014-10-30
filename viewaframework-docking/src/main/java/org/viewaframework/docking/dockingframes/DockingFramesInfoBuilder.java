package org.viewaframework.docking.dockingframes;

import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.perspective.PerspectiveConstraint;

import bibliothek.gui.dock.common.SingleCDockable;

/**
 * This class is a helper for building DockingFramesInfo instances.
 * 
 * @author mgg
 *
 */
public class DockingFramesInfoBuilder {

	private DockingFramesInfo dockingFramesInfo;
	
	/**
	 * 
	 */
	public DockingFramesInfoBuilder(){
		this.dockingFramesInfo = new DockingFramesInfo();
	}
	
	/**
	 * @return
	 */
	public DockingFramesInfo build(){
		return this.dockingFramesInfo;
	}
	
	/**
	 * @param constraint
	 * @return
	 */
	public DockingFramesInfoBuilder setConstraint(PerspectiveConstraint constraint){
		this.dockingFramesInfo.setConstraint(constraint);
		return this;
	}
	
	/**
	 * @param dockable
	 * @return
	 */
	public DockingFramesInfoBuilder setDockable(SingleCDockable dockable){
		this.dockingFramesInfo.setDockable(dockable);
		return this;
	}
	
	
	/**
	 * @param viewContainer
	 * @return
	 */
	public DockingFramesInfoBuilder setViewContainer(ViewContainer viewContainer){
		this.dockingFramesInfo.setViewContainer(viewContainer);
		return this;
	}
}
