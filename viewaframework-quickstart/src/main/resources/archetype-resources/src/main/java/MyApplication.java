package ${groupId};

import org.viewaframework.annotation.View;
import org.viewaframework.annotation.Views;
import org.viewaframework.core.DefaultApplication;
import org.viewaframework.core.DefaultApplicationLauncher;

/**
 * This is the main entry point of the application
 * 
 * @since ${version}
 *
 */
@Views({
	@View(type=MyApplicationRootView.class,isRoot=true)
})
public class MyApplication extends DefaultApplication{
	public static void main(String[] args) throws Exception {
		new DefaultApplicationLauncher().execute(MyApplication.class);
	}
}

