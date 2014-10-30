package org.viewaframework.integration.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.viewaframework.core.AbstractApplicationLauncher;
import org.viewaframework.core.Application;
import org.viewaframework.core.ApplicationException;
import org.viewaframework.core.DefaultApplicationContext;
import org.viewaframework.ioc.IOCContext;

/**
 * This launcher allows Viewa to load a Spring context and make it available within the 
 * application context.
 * 
 * @author Mario Garcia
 * @since 1.0.0
 *
 */
public class SpringApplicationLauncher extends AbstractApplicationLauncher{

	private static final String[] VIEWA_CONFIG_FILES = new String[]{
		"classpath*:viewa.xml",
		"classpath*:*/**/viewa.xml"	
	};
	private static final String VIEWA_MAIN_BEAN ="application";	
	private static final Log logger = LogFactory.getLog(SpringApplicationLauncher.class);
	private Application application;
	
	/* (non-Javadoc)
	 * @see org.viewaframework.core.AbstractApplicationLauncher#getApplication()
	 */
	@Override
	public Application getApplication() throws ApplicationException{
		try{
			if (application == null){
				ApplicationContext 	context 	= null;

				logger.info("Loading Spring application context");
				context = new ClassPathXmlApplicationContext(VIEWA_CONFIG_FILES);	

				if (logger.isDebugEnabled()){
					for (String beanName : context.getBeanDefinitionNames()){
						logger.debug(beanName);
					}
				}
				BeanFactory 		factory 	= BeanFactory.class.cast(context);					
				application = Application.class.cast(factory.getBean(VIEWA_MAIN_BEAN));
			 /* ApplicationContext doesn't exists at this point, that's why it needs to be created */
				application.setApplicationContext(new DefaultApplicationContext());
				application.getApplicationContext().setAttribute(IOCContext.ID,new SpringContext(factory));
			}
		} catch (Exception ex){
		 /* since 1.0.2 */
			throw new ApplicationException(ex);
		}
		return application;
	}
}
