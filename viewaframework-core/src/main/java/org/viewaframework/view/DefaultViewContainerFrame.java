package org.viewaframework.view;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

import org.viewaframework.util.StatusBar;
import org.viewaframework.util.ViewActionDescriptorFactoryUtil;

/**
 * @author Mario Garcia
 * @since 1.0
 * 
 */
public class DefaultViewContainerFrame extends AbstractViewContainerFrame {

	private static final List<ViewActionDescriptor> DEFAULT_ACTION_DESCRIPTORS = Arrays.asList(new ViewActionDescriptor[]{
			ViewActionDescriptorFactoryUtil.ACTION_ROOT,
			ViewActionDescriptorFactoryUtil.ACTION_EXIT,
			ViewActionDescriptorFactoryUtil.ACTION_EDIT,
			ViewActionDescriptorFactoryUtil.ACTION_EDIT_COPY,
			ViewActionDescriptorFactoryUtil.ACTION_EDIT_CUT,
			ViewActionDescriptorFactoryUtil.ACTION_EDIT_PASTE,
			ViewActionDescriptorFactoryUtil.ACTION_EDIT_SEPARATOR,
			ViewActionDescriptorFactoryUtil.ACTION_EDIT_SELECT_ALL,
			ViewActionDescriptorFactoryUtil.ACTION_HELP,
			ViewActionDescriptorFactoryUtil.ACTION_HELP_ABOUT
	});
	
	
	
	/**
	 * 
	 */
	public DefaultViewContainerFrame(){
		super();
		this.setActionDescriptors(DefaultViewContainerFrame.DEFAULT_ACTION_DESCRIPTORS);
		this.getContentPane().add(new StatusBar(),BorderLayout.SOUTH);
	}	
}
