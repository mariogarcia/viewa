package viewa.swing.binding;

import viewa.swing.binding.core.BeanAdapter;
import viewa.swing.binding.core.Property;
import viewa.swing.binding.util.Author;
import viewa.swing.binding.util.DataBuilder;

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
