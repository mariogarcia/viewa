package viewa.widget.controller;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.controller.AbstractActionController;
import viewa.view.ViewContainer;
import viewa.view.ViewException;
import viewa.widget.view.DetailView;
import viewa.widget.view.MasterView;
import viewa.widget.view.util.DetailViewUtil;

/**
 * @author Mario Garcia
 * 
 */
public abstract class MasterViewController<M, D> extends AbstractActionController {

	public static final String ACTION_ADD = "addAction";
	public static final String ACTION_DELETE = "deleteAction";
	public static final String ACTION_EDIT = "editAction";
	public static final String ACTION_EXIT = "exitAction";
	public static final String ACTION_SHOW = "showAction";
	private static final Log logger = LogFactory.getLog(MasterViewController.class);
	private DetailView<D, M> detailView;
	private DetailViewUtil<D,M> detailViewUtil;

	/**
	 * @return
	 */
	public DetailView<D, M> getDetailView() {
		return detailView;
	}

	public DetailViewUtil<D, M> getDetailViewUtil() {
		if (this.detailViewUtil == null && this.detailView != null){
			this.detailViewUtil = new DetailViewUtil<D, M>(this.detailView);
		}
		return detailViewUtil;
	}

	/**
	 * @param masterView
	 * @param eo
	 * @throws ViewException
	 */
	protected void handleMasterViewAction(MasterView<M> masterView, ActionEvent eo) throws ViewException { }

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.viewaframework.controller.AbstractViewController#handleView(org.
	 * viewaframework.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void handleView(ViewContainer view, ActionEvent eventObject) {
		MasterView<M> masterView = MasterView.class.cast(view);
		try {
			handleMasterViewAction(masterView, eventObject);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param masterView
	 * @param eo
	 * @throws ViewException
	 */
	protected void postHandlingMasterViewAction(MasterView<M> masterView,ActionEvent eo) throws ViewException {
		this.getDetailViewUtil().
			bindingWorkingObject().
			setEnableActionDescriptors(Arrays.asList("saveAction","exitAction"),Boolean.TRUE).
			setEnablePanel(Boolean.TRUE).
			setProgress(this.getViewManager(),0,"");		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.controller.AbstractViewController#postHandlingView
	 * (viewa.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void postHandlingView(ViewContainer view, ActionEvent eventObject) {
		MasterView<M> masterView = MasterView.class.cast(view);
		try {
			postHandlingMasterViewAction(masterView, eventObject);
		} catch (ViewException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param masterView
	 * @param eo
	 * @throws ViewException
	 */
	protected void preHandlingMasterViewAction(MasterView<M> masterView, ActionEvent eo) throws ViewException {
		/* Some UI initialization. The last statement must be opening the detail view otherwise changes may not
		 * be applied. */		
		this.getDetailViewUtil().				
			setEnablePanel(Boolean.FALSE).
			setEnableActionDescriptors(Arrays.asList("saveAction","exitAction"),Boolean.FALSE).			
			openViewFrom(masterView);		
	}

	@Override
	@SuppressWarnings("unchecked")
	public void preHandlingView(ViewContainer view, ActionEvent eventObject) {
		MasterView<M> masterView = MasterView.class.cast(view);
		try {
			preHandlingMasterViewAction(masterView, eventObject);
		} catch (ViewException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param detailView
	 */
	public void setDetailView(DetailView<D, M> detailView) {
		this.detailView = detailView;
	}

	public void setDetailViewUtil(DetailViewUtil<D, M> detailViewUtil) {
		this.detailViewUtil = detailViewUtil;
	}
}
