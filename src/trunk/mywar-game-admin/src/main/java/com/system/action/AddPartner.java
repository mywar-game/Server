package com.system.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.bo.Partner;
import com.adminTool.service.AdminChangeConstantLogService;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加合作方信息
 * 
 * @author yezp
 */
public class AddPartner extends ALDAdminActionSupport implements
		ModelDriven<Partner> {

	private static final long serialVersionUID = -5560196355165720970L;

	private Partner partner = new Partner();

	private String isCommit = "F";

	public String execute() {
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);

		if (isCommit.equals("F")) {
			return INPUT;
		}

		List<Partner> partnerList = partnerService.findPartnerList();
		for (Partner p : partnerList) {
			if (partner.getPid().equals(p.getPid())) {
				setErroDescrip("存在相同合作ID：" + partner.getPid());
				return INPUT;
			}
		}

		Date date = new Date();
		Timestamp createTime = new Timestamp(date.getTime());
		partner.setCreateTime(createTime);
		partner.setRemoveStatus(0);

		partnerService.addPartner(partner);

		// 记录日志
				AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
				adminChangeConstantLog.setSysNum(0);
				adminChangeConstantLog
						.setAdminName(super.getAdminUser().getAdminName());
				adminChangeConstantLog.setChangeTime(new Timestamp(System
						.currentTimeMillis()));
				adminChangeConstantLog.setConstantName("AddPartner");
				adminChangeConstantLog.setChangeType(1);
				adminChangeConstantLog.setChangeDetail("添加合作方");
				AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
						.getServiceCache().getService(
								AdminChangeConstantLogService.class);
				adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		return SUCCESS;
	}

	@Override
	public Partner getModel() {
		// TODO Auto-generated method stub
		return partner;
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

}
