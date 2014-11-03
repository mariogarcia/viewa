package org.viewaframework.test.component;

import org.viewaframework.test.ButtonTrapper;
import org.viewaframework.test.CheckBoxTrapper;
import org.viewaframework.test.ComboTrapper;
import org.viewaframework.test.ListTrapper;
import org.viewaframework.test.MenuItemTrapper;
import org.viewaframework.test.TextTrapper;

public interface ComponentAware {

	public ButtonTrapper button(String name);
	public MenuItemTrapper menuItem(String name);
	public TextTrapper text(String name);
	public ListTrapper list(String name);
	public ComboTrapper combo(String name);
	public CheckBoxTrapper checkBox(String name);
	
}
