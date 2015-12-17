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
 * 添加黑名单IMEI
 * 
 * @author yezp
 */
public class AddBlackInfo extends ALDAdminActionSupport implements
		ModelDriven<BlackInfo> {

	private static final long serialVersionUID = -6546959733327773701L;

	private BlackInfo blackInfo = new BlackInfo();
	private String gameWebId;

	private String isCommit = "F";

	public String execute() {
		if (isCommit.equals("F")) {
			return INPUT;
		}

		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}

		BlackInfoService infoService = ServiceCacheFactory.getServiceCache()
				.getService(BlackInfoService.class);
		infoService.addInfo(blackInfo, dbSourceId);
		
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("AddBlackInfo");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("添加黑名单");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
				.getServiceCache().getService(
						AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		return SUCCESS;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

	public BlackInfo getBlackInfo() {
		return blackInfo;
	}

	public void setBlackInfo(BlackInfo blackInfo) {
		this.blackInfo = blackInfo;
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
