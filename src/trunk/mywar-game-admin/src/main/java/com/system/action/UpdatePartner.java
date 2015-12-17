package com.system.action;

import java.sql.Timestamp;
import java.util.Date;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.bo.Partner;
import com.adminTool.service.AdminChangeConstantLogService;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 修改合作方信息
 * 
 * @author yezp
 */
public class UpdatePartner extends ALDAdminActionSupport implements
		ModelDriven<Partner> {

	private static final long serialVersionUID = 7671380124077941937L;

	private Partner partner = new Partner();

	private String isCommit = "F";

	public String execute() {
		PartnerService partnerService = ServiceCacheFactory
				.getServiceCache().getService(PartnerService.class);
		if ("F".equals(isCommit)) {
			partner = partnerService.getPartnerByPid(partner.getPid());
			return INPUT;
		}

		// TODO 修改时间
		Date date = new Date();
		Timestamp updateTime = new Timestamp(date.getTime());
		partner.setEditTime(updateTime);
		
		partnerService.updatePartner(partner);
		
		// 记录日志
				AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
				adminChangeConstantLog.setSysNum(0);
				adminChangeConstantLog
						.setAdminName(super.getAdminUser().getAdminName());
				adminChangeConstantLog.setChangeTime(new Timestamp(System
						.currentTimeMillis()));
				adminChangeConstantLog.setConstantName("UpdatePartner");
				adminChangeConstantLog.setChangeType(1);
				adminChangeConstantLog.setChangeDetail("修改合作方");
				AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
						.getServiceCache().getService(
								AdminChangeConstantLogService.class);
				adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		return SUCCESS;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public Partner getModel() {
		// TODO Auto-generated method stub
		return partner;
	}

}
