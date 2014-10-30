package org.viewaframework.swing;

import org.jdesktop.swingx.JXTitledPanel;

public class LoginTitledPanel extends JXTitledPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginTitledPanel(){
		super("Login");
		this.add(new LoginPanel());
	}
	
}
