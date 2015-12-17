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
public class WeekCollectorEntryTest  extends SessionTest {
	public void testExecute() throws JobExecutionException {
		WeekCollectorEntry puss = ServiceCacheFactory.getServiceCache()
		.getService(WeekCollectorEntry.class);
		puss.execute(null);
	}
}
