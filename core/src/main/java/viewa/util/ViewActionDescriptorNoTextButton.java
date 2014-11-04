package viewa.util;

import javax.swing.JButton;

/**
 * 
 * This fake button is used by ActionDescriptorDelegator for hiding text in the tool bar buttons.
 * 
 * @author Mario Garcia
 *
 */
public class ViewActionDescriptorNoTextButton extends JButton{

	private static final long serialVersionUID = -880584442187804586L;

	/**
	 * Default constructor
	 */
	public ViewActionDescriptorNoTextButton(){
	 /* Deactivating button text */ 
		super.setText(null);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.AbstractButton#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) { /* Deactivating button text */ }
}
