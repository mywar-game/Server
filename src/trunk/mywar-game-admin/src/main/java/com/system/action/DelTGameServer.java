package com.system.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;

public class DelTGameServer extends ALDAdminActionSupport implements
		ModelDriven<TGameServer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TGameServer tgameServer = new TGameServer();

	public void executeDel() {
		TGameServerService tGameServerService = ServiceCacheFactory
				.getServiceCache().getService(TGameServerService.class);
		tGameServerService.delOneTGameServer(tgameServer.getServerId());

		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("DelTGameServer");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("删除 ");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
				.getServiceCache().getService(
						AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
	}

	@Override
	public TGameServer getModel() {
		// TODO Auto-generated method stub
		return tgameServer;
	}

	public TGameServer getTgameServer() {
		return tgameServer;
	}

	public void setTgameServer(TGameServer tgameServer) {
		this.tgameServer = tgameServer;
	}

}
