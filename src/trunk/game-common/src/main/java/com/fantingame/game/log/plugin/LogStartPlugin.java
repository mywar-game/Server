package com.fantingame.game.log.plugin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.plugin.IAppPlugin;
import com.fantingame.game.log.service.LogService;
import com.fantingame.game.server.threadpool.ThreadPoolBean;

public class LogStartPlugin implements IAppPlugin {
	private ThreadPoolBean logWriteThreadPoolBean;
	@Autowired
	private LogService logService;
	@Override
	public void shutdown() throws Exception {
	}
	@Override
	public void startup() throws Exception {
		//异步插入日志线程
		new Thread(new WorkThread()).start();
	}
	
	class WorkThread implements Runnable{
		@Override
		public void run() {
			   while(true){
				   try {
					  final List<String> list =  logService.getAll();
//					  LogSystem.debug("取出数据 进行处理~~~~~~~~~~~~~~数量="+list.size());
					  if(list!=null&&list.size()>0){
						  final String[] strArray = new String[list.size()];
						  for(int i=0;i<list.size();i++){
							  strArray[i] = list.get(i);
						  }
						  Runnable runnable = new Runnable() {
							@Override
							public void run() {
								logService.executeInsertLog(list);
							}
						};
						logWriteThreadPoolBean.getThreadPoolExecutor().execute(runnable);
					  }
					  Thread.sleep(3000);
				} catch (Exception e) {
					LogSystem.error(e,"");
				}
			   }
		}
		
	}
	public ThreadPoolBean getLogWriteThreadPoolBean() {
		return logWriteThreadPoolBean;
	}

	public void setLogWriteThreadPoolBean(ThreadPoolBean logWriteThreadPoolBean) {
		this.logWriteThreadPoolBean = logWriteThreadPoolBean;
	}
	@Override
	public int cpOrder() {
		return 0;
	}

}
