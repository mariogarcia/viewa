package viewa.widget.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;

import viewa.swing.MasterViewPanel;
import viewa.view.DefaultViewContainer;
import viewa.view.ViewActionDescriptor;
import viewa.view.delegator.ActionDescriptorDelegator;
import viewa.view.delegator.DefaultViewResourceDelegator;
import viewa.view.delegator.Delegator;
import viewa.view.delegator.NamedComponentsDelegator;
import viewa.view.delegator.ViewContainerControllerDelegator;
import viewa.widget.view.delegator.MasterViewDelegator;
import viewa.widget.view.ui.MasterViewColumn;
import viewa.widget.view.ui.MasterViewModel;

/**
 * @author mario
 *
 * @param <E>
 */
public abstract class MasterView<E> extends DefaultViewContainer {

	private List<MasterViewColumn> columns;
	private List<Delegator> delegators;
	private MasterViewModel<E> model;
	
	/**
	 * 
	 */
	public MasterView(String id,List<MasterViewColumn> columns){
		super();		
		this.setId(id);
		this.setColumns(columns);
		initMasterView();
	}

	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#getActionDescriptors()
	 */
	@Override
	public List<ViewActionDescriptor> getActionDescriptors() {
		List<ViewActionDescriptor> descriptors = new ArrayList<ViewActionDescriptor>();
		descriptors.add(new ViewActionDescriptor("/masterViewActions[@text='masterViewActions' and @visible='false']"));
		descriptors.add(new ViewActionDescriptor("/masterViewActions/addAction[@toolBarGroup='1' and @text='addAction' and @icon='org/viewaframework/widget/icon/fan/img/page/page_add.png']"));
		descriptors.add(new ViewActionDescriptor("/masterViewActions/showAction[@toolBarGroup='1' and @text='showAction' and @icon='org/viewaframework/widget/icon/fan/img/page/page_white_magnify.png']"));
		descriptors.add(new ViewActionDescriptor("/masterViewActions/editAction[@toolBarGroup='1' and @text='editAction' and @icon='org/viewaframework/widget/icon/fan/img/page/page_edit.png']"));
		descriptors.add(new ViewActionDescriptor("/masterViewActions/deleteAction[@toolBarGroup='1' and @text='deleteAction' and @icon='org/viewaframework/widget/icon/fan/img/page/page_delete.png']"));
		descriptors.add(new ViewActionDescriptor("/masterViewActions/other/printAction[@toolBarGroup='2' and @text='printAction' and @icon='org/viewaframework/widget/icon/fan/img/printer/printer.png']"));
		descriptors.add(new ViewActionDescriptor("/masterViewActions/other/helpAction[@toolBarGroup='2' and @text='helpAction' and @icon='org/viewaframework/widget/icon/fan/img/misc/help.png']"));
		descriptors.add(new ViewActionDescriptor("/masterViewActions/exit/exitAction[@toolBarGroup='3' and @text='exitAction' and @icon='org/viewaframework/widget/icon/fan/img/door/door.png']"));
		return descriptors;
	}

	/**
	 * @return
	 */
	public List<MasterViewColumn> getColumns() {
		return columns;
	}
	
	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#getDelegators()
	 */
	public List<Delegator> getDelegators() {
		if (delegators == null){
			this.delegators = new ArrayList<Delegator>(Arrays.asList(
			 /* ActionDescriptor must always be the first delegator because once it has been injected
			  * all initial java.awt.Component are available, like the JToolBar and the JMenuBar */
				new ActionDescriptorDelegator(),
				new NamedComponentsDelegator(),
				new ViewContainerControllerDelegator(),
				new DefaultViewResourceDelegator(),
				new MasterViewDelegator()));
		}
		return delegators;
	}

	/**
	 * @return
	 */
	public abstract Class<E> getMasterType();

	/**
	 * @return
	 */
	public MasterViewModel<E> getModel() {
		return model;
	}

	/**
	 * 
	 */
	private void initMasterView() {
		MasterViewPanel<E> 	panel 			= new MasterViewPanel<E>();
		JTable 				table 			= panel.getTable();		
		MasterViewModel<E> 	model 			= new MasterViewModel<E>(columns,table);
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		this.setModel(model);		
		this.getContentPane().add(panel,BorderLayout.CENTER);
	}

	/**
	 * @param columns
	 */
	public void setColumns(List<MasterViewColumn> columns) {
		this.columns = columns;
	}
	
	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#setDelegators(java.util.List)
	 */
	public void setDelegators(List<Delegator> delegators) {
		this.delegators = delegators;
	}

	/**
	 * @param model
	 */
	public void setModel(MasterViewModel<E> model) {
		this.model = model;
	}
}
