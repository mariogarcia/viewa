package ${groupId};

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.viewaframework.core.DefaultApplicationLauncher;
import org.viewaframework.test.ApplicationTrapper;
import org.viewaframework.view.ViewManager;
import org.viewaframework.widget.view.AboutView;

/**
 * This test case could be a good example about how to test Viewa applications with JUnit 4.7.
 * 
 * This kind of test is recommended if you want to use a single instance application to run all
 * tests within this class.
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public class MyApplicationRootViewTest2 {

	private static ApplicationTrapper app;
    
    /**
     * Starting application
     * 
     * @throws Exception
     */
    @BeforeClass public static void initialization() throws Exception{
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
    @Test
	public void testAboutView(){
		TestCase.assertNull(
			app.menuItem("about").click().
				applicationTrapper().
					view(AboutView.ID).button("aboutClose").requireEnabled().click().
				applicationTrapper().
					view(AboutView.ID).getTarget());
	}
	
	
	/**
	 * Testing all required menu items at startup
	 */
    @Test
	public void testMenuItems(){
		app.view(ViewManager.ROOT_VIEW_ID).menuItem("exit").requireEnabled().
			view().menuItem("cut").requireEnabled().
			view().menuItem("paste").requireEnabled().
			view().menuItem("copy").requireEnabled().
			view().menuItem("selectAll").requireEnabled().
			view().menuItem("about").requireEnabled();
	}
    
    
    /**
     * Closing application
     */
    @AfterClass public static void finalization(){
        app.close();
    }
	
}
