package com.framework.manager;

import java.util.Random;

/**
 * 序列好生成器械
 * 
 * @author mengc
 */
public class SequenseManager {

	// 单例对象
	private static SequenseManager sequenseManager;

	private long startSeq;
	
    private Random random;
	private Object locker = new Object();
	

	// 单例实现 私有构造方法
	private SequenseManager() {
		startSeq = System.nanoTime();
		random = new Random();
	}

	// 单例实例取得方法
	public static SequenseManager getInstance() {
		if (sequenseManager == null) {
			sequenseManager = new SequenseManager();
		}
		return sequenseManager;
	}

	public long generateStaticseq() {
		synchronized (locker) {
			startSeq=startSeq+random.nextInt(10000)+1;
		}
		return startSeq;
	}
	
	
	public static void main(String[] args) {
		System.out.println(SequenseManager.getInstance().generateStaticseq());
		System.out.println(SequenseManager.getInstance().generateStaticseq());
	}
}
