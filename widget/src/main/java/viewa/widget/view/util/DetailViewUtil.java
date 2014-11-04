package viewa.widget.view.util;

import java.awt.Component;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.beansbinding.Property;
import viewa.model.DefaultViewModel;
import viewa.model.ViewModel;
import viewa.swing.binding.SwingBinding;
import viewa.swing.binding.collection.EventList;
import viewa.swing.binding.core.BasicBeanAdapter;
import viewa.util.StatusBar;
import viewa.view.ViewException;
import viewa.view.ViewManager;
import viewa.widget.view.DetailView;
import viewa.widget.view.MasterView;
import viewa.widget.view.ui.DetailViewRelationshipPanel;
import viewa.widget.view.ui.MasterViewModel;

/**
 * This class is built like a builder. It's a helper for preparing the detail view
 * before showing it up. This class should be used within a MasterViewController.
 * 
 * @author Mario Garcia
 *
 * @param <D> The detail dto class
 * @param <M> The master dto class
 */
public class DetailViewUtil<D,M> {

	private static final String EMPTY_STRING = "";
	public static final String ID = "InvoiceMasterViewControllerId";
	private static final Log logger = LogFactory.getLog(DetailViewUtil.class);
	private static final String POINT = ".";
	private static final String PROPERTY_DATE = "date";
	private static final String PROPERTY_TEXT = "text";
	private DetailView<D,M> detailView;
	
	public DetailViewUtil(DetailView<D,M> detailView){
		this.detailView = detailView;
	}
	
	/**
	 * Binds a list with a detail's working object's property.
	 * 
	 * @param <R>
	 * @param name
	 * @param relationshipData
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <R> DetailViewUtil bindingRelationshipList(String name,List<R> relationshipData){
		Map<String,Object> workingObjectRelationships = Map.class.cast(detailView.getModelValue(DetailView.MODEL_RELATIONSHIPS));
		if (workingObjectRelationships == null){
			workingObjectRelationships = new HashMap<String, Object>();
			detailView.addModelValue(DetailView.MODEL_RELATIONSHIPS,workingObjectRelationships);
		}
		workingObjectRelationships.put(name, relationshipData);
		return this;
	}
	
	/**
	 * Populates a relationship panel
	 * 
	 * @param <R>
	 * @param name
	 * @param relationshipData
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <R> DetailViewUtil bindingRelationshipPanelList(String name,List<R> relationshipData){
		DetailViewRelationshipPanel<R,D> 	tablePanel 	= DetailViewRelationshipPanel.class.cast(detailView.getComponentByName(name));
		MasterViewModel<R> 					model 		= MasterViewModel.class.cast(tablePanel.getRelationshipModel());
		model.addAll(relationshipData);		
		return this;
	}
	
	/**
	 * Cleans both the selected master object and the detail working object.
	 * 
	 * @param masterView
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public DetailViewUtil<D,M>  cleanWorkingModelObject(MasterView<M> masterView)
	throws ViewException {
		if (detailView != null){
			detailView.addModelValue(DetailView.MODEL_MASTER_OBJECT, null);
			detailView.addModelValue(DetailView.MODEL_WORKING_OBJECT, null);
		}
		return this;
	}
	
	/**
	 * Populates both the selected master object and the detail working object.
	 * 
	 * @param masterView
	 * @throws Exception
	 */
	public DetailViewUtil<D,M>  populateWorkingAndMasterObjects(MasterView<M> masterView) throws ViewException {
		if (detailView != null){
			M selectedObject = masterView.getModel().getSelectedObject();
			detailView.addModelValue(DetailView.MODEL_MASTER_OBJECT,selectedObject);
			detailView.addModelValue(DetailView.MODEL_WORKING_OBJECT,null);
		}
		return this;
	}
	
	/**
	 * Binds the working object with the UI components
	 * 
	 * @return
	 * @throws ViewException
	 */
	@SuppressWarnings("unchecked")
	public DetailViewUtil<D,M> bindingWorkingObject() throws ViewException{
		 Map<String, AutoBinding<Object, Object, Component, Object>> viewContext = new HashMap<String, AutoBinding<Object, Object, Component, Object>>();
		Map<String, ViewModel> arg1 = detailView.getViewModelMap();
		detailView.getViewModelMap().put(
				DetailView.MODEL_WORKING_OBJECT,
					new DefaultViewModel(detailView.getDetailType()));		
		try {
			for (String key : arg1.keySet()) {
				ViewModel 	viewModel 	= arg1.get(key);
				String 		alias 		= key.replace(detailView.getId() + POINT, EMPTY_STRING);
				Class<?> 	modelClass 	= viewModel.getModelClass();
				Field[] 	fields 		= modelClass.getDeclaredFields();
				Object 		liveModel 	= null;
				
				liveModel = detailView.getModelValue(alias) != null ? detailView.getModelValue(alias) : modelClass.newInstance();
				for (Field field : fields) {
					Map<String,Object> relationShips = Map.class.cast(detailView.getModelValue(DetailView.MODEL_RELATIONSHIPS));
					if (relationShips!= null && relationShips.get(field.getName()) != null){
						List 			relationShipList 	= List.class.cast(relationShips.get(field.getName()));						
						JComboBox 		combo 				= JComboBox.class.cast(detailView.getComponentByName(field.getName()));
						SwingBinding	swb					= new SwingBinding();
						Object 			o 					= new PropertyUtilsBean().getProperty(liveModel, field.getName());
						boolean			newInstance			= false;
						
						if (o == null){
							o = field.getType().newInstance();
							newInstance = true;
						}
						
						new PropertyUtilsBean().setProperty(liveModel, field.getName(), o);
						
						if (logger.isDebugEnabled()){
							logger.debug("relationShip[type:"+field.getType()+"]");
						}
						
						swb.
							createComboBoxListBinding(
									combo,
									new EventList(relationShipList)).
							createComboBoxSelectionBinding(
									combo,
									new BasicBeanAdapter(o)).
						bind();
						detailView.addModelValue("swingBinding",swb);
						if (newInstance){
							combo.getModel().setSelectedItem(combo.getItemAt(0));
						} else {
							combo.getModel().setSelectedItem(o);
						}
						
						Bindings.createAutoBinding(
							UpdateStrategy.READ_WRITE,
							o,
							liveModel,
							ELProperty.create("${"+field.getName()+"}")).bind();
						
						
					} else {					
						Component 					liveSource 	= detailView.getComponentByName(field.getName());
						Property<Object, Object> 	propertyOne = BeanProperty.create(field.getName());
						Property<Component, Object> propertyTwo = null;
							if (field.getType().equals(Date.class) ){
								propertyTwo = BeanProperty.create(PROPERTY_DATE);
							} else {
								propertyTwo = BeanProperty.create(PROPERTY_TEXT);
							}
						if (liveSource != null) {
							AutoBinding<Object, Object, Component, Object> viewBinding = Bindings
							.createAutoBinding(UpdateStrategy.READ_WRITE,
									liveModel, 
									propertyOne, 
									liveSource,
									propertyTwo);
							viewContext.put(alias, viewBinding);
							viewBinding.bind();
						}
					}
				}
				detailView.addModelValue(alias, liveModel);
			}
		} catch (Exception ex) {
			logger.fatal(ex.getMessage());
			throw new ViewException();
		}
		detailView.addModelValue("viewContext",viewContext);
		return this;
	}
	
	/**
	 * Opens the detail view using the given master view
	 * 
	 * @param masterView
	 * @return
	 * @throws ViewException
	 */
	public DetailViewUtil<D,M>  openViewFrom(MasterView<M> masterView) throws ViewException{
		masterView.getApplication().getViewManager().addView(detailView);
		return this;
	}
	
	/**
	 * Enables / Disables some action descriptors.
	 * 
	 * @param actionNames
	 * @param enabled
	 * @return
	 */
	public DetailViewUtil<D,M>  setEnableActionDescriptors(List<String> actionNames,Boolean enabled){
		if (enabled){
			this.setEnableComponents(actionNames, enabled);
		} else {
			detailView.getNonEditableDisabledActions().addAll(actionNames);
		}
		return this;
	}
	
	/**
	 * Enables / Disables the components with the given names
	 * 
	 * @param componentNames
	 * @param enabled
	 * @return
	 */
	private DetailViewUtil<D,M>  setEnableComponents (List<String> componentNames,Boolean enabled){
		for (String st: componentNames){
			List<Component> componentsToDisable = detailView.getComponentsByName(st);
			if (componentsToDisable != null){
				for (Component c : componentsToDisable){
					c.setEnabled(enabled);
				}
			}
		}
		return this;
	}
	
	/**
	 * Makes the detail view glass panel to show up or dissapear
	 * 
	 * @param enabled
	 * @return
	 */
	public  DetailViewUtil<D,M> setEnablePanel(Boolean enabled){
		detailView.getGlassPane().setVisible(!enabled);
		return this;
	}
	
	/**
	 * This method can access to the status bar to set a message and a progress status
	 * 
	 * @param viewManager
	 * @param progress
	 * @param message
	 * @return
	 */
	public DetailViewUtil<D,M> setProgress(ViewManager viewManager,int progress,String message){
		JProgressBar pb = (JProgressBar) viewManager.getRootView().getComponentByName(StatusBar.STATUS_BAR_NAME);
		JLabel la = (JLabel)viewManager.getRootView().getComponentByName(StatusBar.LEFT_PANEL_LABEL);
		pb.setStringPainted(true);
		pb.setValue(progress);
		la.setText(message);
		return this;
	}
	
	/**
	 * Unbinds the working object
	 * 
	 * @return
	 * @throws ViewException
	 */
	@SuppressWarnings("unchecked")
	public DetailViewUtil<D,M>  unbindingWorkingObject () throws ViewException{
		 Map<String, AutoBinding<Object, Object, Component, Object>> viewContext = Map.class.cast(detailView.getModelValue("viewContext"));
		SwingBinding sbinding = SwingBinding.class.cast(detailView.getModelValue("swingBinding"));
		 try {
			for (AutoBinding<Object, Object, Component, Object> binding : viewContext.values()) {
				binding.unbind();
			}
			for (String key : detailView.getViewModelMap().keySet()) {
				detailView.addModelValue(key, null);
			}
			
			
		} catch (Exception ex) {
			throw new ViewException();
		}
		sbinding.unbind();
		return this;
	}
}
