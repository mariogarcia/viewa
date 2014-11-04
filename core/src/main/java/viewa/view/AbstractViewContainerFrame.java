package viewa.view;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;

import viewa.view.delegator.Delegator;
import viewa.view.delegator.DialogViewClosingWindowDelegator;


public abstract class AbstractViewContainerFrame extends AbstractViewContainer implements ViewContainerFrame
{
	private List<Delegator> delegators;
	private JFrame frame;

	/**
	 * 
	 */
	public AbstractViewContainerFrame(){
		super(ViewManager.ROOT_VIEW_ID);
	}

	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#getDelegators()
	 */
	@Override
	public List<Delegator> getDelegators() {
		if (this.delegators == null){
			this.delegators = super.getDelegators();
			this.delegators.add(new DialogViewClosingWindowDelegator(FRAME));
		}
		return this.delegators;
	}
	

	/* (non-Javadoc)
	 * @see viewa.view.ViewContainerFrame#getFrame()
	 */
	public JFrame getFrame() {
		if (this.frame == null){
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();     
			GraphicsDevice 		gd = ge.getDefaultScreenDevice();
			this.frame = new JFrame(this.getId());
			//TODO
			this.frame.getContentPane().setLayout(new BorderLayout());
			
			this.frame.setName(FRAME);
			this.frame.addWindowListener(new WindowAdapter(){
				@Override
				public void windowClosing(WindowEvent arg0) {
					getApplication().close();
				}
			});
			this.frame.setBounds(gd.getDefaultConfiguration().getBounds());		
			this.frame.setLocationByPlatform(true);
		}
		return this.frame;
	}

	/**
	 * Setting the application icon
	 * 
	 * @return
	 */
	public Image getIconImage() {
		return this.getFrame()!=null ? this.getFrame().getIconImage():null;
	}
	
	/* (non-Javadoc)
	 * @see viewa.view.AbstractViewContainer#setDelegators(java.util.List)
	 */
	public void setDelegators(List<Delegator> delegators) {
		this.delegators = delegators;
	}

	/**
	 * Getting the application view
	 * 
	 * @param iconImage
	 */
	public void setIconImage(Image iconImage) {
		if(this.getFrame()!=null)this.getFrame().setIconImage(iconImage);
	}
}
