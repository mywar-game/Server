package com.framework.webservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * 根据类型自动代理Creator
 * 
 */
@SuppressWarnings("serial")
public class BeanClassTypeAutoProxyCreator extends AbstractAutoProxyCreator
		implements ApplicationContextAware, InitializingBean {


	private ApplicationContext applicationContext;

	private MessageSourceAccessor messageSourceAccessor;

	/** 被代理的bean别名列表* */
	@SuppressWarnings("unchecked")
	private List beanNames;

	/** 被代理的classType列表* */
	@SuppressWarnings("unchecked")
	private List classTypes;

	// ---------------------------------------------------------
	// 实现AbstractAutoProxyCreator的抽像方法
	// ---------------------------------------------------------
	@SuppressWarnings("unchecked")
	protected Object[] getAdvicesAndAdvisorsForBean(Class beanClass,
			String beanName, TargetSource targetSource) throws BeansException {
		if (this.beanNames != null) {
			if (this.beanNames.contains(beanName)) {
				return PROXY_WITHOUT_ADDITIONAL_INTERCEPTORS;
			}
		}
		return DO_NOT_PROXY;
	}

	// -------------------------------------------------------
	// 实现ApplicationContextAware接口方法
	// -------------------------------------------------------
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		if (context == null && !isContextRequired()) {
			// Reset internal context state.
			this.applicationContext = null;
			this.messageSourceAccessor = null;
		} else if (this.applicationContext == null) {
			// Initialize with passed-in context.
			if (!requiredContextClass().isInstance(context)) {
				throw new ApplicationContextException(
						"Invalid application context: needs to be of type ["
								+ requiredContextClass().getName() + "]");
			}
			this.applicationContext = context;
			this.messageSourceAccessor = new MessageSourceAccessor(context);
			initApplicationContext();
		} else {
			// Ignore reinitialization if same context passed in.
			if (this.applicationContext != context) {
				throw new ApplicationContextException(
						"Cannot reinitialize with different application context: current one is ["
								+ this.applicationContext
								+ "], passed-in one is [" + context + "]");
			}
		}
	}

	/**
	 * Determine whether this application object needs to run in an
	 * ApplicationContext.
	 * <p>
	 * Default is "false". Can be overridden to enforce running in a context
	 * (i.e. to throw IllegalStateException on accessors if outside a context).
	 * 
	 * @see #getApplicationContext
	 * @see #getMessageSourceAccessor
	 */
	protected boolean isContextRequired() {
		return true;
	}

	/**
	 * Determine the context class that any context passed to
	 * <code>setApplicationContext</code> must be an instance of. Can be
	 * overridden in subclasses.
	 * 
	 * @see #setApplicationContext
	 */
	protected Class requiredContextClass() {
		return ApplicationContext.class;
	}

	/**
	 * Return the ApplicationContext instance used by this object.
	 */
	public final ApplicationContext getApplicationContext()
			throws IllegalStateException {
		if (this.applicationContext == null && isContextRequired()) {
			throw new IllegalStateException(
					"ApplicationObjectSupport instance [" + this
							+ "] does not run in an ApplicationContext");
		}
		return applicationContext;
	}

	/**
	 * Return a MessageSourceAccessor for the application context used by this
	 * object, for easy message access.
	 * 
	 * @throws IllegalStateException
	 *             if not running in an ApplicationContext
	 */
	protected final MessageSourceAccessor getMessageSourceAccessor()
			throws IllegalStateException {
		if (this.messageSourceAccessor == null && isContextRequired()) {
			throw new IllegalStateException(
					"ApplicationObjectSupport instance [" + this
							+ "] does not run in an ApplicationContext");
		}
		return this.messageSourceAccessor;
	}

	public void setClassTypes(String[] classTypes) {
		this.classTypes = Arrays.asList(classTypes);

	}

	/**
	 * Subclasses can override this for custom initialization behavior. Gets
	 * called by <code>setApplicationContext</code> after setting the context
	 * instance.
	 * <p>
	 * Note: Does <i>not</i> get called on reinitialization of the context but
	 * rather just on first initialization of this object's context reference.
	 * 
	 * @throws ApplicationContextException
	 *             in case of initialization errors
	 * @throws BeansException
	 *             if thrown by ApplicationContext methods
	 * @see #setApplicationContext
	 */
	protected void initApplicationContext() throws BeansException {

	}

	// -----------------------------------
	// 实现InitializingBean接口方法
	// -----------------------------------

	/**
	 * 查找指定classType的beanName列表
	 */
	private List getBeanNames(String classType) {
		List beanNameList = null;
		try {
			String[] beanName = this.getApplicationContext()
					.getBeanNamesForType(Class.forName(classType), true, false);
			if (beanName != null) {
				beanNameList = Arrays.asList(beanName);
			}
		} catch (ClassNotFoundException ex) {
			throw new IllegalArgumentException("Class not found: "
					+ ex.getMessage());
		}

		return beanNameList;
	}

	/**
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {

		if (classTypes != null && !classTypes.isEmpty()) {
			beanNames = new ArrayList();
			for (int i = 0; i < classTypes.size(); i++) {
				String classType = (String) classTypes.get(i);
				List aList = getBeanNames(classType);
				beanNames.addAll(aList);
			}
		}
		if (logger.isDebugEnabled()) {
			for (int i = 0; i < beanNames.size(); i++) {

				logger.debug("printBean:" + (String) beanNames.get(i));

			}
		}

	}
}