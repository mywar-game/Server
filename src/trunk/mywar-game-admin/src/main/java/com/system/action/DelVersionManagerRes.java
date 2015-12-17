package com.system.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.service.VersionManagerResService;

public class DelVersionManagerRes extends ALDAdminActionSupport {

	private static final long serialVersionUID = -2703759567659394977L;

	private Integer gameWebId;
	private Integer id;

	public void executeDel() {
		VersionManagerResService service = ServiceCacheFactory
				.getServiceCache().getService(VersionManagerResService.class);
		service.delVersionManagerRes(id, gameWebId);
		
		// 记录日志
				AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
				adminChangeConstantLog.setSysNum(0);
				adminChangeConstantLog
						.setAdminName(super.getAdminUser().getAdminName());
				adminChangeConstantLog.setChangeTime(new Timestamp(System
						.currentTimeMillis()));
				adminChangeConstantLog.setConstantName("DelVersionManagerRes");
				adminChangeConstantLog.setChangeType(1);
				adminChangeConstantLog.setChangeDetail("删除资源版本信息");
				AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
						.getServiceCache().getService(
								AdminChangeConstantLogService.class);
				adminChangeConstantLogService.saveOne(adminChangeConstantLog);
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
