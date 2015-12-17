package com.system.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.GameWebServer;
import com.system.service.GameWebServerService;

/**
 * 更新gameWeb 服务器
 * 
 * @author yezp
 */
public class UpdateGameWebServer extends ALDAdminActionSupport implements
		ModelDriven<GameWebServer> {

	private static final long serialVersionUID = -6840881820304326571L;

	private GameWebServer gameWebServer = new GameWebServer();

	private String isCommit = "F";
	private String gameWebId;

	public String execute() {
		GameWebServerService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebServerService.class);
		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}

		if ("F".equals(isCommit)) {
			gameWebServer = service.findOneServer(gameWebServer.getServerId(),
					dbSourceId);
			return INPUT;
		}

		service.updateGameWebServer(gameWebServer, dbSourceId);
		// 记录日志
				AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
				adminChangeConstantLog.setSysNum(0);
				adminChangeConstantLog
						.setAdminName(super.getAdminUser().getAdminName());
				adminChangeConstantLog.setChangeTime(new Timestamp(System
						.currentTimeMillis()));
				adminChangeConstantLog.setConstantName("UpdateGameWebServer");
				adminChangeConstantLog.setChangeType(1);
				adminChangeConstantLog.setChangeDetail("修改 ");
				AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
						.getServiceCache().getService(
								AdminChangeConstantLogService.class);
				adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		return SUCCESS;
	}

	public GameWebServer getGameWebServer() {
		return gameWebServer;
	}

	public void setGameWebServer(GameWebServer gameWebServer) {
		this.gameWebServer = gameWebServer;
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

	@Override
	public GameWebServer getModel() {
		// TODO Auto-generated method stub
		return gameWebServer;
	}

}
