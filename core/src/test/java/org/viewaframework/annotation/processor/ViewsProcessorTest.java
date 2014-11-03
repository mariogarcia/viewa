package org.viewaframework.annotation.processor;


import java.util.List;

import junit.framework.TestCase;

import org.viewaframework.common.TestApplication;
import org.viewaframework.core.DefaultApplicationLauncher;
import org.viewaframework.test.ApplicationTrapper;

/**
 * This class tests the ApplicationProcessor behavior
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public class ViewsProcessorTest extends TestCase {

	private ApplicationTrapper app;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() throws Exception {
		app = new ApplicationTrapper(new DefaultApplicationLauncher().execute(TestApplication.class));
	}
	
	
	public void testAnnotations() throws Exception{
		AnnotationProcessor processor = new ViewsProcessor(app.getTarget());
		processor.process();		
		@SuppressWarnings("unchecked") 
		List<ViewsProcessorWrapper> wrappers = List.class.cast(processor.getResult());
		boolean thereIsTrayView = false;
		for (ViewsProcessorWrapper wrapper : wrappers){
			if (wrapper.isTrayView()){
				thereIsTrayView = true;
			}
		}
		assertTrue(thereIsTrayView);
		assertNotNull(wrappers);
		assertTrue(wrappers.size() > 0);
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown(){
		app.close();
	}
}
