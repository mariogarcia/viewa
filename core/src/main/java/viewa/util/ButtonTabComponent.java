/*
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package viewa.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicButtonUI;

import viewa.view.ViewContainer;
import viewa.view.ViewException;
import viewa.view.perspective.PerspectiveConstraint;

/**
 * Component to be used as tabComponent;
 * Contains a JLabel to show the text and 
 * a JButton to close the tab it belongs to 
 */ 
public class ButtonTabComponent extends JPanel {
    
	private static final long serialVersionUID = 1765202489761914157L;
	private final JTabbedPane pane;
	private final JSplitPane rightToLeft;
	private final JSplitPane topBottom;
    private final ViewContainer viewContainer;

    public ButtonTabComponent(
    		final JSplitPane rigthToLeft,
    		final JSplitPane topBottom,
    		final JTabbedPane pane,
    		final ViewContainer viewContainer,
    		final Icon iconImage) {
    	
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));        
        if (pane == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        this.pane = pane;
        this.rightToLeft = rigthToLeft;
        this.topBottom = topBottom;
        this.viewContainer = viewContainer;
        setOpaque(false);
        
        //make JLabel read titles from JTabbedPane
        JLabel label = new JLabel() {
			private static final long serialVersionUID = 1L;
			public String getText() {
                int i = pane.indexOfTabComponent(ButtonTabComponent.this);
                if (i != -1) {
                    return pane.getTitleAt(i);
                }
                return null;
            }
        };
        if (iconImage!=null){
        	label.setIcon(iconImage);
        }
        add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        //tab button
        add(new TabButton("viewa/util/detach.png", new MaximizeListener()));
        add(new TabButton("viewa/util/fix.png", new RestoreListener()));
        add(new TabButton("viewa/util/close.png", new ClosingListener()));
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

    private class TabButton extends JButton{
		private static final long serialVersionUID = 1L;
		public TabButton(String iconClassPath,ActionListener listener) {
            int size = 17;
            setPreferredSize(new Dimension(size, size));
            setUI(new BasicButtonUI());
            setContentAreaFilled(false);
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            setIcon(ResourceLocator.getImageIcon(ButtonTabComponent.class,iconClassPath));
            addMouseListener(buttonMouseListener);
            setRolloverEnabled(true);
            addActionListener(listener);
        }
        //we don't want to update UI for this button
        public void updateUI() {}
    }
    
    private class MaximizeListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        	SwingUtilities.invokeLater(new Runnable() {
        		public void run() {        	
        			String name = pane.getName();
        			int i = pane.indexOfTabComponent(ButtonTabComponent.this);
        			if (name.equals(PerspectiveConstraint.RIGHT.name())){
        				rightToLeft.getLeftComponent().setVisible(false);
        				topBottom.getBottomComponent().setVisible(false);
        			} else if (name.equals(PerspectiveConstraint.LEFT.name())){
        				rightToLeft.getRightComponent().setVisible(false);
        			} else if (name.equals(PerspectiveConstraint.BOTTOM.name())){
        				rightToLeft.getLeftComponent().setVisible(false);
        				topBottom.getTopComponent().setVisible(false);
        			}
        			pane.setSelectedIndex(i);
        			rightToLeft.getParent().validate();
        			rightToLeft.getParent().repaint();
        		}
        	});
        }    
    }
    
    private class RestoreListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        	SwingUtilities.invokeLater(new Runnable() {
        		public void run() {
        			int i = pane.indexOfTabComponent(ButtonTabComponent.this);
        			rightToLeft.resetToPreferredSizes();
        			topBottom.resetToPreferredSizes();  		
        			rightToLeft.getLeftComponent().setVisible(true);
        			rightToLeft.getRightComponent().setVisible(true);
        			topBottom.getBottomComponent().setVisible(true);        	
        			topBottom.getTopComponent().setVisible(true); 
        			pane.setSelectedIndex(i);
        		}
        	});
        }    
    }    
    
    private class ClosingListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            int i = ButtonTabComponent.this.pane.indexOfTabComponent(ButtonTabComponent.this);
            if (i != -1) {
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		try{
                			ButtonTabComponent.this.viewContainer.
                				getApplication().
                				getViewManager().
                				removeView(ButtonTabComponent.this.viewContainer);	
                		} catch (ViewException e1) {
                			e1.printStackTrace();
                		}
                	}
                });        											
            }
        }    	
    }

    private final static MouseListener buttonMouseListener = new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };
}


