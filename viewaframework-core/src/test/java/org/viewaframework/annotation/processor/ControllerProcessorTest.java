package org.viewaframework.annotation.processor;

import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.viewaframework.common.TestView;
import org.viewaframework.controller.ViewController;
import org.viewaframework.core.ApplicationContext;
import org.viewaframework.ioc.IOCContext;
import org.viewaframework.view.ViewContainer;

/**
 * 
 * @author Mario Garcia
 * @since 1.0.2
 * 
 */
public class ControllerProcessorTest extends TestCase{

	private ViewContainer view;
	private ApplicationContext ctx;
	
	public void setUp(){
		Mockery mockery = new Mockery();
		ctx = mockery.mock(ApplicationContext.class);
		view = new TestView();
		mockery.checking(new Expectations(){
			{oneOf(ctx).getAttribute(IOCContext.ID); will(returnValue(null));}
		});
	}
	
	public void testAnnotationProcessor()throws Exception{
		ControllersProcessor processor = new ControllersProcessor(view,ctx);
		Map<String,List<ViewController<? extends EventListener,? extends EventObject>>> clazzes = processor.process();		
		assertNotNull(clazzes);
		assertNotNull(clazzes.get(view.getId()+".testButton"));
		assertEquals(clazzes.get(view.getId()+".testButton").size(),1);
	}
	
}
