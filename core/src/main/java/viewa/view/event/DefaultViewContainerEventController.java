package viewa.view.event;

/**
 * Default implementation of the {@link ViewContainerEventController}
 * 
 * @author mgg
 *
 */
public class DefaultViewContainerEventController implements ViewContainerEventController{
	
	/* (non-Javadoc)
	 * @see viewa.view.event.ViewContainerEventController#onViewClose(viewa.view.event.ViewContainerEvent)
	 */
	@Override
	public void onViewClose(ViewContainerEvent event) {}

	/* (non-Javadoc)
	 * @see viewa.view.event.ViewContainerEventController#onViewInit(viewa.view.event.ViewContainerEvent)
	 */
	@Override
	public void onViewInit(ViewContainerEvent event) {}

	/* (non-Javadoc)
	 * @see viewa.view.event.ViewContainerEventController#onViewInitUIState(viewa.view.event.ViewContainerEvent)
	 */
	@Override
	public void onViewInitUIState(ViewContainerEvent event) {}

	/* (non-Javadoc)
	 * @see viewa.view.event.ViewContainerEventController#onViewInitBackActions(viewa.view.event.ViewContainerEvent)
	 */
	@Override
	public void onViewInitBackActions(ViewContainerEvent event) {}

	/* (non-Javadoc)
	 * @see viewa.view.event.ViewContainerEventController#onViewFinalUIState(viewa.view.event.ViewContainerEvent)
	 */
	@Override
	public void onViewFinalUIState(ViewContainerEvent event) {}
}
