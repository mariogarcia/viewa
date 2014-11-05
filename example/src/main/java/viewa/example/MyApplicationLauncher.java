package viewa.example;

import javax.swing.LookAndFeel;
import viewa.core.DefaultApplicationLauncher;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.LightGray;

public class MyApplicationLauncher extends DefaultApplicationLauncher{
    @Override
    public LookAndFeel getLookAndFeel() {
        PlasticLookAndFeel lnf =  new PlasticLookAndFeel();
        PlasticLookAndFeel.setCurrentTheme(new LightGray());
        return lnf;
    }
}
