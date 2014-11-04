package viewa.util;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * When this class becomes greater then it should be replaced by
 * the Apache BeanUtils dependency
 * 
 * @author mario
 *
 */
public final class BeanUtils {
	
	private static final Log logger = LogFactory.getLog(BeanUtils.class);
	
	/**
	 * This method copies all fields that follows the JavaBeans getter and setter rules.
	 * 
	 * @param dst The destination object
	 * @param src The source object
	 * 
	 * @return The destination object with all source object properties
	 */
	public static Object copyProperties(Object dst, Object src){		
		Method[]  methods 	= src.getClass().getMethods();
		for (Method srcGetter: methods){
			if (srcGetter.getName().startsWith("get")){
				String fieldName = srcGetter.getName().replace("get","");
				Method dstSetter= null;
				try {
					dstSetter = dst.getClass().getMethod("set"+fieldName,new Class[]{srcGetter.getReturnType()});			
					dstSetter.invoke(dst, srcGetter.invoke(src,new Object[]{}));
				} catch (Exception e) {
					logger.warn(fieldName+" cant be copied");
				}
			}
		}
		return dst;
	}
}
