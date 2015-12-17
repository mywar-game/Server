package com.system.action;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.bo.Partner;
import com.adminTool.bo.PartnerQn;
import com.adminTool.service.AdminChangeConstantLogService;
import com.adminTool.service.PartnerQnService;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.VersionManagerApk;
import com.system.service.VersionManagerApkService;

/**
 * 修改apk版本信息
 * 
 * @author yezp
 */
public class UpdateVersionManagerApk extends ALDAdminActionSupport implements
		ModelDriven<VersionManagerApk> {

	private static final long serialVersionUID = 5426539764491684342L;

	private VersionManagerApk versionManagerApk = new VersionManagerApk();
	private List<Partner> partnerList;
	private Map<String, PartnerQn> qnMap;
	private List<PartnerQn> qnList;

	private String isCommit = "F";
	private Integer gameWebId;

	public String execute() {
		VersionManagerApkService service = ServiceCacheFactory
				.getServiceCache().getService(VersionManagerApkService.class);
		if (isCommit.equals("F")) {
			PartnerService partnerService = ServiceCacheFactory
					.getServiceCache().getService(PartnerService.class);
			PartnerQnService qnService = ServiceCacheFactory.getServiceCache()
					.getService(PartnerQnService.class);
			partnerList = partnerService.findPartnerList();
			qnMap = qnService.findAllQnMap();
			qnList = qnService.getPartnerQnList();

			versionManagerApk = service.getVersionManagerApk(
					versionManagerApk.getId(), gameWebId);
			return INPUT;
		}

		service.updateVersionManagerApk(versionManagerApk, gameWebId);
		
		// 记录日志
				AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
				adminChangeConstantLog.setSysNum(0);
				adminChangeConstantLog
						.setAdminName(super.getAdminUser().getAdminName());
				adminChangeConstantLog.setChangeTime(new Timestamp(System
						.currentTimeMillis()));
				adminChangeConstantLog.setConstantName("UpdateVersionManagerApk");
				adminChangeConstantLog.setChangeType(1);
				adminChangeConstantLog.setChangeDetail("修改APK版本信息");
				AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
						.getServiceCache().getService(
								AdminChangeConstantLogService.class);
				adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		return SUCCESS;
	}

	@Override
	public VersionManagerApk getModel() {
		// TODO Auto-generated method stub
		return versionManagerApk;
	}

	public VersionManagerApk getVersionManagerApk() {
		return versionManagerApk;
	}

	public void setVersionManagerApk(VersionManagerApk versionManagerApk) {
		this.versionManagerApk = versionManagerApk;
	}

	public Map<String, PartnerQn> getQnMap() {
		return qnMap;
	}

	public void setQnMap(Map<String, PartnerQn> qnMap) {
		this.qnMap = qnMap;
	}

	public List<PartnerQn> getQnList() {
		return qnList;
	}

	public void setQnList(List<PartnerQn> qnList) {
		this.qnList = qnList;
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
