package viewa.example;

import viewa.annotation.View;
import viewa.annotation.Views;
import viewa.core.DefaultApplication;
import viewa.core.DefaultApplicationLauncher;

@Views({
    @View(type=MyApplicationRootView.class,isRoot=true)
})
public class MyApplication extends DefaultApplication {
    public static void main(String[] args) throws Exception {
        new DefaultApplicationLauncher().execute(MyApplication.class);
    }
}