package viewa.widget.view.ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import org.jdesktop.swingx.JXTable;
import viewa.util.ResourceLocator;

/**
 * This class is a kind of MasterView. It is used in DetailView instances for showing
 * relationship's lists.
 * 
 * @author Mario Garcia
 *
 * @param <T> The entity this list represents
 * @param <D> The dto representing the previous entity
 */
public class DetailViewRelationshipPanel<T,D> extends JPanel{
	
	public static final String NAME_RELATIONSHIP_PANEL_SUFFIX = "relationshipPanel";
	public static final String NAME_RELATIONSHIP_SCROLL_PANE = "relationshipScrollPane";
	public static final String NAME_RELATIONSHIP_TABLE = "relationshipTable";
	public static final String NAME_RELATIONSHIP_TOOLBAR = "relationshipToolBar";
	
	private static final long serialVersionUID = 1L;
	private List<MasterViewColumn> columns;
	private JButton relationshipAddButton;
	private Class<T> relationshipClass;
	private String relationshipClassName;
	private JButton relationshipDeleteButton;
	private JButton relationshipEditButton;
	private MasterViewModel<D> relationshipModel;
	private JScrollPane relationshipScrollPane;
	private JButton relationshipShowButton;
	private JTable relationshipTable;
	private JToolBar toolBar;
	
	/**
	 * @param columns
	 * @param relationshipClass
	 */
	public DetailViewRelationshipPanel(List<MasterViewColumn> columns,Class<T> relationshipClass){
		this.columns = columns;
		this.setName(this.relationshipClassName + NAME_RELATIONSHIP_PANEL_SUFFIX);
		this.relationshipClass = relationshipClass;
		this.relationshipClassName = this.relationshipClass.getSimpleName();
		this.init();
	}
	/**
	 * @return
	 */
	public JButton getRelationshipAddButton() {
		return relationshipAddButton;
	}

	/**
	 * @return
	 */
	public JButton getRelationshipDeleteButton() {
		return relationshipDeleteButton;
	}

	/**
	 * @return
	 */
	public JButton getRelationshipEditButton() {
		return relationshipEditButton;
	}

	/**
	 * @return
	 */
	public MasterViewModel<D> getRelationshipModel() {
		return relationshipModel;
	}

	/**
	 * @return
	 */
	public JButton getRelationshipShowButton() {
		return relationshipShowButton;
	}

	/**
	 * @return
	 */
	public JToolBar getToolBar() {
		return toolBar;
	}

	/**
	 * 
	 */
	private void init(){		
		this.setLayout(new BorderLayout());
		this.setName(this.relationshipClassName+"relationshipPanel");
		this.relationshipTable = new JXTable();
//		this.relationshipModel = new MasterViewModel<D>(columns, relationshipTable,);
		this.relationshipScrollPane = new JScrollPane(relationshipTable);				
		this.relationshipScrollPane.setName(NAME_RELATIONSHIP_SCROLL_PANE);
		this.relationshipTable.setName(NAME_RELATIONSHIP_TABLE);
		this.relationshipTable.setModel(relationshipModel);
		this.add(this.relationshipScrollPane,BorderLayout.CENTER);
	 /* ToolBar */
		this.toolBar = new JToolBar();
		this.toolBar.setFloatable(false);
		this.toolBar.setRollover(true);
		this.relationshipAddButton = new JButton(ResourceLocator.getImageIcon(DetailViewRelationshipPanel.class,"org/viewaframework/widget/icon/fan/img/page/page_add.png"));
		this.relationshipAddButton.setName("addAction"+relationshipClassName);
		this.relationshipAddButton.setBorderPainted(false);
		this.relationshipAddButton.setOpaque(false);
		this.relationshipShowButton = new JButton(ResourceLocator.getImageIcon(DetailViewRelationshipPanel.class,"org/viewaframework/widget/icon/fan/img/page/page_white_magnify.png"));
		this.relationshipShowButton.setName("showAction"+relationshipClassName);
		this.relationshipShowButton.setBorderPainted(false);
		this.relationshipShowButton.setOpaque(false);
		this.relationshipEditButton = new JButton(ResourceLocator.getImageIcon(DetailViewRelationshipPanel.class,"org/viewaframework/widget/icon/fan/img/page/page_edit.png"));
		this.relationshipEditButton.setName("editAction"+relationshipClassName);
		this.relationshipEditButton.setBorderPainted(false);
		this.relationshipEditButton.setOpaque(false);
		this.relationshipDeleteButton = new JButton(ResourceLocator.getImageIcon(DetailViewRelationshipPanel.class,"org/viewaframework/widget/icon/fan/img/page/page_delete.png"));
		this.relationshipDeleteButton.setName("deleteAction"+relationshipClassName);
		this.relationshipDeleteButton.setBorderPainted(false);
		this.relationshipDeleteButton.setOpaque(false);
		this.toolBar.setName(NAME_RELATIONSHIP_TOOLBAR);
		this.toolBar.add(this.relationshipAddButton);
		this.toolBar.add(this.relationshipShowButton);
		this.toolBar.add(this.relationshipEditButton);
		this.toolBar.add(this.relationshipDeleteButton);
		this.add(this.toolBar,BorderLayout.NORTH);
	}

	/**
	 * @param relationshipAddButton
	 */
	public void setRelationshipAddButton(JButton relationshipAddButton) {
		this.relationshipAddButton = relationshipAddButton;
	}

	/**
	 * @param relationshipDeleteButton
	 */
	public void setRelationshipDeleteButton(JButton relationshipDeleteButton) {
		this.relationshipDeleteButton = relationshipDeleteButton;
	}
	
	/**
	 * @param relationshipEditButton
	 */
	public void setRelationshipEditButton(JButton relationshipEditButton) {
		this.relationshipEditButton = relationshipEditButton;
	}
	
	/**
	 * @param relationshipModel
	 */
	public void setRelationshipModel(MasterViewModel<D> relationshipModel) {
		this.relationshipModel = relationshipModel;
	}
	
	/**
	 * @param relationshipShowButton
	 */
	public void setRelationshipShowButton(JButton relationshipShowButton) {
		this.relationshipShowButton = relationshipShowButton;
	}
	
	/**
	 * @param toolBar
	 */
	public void setToolBar(JToolBar toolBar) {
		this.toolBar = toolBar;
	}
}
