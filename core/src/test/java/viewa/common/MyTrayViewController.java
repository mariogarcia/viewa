package viewa.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import viewa.controller.AbstractViewController;
import viewa.view.ViewContainer;
import viewa.view.ViewException;

public class MyTrayViewController extends AbstractViewController<ActionListener, ActionEvent> {

	public Class<ActionListener> getSupportedClass() {
		return ActionListener.class;
	}

	@Override
	public void postHandlingView(ViewContainer view, ActionEvent eventObject)throws ViewException {
		view.getApplication().hideOrRestore();
	}
}
