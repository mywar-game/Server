package com.adminTool.action;

import com.adminTool.bo.AdminMail;
import com.adminTool.service.MailToolService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class MailDelete extends ALDAdminActionSupport implements ModelDriven<AdminMail>{

	private static final long serialVersionUID = 8925541472331132431L;

	private AdminMail adminMail = new AdminMail();
	
	public String execute() {
		MailToolService mts = ServiceCacheFactory.getServiceCache().getService(MailToolService.class);
		mts.deleteMail(adminMail.getAdminMailId());
		
		return SUCCESS;
	}
	
	@Override
	public AdminMail getModel() {
		return adminMail;
	}

	
}
