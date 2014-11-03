package org.viewaframework.widget.controller;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;

import javax.swing.text.JTextComponent;

import org.viewaframework.controller.AbstractActionController;
import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.ViewException;

/**
 * @author mario
 *
 */
public class EditActionController extends AbstractActionController{

	public static final String EDIT_PATTERN = "cut|copy|paste|selectAll";

	/* (non-Javadoc)
	 * @see org.viewaframework.controller.AbstractViewController#postHandlingView(org.viewaframework.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	public void postHandlingView(ViewContainer view, ActionEvent eventObject) 
		throws ViewException {		
		String componentName = Component.class.cast(eventObject.getSource()).getName();
		KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		Component component = focusManager.getFocusOwner();		
		if (JTextComponent.class.isAssignableFrom(component.getClass())){
			JTextComponent textComponent = JTextComponent.class.cast(component);
			if (componentName.equals("cut")){
				textComponent.cut();
			} else if (componentName.equals("copy")){
				textComponent.copy();
			} else if (componentName.equals("paste")){
				textComponent.paste();
			} else if (componentName.equals("selectAll")){
				textComponent.setSelectionStart(0);
				textComponent.setSelectionEnd(textComponent.getText().length());
			} 
		}
	}
}
