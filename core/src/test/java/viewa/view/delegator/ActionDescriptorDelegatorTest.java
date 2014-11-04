package viewa.view.delegator;

import junit.framework.TestCase;

import viewa.common.TestApplication;
import viewa.common.TestView;
import viewa.core.DefaultApplicationLauncher;
import viewa.test.ApplicationTrapper;

public class ActionDescriptorDelegatorTest extends TestCase{

	private ApplicationTrapper trapper;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp()throws Exception{
		trapper = new ApplicationTrapper(new DefaultApplicationLauncher().execute(TestApplication.class));
		trapper.getSettings().setTimeBeforeNextEvent(2500);
	}
	
	/**
	 * 
	 */
	public void testToolBarButtonWithNotTest(){
		trapper.
			view(TestView.ID).
				menuItem("add").
					requireEnabled().
					requireText("Adding");
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown(){
		trapper.close();
	}
	
}
