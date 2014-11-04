package viewa.model;

public class DefaultViewModel extends AbstractViewModel{
	public DefaultViewModel(Class<?> classType){
		this.setModelClass(classType);
	}
	
	public DefaultViewModel(String classPath){
		try {
			this.setModelClass(DefaultViewModel.class.getClassLoader().loadClass(classPath));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
