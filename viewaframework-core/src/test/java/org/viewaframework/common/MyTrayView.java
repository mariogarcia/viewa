package org.viewaframework.common;

import org.viewaframework.annotation.Controller;
import org.viewaframework.annotation.Controllers;
import org.viewaframework.view.AbstractViewContainerTray;

@Controllers({
	@Controller(type=MyTrayViewController.class,pattern="hiderestore")
})
public class MyTrayView extends AbstractViewContainerTray{
	
	public static final String ID = "MyTrayViewID";
	
	public MyTrayView(){
		super(ID);
	}
}
