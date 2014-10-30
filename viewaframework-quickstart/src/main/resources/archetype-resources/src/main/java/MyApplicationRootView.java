package ${groupId};

import org.viewaframework.annotation.Controller;
import org.viewaframework.annotation.Controllers;
import org.viewaframework.view.DefaultViewContainerFrame;

import org.viewaframework.widget.controller.EditActionController;
import org.viewaframework.widget.controller.ExitActionController;
import org.viewaframework.widget.controller.AboutActionController;

import static org.viewaframework.widget.controller.EditActionController.EDIT_PATTERN;
import static org.viewaframework.widget.controller.ExitActionController.EXIT_PATTERN;
import static org.viewaframework.widget.controller.AboutActionController.HELP_PATTERN;

/**
 * This View is the root view of the application. All views are nested inside this one.
 * 
 * @since ${version}
 * 
 */
@Controllers({ 
	@Controller(type = EditActionController.class, pattern = EDIT_PATTERN),	
	@Controller(type = ExitActionController.class, pattern = EXIT_PATTERN),
	@Controller(type = AboutActionController.class, pattern = HELP_PATTERN)
})
public class MyApplicationRootView extends DefaultViewContainerFrame { }
