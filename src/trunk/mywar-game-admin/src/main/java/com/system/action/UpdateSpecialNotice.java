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
import com.system.bo.AmendNotice;
import com.system.bo.GameWebServer;
import com.system.bo.SpecialNotice;
import com.system.service.GameWebServerService;
import com.system.service.SpecialNoticeService;

/**
 * 修改登录通知信息
 * 
 * @author lin
 */
public class UpdateSpecialNotice extends ALDAdminActionSupport implements
		ModelDriven<SpecialNotice> {

	private static final long serialVersionUID = -4996402797366020907L;

	private SpecialNotice specialNotice = new SpecialNotice();
	private List<Partner> partnerList;
	private String isCommit = "F";
	private String serverIdParam;
	private String partnerIdParam;
	private String gameWebId;
	private List<GameWebServer> serverList;

	public String execute() {
		SpecialNoticeService specialNoticeService = ServiceCacheFactory
				.getServiceCache().getService(SpecialNoticeService.class);
		GameWebServerService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebServerService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);
		partnerList = partnerService.findPartnerList();

		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}

		serverList = service.findAllServer(dbSourceId);
		if (isCommit.equals("F")) {
			specialNotice = specialNoticeService
					.getSpecialNoticeByServerIdAndPartnerId(
							specialNotice.getServerId(),
							specialNotice.getPartnerId(), dbSourceId);
			return INPUT;
		}

		// TODO serverId唯一
		List<SpecialNotice> specialNoticeList = specialNoticeService
				.getSpecialNoticeList(dbSourceId);

		for (SpecialNotice n : specialNoticeList) {
			if (n.getServerId().equals(serverIdParam)
					&& n.getPartnerId().equals(partnerIdParam))
				continue;
			if (n.getServerId().equals(specialNotice.getServerId())
					&& n.getPartnerId().equals(specialNotice.getPartnerId())) {
				specialNotice = specialNoticeService
						.getSpecialNoticeByServerIdAndPartnerId(serverIdParam,
								partnerIdParam, dbSourceId);
				setErroDescrip("该渠道已在此服务器中存在！");
				return INPUT;
			}
		}

		Date date = new Date();
		Timestamp updatedTime = new Timestamp(date.getTime());
		specialNotice.setUpdatedTime(updatedTime);

		if (!specialNotice.getPartnerId().equals(partnerIdParam)) {
			specialNoticeService.delSpecialNotice(serverIdParam,
					partnerIdParam, dbSourceId);
			specialNoticeService.addSpecialNotice(specialNotice, dbSourceId);
			recordLog();
			return SUCCESS;
		}
		specialNoticeService.updateSpecialNotice(specialNotice, dbSourceId);

		recordLog();
		return SUCCESS;
	}

	public void recordLog() {
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("UpdateSpecialNotice");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("修改登录通知信息");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
				.getServiceCache().getService(
						AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
	}

	public String getServerIdParam() {
		return serverIdParam;
	}

	public void setServerIdParam(String serverIdParam) {
		this.serverIdParam = serverIdParam;
	}

	public String getPartnerIdParam() {
		return partnerIdParam;
	}

	public void setPartnerIdParam(String partnerIdParam) {
		this.partnerIdParam = partnerIdParam;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	public SpecialNotice getSpecialNotice() {
		return specialNotice;
	}

	public void setSpecialNotice(SpecialNotice specialNotice) {
		this.specialNotice = specialNotice;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

	public List<GameWebServer> getServerList() {
		return serverList;
	}

	public void setServerList(List<GameWebServer> serverList) {
		this.serverList = serverList;
	}

	@Override
	public SpecialNotice getModel() {
		// TODO Auto-generated method stub
		return specialNotice;
	}

}
