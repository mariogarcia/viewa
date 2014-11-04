package viewa.test;

import java.util.Collection;

import junit.framework.TestCase;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import viewa.test.application.ApplicationTrapperAware;
import viewa.test.application.NumberAware;
import viewa.test.application.ViewTrapperAware;


public class PropertyTrapper 
	implements Trapper<Object>, ApplicationTrapperAware, 
	ViewTrapperAware,NumberAware{

	private static final Log logger = LogFactory.getLog(PropertyTrapper.class);
	private ApplicationTrapper applicationTrapper;	
	private Object target;
	
	/**
	 * @param applicationTrapper
	 * @param componentName
	 */
	public PropertyTrapper(ApplicationTrapper applicationTrapper,String targetName) {
		this.applicationTrapper = applicationTrapper;
		this.target = applicationTrapper.view().getTarget();
		try {
			this.target = new PropertyUtilsBean().getProperty(this.target,targetName);
		} catch (Exception e) {
			TestCase.fail(e.getMessage());
		} 
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ApplicationTrapperAware#applicationTrapper()
	 */
	public ApplicationTrapper applicationTrapper() {
		return this.applicationTrapper;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asByte()
	 */
	public PropertyTrapper asByte() {
		try{
			if (this.target instanceof Number){
				this.target = Number.class.cast(this.target).byteValue();
			}
		} catch (Throwable t){
			TestCase.fail(t.getMessage());
		}
		return this;
	}
	
	
	/**
	 * @return
	 */
	public PropertyTrapper asCollection(){
		this.target = Collection.class.cast(target);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asDouble()
	 */
	public PropertyTrapper asDouble() {
		try{
			if (this.target instanceof Number){
				this.target = Number.class.cast(this.target).doubleValue();
			}
		} catch (Throwable t){
			TestCase.fail(t.getMessage());
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asFloat()
	 */
	public PropertyTrapper asFloat() {
		try{
			if (this.target instanceof Number){
				this.target = Number.class.cast(this.target).floatValue();
			}
		} catch (Throwable t){
			TestCase.fail(t.getMessage());
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asInteger()
	 */
	public PropertyTrapper asInteger() {
		try{
			if (this.target instanceof Number){
				this.target = Number.class.cast(this.target).intValue();
			}
		} catch (Throwable t){
			TestCase.fail(t.getMessage());
		}
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asLong()
	 */
	public PropertyTrapper asLong() {
		try{
			if (this.target instanceof Number){
				this.target = Number.class.cast(this.target).longValue();
			}
		} catch (Throwable t){
			TestCase.fail(t.getMessage());
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asNumber()
	 */
	public PropertyTrapper asNumber() {
		try{
			if (this.target instanceof Number){
				this.target = Number.class.cast(this.target);
			}
		} catch (Throwable t){
			TestCase.fail(t.getMessage());
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asShort()
	 */
	public PropertyTrapper asShort() {
		try{
			if (this.target instanceof Number){
				this.target = Number.class.cast(this.target).shortValue();
			}
		} catch (Throwable t){
			TestCase.fail(t.getMessage());
		}
		return this;
	}

	/**
	 * @return
	 */
	public Integer collectionSize(){
		Integer result = -1;
		if (this.target.getClass().isAssignableFrom(Collection.class)){
			result = Collection.class.cast(this.target).size();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getTarget()
	 */
	public Object getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getType()
	 */
	public Class<Object> getType() {
		return Object.class;
	}

	/* (non-Javadoc)
	 * @see viewa.test.Trapper#log(java.lang.String)
	 */
	public PropertyTrapper log(String message) {
		logger.info(message);
		return this;
	}

	/**
	 * @param propertyName
	 * @return
	 */
	public PropertyTrapper property(String propertyName){
		try {
			this.target = new PropertyUtilsBean().getProperty(this.target,propertyName);
		} catch (Exception e) {
			TestCase.fail(e.getMessage());
		} 
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireByte(java.lang.Byte)
	 */
	public PropertyTrapper requireByte(Byte number) {
		if (this.target instanceof Byte){
			TestCase.assertEquals(number,this.target);
		} else {
			TestCase.fail("Not correct type Byte expected");
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireDouble(java.lang.Double)
	 */
	public PropertyTrapper requireDouble(Double number) {
		if (this.target instanceof Double){
			TestCase.assertEquals(number,this.target);
		} else {
			TestCase.fail("Not correct type Double expected");
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireFloat(java.lang.Float)
	 */
	public PropertyTrapper requireFloat(Float number) {
		if (this.target instanceof Float){
			TestCase.assertEquals(number,this.target);
		} else {
			TestCase.fail("Not correct type Float expected");
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireInteger(java.lang.Integer)
	 */
	public PropertyTrapper requireInteger(Integer number) {
		if (this.target instanceof Integer){
			TestCase.assertEquals(number,this.target);
		} else {
			TestCase.fail("Not correct type Integer expected");
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireLong(java.lang.Long)
	 */
	public PropertyTrapper requireLong(Long number) {
		if (this.target instanceof Long){
			TestCase.assertEquals(number,this.target);
		} else {
			TestCase.fail("Not correct type Long expected");
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireNumber(java.lang.Number)
	 */
	public PropertyTrapper requireNumber(Number number) {
		if (this.target instanceof Number){
			TestCase.assertEquals(number,this.target);
		} else {
			TestCase.fail("Not correct type Number expected");
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireShort(java.lang.Short)
	 */
	public PropertyTrapper requireShort(Short number) {
		if (this.target instanceof Short){
			TestCase.assertEquals(number,this.target);
		} else {
			TestCase.fail("Not correct type Short expected");
		}
		return this;
	}

	/**
	 * @param expected
	 * @return
	 */
	public PropertyTrapper requireSize(Integer expected){
		if (this.target.getClass().isAssignableFrom(Collection.class)){
			TestCase.assertEquals(expected.intValue(),
					Collection.class.cast(this.target).size());
		} else {
			TestCase.fail("Target is not a collection");
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#setTarget(java.lang.Object)
	 */
	public void setTarget(Object target) {
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ViewTrapperAware#view()
	 */
	public ViewTrapper view() {
		return this.applicationTrapper().view();
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ViewTrapperAware#view(java.lang.String)
	 */
	public ViewTrapper view(String viewId) {
		return this.applicationTrapper().view(viewId);
	}

}
