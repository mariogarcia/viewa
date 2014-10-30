package org.viewaframework.widget.swing.builder;

import javax.swing.UIManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.viewaframework.core.DefaultApplicationLauncher;
import org.viewaframework.test.ApplicationTrapper;
import org.viewaframework.util.ResourceLocator;
import org.viewaframework.widget.swing.builder.util.SwingBuilderApplication;

/**
 * This is a simple test for showing how to combine several component and container builders to
 * create a simple user interface. 
 * 
 * @author mgg
 *
 */
public class SwingBuilderTest {
	
	private ApplicationTrapper app;
	
	@BeforeClass
	public static void initClass() throws Exception {
		UIManager.put("JXDatePicker.arrowIcon",
			ResourceLocator.getImageIcon(
				SwingBuilderTest.class,
				"org/viewaframework/widget/icon/fan/img/date/date.png"));
	}
	
	/**
	 * @throws Exception
	 */
	@Before
	public void init() throws Exception {
		app = new ApplicationTrapper(new DefaultApplicationLauncher().execute(SwingBuilderApplication.class));
		app.getSettings().setTimeBeforeNextEvent(5000);
	}
	
	/**
	 * This test just creates a simple user interface with some of the component
	 * and container builders.
	 */
	@Test
	public void testSwingBuilderInterface() throws Exception{
		app.
			log("Introducing new departure site").
				text("from").
					setText("Madrid (Barajas)").				
					requireEnabled().applicationTrapper().
			log("Introducing new arrival site").
				text("to").
					setText("London (Heathrow)").
					requireEnabled();
	}
}
