package com.adminTool.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.AdminMail;
import com.adminTool.service.MailToolService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;

public class MailUpdate extends ALDAdminPageActionSupport implements ModelDriven<AdminMail>{

	private static final long serialVersionUID = -6997238574399122829L;
private List<TGameServer> tgameServerList ;
	
	private String mailAttach;
	private Map<String, String> tools;
	
	private AdminMail adminMail = new AdminMail();
	private String isCommit = "F";
	
	public String execute() {
		MailToolService mailToolService = ServiceCacheFactory.getServiceCache().getService(MailToolService.class);
		TGameServerService tgs = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		tgameServerList = tgs.findTGameServerList();
		
		tools = mailToolService.findTools();

		if ("F".equals(isCommit)) {
			adminMail = mailToolService.findOneMail(adminMail.getAdminMailId());

			return INPUT;
		} else {
				String toolIds = "";
				if (mailAttach.length() != 0) {
					toolIds = mailAttach.substring(0, mailAttach.length()-1);
				}
				
				adminMail.setToolIds(toolIds);
				
				adminMail.setUpdatedTime(new Timestamp(new Date().getTime()));
				adminMail.setStatus(0);
				
				mailToolService.updateMail(adminMail);
				
				return SUCCESS;
		}
	}

	
	public List<TGameServer> getTgameServerList() {
		return tgameServerList;
	}


	public void setTgameServerList(List<TGameServer> tgameServerList) {
		this.tgameServerList = tgameServerList;
	}


	public String getMailAttach() {
		return mailAttach;
	}


	public void setMailAttach(String mailAttach) {
		this.mailAttach = mailAttach;
	}


	public Map<String, String> getTools() {
		return tools;
	}


	public void setTools(Map<String, String> tools) {
		this.tools = tools;
	}


	public AdminMail getAdminMail() {
		return adminMail;
	}


	public void setAdminMail(AdminMail adminMail) {
		this.adminMail = adminMail;
	}


	public String getIsCommit() {
		return isCommit;
	}


	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}


	@Override
	public AdminMail getModel() {
		return adminMail;
	}
}
