package org.viewaframework.docking.mydoggy;

import org.viewaframework.core.DefaultApplicationLauncher;
import org.viewaframework.test.ApplicationTrapper;

import junit.framework.TestCase;

public class MyDoggyPerspectiveTest extends TestCase{

	private ApplicationTrapper app;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() throws Exception{
		app = new ApplicationTrapper(new DefaultApplicationLauncher().execute(MyDoggyApplication.class));
		app.getSettings().setTimeBeforeNextEvent(2500);
	}
	
	public void testMyDoggyApplication(){
		assertNotNull(app.view(MyDoggyView.ID));
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown(){
		app.close();
	}
}
