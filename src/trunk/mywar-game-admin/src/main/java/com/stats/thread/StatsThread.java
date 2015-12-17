package com.stats.thread;

import java.util.Date;
import java.util.Map;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.collector.InvocationCollector;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class StatsThread extends Thread {
	public void run() {
		Map<Integer, TGameServer> map = DataSourceManager.getInstatnce().getGameServerMap();
		Date date = new Date();
		for(TGameServer server : map.values()){
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId:"+server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			InvocationCollector invocationCollector = (InvocationCollector) ServiceCacheFactory.getServiceCache().getBeanById("summaryData");
			invocationCollector.invoke(date);
		}
	}
}
