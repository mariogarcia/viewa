package viewa.view.delegator.converter;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.util.ClassPathURLHandler;
import viewa.view.delegator.ViewResourceConverter;

/**
 * This converter can convert an icon path in a java.awt.Image object
 * 
 * @author Mario Garcia
 *
 */
public class ViewResourceImageConverter implements ViewResourceConverter{

	private static final Log logger = LogFactory.getLog(ViewResourceImageConverter.class);
	
	/* (non-Javadoc)
	 * @see viewa.view.delegator.ViewResourceConverter#convert(java.lang.Object, java.lang.Class)
	 */
	public Object convert(Object arg0, Class<? extends Object> arg1) {
		String pathUrl = arg0.toString();
		ImageIcon imageIcon = null;
		try {
			imageIcon = new ImageIcon(new URL(null,pathUrl,new ClassPathURLHandler()));
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		}		
		return imageIcon.getImage();
	}

	public Class<? extends Object> getDestinationClass() {
		return Image.class;
	}

}
