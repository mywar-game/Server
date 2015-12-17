package com.system.action;

import java.sql.Timestamp;
import java.util.List;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.service.GameWebServerService;

/**
 * 清服务器 以_stats结尾的数据表
 * 
 * @author Administrator
 * 
 */
public class ClearTGameServer extends ALDAdminActionSupport {

	private static final long serialVersionUID = 4337184606964019051L;

	private String serverId;

	private String gameWebId;

	public void executeCls() {
		GameWebServerService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebServerService.class);
		// Integer dbSourceId = 0;
		// if (gameWebId != null && !gameWebId.equals("")) {
		// dbSourceId = Integer.parseInt(gameWebId);
		// }
		List<Object> list = service.getEndStatsTables(serverId);
		if (list != null) {
			for (Object obj : list) {
				Object[] arr = (Object[]) obj;
				String tableName = (String) arr[1];
				clearTable(tableName);
			}
		}
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("ClearTGameServer");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("清空 ");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
				.getServiceCache().getService(
						AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
	}

	private void clearTable(String tableName) {
		GameWebServerService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebServerService.class);
		System.out.println("tableName = " + tableName + " serverId = "
				+ serverId);
		service.clearEndWithStatsTable(tableName, serverId);
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}
}
