package viewa.swing.binding;

import java.util.Arrays;

import viewa.swing.binding.collection.EventList;
import viewa.swing.binding.core.BeanAdapter;
import viewa.swing.binding.core.Property;
import viewa.swing.binding.table.ColumnInfo;
import viewa.swing.binding.util.Author;
import viewa.swing.binding.util.DataBuilder;

public class ButtonBindingTest extends FrameAvailableTestBase {

	public void testButtonBinding()throws Exception {
		SwingBinding swb = getSwingBinding();
		BeanAdapter<Author> author = DataBuilder.createAuthorBeanAdapter();
		EventList<Author> list = new EventList<Author>(DataBuilder.createAuthorList());
		swb.createTableListBinding(getTable(),list,Arrays.asList(new ColumnInfo(0,"name"))).
			createTableSelectionBinding(getTable(), author).
				createButtonBinding(
						getButton(),
						new Property<String>(String.class, "text"), 
						author,
						new Property<String>(String.class, "name")).bind();
		getFrame().setVisible(true);
		assertEquals(getButton().getText(),"Gothard");
	}

}
