package org.viewaframework.docking.dockingframes;

import static org.viewaframework.view.perspective.PerspectiveConstraint.RIGHT;

import java.awt.Container;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.ViewException;
import org.viewaframework.view.ViewManagerException;
import org.viewaframework.view.perspective.Perspective;
import org.viewaframework.view.perspective.PerspectiveConstraint;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.SingleCDockable;
import bibliothek.gui.dock.common.event.CControlListener;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.common.theme.ThemeMap;

/**
 * Perspective implementation for DockingFrames {@link http://dock.javaforge.com/}
 * 
 * @author mgg
 *
 */
public class DockingFramesPerspective implements Perspective, CControlListener{

	private Container contentPanel = new JPanel();;
	private CControl control;
	private JFrame frame = null;
	private String id;
	private Map<Object,ViewContainer> views;
	private Set<DockingFramesInfo> dockablesInfoSet;
	
	/**
	 * Default constructor
	 */
	public DockingFramesPerspective(){
		this.views = new HashMap<Object, ViewContainer>();
		this.dockablesInfoSet = new HashSet<DockingFramesInfo>();
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewAware#addView(org.viewaframework.view.ViewContainer)
	 */
	public void addView(ViewContainer arg0) {
		this.addView(arg0, null);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewAware#addView(org.viewaframework.view.ViewContainer, org.viewaframework.view.perspective.PerspectiveConstraint)
	 */
	public void addView(ViewContainer arg0, PerspectiveConstraint arg1) {
		String viewId = arg0.getId();
		DefaultSingleCDockable dockable = null;
		Container container = arg0.getContentPane();
	 /* Initializing control object */
		if (this.control == null){			
			this.frame			= arg0.getApplication().getViewManager().getRootView().getFrame();
			this.contentPanel 	= frame.getContentPane();			
			this.control 		= new CControl(frame);		
			this.control.addControlListener(this);
			this.control.setTheme(ThemeMap.KEY_ECLIPSE_THEME);									
			this.frame.add(this.control.getContentArea());			
		}		
	 /* Adding a new Dockable with the view information */
		dockable = new DefaultSingleCDockable(viewId, container);	
		dockable.setTitleText(viewId);
		dockable.setCloseable(true);
		dockable.setStackable(true);
		arg1 = arg1 == null ? RIGHT : arg1;
	 /* Adding the dockable to the set of dockables to view */
		this.dockablesInfoSet.add(
				new DockingFramesInfoBuilder().
					setDockable(dockable).
					setViewContainer(arg0).
					setConstraint(arg1).build());
	 /* Adding the view to Viewa */
		this.views.put(arg0.getId(),arg0);
	 /* Deploying the grid with all dockables */
		this.control.getContentArea().
			deploy(new DockingFramesGridBuilder(control).
					addDockable(this.dockablesInfoSet).build());
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.view.perspective.Perspective#arrange()
	 */
	public Container arrange() throws ViewManagerException {
		return this.contentPanel;
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

	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewAware#getViews()
	 */
	public Map<Object, ViewContainer> getViews() {
		return this.views;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewAware#removeView(org.viewaframework.view.ViewContainer)
	 */
	public void removeView(ViewContainer arg0) {
		this.realRemove(arg0);
	}
	
	/**
	 * This method calls to the docking api to do the real work of removing the
	 * dockable
	 * 
	 * @param arg0
	 */
	private void realRemove(ViewContainer arg0){		
		SingleCDockable dockable =  this.control.getSingleDockable(arg0.getId());
	 /* This is a recursive call. This will fire the removed event and then the Viewa view manager will 
	  * call again to the removeView method from the perspective. This loop only breaks when the view container
	  * has been removed from the perspective views map. */
		this.control.remove(dockable);
	 /* Removing view from map */ 
		ViewContainer v = this.views.get(SingleCDockable.class.cast(dockable).getUniqueId());
		if (v != null){
			String key = v.getId();
			views.remove(key);
			dockablesInfoSet.
				remove(new DockingFramesInfoBuilder().
						setDockable(SingleCDockable.class.cast(dockable)).
							build());
			v.getApplication().getViewManager().getViews().remove(key);
		}
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.view.perspective.Perspective#setId(java.lang.String)
	 */
	public void setId(String arg0) { 
		this.id = arg0;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewAware#setViews(java.util.Map)
	 */
	public void setViews(Map<Object, ViewContainer> arg0) {
		this.views = arg0;
	}

	/* (non-Javadoc)
	 * @see bibliothek.gui.dock.common.event.CControlListener#added(bibliothek.gui.dock.common.CControl, bibliothek.gui.dock.common.intern.CDockable)
	 */
	public void added(CControl control, CDockable dockable) { }

	/* (non-Javadoc)
	 * @see bibliothek.gui.dock.common.event.CControlListener#removed(bibliothek.gui.dock.common.CControl, bibliothek.gui.dock.common.intern.CDockable)
	 */
	public void removed(CControl control, CDockable dockable) {
	 /* Right now we're not pooling views, so closing is the same as removing: Part of the recursive call. See realRemove() method. */
		SingleCDockable dock = SingleCDockable.class.cast(dockable);
		String uniqueId = dock.getUniqueId();
		ViewContainer view = views.get(uniqueId);
		if (view != null){
			try {
				view.getApplication().getViewManager().removeView(view);
			} catch (ViewException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see bibliothek.gui.dock.common.event.CControlListener#opened(bibliothek.gui.dock.common.CControl, bibliothek.gui.dock.common.intern.CDockable)
	 */
	public void opened(CControl control, CDockable dockable) { }

	/* (non-Javadoc)
	 * @see bibliothek.gui.dock.common.event.CControlListener#closed(bibliothek.gui.dock.common.CControl, bibliothek.gui.dock.common.intern.CDockable)
	 */
	public void closed(CControl control, CDockable dockable) {
		this.removed(control, dockable);
	}
}
