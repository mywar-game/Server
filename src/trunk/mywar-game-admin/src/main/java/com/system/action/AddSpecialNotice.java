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
 * 添加登录通知信息
 * 
 * @author lin
 */
public class AddSpecialNotice extends ALDAdminActionSupport implements
		ModelDriven<SpecialNotice> {

	private static final long serialVersionUID = -4086282652819860429L;
	
	private SpecialNotice specialNotice = new SpecialNotice();
	private List<Partner> partnerList;
	private List<GameWebServer> serverList;
	private String gameWebId;
	private String isCommit = "F";
	private String sid[];
	private String[] gameWebId1;

	
	public String execute() {
		SpecialNoticeService specialNoticeService = ServiceCacheFactory.getServiceCache()
				.getService(SpecialNoticeService.class);
		GameWebServerService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebServerService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);
		partnerList = partnerService.findPartnerList();

		Integer dbSourceId = 0;
		if (gameWebId1 != null && gameWebId1.length >= 1) {
			gameWebId = gameWebId1[0];
		}
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}

		serverList = service.findAllServer(dbSourceId);
		if (isCommit.equals("F")) {
			return INPUT;
		}

		if (sid == null || sid.length == 0) {
			setErroDescrip("请选择服务器。");
			return INPUT;
		}

		// TODO serverId唯一
				List<SpecialNotice> specialNoticeList = specialNoticeService.getSpecialNoticeList(dbSourceId);
				for (String serverId : sid) {
					for (SpecialNotice n : specialNoticeList) {
						if (n.getServerId().equals(serverId) && n.getPartnerId().equals(specialNotice.getPartnerId())) {
							setErroDescrip("该渠道已在此服务器中存在！");
							return INPUT;
						}
					}
				}

		// 添加
		Date date = new Date();
		Timestamp createdTime = new Timestamp(date.getTime());
		specialNotice.setCreatedTime(createdTime);

		for (String serverId : sid) {
			specialNotice.setServerId(serverId);
			specialNoticeService.addSpecialNotice(specialNotice, dbSourceId);
		}
		
		// 记录日志
				AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
				adminChangeConstantLog.setSysNum(0);
				adminChangeConstantLog
						.setAdminName(super.getAdminUser().getAdminName());
				adminChangeConstantLog.setChangeTime(new Timestamp(System
						.currentTimeMillis()));
				adminChangeConstantLog.setConstantName("AddSpecialNotice");
				adminChangeConstantLog.setChangeType(1);
				adminChangeConstantLog.setChangeDetail("添加登录通知信息");
				AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
						.getServiceCache().getService(
								AdminChangeConstantLogService.class);
				adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		return SUCCESS;
	}

	
	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}


	public SpecialNotice geSpecialtNotice() {
		return specialNotice;
	}

	public void setSpecialNotice(SpecialNotice specialNotice) {
		this.specialNotice = specialNotice;
	}

	public List<GameWebServer> getServerList() {
		return serverList;
	}

	public void setServerList(List<GameWebServer> serverList) {
		this.serverList = serverList;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String[] getSid() {
		return sid;
	}

	public void setSid(String[] sid) {
		this.sid = sid;
	}

	@Override
	public SpecialNotice getModel() {
		// TODO Auto-generated method stub
		return specialNotice;
	}
	public String[] getGameWebId1() {
		return gameWebId1;
	}

	public void setGameWebId1(String[] gameWebId1) {
		this.gameWebId1 = gameWebId1;
	}


}
