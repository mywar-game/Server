package com.framework.dao;

import java.util.ArrayList;
import java.util.List;

import com.framework.log.LogSystem;

public class WriteThread implements Runnable {

	private static OperatorListStruct list = new OperatorListStruct();

	private int maxNum;

	//线程睡眠时间
	private final static long SLEEP_TIME = 1000;

	//线程睡眠条件1  获得空数据次数 连续10次获得空数据 则睡眠
	private final static int EMPTY_DATA_TIMES = 10;
	//线程睡眠条件2  连续50次 取得数据小于最大处理数的一半
	private final static int NOT_HALF_FULL_DATA_TIMES = 50;
	//线程睡眠条件3  连续100次 取得的数据小于最大处理数
	private final static int NOT_FULL_DATA_TIMES = 100;

	//当前连续获取空数据次数
	private int current_empty_data_times;
	//当前连续获取小于最大处理数的一半次数
	private int current_not_half_full_data_times;
	//当前连续获取小于最大处理数的次数
	private int current_not_full_data_times;

	public WriteThread(int maxNum) {
		super();
		this.maxNum = maxNum;
	}

	public static void pushOperator(DaoOperatorBean daoOperatorBean) {
		list.pushMsg(daoOperatorBean);
	}

	public void run() {
		// TODO Auto-generated method stub
		List<DaoOperatorBean> listTemp = new ArrayList<DaoOperatorBean>();
		boolean isSleep = false;
		while (true) {
			if (list != null) {
				listTemp = list.popMsgList(maxNum);
				int currentSize = 0; 
				if (listTemp != null) {
					currentSize = listTemp.size();
				}
				//判断是否睡眠
				isSleep = isSleep(currentSize);
				
				if (listTemp != null && listTemp.size() > 0) {
					for (int i = 0; i < listTemp.size(); i++) {
						listTemp.get(i).execute();
					}
				}
				
				if (isSleep) {
					try {
//						LogSystem.info("异步写进入休眠！时间："+SLEEP_TIME+"毫秒");
						Thread.sleep(SLEEP_TIME);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						LogSystem.error(e, "");
					}
				}
			}
		}
	}

	public boolean isSleep(int currentSize) {
		
		if (currentSize == 0) {
			current_empty_data_times++;
		} else {
			current_empty_data_times = 0;
		}
		
		if (currentSize < maxNum / 2) {
			current_not_half_full_data_times++;
		} else {
			current_not_half_full_data_times = 0;
		}
		
		if (currentSize < maxNum) {
			current_not_full_data_times++;
		} else {
			current_not_full_data_times = 0;
		}
		
		if (current_empty_data_times >= EMPTY_DATA_TIMES) {
			return true;
		}
		
		if (current_not_half_full_data_times >= NOT_HALF_FULL_DATA_TIMES) {
			return true;
		}
		
		if (current_not_full_data_times >= NOT_FULL_DATA_TIMES) {
			return true;
		}
		
		return false;
	}

}
