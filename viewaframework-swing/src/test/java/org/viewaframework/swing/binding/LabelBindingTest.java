package org.viewaframework.swing.binding;

import java.util.Arrays;

import org.viewaframework.swing.binding.collection.EventList;
import org.viewaframework.swing.binding.core.BeanAdapter;
import org.viewaframework.swing.binding.core.Property;
import org.viewaframework.swing.binding.table.ColumnInfo;
import org.viewaframework.swing.binding.util.Author;
import org.viewaframework.swing.binding.util.DataBuilder;

public class LabelBindingTest extends FrameAvailableTestBase{

	public void testLabelBinding() throws Exception {
		SwingBinding swb = getSwingBinding();
		BeanAdapter<Author> author = DataBuilder.createAuthorBeanAdapter();
		EventList<Author> list = new EventList<Author>(DataBuilder.createAuthorList());
		swb.createTableListBinding(getTable(),list,Arrays.asList(new ColumnInfo(0,"name"))).
			createTableSelectionBinding(getTable(), author).
				createLabelBinding(
						getLabel(),
						new Property<String>(String.class, "text"), 
						author,
						new Property<String>(String.class, "name")).bind();
		getFrame().setVisible(true);
		assertEquals(getLabel().getText(),"Gothard");
	}
	
}
