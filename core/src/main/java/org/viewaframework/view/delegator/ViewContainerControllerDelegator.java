package org.viewaframework.view.delegator;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JMenu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.viewaframework.controller.GenericListenerHandler;
import org.viewaframework.controller.ListenerProxy;
import org.viewaframework.controller.ViewController;
import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.ViewContainerDialog;
import org.viewaframework.view.ViewException;
import org.viewaframework.view.ViewManager;

/**
 * This is the default implementation for a ViewContainerControllerDelegator. It injects the given controllers
 * to the view when the view is initialized and it removes the controllers from the view once the viewClose
 * method from the view has been called.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */
public class ViewContainerControllerDelegator implements Delegator {

	private static final String EMPTY = "";
	private static final String POINT_ASTERISK = ".*";
	private static final String ASTERISK = "*";
	private static final String PLURAL = "s";
	private static final String GET_PREFIX = "get";
	private static final String ADD_LISTENER_PREFIX = "add";
	private static final String POINT = ".";
	private static final String REMOVE_LISTENER_PREFIX = "remove";
	private Log logger = LogFactory.getLog(ViewContainerControllerDelegator.class);
	

	/* (non-Javadoc)
	 * @see org.viewaframework.view.delegator.ViewContainerControllerDelegator#cleanControllers(org.viewaframework.view.ViewContainer)
	 */
	public void clean(ViewContainer view) throws ViewException{
		if (logger.isDebugEnabled()){
			logger.debug("Removing proxy listeners from view: "+view.getId());
		}
		try {
			if (view.getId().equalsIgnoreCase(ViewManager.ROOT_VIEW_ID)){
			 /* RootView only should have injected its jtoolbar and its jmenubar, otherwise
			  * you will inject all components within the application again. */
				injectListeners(view,view.getJToolBar(),view.getViewControllerMap(),REMOVE_LISTENER_PREFIX);
				injectListeners(view,view.getRootPane().getJMenuBar(),view.getViewControllerMap(),REMOVE_LISTENER_PREFIX);
			} else {
				injectListeners(view,view.getRootPane(),view.getViewControllerMap(),REMOVE_LISTENER_PREFIX);
			}
		} catch (Exception e) {			
			logger.error(e.getMessage());
			throw new ViewException();
		}
	}
	
	/**
	 * This method creates the right proxy Object to inject into the component's
	 * add***Listener method.
	 * 
	 * @param view
	 * @param component
	 * @param prefixAction
	 * @param vc
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	private void executeInjection(ViewContainer view, Component component,String prefixAction,ViewController<? extends EventListener, ? extends EventObject> vc) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (logger.isDebugEnabled()){
			logger.debug("Injecting proxy listeners in component: "+component.getName());
		}
		Class<? extends EventListener> 	parameterClass 	= vc.getSupportedClass();
		String 							methodName 		= prefixAction + parameterClass.getSimpleName();
		Method 							method 			= component.getClass().getMethod(methodName, parameterClass);
		method.invoke(component,Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{method.getParameterTypes()[0],ListenerProxy.class},new GenericListenerHandler(view,vc)));
	}
	
	/**
	 * This method removes all the proxy listeners from the view
	 * 
	 * @param component
	 * @param prefixAction
	 * @param vc
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void executeRemoving(Component component, String prefixAction,ViewController<? extends EventListener, ? extends EventObject> vc) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (logger.isDebugEnabled()){
			logger.debug("Removing proxy listeners in component: "+component.getName());
		}
		Class<? extends EventListener> 	parameterClass 	= vc.getSupportedClass();
		String 							removeMethodName 		= prefixAction + parameterClass.getSimpleName();
		String							getMethodName			= GET_PREFIX + parameterClass.getSimpleName() + PLURAL;
		Method 							getMethod 				= component.getClass().getMethod(getMethodName);
		Method 							removeMethod 			= component.getClass().getMethod(removeMethodName, parameterClass);
	 /* TODO Now this should work without looping */
		for (EventListener o : EventListener[].class.cast(getMethod.invoke(component))){
			if (Proxy.isProxyClass(o.getClass())){
				removeMethod.invoke(component,o);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewContainerControllerDelegator#injectControllers(org.viewaframework.view.ViewContainer, java.util.Map)
	 */
	public void inject(ViewContainer view) throws ViewException {				
		Map<String, List<ViewController<? extends EventListener, ? extends EventObject>>>  controllerMap = view.getViewControllerMap();	
		if (logger.isDebugEnabled()){
			logger.debug("Injecting proxy listeners from view: "+view.getId());
		}		
		try {
			if (view.getId().equalsIgnoreCase(ViewManager.ROOT_VIEW_ID)){
			 /* RootView only should have injected its jtoolbar and its jmenubar, otherwise
			  * you will inject all components within the application again. */
				injectListeners(view,view.getJToolBar(),controllerMap,ADD_LISTENER_PREFIX);
				injectListeners(view,view.getRootPane().getJMenuBar(),controllerMap,ADD_LISTENER_PREFIX);
			} else if (view instanceof ViewContainerDialog) {
				ViewContainerDialog viewDialog = ViewContainerDialog.class.cast(view);
				injectListeners(viewDialog,viewDialog.getDialog(),controllerMap,ADD_LISTENER_PREFIX);
			} else {
				injectListeners(view,view.getRootPane(),controllerMap,ADD_LISTENER_PREFIX);
			}
		} catch (Exception e) {			
			e.printStackTrace();
			throw new ViewException();
		}
	}	

	/**
	 * This method is one of the "automagic" methods from this framework. It is
	 * responsible for injecting the listeners declared for a given view to
	 * the components within that view.<br/><br/>
	 * 
	 * Notice that whatever the kind of listener declared, we are going to create
	 * a proxy that fits the requirements of the component, but the method
	 * itself as a proxy is going to redirect the call to our ViewController
	 * methods.<br/><br/>
	 * 
	 * @param view The view where the listeners are going to be injected
	 * @param component The component we are going to handle
	 * @param viewControllers A Map where we are going to look for the listeners of the current component
	 * @throws Exception If anything goes wrong...
	 */
	private void injectListeners(ViewContainer view,Component component,
			Map<String,List<ViewController<? extends EventListener,? extends EventObject>>> viewControllers,
			String prefixAction) throws Exception {
	 /* Getting component information */
		String 		viewId 					= view.getId();
		String 		componentName 			= component!=null ? component.getName() : null;
		Set<String> viewControllersKeys 	= viewControllers.keySet();
		List<ViewController<
			? extends EventListener,
			? extends EventObject>> vcl 	= null;
		Iterator<String> 			it 		= viewControllersKeys.iterator();
		Boolean 	controllerListFound 	= false;
	 /* Checking if there are controllers available for this component within the given view. */
		while (it.hasNext() && !controllerListFound && componentName != null){
			String controllerKey = it.next();
			String workingKey = controllerKey.replace(viewId + POINT , EMPTY);
			if (componentName!= null && componentName.matches(workingKey.replace(ASTERISK,POINT_ASTERISK))){
				vcl = viewControllers.get(controllerKey);
				controllerListFound = true;
				if (vcl!=null){
					for (ViewController<? extends EventListener,? extends EventObject> vc: vcl){
						if (vc!=null){
							if (prefixAction.equalsIgnoreCase(ADD_LISTENER_PREFIX)){
								executeInjection(view, component, prefixAction, vc);	
							} else if (prefixAction.equalsIgnoreCase(REMOVE_LISTENER_PREFIX)){
								executeRemoving(component, prefixAction, vc);
							}
						}
					}
				}				
			}		
		}			
	/* Once the component has its controllers injected, their children are injected too. */
		if (component instanceof Container){			
		/*	Menu components should be treated specially */
			if (component instanceof JMenu){			
				for (Component c : JMenu.class.cast(component).getMenuComponents()){
					injectListeners(view,c,viewControllers,prefixAction);
				}						
			} else {		
				if (component instanceof JComponent){
						injectListeners(view,JComponent.class.cast(component).getComponentPopupMenu(),viewControllers,prefixAction);
				}				
				for (Component c : Container.class.cast(component).getComponents()){
					injectListeners(view,c,viewControllers,prefixAction);
				}
			}
		} 
	}
}
