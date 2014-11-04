package viewa.core;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

public class ApplicationContextTest extends TestCase{

	private ApplicationContext ctx;
	
	public void setUp(){
		Mockery mockery = new Mockery();
		ctx = mockery.mock(ApplicationContext.class);
		mockery.checking(new Expectations(){
			{ oneOf(ctx).setAttribute("name","John");}
			{ oneOf(ctx).removeAttribute("name");}
			{ exactly(2).of(ctx).getAttribute("name"); 
				will(onConsecutiveCalls(
						returnValue(new String("John")),
						returnValue(null)));}
		});
	}
	
	public void testApplicationContextMethods(){
		assertNotNull(ctx);
		ctx.setAttribute("name",new String("John"));
		assertEquals(ctx.getAttribute("name"),"John");
		ctx.removeAttribute("name");
		assertNull(ctx.getAttribute("name"));
	}
}
