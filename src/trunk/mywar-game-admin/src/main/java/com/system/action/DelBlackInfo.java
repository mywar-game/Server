package com.system.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.service.BlackInfoService;

/**
 * 删除IMEI黑名单
 * 
 * @author yezp
 */
public class DelBlackInfo extends ALDAdminActionSupport {

	private static final long serialVersionUID = -130429431572726881L;

	private Integer id;
	private String gameWebId;

	public void executeDel() {
		BlackInfoService service = ServiceCacheFactory.getServiceCache()
				.getService(BlackInfoService.class);
		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}

		service.delInfo(id, dbSourceId);
		
		// 记录日志
				AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
				adminChangeConstantLog.setSysNum(0);
				adminChangeConstantLog
						.setAdminName(super.getAdminUser().getAdminName());
				adminChangeConstantLog.setChangeTime(new Timestamp(System
						.currentTimeMillis()));
				adminChangeConstantLog.setConstantName("DelBlackInfo");
				adminChangeConstantLog.setChangeType(1);
				adminChangeConstantLog.setChangeDetail("删除黑名单");
				AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
						.getServiceCache().getService(
								AdminChangeConstantLogService.class);
				adminChangeConstantLogService.saveOne(adminChangeConstantLog);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

}
