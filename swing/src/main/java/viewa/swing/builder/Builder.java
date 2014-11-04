package viewa.swing.builder;


/**
 * @author mgg
 *
 * @param <T>
 */
public interface Builder<T> {

	/**
	 * @return
	 */
	public T getTarget();
	/**
	 * @return
	 */
	public Class<T> getType();
	/**
	 * @param target
	 */
	public void setTarget(T target);
}