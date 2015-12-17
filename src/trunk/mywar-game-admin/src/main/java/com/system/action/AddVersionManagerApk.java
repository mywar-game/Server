package com.system.action;

import java.sql.Timestamp;
import java.util.List;

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
 * 添加apk版本信息
 * 
 * @author yezp
 */
public class AddVersionManagerApk extends ALDAdminActionSupport implements
		ModelDriven<VersionManagerApk> {

	private static final long serialVersionUID = -121876636462772748L;

	private VersionManagerApk versionManagerApk = new VersionManagerApk();
	private List<Partner> partnerList;
	private List<PartnerQn> qnList;

	private String isCommit = "F";
	private Integer gameWebId;

	public String execute() {
		VersionManagerApkService service = ServiceCacheFactory
				.getServiceCache().getService(VersionManagerApkService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);
		PartnerQnService qnService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerQnService.class);

		partnerList = partnerService.findPartnerList();
		qnList = qnService.getPartnerQnList();

		if (isCommit.equals("F")) {
			versionManagerApk = service.getMaxVersion(gameWebId);
			return INPUT;
		}

		int pid = Integer.parseInt(versionManagerApk.getPartnerId());
		Partner partner = partnerService.getPartnerByPid(pid);

		VersionManagerApk apk = service.getMaxVersionByParentId(
				versionManagerApk.getPartnerId(), gameWebId);
		// 同渠道下，大于最大版本号
		if (apk.getApkBigVersion() != 0
				&& (versionManagerApk.getApkBigVersion() < apk
						.getApkBigVersion() || (versionManagerApk
						.getApkBigVersion() >= apk.getApkBigVersion() && versionManagerApk
						.getApkSmallVersion() <= apk.getApkSmallVersion()))) {
			setErroDescrip("版本号小于或等于渠道：" + partner.getPName() + "下的最大版本号。");
			return INPUT;
		}

		service.addVersionManagerApk(versionManagerApk, gameWebId);
		
		// 记录日志
				AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
				adminChangeConstantLog.setSysNum(0);
				adminChangeConstantLog
						.setAdminName(super.getAdminUser().getAdminName());
				adminChangeConstantLog.setChangeTime(new Timestamp(System
						.currentTimeMillis()));
				adminChangeConstantLog.setConstantName("AddVersionManagerApk");
				adminChangeConstantLog.setChangeType(1);
				adminChangeConstantLog.setChangeDetail("添加APK版本信息");
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

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	public List<PartnerQn> getQnList() {
		return qnList;
	}

	public void setQnList(List<PartnerQn> qnList) {
		this.qnList = qnList;
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

}
