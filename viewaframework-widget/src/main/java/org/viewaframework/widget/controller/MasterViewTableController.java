package org.viewaframework.widget.controller;

import java.awt.event.MouseEvent;

import org.viewaframework.controller.AbstractMouseClickController;
import org.viewaframework.util.BeanUtils;
import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.ViewException;
import org.viewaframework.widget.view.DetailView;
import org.viewaframework.widget.view.MasterView;

/**
 * @author Mario Garcia
 *
 * @param <T>
 */
public abstract class MasterViewTableController<T,D> extends AbstractMouseClickController{

	private DetailView<D,T> detailView;
	
	/**
	 * 
	 */
	public MasterViewTableController(){
		super(2);
	}

	/**
	 * @return
	 */
	public DetailView<D,T> getDetailView() {
		return detailView;
	}

	/**
	 * @param masterView
	 * @param eventObject
	 */
	protected void handleClickView(MasterView<T> masterView,MouseEvent eventObject) {}

	/* (non-Javadoc)
	 * @see org.viewaframework.controller.AbstractViewController#handleView(org.viewaframework.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void handleView(ViewContainer view, MouseEvent eventObject) {
		try {
			MasterView<T> 	masterView 		= MasterView.class.cast(view);
			DetailView<D,T> 	detailView 		= this.getDetailView();
			T 				workingObject 	= masterView.getMasterType().newInstance();
			T 				selectedObject 	= masterView.getModel().getSelectedObject();
		 /* Populating detailView model */
			detailView.addModelValue(DetailView.MODEL_MASTER_OBJECT,selectedObject);			
			BeanUtils.copyProperties(workingObject,selectedObject);
			detailView.addModelValue(DetailView.MODEL_WORKING_OBJECT,workingObject);
		 /* Executing callback actions */
			handleClickView(masterView,eventObject);	
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * @param masterView
	 * @param eventObject
	 */
	protected void postHandlingClickView(MasterView<T> masterView,MouseEvent eventObject) {}

	/* (non-Javadoc)
	 * @see org.viewaframework.controller.AbstractViewController#postHandlingView(org.viewaframework.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void postHandlingView(ViewContainer view, MouseEvent eventObject) {
		MasterView<T> masterView = MasterView.class.cast(view);
		try {
			postHandlingClickView(masterView,eventObject);
			masterView.getApplication().getViewManager().addView(this.getDetailView());
		} catch (ViewException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param masterView
	 * @param eventObject
	 */
	protected void preHandlingClickView(MasterView<T> masterView,MouseEvent eventObject) {}

	/* (non-Javadoc)
	 * @see org.viewaframework.controller.AbstractViewController#preHandlingView(org.viewaframework.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void preHandlingView(ViewContainer view, MouseEvent eventObject) {
		MasterView<T> masterView = MasterView.class.cast(view);
		preHandlingClickView(masterView,eventObject);
	}

	/**
	 * @param detailView
	 */
	public void setDetailView(DetailView<D,T> detailView) {
		this.detailView = detailView;
	}
}
