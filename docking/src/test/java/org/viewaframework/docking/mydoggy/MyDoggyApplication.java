package org.viewaframework.docking.mydoggy;

import org.viewaframework.annotation.View;
import org.viewaframework.annotation.Views;
import org.viewaframework.annotation.ViewsPerspective;
import org.viewaframework.core.DefaultApplication;
import org.viewaframework.view.perspective.PerspectiveConstraint;

@ViewsPerspective(MyDoggyPerspective.class)
@Views(@View(type=MyDoggyView.class,position=PerspectiveConstraint.RIGHT))
public class MyDoggyApplication extends DefaultApplication{ }
