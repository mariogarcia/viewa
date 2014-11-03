package org.viewaframework.swing.pagination;

import org.viewaframework.swing.PaginationPanel;


/**
 * Those who want to be posted about {@link PaginationPanel} events should
 * implement this interface
 * 
 * @author mgg
 *
 */
public interface PaginationListener {

	/**
	 * @param ev
	 */
	public void paginationPerformed(PaginationEvent ev);
}
