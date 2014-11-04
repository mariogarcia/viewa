package viewa.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JRootPane;

import viewa.model.ViewModel;
import viewa.view.delegator.ActionDescriptorDelegator;
import viewa.view.delegator.DefaultViewResourceDelegator;
import viewa.view.delegator.Delegator;
import viewa.view.delegator.DialogViewClosingWindowDelegator;
import viewa.view.delegator.NamedComponentsDelegator;
import viewa.view.delegator.ViewContainerControllerDelegator;

/**
 * 
 * This kind of view is shown as a javax.swing.JDialog window. This dialog has an access name through
 * the constant ViewContainerDialog.VIEW_DIALOG_NAME. That way you can change dialog through properties
 * like<br/><br/>
 * viewDialog.modal = true<br/>
 * viewDialog.setSize = 300,250<br/>
 * 
 * @author Mario Garcia
 *
 */
public abstract class AbstractViewContainerDialog extends AbstractViewContainer implements ViewContainerDialog
{
	public static final Integer DIMENSION_DEFAULT_HEIGHT = 300;
	public static final Integer DIMENSION_DEFAULT_WIDTH = 400;	
	
	private List<Delegator> delegators;
	private JDialog dialog;
	
	/**
	 * @param id
	 */
	public AbstractViewContainerDialog(String id,Component component){
		this(id,component,false);
	}

	/**
	 * @param id
	 * @param title
	 * @param modal
	 */
	public AbstractViewContainerDialog(String id,Component component,Boolean modal){
		super();
		this.setId(id);		
		this.getDialog().setSize(DIMENSION_DEFAULT_WIDTH,DIMENSION_DEFAULT_HEIGHT);
		this.getDialog().setModal(modal);
		this.setViewModelMap(new HashMap<String, ViewModel>());	
		this.getContentPane().add(component,BorderLayout.CENTER);
	}	
	
	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#getComponentsByName(java.lang.String)
	 */
	@Override
	public List<Component> getComponentsByName(String name) {
		List<Component> components = super.getComponentsByName(name);
		if (name.equalsIgnoreCase(VIEW_DIALOG_NAME)){
			if (components == null) {
				components = new ArrayList<Component>();
			}
			components.add(this.getDialog());
		}
		return components;
	}

	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#getDelegators()
	 */
	@Override
	public List<Delegator> getDelegators() {
		if (this.delegators == null){
			this.delegators = Arrays.asList(		
					 /* Renember: The ActionDescriptorDelegator must be always the first delegator */
						new ActionDescriptorDelegator(),
						new NamedComponentsDelegator(),	
						new DefaultViewResourceDelegator(),
						new DialogViewClosingWindowDelegator(VIEW_DIALOG_NAME),
						new ViewContainerControllerDelegator());
		}
		return this.delegators;
	}

	/* (non-Javadoc)
	 * @see viewa.view.ViewContainerDialog#getDialog()
	 */
	public JDialog getDialog() {
		if (this.dialog == null){
			this.dialog = new JDialog();
			this.dialog.setName(VIEW_DIALOG_NAME);
			this.dialog.setTitle(this.getTitle());
		}
		return this.dialog;
	}

	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#getRootPane()
	 */
	@Override
	public JRootPane getRootPane() {
		return this.getDialog().getRootPane();
	}

	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#setDelegators(java.util.List)
	 */
	public void setDelegators(List<Delegator> delegators) {
		this.delegators = delegators;
	}

	/* (non-Javadoc)
	 * @see viewa.view.ViewContainerDialog#setDialog(javax.swing.JDialog)
	 */
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;		
	}	

	/* (non-Javadoc)
	 * @see viewa.view.ViewContainer#viewClose()
	 */
	public void viewClose() throws ViewException{
		super.viewClose();
		if (this.getDialog() != null) this.getDialog().dispose();
	}
}
