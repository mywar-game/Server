package com.system.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.service.AmendNoticeService;

/**
 * 删除渠道通知
 * 
 * @author lin
 */
public class DelAmendNotice extends ALDAdminActionSupport {

	private static final long serialVersionUID = 6495173057175922167L;

	private String serverId;
	private String partnerId;
	private String gameWebId;

	public void executeDel() {
		AmendNoticeService amendNoticeservice = ServiceCacheFactory.getServiceCache()
				.getService(AmendNoticeService.class);
		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}
		
		amendNoticeservice.delAmendNotice(serverId, partnerId, dbSourceId);
		
		// 记录日志
				AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
				adminChangeConstantLog.setSysNum(0);
				adminChangeConstantLog
						.setAdminName(super.getAdminUser().getAdminName());
				adminChangeConstantLog.setChangeTime(new Timestamp(System
						.currentTimeMillis()));
				adminChangeConstantLog.setConstantName("DelAmendNotice");
				adminChangeConstantLog.setChangeType(1);
				adminChangeConstantLog.setChangeDetail("删除渠道通知信息");
				AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
						.getServiceCache().getService(
								AdminChangeConstantLogService.class);
				adminChangeConstantLogService.saveOne(adminChangeConstantLog);
	}

	
	public String getPartnerId() {
		return partnerId;
	}


	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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
