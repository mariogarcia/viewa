package viewa.model;

import java.util.HashMap;
import java.util.Map;

public class AbstractViewModelManager implements ViewModelManager{

	private Map<String,ViewModel> viewModels = new HashMap<String, ViewModel>();

	/* (non-Javadoc)
	 * @see viewa.model.ViewModelManager#addViewModel(java.lang.String, viewa.model.ViewModel)
	 */
	public void addViewModel(String viewId, ViewModel viewModel) {
		this.viewModels.put(viewId, viewModel);
	}

	/* (non-Javadoc)
	 * @see viewa.model.ViewModelManager#getViewModelMap(java.lang.String)
	 */
	public Map<String, ViewModel> getViewModelMap(String viewId) {
		Map<String,ViewModel> justViewModel = new HashMap<String,ViewModel>();
		for (String key: this.viewModels.keySet()){
			if (key.toLowerCase().startsWith(viewId.toLowerCase())){
				justViewModel.put(key, this.viewModels.get(key));
			}
		}
		return justViewModel;
	}

	/* (non-Javadoc)
	 * @see viewa.model.ViewModelManager#removeViewModel(java.lang.String)
	 */
	public void removeViewModel(String viewId) {
		this.viewModels.remove(viewId);
	}
	
}
