package viewa.widget.view;

import java.util.List;

import viewa.view.ViewContainerEditor;
import viewa.widget.view.ui.MasterViewColumn;

/**
 * @author mario
 *
 * @param <E>
 */
public abstract class MasterViewEditor<E> extends MasterView implements ViewContainerEditor {

	public MasterViewEditor(String id,List<MasterViewColumn> columns){
		super(id, columns);
	}

}
