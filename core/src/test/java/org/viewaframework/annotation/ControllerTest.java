package org.viewaframework.annotation;

import org.viewaframework.common.TestApplication;
import org.viewaframework.common.TestView;
import org.viewaframework.core.DefaultApplicationLauncher;
import org.viewaframework.test.ApplicationTrapper;

import junit.framework.TestCase;

/**
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public class ControllerTest extends TestCase{

	private ApplicationTrapper app;
	
	public void setUp()throws Exception{
		app = new ApplicationTrapper(new DefaultApplicationLauncher().execute(TestApplication.class));
		app.getSettings().setTimeBeforeNextEvent(2500);
	}
	
	public void testAnnotations(){
		app.button(TestView.ID,"testButton").click(2500);
		app.text(TestView.ID,"text").requireText("Hey it worked");
	}
	
	public void tearDown(){
		app.close();
	}
	
}
