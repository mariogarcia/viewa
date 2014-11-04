package viewa.docking.mydoggy;

import viewa.annotation.View;
import viewa.annotation.Views;
import viewa.annotation.ViewsPerspective;
import viewa.core.DefaultApplication;
import viewa.view.perspective.PerspectiveConstraint;

@ViewsPerspective(MyDoggyPerspective.class)
@Views(@View(type=MyDoggyView.class,position=PerspectiveConstraint.RIGHT))
public class MyDoggyApplication extends DefaultApplication{ }
