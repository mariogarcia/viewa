package viewa.util;

import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class ResourceLocator {

	private static final Log logger = LogFactory.getLog(ResourceLocator.class);
	
	/**
	 * @param clazz
	 * @param classPath
	 * @return
	 */
	public static ImageIcon getImageIcon(Class<? extends Object> clazz,String classPath) {
		ImageIcon image = null;
		try{
			image = new ImageIcon(clazz.getClassLoader().getResource(classPath));
		} catch (RuntimeException ex){
			logger.error("Unable to get ImageIcon from "+classPath);
		}
		return image;
	}
	
}
