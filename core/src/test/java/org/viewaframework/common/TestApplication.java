package org.viewaframework.common;

import org.viewaframework.annotation.View;
import org.viewaframework.annotation.Views;
import org.viewaframework.annotation.ViewsPerspective;
import org.viewaframework.core.DefaultApplication;
import org.viewaframework.core.DefaultApplicationLauncher;
import org.viewaframework.view.perspective.DefaultPerspective;
import org.viewaframework.view.perspective.PerspectiveConstraint;

/**
 * @author Mario Garcia
 * @since 1.0.2
 */
@ViewsPerspective(DefaultPerspective.class)
@Views({
	@View(type = TestView.class, position = PerspectiveConstraint.LEFT),
	@View(type = MyTrayView.class, isTray = true)
})
public class TestApplication extends DefaultApplication {
	public static void main(String[] args) throws Exception {
		new DefaultApplicationLauncher().execute(TestApplication.class);
	}
}
