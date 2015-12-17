package com.system.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.GameWebServer;
import com.system.bo.Notice;
import com.system.service.GameWebServerService;
import com.system.service.NoticeService;

/**
 * 添加通知信息
 * 
 * @author yezp
 */
public class AddNotice extends ALDAdminActionSupport implements
		ModelDriven<Notice> {

	private static final long serialVersionUID = 8013615837381215582L;

	private Notice notice = new Notice();
	private List<GameWebServer> serverList;
	private String gameWebId;
	private String isCommit = "F";
	private String sid[];
	private String[] gameWebId1;

	
	public String execute() {
		NoticeService noticeService = ServiceCacheFactory.getServiceCache()
				.getService(NoticeService.class);
		GameWebServerService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebServerService.class);

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
		List<Notice> noticeList = noticeService.getNoticeList(dbSourceId);
		for (String serverId : sid) {
			for (Notice n : noticeList) {
				if (n.getServerId().equals(serverId)) {
					setErroDescrip("存在相同的服务器ID：" + notice.getServerId());
					return INPUT;
				}
			}
		}

		// 添加
		Date date = new Date();
		Timestamp createdTime = new Timestamp(date.getTime());
		notice.setCreatedTime(createdTime);

		for (String serverId : sid) {
			notice.setServerId(serverId);
			noticeService.addNotice(notice, dbSourceId);
		}
		
		// 记录日志
				AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
				adminChangeConstantLog.setSysNum(0);
				adminChangeConstantLog
						.setAdminName(super.getAdminUser().getAdminName());
				adminChangeConstantLog.setChangeTime(new Timestamp(System
						.currentTimeMillis()));
				adminChangeConstantLog.setConstantName("AddNotice");
				adminChangeConstantLog.setChangeType(1);
				adminChangeConstantLog.setChangeDetail("添加通知信息");
				AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
						.getServiceCache().getService(
								AdminChangeConstantLogService.class);
				adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		return SUCCESS;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
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
	public Notice getModel() {
		// TODO Auto-generated method stub
		return notice;
	}
	public String[] getGameWebId1() {
		return gameWebId1;
	}

	public void setGameWebId1(String[] gameWebId1) {
		this.gameWebId1 = gameWebId1;
	}


}
