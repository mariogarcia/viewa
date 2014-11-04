package viewa.swing.binding.util;

import java.util.Arrays;
import java.util.List;

import viewa.swing.binding.core.BasicBeanAdapter;
import viewa.swing.binding.core.BeanAdapter;

public class DataBuilder {

	public static List<Author> createAuthorList(){
		return Arrays.asList(
				new Author("Metallica",33,"Chicago"),
				new Author("Iron Maiden",34,"Liverpool"),
				new Author("Manowar",33,"Chicago"),
				new Author("Whitesnake",33,"Illinois"),
				new Author("Yngwie Malmsteen",33,"Oslo"),
				new Author("Europe",33,"Oslo"),
				new Author("Twister Sister",33,"L.A."),
				new Author("Gothard",33,"Berlin"),
				new Author("Saratoga",33,"Madrid"));
	}
	
	/**
	 * @return
	 */
	public static BeanAdapter<Author> createAuthorBeanAdapter(){
		return new BasicBeanAdapter<Author>(new Author("Gothard",33,"Berlin"));
	}
}
