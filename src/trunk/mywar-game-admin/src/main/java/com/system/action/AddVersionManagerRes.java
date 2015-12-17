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

/**
 * 添加资源版本信息
 * 
 * @author yezp
 */
public class AddVersionManagerRes extends ALDAdminActionSupport implements
		ModelDriven<VersionManagerRes> {

	private static final long serialVersionUID = 2140021507741592081L;

	private VersionManagerRes versionManagerRes = new VersionManagerRes();
	private List<Partner> partnerList;
	private String isCommit = "F";
	private Integer gameWebId;

	public String execute() {
		VersionManagerResService service = ServiceCacheFactory
				.getServiceCache().getService(VersionManagerResService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);
		partnerList = partnerService.findPartnerList();

		if (isCommit.equals("F")) {
			versionManagerRes = service.getMaxVersion(gameWebId);
			return INPUT;
		}

		int pid = Integer.parseInt(versionManagerRes.getPartnerId());
		Partner partner = partnerService.getPartnerByPid(pid);

		VersionManagerRes res = service.getMaxVersionByPartnerId(
				versionManagerRes.getPartnerId(), gameWebId);
		// 同渠道下，要大于最大版本号
		if (res.getBeUpdateBigVersion() != 0 && (versionManagerRes.getResBigVersion() < res.getResBigVersion()
				|| (versionManagerRes.getResBigVersion() >= res
						.getResBigVersion() && versionManagerRes
						.getResSmallVersion() <= res.getResSmallVersion()))) {
			if (partner == null) {
				setErroDescrip("版本号小于当前资源最大版本号。");
			} else {
				setErroDescrip("版本号小于或等于渠道：" + partner.getPName() + "下的最大版本号。");
			}			
			
			return INPUT;
		}

		service.addVersionManagerRes(versionManagerRes, gameWebId);
		
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("AddVersionManagerRes");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("添加资源版本信息");
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
