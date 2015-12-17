package com.framework.exception;

public class ServiceException extends RuntimeException {
	/**
	 * service异常，主要在事务中抛出来
	 * 
	 * @author mengchao
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ServiceException() {
		
	}
	
	public ServiceException(String desc) {
		super(desc);
	}
}
