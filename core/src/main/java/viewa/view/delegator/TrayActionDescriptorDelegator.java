package viewa.view.delegator;

import java.awt.SystemTray;

import javax.swing.JPopupMenu;

import viewa.util.ViewActionDescriptorFileHandler;
import viewa.util.ViewTrayIcon;
import viewa.util.ViewTrayIconBuilder;
import viewa.view.AbstractViewContainerTray;
import viewa.view.ViewContainer;
import viewa.view.ViewException;
import org.w3c.dom.Document;

/**
 * This Delegator builds the tray icon's view and adds the tray icon to the 
 * system tray. Once the view has been closed it removes the tray icon from the
 * system tray.
 * 
 * @author Mario Garcia
 * @since 1.0.4
 *
 */
public class TrayActionDescriptorDelegator implements Delegator{

	/**
	 * It's a shortcut
	 * 
	 * @param view
	 * @return
	 */
	private AbstractViewContainerTray castView(ViewContainer view) {
		return AbstractViewContainerTray.class.cast(view);
	}

	/* (non-Javadoc)
	 * @see viewa.view.delegator.Delegator#inject(viewa.view.ViewContainer)
	 */
	public void inject(ViewContainer view) throws ViewException {
		if (isRightViewType(view) && SystemTray.isSupported()){
			AbstractViewContainerTray trayView = castView(view);
			ViewActionDescriptorFileHandler handler = new ViewActionDescriptorFileHandler();
			Document document = handler.getDocument(trayView);			
			ViewTrayIconBuilder builder = new ViewTrayIconBuilder(document);
			try {
				ViewTrayIcon trayIcon = builder.build();
				addMenuToContentPane(view,trayIcon.getLeftClickMenu());
				addMenuToContentPane(view,trayIcon.getRightClickMenu());
				trayView.setTrayIcon(trayIcon);		
				SystemTray.getSystemTray().add(trayIcon);
			} catch (Exception e) {
				throw new ViewException(e.getMessage());
			}
		}		
	}

	/**
	 * A checked way of adding the menu to the content pane. This way both JPopupMenu are
	 * available through all views
	 * 
	 * @param view
	 * @param menu
	 */
	private void addMenuToContentPane(ViewContainer view,
			JPopupMenu menu) {
		if (menu != null){
			view.getContentPane().add(menu);
		}		
	}
	
	/**
	 * A checked way of hiding a menu
	 * 
	 * @param menu
	 */
	private void hideMenu(JPopupMenu menu){
		if (menu != null){
			menu.setVisible(false);
		}
	}

	/* (non-Javadoc)
	 * @see viewa.view.delegator.Delegator#clean(viewa.view.ViewContainer)
	 */
	public void clean(ViewContainer view) throws ViewException {
		if (isRightViewType(view) && SystemTray.isSupported()){
			AbstractViewContainerTray trayView = castView(view);			
			ViewTrayIcon viewTrayIcon = trayView.getTrayIcon();
		 /* Everything must be hidden before removing tray icon from system tray */
			hideMenu(viewTrayIcon.getLeftClickMenu());
			hideMenu(viewTrayIcon.getRightClickMenu());
		 /* Now we can safely remove the tray icon from system tray */
			SystemTray.getSystemTray().remove(viewTrayIcon);
		}
	}

	/**
	 * Checking if the view is the right one.
	 * 
	 * @param view
	 * @return
	 */
	private boolean isRightViewType(ViewContainer view) {
		return AbstractViewContainerTray.class
				.isAssignableFrom(view.getClass());
	}
}
