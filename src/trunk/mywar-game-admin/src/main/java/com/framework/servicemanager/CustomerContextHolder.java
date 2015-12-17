package com.framework.servicemanager;

import com.framework.common.DBSource;
import com.framework.log.LogSystem;

public class CustomerContextHolder {
	private static final ThreadLocal<Integer> contextHolder = new ThreadLocal<Integer>();
	private static final ThreadLocal<Integer> systemNumHolder = new ThreadLocal<Integer>();

	public static void setCustomerType(Integer uid) {
		contextHolder.set(calcCustomerType(uid));
	}

	public static Integer getCustomerType() {
		Integer customerType = contextHolder.get() == null ? null : Integer
				.valueOf(contextHolder.get().toString());
		return customerType;
	}

	public static void setSystemNum(int systemNum) {
		systemNumHolder.set(systemNum);
	}

	public static Integer getSystemNum() {
		return systemNumHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}

	public static int calcCustomerType(Integer uid) {
		int customerType = 0;
		if (uid == null) {
			return 0;
		}
		if (uid.intValue() < 0) {
			if (uid != DBSource.ADMIN) {
				if (systemNumHolder.get() != 0) {
					String tempId = uid + "" + systemNumHolder.get();
					int reId = Integer.valueOf(tempId);
					LogSystem.info("uid: " + reId);
					return reId;
				} else {
					throw new RuntimeException("systemNum没有设置，请先设置systemNum");
				}
			} else {
				return uid;
			}
		} else if (0 < uid && uid < 10000) {
			return uid;
		}
		
		customerType = uid / 1000000 + 1;
		LogSystem.info("uid: " + uid + "customerType: " + customerType);
		return customerType;
	}
}
