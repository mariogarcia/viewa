package viewa.swing.binding.core;


/**
 * @author Mario Garcia
 *
 * @param <SS>
 * @param <SP>
 * @param <TS>
 * @param <TP>
 */
public class BeanAdapterBinding<SS,SP,TS,TP> extends AbstractSelectionBinding<SS, SP, TS, TP>{
	
	private BeanAdapterPropertyListener<TS,TP,SP> sourceListener;
	private BeanAdapterPropertyListener<SS,SP,TP> targetListener;

	/**
	 * @param source
	 * @param sourceProperty
	 * @param target
	 * @param targetProperty
	 */
	public BeanAdapterBinding(BeanAdapter<SS> source, Property<SP> sourceProperty,BeanAdapter<TS> target, Property<TP> targetProperty) {
		super(source, sourceProperty,target, targetProperty);
		this.createListeners();
	}
	
	/**
	 * @param source
	 * @param sourceProperty
	 * @param target
	 * @param targetProperty
	 */
	public BeanAdapterBinding(SS source, Property<SP> sourceProperty,TS target, Property<TP> targetProperty) {
		super(new BasicBeanAdapter<SS>(source), sourceProperty, new BasicBeanAdapter<TS>(target), targetProperty);
		this.createListeners();
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#bind()
	 */
	public void bind() {
		this.getSource().addPropertyChangeListener(sourceListener);
		this.getTarget().addPropertyChangeListener(targetListener);
	}

	/**
	 * 
	 */
	private void createListeners() {
		this.targetListener = new BeanAdapterPropertyListener<SS, SP, TP>(
				this.getSource(),this.getSourceProperty(),this.getTargetProperty());
		this.sourceListener = new BeanAdapterPropertyListener<TS, TP, SP>(
				this.getTarget(),this.getTargetProperty(),this.getSourceProperty());
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Binding#unbind()
	 */
	public void unbind() {
		this.getSource().removePropertyChangeListener(sourceListener);
		this.getTarget().removePropertyChangeListener(targetListener);
	}
	
}
