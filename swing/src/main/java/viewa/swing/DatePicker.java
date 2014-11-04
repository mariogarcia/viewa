package viewa.swing;

import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXDatePicker;
import viewa.swing.datepicker.BasicDatePickerUI;


/**
 * This class is a customized version of the JXDatePicker from www.swinglabs.org. It
 * allows to enable / disable the button border through the look and feel. Here you
 * have the lnf keys for the available customizations: <br/><br/>
 * 
 * <ul>
 * 		<li>JXDatePicker.popupButton : The JButton that triggers the popup</li>
 * 		<li>JXDatePicker.popupButton.border : The popup button border</li>
 * 		<li>JXDatePicker.popupButton.border.enabled : Enables or disables the popup button border (Disabled by default)</li>
 * 		<li>JXDatePicker.arrowIcon : The popup button image icon (This key already existed)</li>
 * </ul>
 * 
 * @author Mario Garcia
 * @since 1.0.5
 *
 */
public class DatePicker extends JXDatePicker{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3962076799479953441L;
	
	public void updateUI() {
        setUI(new BasicDatePickerUI());
        // JW: quick hack around #706-swingx - monthView not updated
        // is this complete? how about editor (if not uiResource), linkPanel?
        SwingUtilities.updateComponentTreeUI(getMonthView());
        invalidate();
    }
}
