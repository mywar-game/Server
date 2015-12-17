/**
 * 
 */
package com.stats.collector;

import java.util.Date;

/**
 * @author huanglong
 *
 * 2011-9-29
 */
public interface Collector {
	/**
	 * 数据收集器(把采集时间传递进去)
	 */
	void execute(Date date) throws Exception;
}
