package viewa.widget.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;

import viewa.view.AbstractViewContainerDialog;
import viewa.view.ViewActionDescriptor;
import viewa.view.delegator.ActionDescriptorDelegator;
import viewa.view.delegator.DefaultViewResourceDelegator;
import viewa.view.delegator.Delegator;
import viewa.view.delegator.DialogViewClosingWindowDelegator;
import viewa.view.delegator.NamedComponentsDelegator;
import viewa.view.delegator.ViewContainerControllerDelegator;
import viewa.widget.controller.DetailViewController;
import viewa.widget.view.delegator.DetailViewDelegator;

/**
 * This kind of view is tightly couple with the MasterView view. If you match a 
 * MasterView with a given DetailView, you can see row records from the MasterView
 * in the detailView with little effort.
 * 
 * @author Mario Garcia
 *
 * @param <D>
 */
public abstract class DetailView<D,M> extends AbstractViewContainerDialog{
	
	public static final String ACTION_SAVE = "saveAction";
	public static final String ACTION_CANCEL = "cancelAction";
	public static final String ACTION_HELP = "helpAction";
	
	/**
	 * @author Mario Garcia
	 *
	 */
	private class TranslucentGlassPane extends JComponent {
		private static final long serialVersionUID = 1L;
		private DetailView<D,M> view;
		/**
		 * 
		 */
		TranslucentGlassPane(){
			this.view = DetailView.this;
			addMouseListener(new MouseAdapter() {});
			addMouseMotionListener(new MouseMotionAdapter() {});
			addKeyListener(new KeyAdapter() {});
			addComponentListener(new ComponentAdapter() {
				public void componentShown(ComponentEvent evt){
					requestFocusInWindow();
				}
			});
			setFocusTraversalKeysEnabled(false);
			setOpaque(false);
		}
		
		public boolean contains(int x,int y){
			return !view.getJToolBar().getBounds().contains(x,y);
		}
		
		/* (non-Javadoc)
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		protected void paintComponent(Graphics g){
			Rectangle clip = g.getClipBounds();
			Area rect = new Area(clip);
			rect.subtract(new Area(view.getJToolBar().getBounds()));
			g.setClip(rect);
			Color alphaWhite = new Color(1.0f,1.0f,1.0f,0.65f);
			g.setColor(alphaWhite);
			g.fillRect(clip.x, clip.y, clip.width, clip.height);
		}		
	}
	public static final String MODEL_MASTER_OBJECT ="masterObject";
	public static final String MODEL_RELATIONSHIPS = "relationships";
	public static final String MODEL_WORKING_OBJECT ="workingObject";
	
	private List<Delegator> delegators;
	private List<String> nonEditableDisabledActions;


	/**
	 * @param id
	 * @param component
	 */
	public DetailView(String id, Component component) {
		super(id, component);
		this.getRootPane().setGlassPane(new TranslucentGlassPane());
	}

	/**
	 * @param id
	 * @param component
	 * @param modal
	 */
	public DetailView(String id, Component component, Boolean modal) {
		super(id, component, modal);
		this.getRootPane().setGlassPane(new TranslucentGlassPane());
	}

	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#getActionDescriptors()
	 */
	@Override
	public List<ViewActionDescriptor> getActionDescriptors() {
		List<ViewActionDescriptor> descriptors = new ArrayList<ViewActionDescriptor>();
		descriptors.add(new ViewActionDescriptor("/detailViewActions[@name='detailViewActions' and @visible='false']"));
		descriptors.add(new ViewActionDescriptor("/detailViewActions/" + ACTION_SAVE + "[@toolBarGroup='1' and @name='saveAction' and @icon='org/viewaframework/widget/icon/fan/img/page/page_save.png']"));		
		descriptors.add(new ViewActionDescriptor("/detailViewActions/" + ACTION_HELP + "[@toolBarGroup='1' and @text='helpAction' and @icon='org/viewaframework/widget/icon/fan/img/misc/help.png']"));
		descriptors.add(new ViewActionDescriptor("/detailViewActions/" + ACTION_CANCEL + "[@toolBarGroup='1' and @name='cancelAction' and @icon='org/viewaframework/widget/icon/fan/img/door/door.png']"));
		return descriptors;
	}

	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#getDelegators()
	 */
	@Override
	public List<Delegator> getDelegators() {
		if (this.delegators == null){
			this.delegators = Arrays.asList(		
				 /* Remember: The ActionDescriptorDelegator must be always the first delegator */
					new ActionDescriptorDelegator(),
					new NamedComponentsDelegator(),		
					new DetailViewDelegator(),
					new DefaultViewResourceDelegator(),
					new DialogViewClosingWindowDelegator(VIEW_DIALOG_NAME),
					new ViewContainerControllerDelegator());
		}
		return this.delegators;
	}
	
	public abstract Class<D> getDetailType() ;



	/**
	 * @return
	 */
	public List<String> getNonEditableDisabledActions() {
		if (this.nonEditableDisabledActions == null){
			this.nonEditableDisabledActions = new ArrayList<String>();
			this.nonEditableDisabledActions.add(DetailViewController.SAVE);
		}
		return nonEditableDisabledActions;
	}


	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#setDelegators(java.util.List)
	 */
	public void setDelegators(List<Delegator> delegators) {
		this.delegators = delegators;
	}
	
	
	public void setNonEditableDisabledActions(List<String> nonEditableActiveActions) {
		this.nonEditableDisabledActions = nonEditableActiveActions;
	}	
}
