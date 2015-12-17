package com.adminTool.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.AdminMail;
import com.adminTool.bo.DropTool;
import com.adminTool.bo.Partner;
import com.adminTool.service.MailToolService;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.GameWeb;
import com.system.bo.TGameServer;
import com.system.service.GameWebService;
import com.system.service.TGameServerService;

public class MailSend extends ALDAdminActionSupport implements
		ModelDriven<AdminMail> {

	private static final long serialVersionUID = -3632307771707801095L;
	private List<TGameServer> tgameServerList;
	private List<Partner> partnerList;

	private String mailAttach;
	private Map<String, String> tools;

	private AdminMail adminMail = new AdminMail();
	private String isCommit = "F";
	private Map<GameWeb, List<TGameServer>> gameWebMap = new HashMap<GameWeb, List<TGameServer>>();
	private String minRegTime;

	public String execute() {
		MailToolService mailToolService = ServiceCacheFactory.getServiceCache()
				.getService(MailToolService.class);
		TGameServerService tgs = ServiceCacheFactory.getServiceCache()
				.getService(TGameServerService.class);
		PartnerService pts = ServiceCacheFactory.getServiceCache().getService(
				PartnerService.class);
		tgameServerList = tgs.findTGameServerList();
		partnerList = pts.findPartnerList();
		tools = mailToolService.findTools();

		// add
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);

		List<GameWeb> list = gameWebService.findGameWebList();
		for (GameWeb gameWeb : list) {

			List<TGameServer> l = new ArrayList<TGameServer>();
			for (TGameServer gameServer : tgameServerList) {
				if (gameServer.getGameWebServerId() == gameWeb.getServerId()) {
					l.add(gameServer);
				}
			}

			gameWebMap.put(gameWeb, l);
		}

		// end add
		if ("F".equals(isCommit)) {
			return INPUT;
		} else {

			String[] serverIds = adminMail.getServerIds().split(", ");
			;

			if (serverIds.length >= 2 && adminMail.getTarget() == null) {
				adminMail.setTarget(1); // target 属性：1是全服，2是指定用户
			} else if (adminMail.getTarget() == null) {
				adminMail.setTarget(2);
			}

			String toolIds = "";
			if (mailAttach.length() != 0) {
				toolIds = mailAttach.substring(0, mailAttach.length() - 1);
			}

			// 检测是否有正式服  正式服需要限制检查，测试服不需要    
			boolean isOffical = false;
			for (TGameServer gameServer : tgameServerList) {
				if (gameServer.getOfficial() == 1) {
					for (String serverId : serverIds) {
						if (serverId.equals("" + gameServer.getServerId())) {
							isOffical = true;
							break;
						}
					}
					if (isOffical)
						break;
				}
			}
			// 检测数量是否超过限制
			if (isOffical) {
				if (!toolIds.equals("")) {
					List<DropTool> dropToolsList = mailToolService
							.getDropToolsList(toolIds);
					String response = mailToolService.check(dropToolsList);

					if (response != null && !"".equals(response)) {
						setErroDescrip(response);
						return INPUT;
					}
				}
			}

			adminMail.setToolIds(toolIds);
			adminMail.setCreatedTime(new Timestamp(new Date().getTime()));
			adminMail.setUpdatedTime(new Timestamp(new Date().getTime()));
			adminMail.setStatus(0);

			if (adminMail.getTarget() == 2 || adminMail.getDate() == null) {
				adminMail.setDate(new Date());
			}

			if (adminMail.getTarget() != 5) {
				adminMail.setPartner("-1");
			}

			System.out.println(minRegTime);
			if (adminMail.getTarget() == 1) {
				// 全服
				if (minRegTime != null && !minRegTime.equals("")) {
					adminMail.setLodoIds(minRegTime);
				}
			}
			// 发送者，谁敢再乱发。
			adminMail.setSenderm(super.getAdminUser().getAdminName());

			mailToolService.addMail(adminMail);
			return SUCCESS;
		}
	}

	public List<TGameServer> getTgameServerList() {
		return tgameServerList;
	}

	public void setTgameServerList(List<TGameServer> tgameServerList) {
		this.tgameServerList = tgameServerList;
	}

	@Override
	public AdminMail getModel() {
		return adminMail;
	}

	public Map<String, String> getTools() {
		return tools;
	}

	public void setTools(Map<String, String> tools) {
		this.tools = tools;
	}

	public String getMailAttach() {
		return mailAttach;
	}

	public void setMailAttach(String mailAttach) {
		this.mailAttach = mailAttach;
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

	public AdminMail getAdminMail() {
		return adminMail;
	}

	public void setAdminMail(AdminMail adminMail) {
		this.adminMail = adminMail;
	}

	public Map<GameWeb, List<TGameServer>> getGameWebMap() {
		return gameWebMap;
	}

	public void setGameWebMap(Map<GameWeb, List<TGameServer>> gameWebMap) {
		this.gameWebMap = gameWebMap;
	}

	public String getMinRegTime() {
		return minRegTime;
	}

	public void setMinRegTime(String minRegTime) {
		this.minRegTime = minRegTime;
	}
}
