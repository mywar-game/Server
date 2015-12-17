/**
 * 
 */
package com.stats.scheduler;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

/**
 * @author huanglong
 *
 * 2011-10-26
 */
public class MonthCollectorEntryTest  extends SessionTest {
	public void testExecute() throws JobExecutionException {
		MonthCollectorEntry mce = ServiceCacheFactory.getServiceCache()
		.getService(MonthCollectorEntry.class);
		mce.executeJob(null);
	}
}
