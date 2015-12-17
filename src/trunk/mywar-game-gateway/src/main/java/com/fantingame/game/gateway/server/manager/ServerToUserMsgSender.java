package com.fantingame.game.gateway.server.manager;


import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.fandingame.game.framework.plugin.IAppPlugin;


public class ServerToUserMsgSender implements IAppPlugin {
	private long sleepTime = 10;
	private int coreSize = 8 ;
	private ScheduledThreadPoolExecutor scheduledExec;
	public long getSleepTime() {
		return sleepTime;
	}
	
	public int getCoreSize() {
		return coreSize;
	}

	public void setCoreSize(int coreSize) {
		this.coreSize = coreSize;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}
	@Override
	public void shutdown() throws Exception{
		scheduledExec.shutdown();
	}
	@Override
	public void startup() throws Exception {
//	        scheduledExec = new ScheduledThreadPoolExecutor(coreSize,
//	                new ThreadFactory(){
//	                    private final PaddedAtomicInteger idCounter = new PaddedAtomicInteger();
//	                    @Override
//	                    public Thread newThread(Runnable r){
//	                        Thread result = new Thread(r, "Send-User-Msg-thread"
//	                                + idCounter.incrementAndGet());
//	                        result.setPriority(Thread.MAX_PRIORITY);
//	                        return result;
//	                    }
//	                });
//	        scheduledExec.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
//	        scheduledExec.scheduleAtFixedRate(new Runnable() {
//				@Override
//				public void run() {
//					SessionManager.getInstance().handleEvent();
//				}
//			}, 500, sleepTime, TimeUnit.MILLISECONDS);
//		LogSystem.info("服务器向用户发送下行消息器启动！消息发送间隔时间" + sleepTime);
	}

	@Override
	public int cpOrder() {
		return 0;
	}
}