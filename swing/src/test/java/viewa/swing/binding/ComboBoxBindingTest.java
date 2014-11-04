package viewa.swing.binding;

import javax.swing.SwingUtilities;

import viewa.swing.binding.collection.EventList;
import viewa.swing.binding.core.BeanAdapter;
import viewa.swing.binding.util.Author;
import viewa.swing.binding.util.DataBuilder;

public class ComboBoxBindingTest extends FrameAvailableTestBase {

	public void testListTest() throws Exception{
		EventList<Author> eventList = new EventList<Author>(DataBuilder
				.createAuthorList());
		getSwingBinding().
			createComboBoxListBinding(getComboBox(), eventList).
				bind();
		this.getFrame().setVisible(true);
		assertTrue(Author.class.cast(
				getComboBox().getSelectedItem()).
					getName().equals("Metallica"));
	}

	public void testSelectionTest() throws Exception {
		BeanAdapter<Author> author = DataBuilder.createAuthorBeanAdapter();
		EventList<Author> eventList = new EventList<Author>(DataBuilder
				.createAuthorList());
		getSwingBinding().
			createComboBoxListBinding(getComboBox(), eventList).
				createComboBoxSelectionBinding(getComboBox(),author).
					bind();
		this.getFrame().setVisible(true);	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				getComboBox().setSelectedIndex(2);
				getComboBox().updateUI();
			}
		});
		Thread.sleep(1000);
		assertTrue(author.getSource().getName().equals("Manowar"));
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				getComboBox().setSelectedIndex(1);
				getComboBox().updateUI();
			}
		});
		Thread.sleep(1000);
		assertTrue(author.getSource().getName().equals("Iron Maiden"));
	}

}
