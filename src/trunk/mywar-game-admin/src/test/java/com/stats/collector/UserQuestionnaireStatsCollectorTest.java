package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserQuestionnaireStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserQuestionnaireStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserQuestionnaireStatsCollector.class);
		try {
			collector.execute(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}


}