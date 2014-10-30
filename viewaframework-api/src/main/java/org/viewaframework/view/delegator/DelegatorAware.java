package org.viewaframework.view.delegator;

import java.util.List;

public interface DelegatorAware {

	public void setDelegators(List<Delegator> delegators);
	public void addDelegator(Delegator delegator);
	public List<Delegator> getDelegators();
	public void removeDelegator(Delegator delegator);
	
}
