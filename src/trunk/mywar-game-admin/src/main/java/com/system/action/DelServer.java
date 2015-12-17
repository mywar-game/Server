package com.system.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.service.ServerService;

/**
 * 删除服务
 * 
 * @author yezp
 */
public class DelServer extends ALDAdminActionSupport {

	private static final long serialVersionUID = -6700118351077629084L;

	private String serverId;
	private String partnerId;
	private String gameWebId;
	private String aliaServerId;

	public void executeDel() {
		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}

		ServerService service = ServiceCacheFactory.getServiceCache()
				.getService(ServerService.class);
		service.delServer(serverId, partnerId, aliaServerId, dbSourceId);
		// 记录日志
				AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
				adminChangeConstantLog.setSysNum(0);
				adminChangeConstantLog
						.setAdminName(super.getAdminUser().getAdminName());
				adminChangeConstantLog.setChangeTime(new Timestamp(System
						.currentTimeMillis()));
				adminChangeConstantLog.setConstantName("DelServer");
				adminChangeConstantLog.setChangeType(1);
				adminChangeConstantLog.setChangeDetail("删除 ");
				AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
						.getServiceCache().getService(
								AdminChangeConstantLogService.class);
				adminChangeConstantLogService.saveOne(adminChangeConstantLog);
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

	public String getAliaServerId() {
		return aliaServerId;
	}

	public void setAliaServerId(String aliaServerId) {
		this.aliaServerId = aliaServerId;
	}

}
