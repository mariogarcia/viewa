package viewa.common;

import viewa.annotation.Controller;
import viewa.annotation.Controllers;
import viewa.view.AbstractViewContainerTray;

@Controllers({
	@Controller(type=MyTrayViewController.class,pattern="hiderestore")
})
public class MyTrayView extends AbstractViewContainerTray{
	
	public static final String ID = "MyTrayViewID";
	
	public MyTrayView(){
		super(ID);
	}
}
