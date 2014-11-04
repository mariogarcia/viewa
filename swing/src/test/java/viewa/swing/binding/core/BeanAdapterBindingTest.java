package viewa.swing.binding.core;

import junit.framework.TestCase;

import viewa.swing.binding.util.Author;
import viewa.swing.binding.util.City;

/**
 * @author Mario Garcia
 *
 */
public class BeanAdapterBindingTest extends TestCase {
	
	private SelectionBinding<Author,String,City,String> binding;
	private Author author;
	private BasicBeanAdapter<Author> authorClone;
	private City city;
	private Property<String> authorName;
	private Property<String> cityName;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		authorClone	= new BasicBeanAdapter<Author>(new Author());
		author 		= new Author("tres",22,"oui");
		city 		= new City("madrid");
		authorName 	= new Property<String>(String.class,"name");
		cityName 	= new Property<String>(String.class,"name");
		binding 	= new BeanAdapterBinding<Author, String, City, String>(author,authorName,city,cityName);
		binding.bind();
	}
	
	/**
	 * @throws Exception
	 */
	public void testBeanAdapterBinding() throws Exception {		
		binding.getSource().setValue("name","amigo");
		assertEquals(binding.getTarget().getSource().getName(),"amigo");
		binding.getTarget().setValue("name","ddd");
		assertEquals(binding.getSource().getSource().getName(),"ddd");
		binding.getTarget().setValue("name","ddda");
		assertEquals(binding.getSource().getValueString("name"),"ddda");
		this.authorClone.sync(binding.getSource());
		assertTrue(this.authorClone.isSync(binding.getSource()));
		this.authorClone.setValue("name","sasd");
		assertFalse(this.authorClone.isSync(binding.getSource()));
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		binding.unbind();
	}
}
