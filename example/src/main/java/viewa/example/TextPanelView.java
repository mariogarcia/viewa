package example.viewa;

import javax.swing.JTextPane;
import viewa.view.DefaultViewContainer;

public class TextPanelView extends DefaultViewContainer {
    public static final String ID = "TextPanelViewID";

    public TextPanelView(){
        super(ID,new JTextPane());
    }
}
