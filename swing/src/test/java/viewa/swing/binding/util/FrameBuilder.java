package viewa.swing.binding.util;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FrameBuilder {

	public static JFrame createTestingFrame(){
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(350,250);
		return frame;
	}
}
