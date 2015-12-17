package com.framework.manager;

import java.io.IOException;
import java.util.List;

import com.framework.client.socket.NettyClient;
import com.framework.log.LogSystem;
import com.framework.server.msg.Msg;

/**
 * 向登录服务器消息发送器
 * @author mengc
 *
 */
public class ServerMessagerSender implements Runnable {

	private NettyClient nettyClient;
	//一次发送的最大消息量
	public int dealMaxMsgNum;

	public boolean isReconnect;

	public long reconnectWaitTime;

	public List<Msg> listTemp;
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
	
	public ServerMessagerSender(NettyClient nettyClient, int dealMaxMsgNum) {
		if (dealMaxMsgNum == 0) {
			throw new NullPointerException("最大消息处理数不能空");
		}
		this.nettyClient = nettyClient;
		this.dealMaxMsgNum = dealMaxMsgNum;
	}

	public void run() {
		
		try {
			//等待10秒
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean isSleep = false;

		while (true) {
			if (listTemp == null) {
				listTemp = ServerMessagerManager.getInstance().popSendMsgList(
						dealMaxMsgNum);
			}
			
			int currentSize = 0; 
			if (listTemp != null) {
				currentSize = listTemp.size();
			}
			
			if (listTemp != null && listTemp.size() > 0) {
				try {
						nettyClient.sendMsgs(listTemp);
						listTemp = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					LogSystem.error(e, "");
				}
			}
			//判断是否睡眠
			isSleep = isSleep(currentSize);
			if (isSleep) {
				try {
//					LogSystem.info("异步写进入休眠！时间："+SLEEP_TIME+"毫秒");
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					LogSystem.error(e, "");
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
		
		if (currentSize < dealMaxMsgNum / 2) {
			current_not_half_full_data_times++;
		} else {
			current_not_half_full_data_times = 0;
		}
		
		if (currentSize < dealMaxMsgNum) {
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
