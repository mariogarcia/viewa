package viewa.swing.binding.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.TestCase;

import viewa.swing.binding.util.Author;

public class BasicModelAdapterTest extends TestCase{

	private BeanAdapter<Author> author;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() throws Exception {
		author = new BasicBeanAdapter<Author>(new Author());
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown() throws Exception {}
	
	/**
	 * @throws Exception
	 */
	public void testModelProxy() throws Exception {	
		author.addPropertyChangeListener(new PropertyChangeListener() {	
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("newValue --> "+evt.getNewValue());
			}
		});
		
		author.
			setValue("name","Miguel de Cervantes").
			setValue("age",22);
		
		String name = author.getValue(String.class,"name");
		Integer age = author.getValue(Integer.class,"age");
			
		assertEquals(name,"Miguel de Cervantes");
		assertEquals(age,Integer.valueOf(22));
	}
	
	public void testLazyModelProxy() throws Exception {
		LazyBeanAdapter<Author> lazyAuthor = new LazyBeanAdapter<Author>(new Author());
		lazyAuthor.addPropertyChangeListener(new PropertyChangeListener() {	
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("newValue --> "+evt.getNewValue());
			}
		});
		
		lazyAuthor.
			setValue("name","Miguel de Cervantes");
		
		String name = lazyAuthor.getValueString("name");
		System.out.println("Waiting...");
		Thread.sleep(500);
		lazyAuthor.commit();
		
		assertEquals(name,"Miguel de Cervantes");
	}
	
}
