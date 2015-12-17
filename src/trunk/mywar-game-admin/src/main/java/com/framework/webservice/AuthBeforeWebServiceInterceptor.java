package com.framework.webservice; 

import org.aopalliance.intercept.MethodInterceptor; 
import org.aopalliance.intercept.MethodInvocation; 

import com.framework.common.MD5; 
import com.framework.config.LocalTools; 
import com.framework.log.LogSystem; 

/**
 * 在webService执行之前 进行鉴权
 * 
 * @author mengchao
 * 
 */
public class AuthBeforeWebServiceInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		// TODO Auto-generated method stub
		LogSystem.info("before webservice excute:"); 
		String webServiceKey = LocalTools.getLocalKeyConfig().getWebServiceKey(); 
		Object[] args = methodInvocation.getArguments(); 
		String verifyString = ""; 
		String paramString = ""; 
		String paramverifyString = ""; 
        if (args != null && args.length > 0) {
        	verifyString = (String) args[0]; 
        	if (args.length > 1) {
        		for (int i = 1; i < args.length; i++) {
        			if (isBaseType(args[i])) {
            			paramString = paramString + args[i].toString(); 
        			}
        		}
        	}
    		paramString = paramString + webServiceKey; 
    		paramverifyString = MD5.md5Of32(paramString); 
    		
    		if (paramverifyString.equals(verifyString)) {
    			return methodInvocation.proceed(); 
    		} else {
            	LogSystem.info("verifyString Wrong!"); 
    		}
        } else {
        	LogSystem.info("Every WebService Menthod must have a verifyString param at the first position!"); 
        	return null; 
        }
		return null; 
	}

	
	private boolean isBaseType(Object o) {
		if (o instanceof String  || o instanceof Integer  || o instanceof Byte
				 || o instanceof Long  || o instanceof Short
				 || o instanceof Boolean  || o instanceof Double || o instanceof Character || o instanceof Float) {
			return true; 
		}
		return false; 
	}

}
