package viewa.docking.mydoggy;

import javax.swing.JTextPane;

import viewa.view.DefaultViewContainer;

public class MyDoggyView extends DefaultViewContainer{
	public static final String ID = "MyDoggyViewID";
	public MyDoggyView(){
		super(ID,new JTextPane());
	}
}
