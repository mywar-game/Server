package com.framework.server.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 线程池
 * @author mengc
 *
 */
public class ThreadPoolBean {
	private ThreadPoolExecutor threadPoolExecutor;

	public ThreadPoolBean(int coreSize, int maxSize, int maxQueneLength, 
			long aliveTime) {

		RejectThreadHandler handler = new RejectThreadHandler();
		threadPoolExecutor = new ThreadPoolExecutor(coreSize, maxSize, 
				aliveTime, TimeUnit.MILLISECONDS, 
				new ArrayBlockingQueue<Runnable>(maxQueneLength), handler);

	}

	public ThreadPoolExecutor getThreadPoolExecutor() {
		return threadPoolExecutor;
	}
}
