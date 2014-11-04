package viewa.common;

import viewa.annotation.View;
import viewa.annotation.Views;
import viewa.annotation.ViewsPerspective;
import viewa.core.DefaultApplication;
import viewa.core.DefaultApplicationLauncher;
import viewa.view.perspective.DefaultPerspective;
import viewa.view.perspective.PerspectiveConstraint;

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
