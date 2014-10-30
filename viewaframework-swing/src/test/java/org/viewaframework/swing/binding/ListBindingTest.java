package org.viewaframework.swing.binding;

import org.viewaframework.swing.binding.collection.EventList;
import org.viewaframework.swing.binding.core.BasicBeanAdapter;
import org.viewaframework.swing.binding.core.BeanAdapter;
import org.viewaframework.swing.binding.core.Property;
import org.viewaframework.swing.binding.util.Author;
import org.viewaframework.swing.binding.util.DataBuilder;

public class ListBindingTest extends FrameAvailableTestBase{

	public void testListingBinding() throws Exception {
		EventList<Author> authorList = new EventList<Author>(DataBuilder.createAuthorList());
		BeanAdapter<Author> author = new BasicBeanAdapter<Author>(new Author());
		getSwingBinding().createListBinding(getList(), authorList).
			createListSelectionBinding(getList(),author).
			createTextFieldBinding(
					getTextField(),
					new Property<String>(String.class,"text"),
					author,
					new Property<String>(String.class,"name")).bind();
		getFrame().setVisible(true);
		getList().setSelectedIndex(4);
		assertEquals(author.getSource().getName(),"Yngwie Malmsteen");
	}
}
