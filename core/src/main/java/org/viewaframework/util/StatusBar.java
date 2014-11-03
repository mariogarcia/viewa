package org.viewaframework.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;



/**
 * @author Mario Garcia
 *
 */
public class StatusBar extends JPanel {

	private static final String PROGRESS_INFO_BUTTON_ICON ="org/viewaframework/widget/icon/fan/img/misc/information.png";
	//[ID: 2891883]: StatusBar info button can't be accessed
	public static final String PROGRESS_INFO_BUTTON = "progressInfoButton";
	public static final String LEFT_PANEL_LABEL = "applicationLeftPanelLabel";
	private static final long serialVersionUID = 1L;
	public static final String STATUS_BAR_NAME = "applicationProgressBar";
	private JPanel leftPanel;
	private JLabel leftPanelLabel;
	private JProgressBar progressBar;
	private JButton progressInfoButton;
	private JPanel rightPanel;

	
	/**
	 * 
	 */
	public StatusBar() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(10, 25));
		add(this.getRightPanel(), BorderLayout.EAST);
		add(this.getLeftPanel(), BorderLayout.WEST);
		setBorder(BorderFactory.createEtchedBorder());
		setBackground(SystemColor.control);
	}

	/**
	 * @return
	 */
	public JPanel getLeftPanel() {
		if (this.leftPanel == null){
			leftPanel = new JPanel(new BorderLayout());
			leftPanel.setOpaque(false);			
			GridBagLayout gb = new GridBagLayout();
			GridBagConstraints constraints = new GridBagConstraints();
		    constraints.fill = GridBagConstraints.CENTER;
			this.leftPanel.setLayout(gb);	
		    constraints.insets = new Insets(0,2,0,0);
		    gb.setConstraints(this.getLeftPanelLabel(), constraints);
		    
			leftPanel.add(this.getLeftPanelLabel());		
		}
		return leftPanel;
	}

	/**
	 * @return
	 */
	public JLabel getLeftPanelLabel() {
		if (this.leftPanelLabel == null){
			leftPanelLabel = new JLabel();	
			leftPanelLabel.setName(LEFT_PANEL_LABEL);
		}
		return leftPanelLabel;
	}

	/**
	 * @return
	 */
	public JProgressBar getProgressBar() {
		if (this.progressBar == null){
			this.progressBar = new JProgressBar();
			this.progressBar.setName(STATUS_BAR_NAME);
			this.progressBar.setPreferredSize(new Dimension(100,18));
		}
		return progressBar;
	}

	/**
	 * @return
	 */
	public JButton getProgressInfo() {
		if (this.progressInfoButton == null){			
			progressInfoButton = ResourceLocator.getImageIcon(StatusBar.class,PROGRESS_INFO_BUTTON_ICON) != null ? 
					new JButton(ResourceLocator.getImageIcon(StatusBar.class,PROGRESS_INFO_BUTTON_ICON)) : 
					new JButton("i");
		//[ID: 2891883]: StatusBar info button can't be accessed
			progressInfoButton.setName(PROGRESS_INFO_BUTTON);
			progressInfoButton.setRolloverEnabled(true);
			progressInfoButton.setPreferredSize(new Dimension(18,18));			
		}
		return progressInfoButton;
	}
	
	/**
	 * @return
	 */
	public JPanel getRightPanel() {
		if (this.rightPanel == null){
			rightPanel = new JPanel();
			rightPanel.setOpaque(false);
			GridBagLayout gb = new GridBagLayout();
			GridBagConstraints constraints = new GridBagConstraints();
		    constraints.fill = GridBagConstraints.CENTER;
			gb.setConstraints(this.getRightPanel(), constraints);
			this.getRightPanel().setLayout(gb);	
		    constraints.insets = new Insets(0,0,0,2);
		    gb.setConstraints(this.getProgressBar(), constraints);
		    this.getRightPanel().add(this.getProgressBar());
		    gb.setConstraints(getProgressInfo(), constraints);
		    this.getRightPanel().add(getProgressInfo());			
		}
		return rightPanel;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int y = 0;
		g.setColor(new Color(156, 154, 140));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(196, 194, 183));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(218, 215, 201));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(233, 231, 217));
		g.drawLine(0, y, getWidth(), y);

		y = getHeight() - 3;
		g.setColor(new Color(233, 232, 218));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(233, 231, 216));
		g.drawLine(0, y, getWidth(), y);
		y = getHeight() - 1;
		g.setColor(new Color(221, 221, 220));
		g.drawLine(0, y, getWidth(), y);
		

		int w = getWidth( );
		int h = getHeight( );
		 
		GradientPaint gp = new GradientPaint(0, 0, SystemColor.menu,0, h, Color.WHITE );
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint( gp );
		g2d.fillRect( 0, 0, w, h );

	}

	/**
	 * @param leftPanel
	 */
	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	/**
	 * @param leftPanelLabel
	 */
	public void setLeftPanelLabel(JLabel leftPanelLabel) {
		this.leftPanelLabel = leftPanelLabel;
	}

	/**
	 * @param progressBar
	 */
	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	/**
	 * @param progressInfo
	 */
	public void setProgressInfo(JButton progressInfo) {
		this.progressInfoButton = progressInfo;
	}

	/**
	 * @param rightPanel
	 */
	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

}
