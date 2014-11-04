package viewa.common;

import java.util.Arrays;

import viewa.annotation.Controller;
import viewa.annotation.Controllers;
import viewa.view.DefaultViewContainer;
import viewa.view.ViewActionDescriptor;

/**
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
@Controllers({
	@Controller(type=TestController.class,pattern="testButton"),
	@Controller(type=TestControllerFailure.class,pattern="failureButton")
})
public class TestView extends DefaultViewContainer{
	public static final String ID = "ControllerTestViewId";	
	/**
	 * Default constructor
	 */
	public TestView(){
		super(ID,new TestPanel());
		this.setActionDescriptors(Arrays.asList(
				new ViewActionDescriptor("/edit[@text='edit']")));
	}	
}
