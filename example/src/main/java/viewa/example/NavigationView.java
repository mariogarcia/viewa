package viewa.example;

import viewa.annotation.Controller
import viewa.annotation.Controllers
import viewa.view.DefaultViewContainer;

@Controllers({
    @Controller(type= TextPanelLinkController.class, pattern="textPanelLinkName")
})
public class NavigationView extends DefaultViewContainer {
    public static final String ID = "NavigationViewID";

    public NavigationView() {
        super(ID,new NavigationPanel());
    }
}
