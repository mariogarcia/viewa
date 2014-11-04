package viewa.test.component;

import viewa.test.ButtonTrapper;
import viewa.test.CheckBoxTrapper;
import viewa.test.ComboTrapper;
import viewa.test.ListTrapper;
import viewa.test.MenuItemTrapper;
import viewa.test.TextTrapper;

public interface ComponentAware {

	public ButtonTrapper button(String name);
	public MenuItemTrapper menuItem(String name);
	public TextTrapper text(String name);
	public ListTrapper list(String name);
	public ComboTrapper combo(String name);
	public CheckBoxTrapper checkBox(String name);
	
}
