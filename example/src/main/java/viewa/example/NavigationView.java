package viewa.example;

import viewa.view.DefaultViewContainer;

public class NavigationView extends DefaultViewContainer {
    public static final String ID = "NavigationViewID";

    public NavigationView() {
        super(ID,new NavigationPanel());
    }
}
