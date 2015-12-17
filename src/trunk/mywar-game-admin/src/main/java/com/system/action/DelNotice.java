package com.system.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.service.NoticeService;

/**
 * 删除通知
 * 
 * @author yezp
 */
public class DelNotice extends ALDAdminActionSupport {

	private static final long serialVersionUID = 6495173057175922167L;

	private String serverId;
	private String gameWebId;

	public void executeDel() {
		NoticeService service = ServiceCacheFactory.getServiceCache()
				.getService(NoticeService.class);
		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}
		
		service.delNotice(serverId, dbSourceId);
		
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("DelNotice");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("删除通知信息");
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

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

}
