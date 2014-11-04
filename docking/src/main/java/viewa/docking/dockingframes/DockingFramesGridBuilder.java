package viewa.docking.dockingframes;

import static bibliothek.gui.dock.common.mode.ExtendedMode.*;

import java.util.Set;

import viewa.view.perspective.PerspectiveConstraint;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.SingleCDockable;
import bibliothek.gui.dock.common.mode.ExtendedMode;

/**
 * Every time a dockable is added to the grid a new grid
 * is built. And all previous dockables are added to the 
 * new grid, and finally the grid is deployed to the content area.
 * 
 * @author mgg
 *
 */
public class DockingFramesGridBuilder {

	private CGrid grid;
	
	public DockingFramesGridBuilder(CControl control){
		this.grid = new CGrid(control);
	}
	
	/**
	 * @param dockables
	 * @return
	 */
	public DockingFramesGridBuilder addDockable(Set<DockingFramesInfo> dockables){
		for (DockingFramesInfo dockingInfo : dockables){
			PerspectiveConstraint constraint = dockingInfo.getConstraint();
			SingleCDockable dockable = dockingInfo.getDockable();
			ExtendedMode mode = dockable.getExtendedMode();
			/* Positioning the dockable within the grid */
			if (mode== null || !dockable.getExtendedMode().equals(MINIMIZED)){
				switch (constraint) {
				case TOP:
					grid.add ( 0 , 0 , 20 , 100 ,dockable) ;				
					break;	
				case BOTTOM:
					grid.add ( 100 , 100 , 100 , 100 ,dockable) ;
					break;				
				case LEFT:
					grid.add ( 0 , 0 , 20 , 100 ,dockable) ;
					break;
				case LEFT_BOTTOM:
					grid.add ( 0 , 100 , 20 , 100 ,dockable) ;
					break;
				case LEFT_MIDDLE:
					grid.add ( 0 , 50 , 20 , 100 ,dockable) ;
					break;
				case LEFT_TOP:
					grid.add ( 0 , 0 , 20 , 100 ,dockable) ;
					break;
				case RIGHT:
					grid.add ( 100 , 0 , 100 , 100 ,dockable) ;
					break;
				case RIGHT_BOTTOM:
					grid.add ( 100 , 100 , 100 , 100 ,dockable) ;
					break;
				case RIGHT_MIDDLE:
					grid.add ( 100 , 50 , 100 , 100 ,dockable) ;
					break;
				case RIGHT_TOP:
					grid.add ( 100 , 0 , 100 , 100 ,dockable) ;
					break;
				default:
					grid.add ( 100 , 0 , 100 , 100 ,dockable) ;
					break;
				}
				dockable.setExtendedMode(NORMALIZED);
				dockable.setVisible(true);
			}
			
		}		
		return this;
	}
	
	/**
	 * @return
	 */
	public CGrid build(){
		return this.grid;
	}
}
