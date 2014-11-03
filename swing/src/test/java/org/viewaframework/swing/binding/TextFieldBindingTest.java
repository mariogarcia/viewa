package org.viewaframework.swing.binding;

import org.viewaframework.swing.binding.core.BeanAdapter;
import org.viewaframework.swing.binding.core.Property;
import org.viewaframework.swing.binding.util.Author;
import org.viewaframework.swing.binding.util.DataBuilder;

public class TextFieldBindingTest extends FrameAvailableTestBase{

	public void testTextField() throws Exception {
		BeanAdapter<Author> author = DataBuilder.createAuthorBeanAdapter();
		getSwingBinding().
			createTextFieldBinding(
				getTextField(),
				new Property<String>(String.class,"text"),
				author,
				new Property<String>(String.class,"name")).
			bind();
		getFrame().setVisible(true);
		assertEquals(getTextField().getText(),"Gothard");
	}
	
}
