package org.viewaframework.test.util;

import javax.swing.SwingUtilities;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.viewaframework.test.Trapper;

public class TrapperUtils {

	private static final Log logger = LogFactory.getLog(TrapperUtils.class);
	
	@SuppressWarnings("unchecked")
	public static Trapper processRunnableAndWait(Trapper trapper,long millis,Runnable runnable){
		try{
			SwingUtilities.invokeAndWait(runnable);			
			Thread.sleep(millis);
		} catch (Exception ex){
			logger.error("-------------------------------------- ERROR --------------------------------------");
			TestCase.fail(ex.getMessage());
		}		
		return trapper;
	}
}
