package viewa.example;

import static viewa.widget.controller.EditActionController.EDIT_PATTERN;
import static viewa.widget.controller.ExitActionController.EXIT_PATTERN;
import static viewa.widget.controller.AboutActionController.HELP_PATTERN;

import viewa.annotation.Controller;
import viewa.annotation.Controllers;
import viewa.view.DefaultViewContainerFrame;

import viewa.widget.controller.EditActionController;
import viewa.widget.controller.ExitActionController;
import viewa.widget.controller.AboutActionController;

@Controllers({
    @Controller(type= EditActionController.class, pattern= EDIT_PATTERN),
    @Controller(type= ExitActionController.class, pattern= EXIT_PATTERN),
    @Controller(type= AboutActionController.class, pattern= HELP_PATTERN)
})
public class MyApplicationRootView extends DefaultViewContainerFrame { }
