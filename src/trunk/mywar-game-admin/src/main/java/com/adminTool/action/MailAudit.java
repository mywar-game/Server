package com.adminTool.action;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import com.adminTool.bo.AdminMail;
import com.adminTool.bo.Partner;
import com.adminTool.service.MailToolService;
import com.adminTool.service.PartnerService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;
import com.tool.ParseJason;

public class MailAudit extends ALDAdminActionSupport implements ModelDriven<AdminMail> {

	private static final long serialVersionUID = 1055702475109398469L;
	private final static String ADD_MAIL_URL = "sendMail.do";
	private String isCommit = "F";
	private int isAudited = 0;
	private AdminMail adminMail = new AdminMail();

	public String execute() {

		MailToolService mts = ServiceCacheFactory.getServiceCache().getService(MailToolService.class);
		TGameServerService tGameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		PartnerService pts = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		
		adminMail = mts.findOneMail(adminMail.getAdminMailId());

		StringBuilder serverStr = new StringBuilder();
		String[] servers = adminMail.getServerIds().split(", ");
		
		String pid = adminMail.getPartner();

		if ("F".equals(isCommit)) {
			for (String server : servers) {
				TGameServer gameServer = tGameServerService.findOneTGameServer(Integer.valueOf(server));
				if (gameServer == null)
					continue;
				
				String name = gameServer.getServerAlias();
				serverStr.append(name).append("  ");
			}
			adminMail.setServerIds(serverStr.toString());
			
			if (!pid.equals("-1")) {
				Partner partner = pts.getPartnerByPid(Integer.valueOf(pid));
				adminMail.setPartner(partner.getPName());
			}
			
			return INPUT;
		} else {
			// 同意
			if (isAudited == 1) {
				String sendServerIds = adminMail.getSendServerIds();
				
				if (!pid.equals("-1")) {
					Partner partner = pts.getPartnerByPid(Integer.valueOf(pid));
					adminMail.setPartner(String.valueOf(partner.getPid()));
				}

				// 将邮件发送到游戏服务器
				for (String server : servers) {
					TGameServer gameServer = tGameServerService.findOneTGameServer(Integer.valueOf(server));
					if (gameServer == null)
						continue;
					
					String response = addMail(gameServer);

					if ("success".equals(response)) {
						adminMail.setStatus(isAudited);

						if (sendServerIds == null) {
							sendServerIds = "" + server;
						} else {
							sendServerIds += "," + server;
						}
					}
				}

				adminMail.setSendServerIds(sendServerIds);
			} else {
				adminMail.setStatus(isAudited);
			}
			
			adminMail.setApproveTime(new Timestamp(new Date().getTime()));
			// 看谁以后敢乱发
			adminMail.setAuditer(super.getAdminUser().getAdminName());
			
			mts.updateMail(adminMail);
			return SUCCESS;
		}
	}

	@SuppressWarnings("deprecation")
	private String addMail(TGameServer gameServer) {		
		StringBuilder param = new StringBuilder(); // param
		StringBuilder md5 = new StringBuilder(); // md5

		String encodedTitle = URLEncoder.encode(adminMail.getTitle());
		String encodedContent = URLEncoder.encode(adminMail.getContent());
		String encodedToolIds = URLEncoder.encode(adminMail.getToolIds());
		String encodedLodoIds = URLEncoder.encode(adminMail.getLodoIds());
		long date = adminMail.getDate().getTime();

		param.append("&title=").append(encodedTitle);
		param.append("&content=").append(encodedContent);
		param.append("&toolIds=").append(encodedToolIds);
		param.append("&lodoIds=").append(encodedLodoIds);
		param.append("&target=").append(adminMail.getTarget());
		param.append("&date=").append(date);
		param.append("&sourceId=").append(adminMail.getAdminMailId());
		param.append("&partner=").append(adminMail.getPartner());

		md5.append(encodedTitle).append(encodedContent).append(encodedToolIds)
				.append(encodedLodoIds).append(adminMail.getTarget())
				.append(date).append(adminMail.getAdminMailId())
				.append(adminMail.getPartner());

		gameServer.setWebServerPath("gameApi");
		String response = HttpClientBridge.sendToGameServer(ADD_MAIL_URL, param.toString(), md5.toString(), gameServer);

		LogSystem.info(response);
		Map<String, String> map = ParseJason.jason2Map(response);
		String rc = map.get("rc");
		if (rc == null) {
			rc = "3001";
		}

		if (!rc.equals("1000")) {
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	public int getIsAudited() {
		return isAudited;
	}

	public void setIsAudited(int isAudited) {
		this.isAudited = isAudited;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public AdminMail getAdminMail() {
		return adminMail;
	}

	public void setAdminMail(AdminMail adminMail) {
		this.adminMail = adminMail;
	}

	@Override
	public AdminMail getModel() {
		return adminMail;
	}

}
