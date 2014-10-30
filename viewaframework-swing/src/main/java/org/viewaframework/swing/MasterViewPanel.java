package org.viewaframework.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledPanel;
import org.jdesktop.swingx.decorator.HighlighterFactory;

/**
 * This component shows a {@link DynamicTable} and at the bottom it shows a {@link PaginationPanel} to
 * control pagination. 
 * 
 * You can set a title at the top of the master panel using setTitleText("a title").
 * 
 * @author mgg
 *
 * @param <T>
 */
public class MasterViewPanel<T> extends JXTitledPanel{

	private static final long serialVersionUID = 1L;

	private JXTable table;
	private JScrollPane scrollPane;
	private PaginationPanel paginationPanel;
	
	public MasterViewPanel(){
		super();
		this.setName("masterViewPanel");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(this.getScrollPane(),BorderLayout.CENTER);
		panel.add(this.getPaginationPanel(),BorderLayout.SOUTH);
		this.add(panel);
	}

	public PaginationPanel getPaginationPanel(){
		if (this.paginationPanel == null){
			this.paginationPanel = new PaginationPanel();
		}
		return this.paginationPanel;
	}

	public JScrollPane getScrollPane() {
		if (this.scrollPane == null){
			this.scrollPane = new JScrollPane(this.getTable());
		}
		return scrollPane;
	}

	public JXTable getTable() {
		if (this.table == null){
			this.table = new DynamicTable<T>();
			this.table.setEditable(false);
			this.table.setShowHorizontalLines(false);
			this.table.setShowVerticalLines(false);
			this.table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			this.table.setHighlighters(HighlighterFactory.createAlternateStriping());
			this.table.setName("table");
		}
		return table;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
	public void setTable(JXTable table) {
		this.table = table;
		this.scrollPane.getViewport().removeAll();
		this.scrollPane.getViewport().add(this.table);
		this.scrollPane.validate();
	}
	
}
