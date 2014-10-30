package ${groupId};

import org.viewaframework.core.DefaultApplicationLauncher;
import org.viewaframework.test.ApplicationTrapper;
import org.viewaframework.widget.view.AboutView;

import junit.framework.TestCase;

/**
 * This test case could be a good example about how to test Viewa applications. This example
 * is a classic JUnit TestCase with setUp() and tearDown() methods for setting up and
 * releasing application resources respectively.
 * 
 * This kind of test is recommended if you need to create a new instance each time you 
 * execute a test.
 * 
 * @author Mario Garcia
 * @since 1.0.2
 */
public class MyApplicationRootViewTest extends TestCase{

	private ApplicationTrapper app;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp()throws Exception{
	 /* Creating a new application before each test */
		app = new ApplicationTrapper(new DefaultApplicationLauncher().execute(MyApplication.class));
	 /* Setting delay between calls */
		app.getSettings().setTimeBeforeNextEvent(2500);
	}
	
	/**
	 *  When user clicks the "about" menu item then an AboutView window should appear. Then
	 *  if the user clicks the "aboutClose" button from the AboutView window then the
	 *  view should be destroy an no instances of that view can be found in the application.
	 */
	public void testAboutView(){
		assertNull(
			app.menuItem("about").click().
			view(AboutView.ID).button("aboutClose").requireEnabled().click().
			view(AboutView.ID).getTarget());
	}
	
	
	/**
	 * Testing all required menu items at startup
	 */
	public void testMenuItems(){
		app.menuItem("exit").requireEnabled();
		app.menuItem("cut").requireEnabled();
		app.menuItem("paste").requireEnabled();
		app.menuItem("copy").requireEnabled();
		app.menuItem("selectAll").requireEnabled();
		app.menuItem("about").requireEnabled();
	}
	//Tests
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown(){
	 /* At the end of every test the application is closed */
		app.close();
	}
	
}
