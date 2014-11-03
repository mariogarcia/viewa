package org.viewaframework.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.viewaframework.controller.AbstractViewController;
import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.ViewException;

public class MyTrayViewController extends AbstractViewController<ActionListener, ActionEvent> {

	public Class<ActionListener> getSupportedClass() {
		return ActionListener.class;
	}

	@Override
	public void postHandlingView(ViewContainer view, ActionEvent eventObject)throws ViewException {
		view.getApplication().hideOrRestore();
	}
}
