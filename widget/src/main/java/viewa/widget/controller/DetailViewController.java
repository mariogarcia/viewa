package viewa.widget.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.controller.AbstractActionController;
import viewa.util.StatusBar;
import viewa.view.ViewContainer;
import viewa.view.ViewException;
import viewa.widget.view.DetailView;

/**
 * @author mario
 * 
 */
public abstract class DetailViewController<D,M> extends AbstractActionController {

	public static final String CANCEL = "cancelAction";
	public static final String HELP = "helpAction";
	private static final Log logger = LogFactory.getLog(DetailViewController.class);
	public static final String SAVE = "saveAction";

	/**
	 * @param view
	 * @return
	 */
	public JLabel getStatusBarMessageBar(ViewContainer view){
		return JLabel.class.cast(view.getApplication().
				getViewManager().getRootView().
				getComponentByName(StatusBar.LEFT_PANEL_LABEL));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.viewaframework.controller.AbstractViewController#handleView(org.
	 * viewaframework.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void handleView(ViewContainer view, ActionEvent eventObject) {
		try {
			String actionCommand = Component.class.cast(eventObject.getSource()).getName();
			DetailView<D,M> detailView = DetailView.class.cast(view);
			D object2Save = (D) view.getModelValue(DetailView.MODEL_WORKING_OBJECT);
		 /* Depending on the actionCommand */
			if (actionCommand.equals(SAVE)) {
				handleViewAccept(detailView, eventObject, object2Save);
			} else if (actionCommand.equals(CANCEL)) {
				handleViewCancel(detailView, eventObject, object2Save);
			} else if (actionCommand.equals(HELP)) {
				handleViewHelp(detailView, eventObject, object2Save);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param view
	 * @param eventObject
	 */
	protected void handleViewAccept(DetailView<D,M> view,ActionEvent eventObject, D value) throws Exception {}

	/**
	 * @param view
	 * @param eventObject
	 */
	protected void handleViewCancel(DetailView<D,M> view,ActionEvent eventObject, D value) throws Exception {}

	/**
	 * @param view
	 * @param eventObject
	 */
	protected void handleViewHelp(DetailView<D,M> view, ActionEvent eventObject,D value) throws Exception {}

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
		try {
			String actionCommand = Component.class.cast(eventObject.getSource()).getName();
			DetailView<D,M> detailView = DetailView.class.cast(view);
			D object2Save = (D) view.getModelValue(DetailView.MODEL_WORKING_OBJECT);
		 /* Depending on actionCommand */
			if (actionCommand.equals(SAVE)) {
				postHandlingViewAccept(detailView, eventObject, object2Save);
			} else if (actionCommand.equals(CANCEL)) {
				postHandlingViewCancel(detailView, eventObject, object2Save);
			} else if (actionCommand.equals(HELP)) {
				postHandlingViewHelp(detailView, eventObject, object2Save);
			}
			view.getApplication().getViewManager().removeView(view);
		} catch (ViewException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param view
	 * @param eventObject
	 */
	protected void postHandlingViewAccept(DetailView<D,M> view,ActionEvent eventObject, D value) throws Exception {}

	/**
	 * @param view
	 * @param eventObject
	 */
	protected void postHandlingViewCancel(DetailView<D,M> view,ActionEvent eventObject, D value) throws Exception {}

	/**
	 * @param view
	 * @param eventObject
	 */
	protected void postHandlingViewHelp(DetailView<D,M> view,ActionEvent eventObject, D value) throws Exception {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.controller.AbstractViewController#preHandlingView(
	 * viewa.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void preHandlingView(ViewContainer view, ActionEvent eventObject) {
		String actionCommand = Component.class.cast(eventObject.getSource()).getName();
		DetailView<D,M> detailView = DetailView.class.cast(view);
		if (actionCommand.equals(SAVE)) {
			preHandlingViewAccept(detailView, eventObject);
		} else if (actionCommand.equals(CANCEL)) {
			preHandlingViewCancel(detailView, eventObject);
		} else if (actionCommand.equals(HELP)) {
			preHandlingViewHelp(detailView, eventObject);
		}
	}

	/**
	 * @param view
	 * @param eventObject
	 */
	protected void preHandlingViewAccept(DetailView<D,M> view,ActionEvent eventObject) {}

	/**
	 * @param view
	 * @param eventObject
	 */
	protected void preHandlingViewCancel(DetailView<D,M> view,ActionEvent eventObject) {}

	/**
	 * @param view
	 * @param eventObject
	 */
	protected void preHandlingViewHelp(DetailView<D,M> view,ActionEvent eventObject) {}
}
