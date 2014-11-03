package org.viewaframework.util;

import org.viewaframework.common.MyTrayView;
import org.viewaframework.view.ViewException;
import org.w3c.dom.Document;

import junit.framework.TestCase;

public class ViewTrayIconBuilderTest extends TestCase{	
	public void testBuild() throws ViewException,Exception{
		ViewActionDescriptorFileHandler handler = new ViewActionDescriptorFileHandler();
		Document document = handler.getDocument(new MyTrayView());
		ViewTrayIconBuilder builder = new ViewTrayIconBuilder(document);		
		ViewTrayIcon trayIcon = builder.build();		
		assertNotNull(trayIcon.getRightClickMenu());
		assertNotNull(trayIcon.getLeftClickMenu());
		assertTrue(trayIcon.getRightClickMenu().getComponentCount() > 0);
		assertTrue(trayIcon.getLeftClickMenu().getComponentCount() > 0);
	}	
}
