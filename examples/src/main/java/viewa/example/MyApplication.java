package viewa.example;

import viewa.annotation.View;
import viewa.annotation.Views;
import viewa.core.DefaultApplication;
import viewa.core.DefaultApplicationLauncher;
import viewa.view.perspective.PerspectiveConstraint;

@Views({
    @View(type=MyApplicationRootView.class,isRoot=true),
    @View(type=NavigationView.class, position=PerspectiveConstraint.LEFT)
})
public class MyApplication extends DefaultApplication {
    public static void main(String[] args) throws Exception {
        new DefaultApplicationLauncher().execute(MyApplication.class);
    }
}
