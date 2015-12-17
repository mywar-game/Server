package com.system.action;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.SystemCode;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class ReflashServerCache extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = 1L;
	
	public String execute(){
		super.setErroCodeNum(SystemCode.SYS_FAIL); 
		DataSourceManager.getInstatnce().initDataSource();
		Map<Integer, TGameServer> gameServerMap = DataSourceManager.getInstatnce().getGameServerMap();
		
		StringBuilder serverName = new StringBuilder();
		LogSystem.info("打印ReflashServerCache gameServerMap信息开始");
		Iterator<Integer> iter = gameServerMap.keySet().iterator();
		while (iter.hasNext()) {
			Integer key = iter.next();
			TGameServer value = gameServerMap.get(key);
			LogSystem.info("key = " + key + " value = " + value);
			serverName.append(value.getServerName() + ", ");
		}
		LogSystem.info("打印ReflashServerCache gameServerMap信息结束");
		
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("ReflashServerCache");
		adminChangeConstantLog.setChangeType(3);
		adminChangeConstantLog.setChangeDetail("刷新服务器信息");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
			.getServiceCache().getService(
				AdminChangeConstantLogService.class);
				adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		
		super.setErroCodeNum(SystemCode.SYS_SUCESS); 
		return SUCCESS; 
	}
}
