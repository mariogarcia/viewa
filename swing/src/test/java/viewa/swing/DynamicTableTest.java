package viewa.swing;

import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import viewa.swing.DynamicTable;
import viewa.swing.table.DynamicTableColumn;
import viewa.swing.table.DynamicTableModel;

/**
 * This test checks the basic functionality of the {@link DynamicTable} object
 * 
 * @author mgg
 *
 */
public class DynamicTableTest {

	/**
	 * Class created for testing purposes
	 * 
	 * @author mgg
	 *
	 */
	public class Member {
		private String name;
		private String password;
		public Member(){}
		public Member(String name,String password){this.name = name; this.password = password;}
		public String getName(){return name;}
		public String getPassword(){return password;}
		public void setName(String name){this.name = name;}
		public void setPassword(String password){this.password = password;}
	}
	
	private DynamicTable<Member> dynamicTable;
	private DynamicTableModel<Member> dynamicTableModel;
	private List<Member> memberList;
	private List<DynamicTableColumn> columns;
	private JFrame frame;

	/**
	 * Initializing table and the frame which contains it
	 */
	@Before
	public void init(){
		memberList = Arrays.asList(
			new Member("Joe","0392jr"),
			new Member("Janet","323rr"),
			new Member("Blackham","3r23r"),
			new Member("Erikka","234f2"),
			new Member("Moira","23d23d"),
			new Member("Ulrich","23f23f")
		);
		columns = Arrays.asList(
			new DynamicTableColumn("name",0,200),
			new DynamicTableColumn("password",1,50)
		);
	}
	
	/**
	 * We usually create a table first initializing the model and then adding the model
	 * to the table.
	 * 
	 * Once the model has been added to the table the table can be populated.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNaturalTableCreation() throws Exception{		
		dynamicTableModel = new DynamicTableModel<Member>(columns);
		dynamicTable = new DynamicTable<Member>(dynamicTableModel);
		dynamicTableModel.addAll(memberList);
		frame = new JFrame("DynamicTableTest");		
		frame.setSize(500,300);
		frame.getContentPane().add(new JScrollPane(dynamicTable));
		frame.setVisible(true);
		Thread.sleep(2000);
		TestCase.assertTrue(dynamicTable.getColumnCount() == 2);
		TestCase.assertTrue(dynamicTableModel.getRowCount() == 6);
	}
	
}
