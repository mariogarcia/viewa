package viewa.swing.binding.core;

import viewa.swing.binding.collection.EventList;



/**
 * @author Mario Garcia
 *
 * @param <SS>
 * @param <SP>
 * @param <TS>
 * @param <TP>
 */
public interface ListableBinding<SS,TS> extends Binding<SS>{

	/**
	 * @return
	 */
	public BeanAdapter<SS> getSource();
	/**
	 * @return
	 */
	public EventList<TS> getTarget();
	/**
	 * @param source
	 */
	public void setSource(BeanAdapter<SS> source);
	/**
	 * @param target
	 */
	public void setTarget(EventList<TS> target);	
}