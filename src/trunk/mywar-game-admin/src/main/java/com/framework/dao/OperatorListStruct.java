package com.framework.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.framework.log.LogSystem;

/**
 * 缓存数据库操作
 * 
 * @author mengc
 * 
 */
public class OperatorListStruct {

	private final LinkedList<DaoOperatorBean> opratorList = new LinkedList<DaoOperatorBean>();

	/**
	 * 消息压栈
	 * 
	 * @param msg
	 */
	public void pushMsg(DaoOperatorBean daoOperatorBean) {
		if (daoOperatorBean == null) {
			return;
		}
		synchronized (opratorList) {
			opratorList.add(daoOperatorBean);
		}

	}

	/**
	 * 弹出一个消息
	 * @return
	 */
	public DaoOperatorBean popMsg() {
		DaoOperatorBean daoOperatorBean = null;
		synchronized (opratorList) {
		if (!opratorList.isEmpty()) {
			daoOperatorBean = opratorList.remove();
			}
		}
		return daoOperatorBean;
	}


	/*
	 * 取出opratorList中的消息列表
	 * 
	 * @return
	 */
	public List<DaoOperatorBean> popMsgList(int maxNum) {


		List<DaoOperatorBean> reList = new ArrayList<DaoOperatorBean>();

		int size = opratorList.size();

		if (size == 0) {
			return null;
		}

		if (size > maxNum) {
			size = maxNum;
		}
		DaoOperatorBean daoOperatorBean = null;
		for (int i = 0; i < size; i++) {
			daoOperatorBean = popMsg();
			if (daoOperatorBean != null) {
				reList.add(daoOperatorBean);
			}
		}
		return reList;
	}
	/*
	 * 取出所有msgList中的消息列表
	 * 
	 * @return
	 */
	public List<DaoOperatorBean> popAllMsgList() {
		List<DaoOperatorBean> reList = new ArrayList<DaoOperatorBean>();
		int size = opratorList.size();
		if (size == 0) {
			return null;
		}
		DaoOperatorBean daoOperatorBean = null;
		for (int i = 0; i < size; i++) {
			daoOperatorBean = popMsg();
			if (daoOperatorBean != null) {
				reList.add(daoOperatorBean);
			}
		}
		return reList;
	}
	 class TreadRunPush implements Runnable {
        private  OperatorListStruct messager;
        TreadRunPush(OperatorListStruct messager) {
        	this.messager = messager;
        }
		public void run() {
			// TODO Auto-generated method stub
			long now = System.currentTimeMillis();
			int i = 0;
			while (i < 100000) {
			messager.pushMsg(new DaoOperatorBean());
			i++;
//			LogSystem.info("push一个");

			}
			long end = System.currentTimeMillis();
			LogSystem.info("push时间:" + (end - now));

		}
		 
	 }
	 
	 class TreadRunPop implements Runnable {
	        private  OperatorListStruct messager;
	        TreadRunPop(OperatorListStruct messager) {
	        	this.messager = messager;
	        }
			public void run() {
				// TODO Auto-generated method stub
				long now = System.currentTimeMillis();
				int i = 0;
				while (i < 100000) {
				messager.popMsg();
				i++;
//				LogSystem.info("pop一个");
				}
				long end = System.currentTimeMillis();
				LogSystem.info("pop时间:" + (end - now));
			}
			 
		 }
	public static void main(String[] args) {
		 OperatorListStruct messager = new OperatorListStruct();
		 new Thread(messager.new TreadRunPush(messager)).start();
		 
		 new Thread(messager.new TreadRunPop(messager)).start();

		 new Thread(messager.new TreadRunPush(messager)).start();
		 new Thread(messager.new TreadRunPop(messager)).start();

		 new Thread(messager.new TreadRunPush(messager)).start();
		 new Thread(messager.new TreadRunPop(messager)).start();

		 new Thread(messager.new TreadRunPush(messager)).start();
		 new Thread(messager.new TreadRunPop(messager)).start();

		 new Thread(messager.new TreadRunPop(messager)).start();
		 new Thread(messager.new TreadRunPop(messager)).start();
	}

}
