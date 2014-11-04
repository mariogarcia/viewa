package viewa.widget.swing.builder.util;

import viewa.annotation.View;
import viewa.annotation.Views;
import viewa.core.DefaultApplication;
import viewa.view.perspective.PerspectiveConstraint;

@Views({
	@View(type=SwingBuilderView.class,position=PerspectiveConstraint.LEFT)
})
public class SwingBuilderApplication extends DefaultApplication{}