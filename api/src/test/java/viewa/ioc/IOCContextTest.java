package viewa.ioc;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import viewa.core.Application;
import viewa.core.ApplicationContext;

/**
 * This test check out the functionality of the IOCContext contract.
 * 
 * @author Mario Garcia
 *
 */
public class IOCContextTest extends TestCase{

	private Application application;
	private ApplicationContext applicationContext;
	private IOCContext iocContext;
	private String name;
	
	public void setUp(){
		Mockery mockery = new Mockery();
		name = new String("John");
		application = mockery.mock(Application.class);
		applicationContext = mockery.mock(ApplicationContext.class);
		iocContext = mockery.mock(IOCContext.class);
		mockery.checking(new Expectations(){
			{ atLeast(1).of(application).getApplicationContext(); will(returnValue(applicationContext));}
			{ atLeast(1).of(applicationContext).getAttribute(IOCContext.ID); will(returnValue(iocContext));}
			{ atLeast(1).of(iocContext).getBean("name"); will(returnValue(name));}
		});
	}
	
	public void testIOContextRetrieval(){
		Object ioc = application.getApplicationContext().getAttribute(IOCContext.ID);
		assertNotNull("Only one IOCContext can be stored in application context under the name of IOCContext.ID",ioc);
		assertTrue("It should be instanceof IOContext",ioc instanceof IOCContext);
	}

	public void testIOCContextBeanRetrieval(){
		IOCContext ioc = IOCContext.class.cast(application.getApplicationContext().getAttribute(IOCContext.ID));
		assertNotNull(ioc);
		assertEquals("Beans must be retrieved throught the method getBean",ioc.getBean("name"),name);		
	}
	
}
