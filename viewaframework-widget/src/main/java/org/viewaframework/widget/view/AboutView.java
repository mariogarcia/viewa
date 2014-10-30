package org.viewaframework.widget.view;

import org.viewaframework.annotation.Controller;
import org.viewaframework.annotation.Controllers;
import org.viewaframework.view.AbstractViewContainerDialog;
import org.viewaframework.widget.controller.ExitActionController;
import org.viewaframework.widget.view.ui.AboutPanel;

@Controllers({
	@Controller(type=ExitActionController.class,pattern="aboutClose")
})
public class AboutView extends AbstractViewContainerDialog{

	public static final String ID = "aboutViewId";
	
	public AboutView(){
		super(ID,new AboutPanel(),true);
	}
}
