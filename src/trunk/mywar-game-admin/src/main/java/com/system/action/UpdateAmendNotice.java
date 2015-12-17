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
import com.system.service.AmendNoticeService;
import com.system.service.GameWebServerService;

/**修改渠道通知信息
 * 
 * @author lin
 */
public class UpdateAmendNotice extends ALDAdminActionSupport implements
		ModelDriven<AmendNotice> {

	private static final long serialVersionUID = -4996402797366020907L;

	private AmendNotice amendNotice = new AmendNotice();
	private List<Partner> partnerList;
	private String serverIdParam;
	private String partnerIdParam;
	private String isCommit = "F";
	private String gameWebId;
	private List<GameWebServer> serverList;

	public String execute() {
		AmendNoticeService amendNoticeService = ServiceCacheFactory.getServiceCache()
				.getService(AmendNoticeService.class);
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
			amendNotice = amendNoticeService.getAmendNoticeByServerIdAndPartnerId(amendNotice.getServerId(),amendNotice.getPartnerId(),
					dbSourceId);
			return INPUT;
		}
		
		// TODO serverId唯一
				List<AmendNotice> amendNoticeList = amendNoticeService.getAmendNoticeList(dbSourceId);
				
					for (AmendNotice n : amendNoticeList) {
						if(n.getServerId().equals(serverIdParam)&&n.getPartnerId().equals(partnerIdParam))
							continue;
						if (n.getServerId().equals(amendNotice.getServerId()) && n.getPartnerId().equals(amendNotice.getPartnerId())) {
							amendNotice = amendNoticeService.getAmendNoticeByServerIdAndPartnerId(serverIdParam,partnerIdParam,
									dbSourceId);
							setErroDescrip("该渠道已在此服务器中存在！");	
							return INPUT;
						}
					}
				
					

					
		Date date = new Date();
		Timestamp updatedTime = new Timestamp(date.getTime());
		amendNotice.setUpdatedTime(updatedTime);
		
		if(!amendNotice.getPartnerId().equals(partnerIdParam)){
			amendNoticeService.delAmendNotice(serverIdParam,partnerIdParam,
					dbSourceId);
			amendNoticeService.addAmendNotice(amendNotice, dbSourceId);
			recordLog();
			return SUCCESS;
		}
		
		amendNoticeService.updateAmendNotice(amendNotice, dbSourceId);

		recordLog();
		return SUCCESS;
	}
	
	public void recordLog(){
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("UpdateAmendNotice");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("修改渠道通知信息");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
				.getServiceCache().getService(
						AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
	}
	
	public List<Partner> getPartnerList() {
		return partnerList;
	}


	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}


	public AmendNotice getAmendNotice() {
		return amendNotice;
	}

	public void setAmendNotice(AmendNotice amendNotice) {
		this.amendNotice = amendNotice;
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

	@Override
	public AmendNotice getModel() {
		// TODO Auto-generated method stub
		return amendNotice;
	}

}
