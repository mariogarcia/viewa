package viewa.widget.view.delegator;

import java.awt.Component;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.view.ViewContainer;
import viewa.view.ViewException;
import viewa.view.delegator.Delegator;
import viewa.widget.view.DetailView;

/**
 * 
 * @author Mario Garcia
 * 
 */
public class DetailViewDelegator implements Delegator {

	private static final Log logger = LogFactory.getLog(DetailViewDelegator.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.view.delegator.Delegator#clean(org.viewaframework.
	 * view.ViewContainer)
	 */
	public void clean(ViewContainer arg0) throws ViewException {
		cleanNonEditableElements(arg0);		
	}

	
	/**
	 * @param view
	 */
	@SuppressWarnings("unchecked")
	private void cleanNonEditableElements(ViewContainer view) {
		if (logger.isDebugEnabled()){
			logger.debug("Restoring action descriptor components");
		}
		DetailView detailView = DetailView.class.cast(view);
		List<String> availableActions = detailView.getNonEditableDisabledActions();
		for (String st: availableActions){
			List<Component> componentsToEnable = detailView.getComponentsByName(st);
			if (componentsToEnable != null){
				for (Component c : componentsToEnable){
					c.setEnabled(true);
				}
			}
		}
		detailView.getGlassPane().setVisible(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * viewa.view.delegator.Delegator#inject(org.viewaframework
	 * .view.ViewContainer)
	 */
	public void inject(ViewContainer arg0) throws ViewException {
		injectNonEditableViewElements(arg0);
	}

	/**
	 * @param view
	 */
	@SuppressWarnings("unchecked")
	private void injectNonEditableViewElements(ViewContainer view) {
		DetailView detailView = DetailView.class.cast(view);
		List<String> availableActions = detailView.getNonEditableDisabledActions();
		for (String st: availableActions){
			List<Component> componentsToDisable = detailView.getComponentsByName(st);
			if (componentsToDisable != null){
				for (Component c : componentsToDisable){
					c.setEnabled(false);
				}
			}
		}
	}
}
