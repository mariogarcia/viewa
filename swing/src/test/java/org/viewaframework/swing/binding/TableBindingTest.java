package org.viewaframework.swing.binding;

import java.util.Arrays;

import org.viewaframework.swing.binding.collection.EventList;
import org.viewaframework.swing.binding.core.BasicBeanAdapter;
import org.viewaframework.swing.binding.core.Property;
import org.viewaframework.swing.binding.table.ColumnInfo;
import org.viewaframework.swing.binding.util.Author;
import org.viewaframework.swing.binding.util.DataBuilder;

public class TableBindingTest extends FrameAvailableTestBase{
	
	
	public void testAdding()throws Exception{
		Author author = new Author();
		BasicBeanAdapter<Author> bindAuthor = new BasicBeanAdapter<Author>(author);
		EventList<Author> authorList = new EventList<Author>(DataBuilder.createAuthorList());
		SwingBinding swb = new SwingBinding();
			swb.createTableListBinding(getTable(), authorList, Arrays.asList(
						new ColumnInfo(0,"name","Group Band",200),
						new ColumnInfo(1,"age","Years",20),
						new ColumnInfo(1,"city","City",100))).
				createTableSelectionBinding(getTable(),bindAuthor).
				createTextFieldBinding(
							getTextField(),
							new Property<String>(String.class,"text"),
							bindAuthor,
							new Property<String>(String.class,"name")).bind();
		getFrame().setVisible(true);
		Thread.sleep(500);
		authorList.add(new Author("Helloween",55,"Hamburgo"));
		Thread.sleep(500);
		authorList.remove(1);
		Thread.sleep(500);
		authorList.add(0,new Author("Blind Guardian",42,"Heidoven"));
		Thread.sleep(500);
		authorList.set(0,new Author("Armoured Saint",65,"Seatle"));
		Thread.sleep(500);
		authorList.add(new Author("Dio",65,"Chicago"));
		Thread.sleep(500);
		authorList.add(new Author("Van Halen",65,"Texas"));
		Thread.sleep(500);
		authorList.add(new Author("Ozzy Osbourne",65,"Birminham"));
		Thread.sleep(500);
		authorList.add(new Author("Queensryche",65,"Chicago"));
		Thread.sleep(500);
		authorList.add(new Author("Jupiter",65,"Madrid"));
		Thread.sleep(500);
	}
}
