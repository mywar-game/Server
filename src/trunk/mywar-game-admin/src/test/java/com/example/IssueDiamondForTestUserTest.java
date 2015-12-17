package com.example;

import org.quartz.JobExecutionException;

import com.adminTool.scheduler.IssueDiamondForTestUser;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class IssueDiamondForTestUserTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		IssueDiamondForTestUser collector = ServiceCacheFactory.getServiceCache().getService(IssueDiamondForTestUser.class);
		collector.execute(null);
		}
	}
