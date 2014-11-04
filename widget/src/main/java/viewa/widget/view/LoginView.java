package viewa.widget.view;

import java.util.Arrays;
import java.util.List;

import viewa.swing.LoginTitledPanel;
import viewa.view.AbstractViewContainerDialog;
import viewa.view.delegator.ActionDescriptorDelegator;
import viewa.view.delegator.DefaultViewResourceDelegator;
import viewa.view.delegator.Delegator;
import viewa.view.delegator.NamedComponentsDelegator;
import viewa.view.delegator.ViewContainerControllerDelegator;
import viewa.widget.view.delegator.LoginViewWindowDelegator;

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
