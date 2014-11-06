package viewa.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import viewa.controller.AbstractViewController;
import viewa.view.ViewContainer;
import viewa.view.ViewException;
import viewa.view.perspective.PerspectiveConstraint;

public class TextPanelLinkController extends AbstractViewController<ActionListener,ActionEvent> {

    public Class<ActionListener> getSupportedClass() {
        return ActionListener.class;
    }

    @Override
    public void postHandlingView(ViewContainer view, ActionEvent eventObject)
        throws ViewException {
        this.getViewManager().
            addView(new TextPanelView(),PerspectiveConstraint.RIGHT);
    }
}
