package com.system.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.BlackInfo;
import com.system.service.BlackInfoService;

/**
 * 修改IMEI黑名单
 * 
 * @author yezp
 */
public class UpdateBlackInfo extends ALDAdminActionSupport implements
		ModelDriven<BlackInfo> {

	private static final long serialVersionUID = 1171893327346333375L;

	private BlackInfo blackInfo = new BlackInfo();

	private String isCommit = "F";
	private String gameWebId;

	public String execute() {
		BlackInfoService infoService = ServiceCacheFactory.getServiceCache()
				.getService(BlackInfoService.class);
		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}

		if ("F".equals(isCommit)) {
			blackInfo = infoService.getInfoById(blackInfo.getId(), dbSourceId);
			return INPUT;
		}

		infoService.updateInfo(blackInfo, dbSourceId);
		
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("UpdateBlackInfo");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("修改黑名单");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
				.getServiceCache().getService(
						AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		return SUCCESS;
	}

	public BlackInfo getBlackInfo() {
		return blackInfo;
	}

	public void setBlackInfo(BlackInfo blackInfo) {
		this.blackInfo = blackInfo;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public BlackInfo getModel() {
		// TODO Auto-generated method stub
		return blackInfo;
	}

}
