package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.AdminMail;
import com.adminTool.bo.Partner;
import com.adminTool.service.MailToolService;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;

public class MailAuditList extends ALDAdminPageActionSupport{

	private static final long serialVersionUID = -1747839515144078352L; 
	
	private List<AdminMail> mailList;
	
	public String execute() {
		MailToolService mts = ServiceCacheFactory.getServiceCache().getService(MailToolService.class);
		TGameServerService tGameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		PartnerService pts = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		
		IPage<AdminMail> page = mts.findAllMail(super.getToPage(), 10);
		
		if (page != null) {
			mailList = (List<AdminMail>) page.getData();

			for (AdminMail mail : mailList) {
				String[] servers = mail.getServerIds().split(", ");
				StringBuilder serverStr = new StringBuilder();

				for (String server : servers) {
					TGameServer gameServer = tGameServerService.findOneTGameServer(Integer.valueOf(server));
					if (gameServer == null)
						continue;
					
					String name = gameServer.getServerAlias();
					serverStr.append(name).append("  ");
				}
				mail.setServerIds(serverStr.toString());
				
				
				String partnerId = mail.getPartner();
				
				if (!partnerId.equals("-1")) {
					Partner partner = pts.getPartnerByPid(Integer.valueOf(partnerId));
					mail.setPartner(partner.getPName());
				}
			}
			
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
		
	}

	public List<AdminMail> getMailList() {
		return mailList;
	}

	public void setMailList(List<AdminMail> mailList) {
		this.mailList = mailList;
	}
	
	

}
