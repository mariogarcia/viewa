package org.viewaframework.widget.swing.builder.util;

import org.viewaframework.annotation.View;
import org.viewaframework.annotation.Views;
import org.viewaframework.core.DefaultApplication;
import org.viewaframework.view.perspective.PerspectiveConstraint;

@Views({
	@View(type=SwingBuilderView.class,position=PerspectiveConstraint.LEFT)
})
public class SwingBuilderApplication extends DefaultApplication{}