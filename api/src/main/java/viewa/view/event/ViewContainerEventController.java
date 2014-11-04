package viewa.view.event;

public interface ViewContainerEventController {

	public void onViewClose(ViewContainerEvent event);
	
	public void onViewInit(ViewContainerEvent event);
	
	public void onViewInitUIState(ViewContainerEvent event);
	
	public void onViewInitBackActions(ViewContainerEvent event);
	
	public void onViewFinalUIState(ViewContainerEvent event);
	
}
