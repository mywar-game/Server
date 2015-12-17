package com.fantingame.game.mywar.logic.client.service;

import java.util.Random;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.utils.Utils;
import com.fantingame.game.server.concurrent.DisruptorExecutor;

public class ThreadService {

	final DisruptorExecutor[] userThreadPool;
	
	final DisruptorExecutor[] otherThreadPool;
	
	final DisruptorExecutor[] actionGlobThreadPool;
	
	final int modUserThread;
	final int modActionGlobThread;
	final Random  random;
	private int otherThreadCount;
	
	public ThreadService(int userThreadCount,int otherThreadCount,int actionGlobThreadCount){
		    this.otherThreadCount = otherThreadCount;
		    modUserThread = userThreadCount - 1;
		    modActionGlobThread = actionGlobThreadCount -1;
	        userThreadPool = new DisruptorExecutor[userThreadCount];
	        for (int i = 0; i < userThreadPool.length; i++){
	        	userThreadPool[i] = new DisruptorExecutor(i, 8192, "LOGIC_USER_WORKER_" + i);
	        }
	        otherThreadPool = new DisruptorExecutor[otherThreadCount];
	        for (int i = 0; i < otherThreadPool.length; i++){
	        	otherThreadPool[i] = new DisruptorExecutor(i, 8192, "LOGIC_OTHER_WORKER_" + i);
	        }
	        random= new Random();
	        actionGlobThreadPool = new DisruptorExecutor[actionGlobThreadCount];
	        for(int i=0;i<actionGlobThreadCount;i++){
	        	actionGlobThreadPool[i] = new DisruptorExecutor(i, 8192, "LOGIC_ACTION_GLOB_WORKER_" + i);
	        }
	        LogSystem.info("逻辑服启动的用户线程数=["+userThreadCount+"],其他线程数=["+otherThreadCount+"],全局接口线程数["+actionGlobThreadCount+"]");
	}
	
	public DisruptorExecutor getUserThreadPool(String userId){
		long userIdHashCode = Utils.getUserHashCode(userId);
		int threadIndex = (int)(userIdHashCode%modUserThread);
		LogSystem.debug("用户执行线程id为"+threadIndex+",用户id="+userId);
		return userThreadPool[threadIndex];
	}
	public DisruptorExecutor getOtherThreadPool(){
		int threadIndex = random.nextInt(otherThreadCount);
		LogSystem.debug("其他执行线程id为"+threadIndex);
		return otherThreadPool[threadIndex];
	}
	public DisruptorExecutor actionGlobThreadPool(String cmdCode,long key){
		int threadIndex = (int)(key%modActionGlobThread);
		LogSystem.debug(cmdCode+"接口全局线程执行线程id为"+threadIndex);
		return actionGlobThreadPool[threadIndex];
	}
}
