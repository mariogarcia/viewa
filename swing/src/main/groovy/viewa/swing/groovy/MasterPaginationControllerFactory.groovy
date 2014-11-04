package viewa.swing.groovy

class MasterPaginationControllerFactory extends AbstractFactory{

	static class PaginationListenerWrapper{
		def closure		
	}
	
	def paginationListenerWrapper
	
	public Object newInstance(FactoryBuilderSupport builder, Object name,
			Object value, Map attributes) throws InstantiationException,
			IllegalAccessException {
		return new PaginationListenerWrapper();
	}

	@Override
	public boolean isHandlesNodeChildren() {
		true
	}

	@Override
	public boolean onNodeChildren(FactoryBuilderSupport builder, Object node,
			Closure childContent) {
		node.closure = childContent
		
		return false
	}
			
			

			
	
}
