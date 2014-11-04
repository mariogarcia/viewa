package viewa.view;

import java.awt.Component;

/**
 * @author Mario Garcia
 *
 */
public class DefaultViewContainerEditor extends DefaultViewContainer implements ViewContainerEditor{

	/**
	 *  Default constructor
	 */
	public DefaultViewContainerEditor(){
		super();
	}
	
	/**
	 * @param id
	 * @param component
	 */
	public DefaultViewContainerEditor(String id,Component component){
		super(id,id,component);
	}
	
	/**
	 * @param id
	 * @param title
	 * @param panel
	 */
	public DefaultViewContainerEditor(String id,String title,Component panel){
		super(id,title,panel);
	}
	
}
