package viewa.swing.binding;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import junit.framework.TestCase;

import viewa.swing.binding.util.FrameBuilder;

public class FrameAvailableTestBase extends TestCase{

	private JButton button;
	private JComboBox comboBox;
	private JFrame frame;
	private JLabel label;
	private JList list;
	private SwingBinding swingBinding;
	private JTable table;
	private JTextField textField;
	
	public JButton getButton() {
		return button;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JLabel getLabel() {
		return label;
	}

	public JList getList() {
		return list;
	}

	public SwingBinding getSwingBinding() {
		return swingBinding;
	}
	public JTable getTable() {
		return table;
	}
	
	public JTextField getTextField() {
		return textField;
	}

	@Override
	protected void setUp() throws Exception {
		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1,2));
		southPanel.setLayout(new GridLayout(1,2));
		centerPanel.setLayout(new GridLayout(1,2));
	/* -------------------------------------------- */
		this.button = new JButton("go");
		this.comboBox = new JComboBox();
		this.label = new JLabel();
		this.list = new JList();
		this.textField = new JTextField();
		this.table = new JTable();
		this.swingBinding = new SwingBinding();
	 /* -------------------------------------------- */
		northPanel.add(comboBox);
		northPanel.add(textField);
		southPanel.add(label);
		southPanel.add(button);
		centerPanel.add(new JScrollPane(table));
		centerPanel.add(new JScrollPane(list));
		this.frame = FrameBuilder.createTestingFrame();
		this.frame.add(northPanel,BorderLayout.NORTH);
		this.frame.add(centerPanel,BorderLayout.CENTER);
		this.frame.add(southPanel,BorderLayout.SOUTH);
	}

	@Override
	protected void tearDown() throws Exception {
		frame.setVisible(false);
		frame.dispose();
		swingBinding.unbind();
	}

}
