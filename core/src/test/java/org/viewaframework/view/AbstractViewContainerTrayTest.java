package org.viewaframework.view;

import junit.framework.TestCase;

import org.viewaframework.common.MyTrayView;
import org.viewaframework.common.TestApplication;
import org.viewaframework.core.DefaultApplicationLauncher;
import org.viewaframework.test.ApplicationTrapper;

public class AbstractViewContainerTrayTest extends TestCase {

	private ApplicationTrapper app;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() throws Exception {
		app = new ApplicationTrapper(new DefaultApplicationLauncher()
				.execute(TestApplication.class));
	}

	/**
	 * 
	 */
	public void testApplicationViewAnnotations() {
		app.view(MyTrayView.ID).
			menuItem("hiderestore").
				requireEnabled().
				click(5000);

		ViewContainerFrame frame = ViewContainerFrame.class.cast(
				app.view(ViewManager.ROOT_VIEW_ID).getTarget());
		assertFalse(frame.getFrame().isVisible());

		app.view(MyTrayView.ID).
			menuItem("hiderestore").
				requireEnabled().
				click(5000);

		assertTrue(frame.getFrame().isVisible());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown() {
		app.close();
	}
}