package viewa.example;

import javax.swing.LookAndFeel;
import javax.swing.plaf.metal.MetalLookAndFeel;
import viewa.core.DefaultApplicationLauncher;

public class MyApplicationLauncher extends DefaultApplicationLauncher{
    @Override
    public LookAndFeel getLookAndFeel() {

        return new MetalLookAndFeel();
    }
}
