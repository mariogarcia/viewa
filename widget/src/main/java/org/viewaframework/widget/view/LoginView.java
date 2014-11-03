package org.viewaframework.widget.view;

import java.util.Arrays;
import java.util.List;

import org.viewaframework.swing.LoginTitledPanel;
import org.viewaframework.view.AbstractViewContainerDialog;
import org.viewaframework.view.delegator.ActionDescriptorDelegator;
import org.viewaframework.view.delegator.DefaultViewResourceDelegator;
import org.viewaframework.view.delegator.Delegator;
import org.viewaframework.view.delegator.NamedComponentsDelegator;
import org.viewaframework.view.delegator.ViewContainerControllerDelegator;
import org.viewaframework.widget.view.delegator.LoginViewWindowDelegator;

public class LoginView extends AbstractViewContainerDialog{

	public static final String ID ="loginDialogViewId";
	private List<Delegator> delegators;
	
	public LoginView() {
		super(ID,new LoginTitledPanel());
		this.getDialog().setModal(true);
	}
	
	@Override
	public List<Delegator> getDelegators() {
		if (this.delegators == null){
			this.delegators = Arrays.asList(		
				 /* Remember: The ActionDescriptorDelegator must be always the first delegator */
					new ActionDescriptorDelegator(),
					new NamedComponentsDelegator(),	
					new DefaultViewResourceDelegator(),
					new LoginViewWindowDelegator(VIEW_DIALOG_NAME),
					new ViewContainerControllerDelegator());					
		}
		return this.delegators;
	}
	
	
}
