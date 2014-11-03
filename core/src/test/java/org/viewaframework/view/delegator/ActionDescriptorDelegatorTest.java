package org.viewaframework.view.delegator;

import junit.framework.TestCase;

import org.viewaframework.common.TestApplication;
import org.viewaframework.common.TestView;
import org.viewaframework.core.DefaultApplicationLauncher;
import org.viewaframework.test.ApplicationTrapper;

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
