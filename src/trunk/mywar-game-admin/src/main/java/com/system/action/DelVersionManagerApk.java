package com.system.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.service.VersionManagerApkService;

/**
 * 删除apk版本信息
 * 
 * @author yezp
 */
public class DelVersionManagerApk extends ALDAdminActionSupport {

	private static final long serialVersionUID = 3519293762965706029L;

	private Integer id;
	private Integer gameWebId;

	public void executeDel() {
		VersionManagerApkService apkService = ServiceCacheFactory
				.getServiceCache().getService(VersionManagerApkService.class);
		apkService.delVersionManagerApk(id, gameWebId);
		
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("DelVersionManagerApk");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("删除APK版本信息");
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

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

}
