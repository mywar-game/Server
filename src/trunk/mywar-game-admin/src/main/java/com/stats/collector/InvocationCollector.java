/**
 * 
 */
package com.stats.collector;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.framework.log.LogSystem;

/**
 * @author huanglong
 * 
 *         2011-9-29
 */
public class InvocationCollector {
	private Iterator<Collector> iterator;
	private int count;

	public InvocationCollector(List list) {
		List<Collector> collectorList = new LinkedList<Collector>();
		for (int i = 0; i < list.size(); i++) {
			try {
//				System.out.println(list.get(i));
				Class clazz = Class.forName((String) list.get(i));
				collectorList.add((Collector) clazz.newInstance());
			} catch (ClassNotFoundException e) {
				LogSystem.error(e, "");
			} catch (InstantiationException e) {
				LogSystem.error(e, "");
			} catch (IllegalAccessException e) {
				LogSystem.error(e, "");
			}
			iterator = collectorList.iterator();
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 职责数据加载传递链
	 */
	public void invoke(Date date) {
		while(iterator.hasNext()){
			try {
				Collector collector = iterator.next();
				collector.execute(date);
			} catch (Exception e) {
				LogSystem.error(e, "采集数据异常.");
			}
			count++;
		}
	}
}
