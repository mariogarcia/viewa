package org.viewaframework.widget.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;

import org.viewaframework.swing.MasterViewPanel;
import org.viewaframework.view.DefaultViewContainerEditor;
import org.viewaframework.view.ViewContainerEditor;
import org.viewaframework.view.ViewActionDescriptor;
import org.viewaframework.view.delegator.ActionDescriptorDelegator;
import org.viewaframework.view.delegator.DefaultViewResourceDelegator;
import org.viewaframework.view.delegator.Delegator;
import org.viewaframework.view.delegator.NamedComponentsDelegator;
import org.viewaframework.view.delegator.ViewContainerControllerDelegator;
import org.viewaframework.widget.view.delegator.MasterViewDelegator;
import org.viewaframework.widget.view.ui.MasterViewColumn;
import org.viewaframework.widget.view.ui.MasterViewModel;

/**
 * @author mario
 *
 * @param <E>
 */
public abstract class MasterViewEditor<E> extends MasterView implements ViewContainerEditor {

	public MasterViewEditor(String id,List<MasterViewColumn> columns){
		super(id, columns);
	}

}
