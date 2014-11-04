package viewa.swing.binding.core;

/**
 * @author Mario Garcia
 *
 * @param <SS>
 */
public interface Binding<SS> {

	/**
	 * Binds source and target objects
	 */
	public void bind();
	/**
	 * Unbinds source and target objects
	 */
	public void unbind();
}
