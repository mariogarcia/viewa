package viewa.widget.view;

import viewa.annotation.Controller;
import viewa.annotation.Controllers;
import viewa.view.AbstractViewContainerDialog;
import viewa.widget.controller.ExitActionController;
import viewa.widget.view.ui.AboutPanel;

@Controllers({
	@Controller(type=ExitActionController.class,pattern="aboutClose")
})
public class AboutView extends AbstractViewContainerDialog{

	public static final String ID = "aboutViewId";
	
	public AboutView(){
		super(ID,new AboutPanel(),true);
	}
}
