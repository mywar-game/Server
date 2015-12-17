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
import com.system.service.GameWebServerService;

/**
 * 添加GameWeb服务器
 * 
 * @author yezp
 */
public class AddGameWebServer extends ALDAdminActionSupport implements
		ModelDriven<GameWebServer> {

	private static final long serialVersionUID = -3238519102395144602L;

	private GameWebServer gameWebServer = new GameWebServer();

	private String gameWebId;
	private String isCommit = "F";

	public String execute() {
		if (isCommit.equals("F")) {
			return INPUT;
		}

		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}

		GameWebServerService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebServerService.class);
		// TODO serverId为主键
		List<GameWebServer> list = service.findAllServer(dbSourceId);
		for (GameWebServer gwServer : list) {
			if (gameWebServer.getServerId().equals(gwServer.getServerId())) {
				setErroDescrip("已存在相同的服务器ID：" + gameWebServer.getServerId());
				return INPUT;
			}
		}

		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());

		gameWebServer.setCreateTime(timestamp);
		service.addGameWebServer(gameWebServer, dbSourceId);

		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("AddGameWebServer");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("添加 ");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
				.getServiceCache().getService(
						AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		return SUCCESS;

	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public GameWebServer getGameWebServer() {
		return gameWebServer;
	}

	public void setGameWebServer(GameWebServer gameWebServer) {
		this.gameWebServer = gameWebServer;
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
