package com.system.action;

import java.sql.Timestamp;
import java.util.List;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.bo.Partner;
import com.adminTool.service.AdminChangeConstantLogService;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.VersionManagerRes;
import com.system.service.VersionManagerResService;

public class UpdateVersionManagerRes extends ALDAdminActionSupport implements
		ModelDriven<VersionManagerRes> {

	private static final long serialVersionUID = -1435904493899674411L;

	private VersionManagerRes versionManagerRes = new VersionManagerRes();
	private List<Partner> partnerList;
	private String isCommit = "F";
	private Integer gameWebId;

	public String execute() {
		VersionManagerResService service = ServiceCacheFactory
				.getServiceCache().getService(VersionManagerResService.class);
		if (isCommit.equals("F")) {
			PartnerService partnerService = ServiceCacheFactory
					.getServiceCache().getService(PartnerService.class);
			partnerList = partnerService.findPartnerList();
			versionManagerRes = service.getVersionManagerRes(
					versionManagerRes.getId(), gameWebId);

			return INPUT;
		}

		service.updateVersionManagerRes(versionManagerRes, gameWebId);
		
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("UpdateVersionManagerRes");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("修改资源版本信息");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
				.getServiceCache().getService(
						AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		return SUCCESS;
	}

	@Override
	public VersionManagerRes getModel() {
		// TODO Auto-generated method stub
		return versionManagerRes;
	}

	public VersionManagerRes getVersionManagerRes() {
		return versionManagerRes;
	}

	public void setVersionManagerRes(VersionManagerRes versionManagerRes) {
		this.versionManagerRes = versionManagerRes;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

}
