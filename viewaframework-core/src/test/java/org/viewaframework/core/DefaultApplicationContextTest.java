package org.viewaframework.core;

import org.viewaframework.common.TestApplication;
import org.viewaframework.test.ApplicationTrapper;

import junit.framework.TestCase;

/**
 * @author Mario Garcia
 * @since 1.0.2
 */
public class DefaultApplicationContextTest extends TestCase {

	private ApplicationTrapper appTrapper;

	public void setUp() throws Exception {
		appTrapper = new ApplicationTrapper(new DefaultApplicationLauncher()
				.execute(TestApplication.class));
	}

	public void testContext() {
		ApplicationContext ctx = appTrapper.getTarget().getApplicationContext();
		ctx.setAttribute("name","John");
		assertEquals(ctx.getAttribute("name"),"John");
		ctx.removeAttribute("name");
		assertNull(ctx.getAttribute("name"));
		try{
			appTrapper.getTarget().setApplicationContext(new DefaultApplicationContext());
		} catch(Exception ex){
			assertTrue(ex instanceof ApplicationContextException);
		}
	}

	public void tearDown() {
		appTrapper.close();
	}

}
