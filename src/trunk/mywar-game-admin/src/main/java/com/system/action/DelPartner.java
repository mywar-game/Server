package com.system.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 删除合作方
 * 
 * @author yezp
 */
public class DelPartner extends ALDAdminActionSupport {

	private static final long serialVersionUID = -5247319427200432913L;

	private Integer pid;

	public void executeDel() {
		PartnerService partnerService = ServiceCacheFactory
				.getServiceCache().getService(PartnerService.class);
		partnerService.delPartner(pid);
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("DelPartner");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("删除合作方");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
				.getServiceCache().getService(
						AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
