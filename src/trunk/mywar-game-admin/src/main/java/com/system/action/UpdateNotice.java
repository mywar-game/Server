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
 * 
 * @author yezp
 */
public class UpdateNotice extends ALDAdminActionSupport implements
		ModelDriven<Notice> {

	private static final long serialVersionUID = -4996402797366020907L;

	private Notice notice = new Notice();
	private String isCommit = "F";
	private String gameWebId;
	private List<GameWebServer> serverList;

	public String execute() {
		NoticeService noticeService = ServiceCacheFactory.getServiceCache()
				.getService(NoticeService.class);
		GameWebServerService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebServerService.class);

		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}

		serverList = service.findAllServer(dbSourceId);
		if (isCommit.equals("F")) {
			notice = noticeService.getNoticeByServerId(notice.getServerId(),
					dbSourceId);
			return INPUT;
		}

		Date date = new Date();
		Timestamp updatedTime = new Timestamp(date.getTime());
		notice.setUpdatedTime(updatedTime);
		noticeService.updateNotice(notice, dbSourceId);

		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("UpdateNotice");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("修改通知信息");
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
	public Notice getModel() {
		// TODO Auto-generated method stub
		return notice;
	}

}
