package viewa.annotation.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.annotation.Listener;
import viewa.annotation.Listeners;
import viewa.core.ApplicationContext;
import viewa.ioc.IOCContext;
import viewa.view.ViewContainer;
import viewa.view.event.ViewContainerEventController;

public class ListenersProcessor implements AnnotationProcessor<List<ViewContainerEventController>>{

	private static final Log logger = LogFactory.getLog(ListenersProcessor.class);
	private ViewContainer view;
	private List<ViewContainerEventController> listeners;
	
	public ListenersProcessor(ViewContainer view){
		this.view = view;
		this.listeners = new ArrayList<ViewContainerEventController>();
	}
	
	@Override
	public void process() throws Exception {
		Listeners listeners = view.getClass().getAnnotation(Listeners.class);
		ApplicationContext ctx = this.view.getApplication().getApplicationContext();
		IOCContext ioc = (IOCContext)ctx.getAttribute(IOCContext.ID);
		for (Listener l : listeners.value()){
			ViewContainerEventController listener = 
				ViewContainerEventController.class.cast(
					ioc != null ? 
						ioc.getBean(l.id()) : 
						l.type().newInstance()
					);
			this.listeners.add(listener);
		}
	}

	@Override
	public List<ViewContainerEventController> getResult() {
		try {
			this.process();
		} catch (Exception e) {
			logger.error(
				new StringBuilder("Cant process listeners from: ").
					append(this.view.getClass().getSimpleName()).
					append(" [").append(e.getMessage()).append("]"));
		}
		return this.listeners;
	}

}
