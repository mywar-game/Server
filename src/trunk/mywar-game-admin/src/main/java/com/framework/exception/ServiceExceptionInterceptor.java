package com.framework.exception;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.framework.log.LogSystem;

/**
 * service异常拦截器
 * 
 * @author mengchao
 * 
 */
public class ServiceExceptionInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {

		Object result = null;

		// 注意有些特殊的异常要重新抛出去，例如StateObjectException(数据同步vesion)
		try {
			result = methodInvocation.proceed();
		} catch (Exception e) {
			LogSystem.error(e, "");
			throw e;
		}

		return result;
	}
}
