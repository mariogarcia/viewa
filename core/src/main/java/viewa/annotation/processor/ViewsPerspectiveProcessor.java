package viewa.annotation.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.annotation.ViewsPerspective;
import viewa.core.Application;
import viewa.view.perspective.Perspective;

/**
 * This processor handles the @ViewsPerspective annotation
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public class ViewsPerspectiveProcessor implements AnnotationProcessor{

	private static final Log logger = LogFactory.getLog(ViewsPerspectiveProcessor.class);
	private Application app;
	private Class<? extends Perspective> perspectiveClass;
	
	/**
	 * @param app
	 */
	public ViewsPerspectiveProcessor(Application app){
		this.app = app;
	}
	
	/* (non-Javadoc)
	 * @see viewa.annotation.processor.AnnotationProcessor#getResult()
	 */
	public Object getResult() {
		Perspective perspective = null;		
		try {
			if (perspectiveClass != null){
				perspective = perspectiveClass.newInstance();
			}
		} catch (Exception e) {
			logger.warn("Error while processing ViewsPerspective annotation at startup");
		} 		
		return perspective;
	}

	/* (non-Javadoc)
	 * @see viewa.annotation.processor.AnnotationProcessor#process()
	 */
	public void process() throws Exception {
		if (logger.isDebugEnabled()){
			logger.debug("Processing @ViewsPerspective annotation");
		}
		ViewsPerspective perspectiveAnnotation = this.app.getClass().getAnnotation(ViewsPerspective.class);
		if (perspectiveAnnotation != null){
			this.perspectiveClass = perspectiveAnnotation.value();
		}
	}

}
