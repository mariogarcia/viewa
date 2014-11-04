package viewa.core;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

public class ApplicationContextAwareTest extends TestCase{

	private Application application;
	private ApplicationContext applicationContext;
	
	public void setUp() throws Exception{
		Mockery mockery = new Mockery();
		application = mockery.mock(Application.class);
		applicationContext = mockery.mock(ApplicationContext.class);
		mockery.checking(new Expectations(){
			{ oneOf(application).setApplicationContext(applicationContext);}
			{ atLeast(1).of(application).getApplicationContext(); will(returnValue(applicationContext));}
			{ oneOf(applicationContext).setAttribute("name","John");}
			{ oneOf(applicationContext).getAttribute("name"); will(returnValue("John"));}
			{ oneOf(application).setApplicationContext(applicationContext); will(throwException(new ApplicationContextException()));}			
		});
	}
	
	public void testApplicationContextAwareness() throws Exception{
		application.setApplicationContext(this.applicationContext);
		assertNotNull("Once we've set up the context, it will be available",application.getApplicationContext());
		application.getApplicationContext().setAttribute("name","John");
		assertEquals("We can retrieve attributes from application context",application.getApplicationContext().getAttribute("name"),"John");		
		try{
			application.setApplicationContext(this.applicationContext);
		} catch (Exception ex){
			assertTrue("ApplicationContext can only be set up once",ex instanceof ApplicationContextException);
		}
	}
	
}
