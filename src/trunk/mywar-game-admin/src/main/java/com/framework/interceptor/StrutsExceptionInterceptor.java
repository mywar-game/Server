package com.framework.interceptor;

import com.framework.log.LogSystem;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * struts异常拦截器
 * 
 * @author liangboju
 * 
 */
public class StrutsExceptionInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;
		try {
			result = invocation.invoke();
		} catch (Exception e) {
			LogSystem.error(e, null);
			return "glober_error";
		}

		return result;
	}

}
