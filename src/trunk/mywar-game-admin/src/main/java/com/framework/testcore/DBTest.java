package com.framework.testcore;

import junit.framework.TestCase;

import com.framework.context.SpringLoad;
import com.framework.servicemanager.CustomerContextHolder;

public class DBTest extends TestCase {

	public DBTest() {
		super();
		SpringLoad.getApplicationLoad();
		CustomerContextHolder.setSystemNum(1001);
	}

}
