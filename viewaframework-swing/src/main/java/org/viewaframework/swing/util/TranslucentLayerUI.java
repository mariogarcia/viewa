package org.viewaframework.swing.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;

import javax.swing.JComponent;

import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.AbstractLayerUI;

/**
 * Common layer to block user interaction
 * 
 * @author mgg
 *
 */
public class TranslucentLayerUI extends AbstractLayerUI<JComponent>{

	private static final long serialVersionUID = 674458593250952042L;

	/* (non-Javadoc)
	 * @see org.jdesktop.jxlayer.plaf.AbstractLayerUI#paintLayer(java.awt.Graphics2D, org.jdesktop.jxlayer.JXLayer)
	 */
	@Override
	protected void paintLayer(Graphics2D g2, JXLayer<? extends JComponent> l) {
		super.paintLayer(g2, l);
		Rectangle clip = g2.getClipBounds();
		Area rect = new Area(clip);
		g2.setClip(rect);
		Color alphaWhite = new Color(1.0f,1.0f,1.0f,0.65f);
		g2.setColor(alphaWhite);
		g2.fillRect(clip.x, clip.y, clip.width, clip.height);
	}
}
