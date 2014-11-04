package viewa.core;

import viewa.common.MyTrayView;
import viewa.common.TestApplication;
import viewa.common.TestView;
import viewa.test.ApplicationTrapper;

import junit.framework.TestCase;

/**
 * @author Mario Garcia
 * @since 1.0.4
 *
 */
public class DefaultApplicationTest extends TestCase{

	private ApplicationTrapper app;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() throws Exception{
		app = new ApplicationTrapper(new DefaultApplicationLauncher().execute(TestApplication.class));
	}
	
	/**
	 *  This test checks the method responsible for hiding and
	 *  restoring the root view.
	 */
	public void testSetVisible(){
		app.
			view(MyTrayView.ID).
				menuItem("hiderestore").
				click(3000).
				applicationTrapper().
				requireVisible(false).					
			view(MyTrayView.ID).
				menuItem("hiderestore").
				click(3000).
				applicationTrapper().
				requireVisible()
			.view(TestView.ID).
				button("failureButton").
				click(5000).
			view().
				text("text").
				requireText("Bad situation!");		
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown(){
		app.close();
	}
}
