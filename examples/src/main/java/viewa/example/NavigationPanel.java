package viewa.example;

import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

public class NavigationPanel extends JXTaskPaneContainer{
    private static final long serialVersionUID = 1L;

    private JXTaskPane textTaskPanel;
    private JXHyperlink textPanelLink;

    public NavigationPanel(){
        super();
        init();
    }

    private void init() {
        this.setName("navigationPanel");
        this.textPanelLink = new JXHyperlink();
        this.textPanelLink.setName("textPanelLinkName");
        this.textPanelLink.setText("Show text panel");
        this.textTaskPanel = new JXTaskPane();
        this.textTaskPanel.setName("imageView");
        this.textTaskPanel.setTitle("Images");
        this.textTaskPanel.add(textPanelLink);
        this.add(this.textTaskPanel);
    }
}
