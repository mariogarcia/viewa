package viewa.view.delegator;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JMenu;

import viewa.view.ViewContainer;
import viewa.view.ViewException;

public class NamedComponentsDelegator implements Delegator{

	/* (non-Javadoc)
	 * @see viewa.view.delegator.Delegator#clean(viewa.view.ViewContainer)
	 */
	public void clean(ViewContainer view) throws ViewException {
		view.setNamedComponents(null);
	}

	/* (non-Javadoc)
	 * @see viewa.view.delegator.Delegator#inject(viewa.view.ViewContainer)
	 */
	public void inject(ViewContainer view) throws ViewException {
		view.setNamedComponents(extractComponents(view.getRootPane(),new HashMap<String,List<Component>>()));
	}
	
	/**
	 * @param con
	 * @param namedComponents
	 * @return
	 */
	private Map<String,List<Component>> extractComponents(Component con,Map<String,List<Component>> namedComponents){		
		String componentName = con.getName();
		if (componentName!=null && !componentName.equalsIgnoreCase("")){
			List<Component> components = namedComponents.get(componentName);
			if (components==null){
				namedComponents.put(componentName, new ArrayList<Component>());
			}
			namedComponents.get(componentName).add(con);
		}			
		if (con instanceof Container){
			if (con instanceof JMenu){
				for (Component c : ((JMenu)con).getMenuComponents()){
					extractComponents(c,namedComponents);
				}
			} else {
				for (Component c : ((Container)con).getComponents()){
					extractComponents(c,namedComponents);
				}
			}
		}	
		return namedComponents;
	}
	
}
