package org.viewaframework.integration.spring;

import org.springframework.beans.factory.BeanFactory;
import org.viewaframework.ioc.IOCContext;

/**
 * This class encapsules a IOC container. This time the Spring beanFactory.
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public class SpringContext implements IOCContext{

	private BeanFactory beanFactory;
	
	/**
	 * @param factoryBean
	 */
	public SpringContext(BeanFactory factoryBean){
		this.beanFactory = factoryBean;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.ioc.IOCContext#getBean(java.lang.String)
	 */
	public Object getBean(String name) {
		return beanFactory.getBean(name);
	}

}
