package org.viewaframework.annotation;

import junit.framework.TestCase;

import org.viewaframework.common.TestApplication;
import org.viewaframework.common.TestView;
import org.viewaframework.core.DefaultApplicationLauncher;
import org.viewaframework.test.ApplicationTrapper;

/**
 * This test checks whether the @Views and @View annotations have worked
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public class ViewsTest extends TestCase{

	private ApplicationTrapper app;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp()throws Exception{
		app = new ApplicationTrapper(
				new DefaultApplicationLauncher().
					execute(TestApplication.class));
	}
	
	/**
	 * 
	 */
	public void testApplicationViewAnnotations(){
		assertTrue("There's an Views annotation",app.getTarget().getClass().getAnnotation(Views.class) != null);
		app.view(TestView.ID).requireOpened().
				button("testButton").requireEnabled();
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown(){
		app.close();
	}
}
