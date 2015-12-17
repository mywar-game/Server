package com.system.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TDbServer;
import com.system.service.TDbServerService;

public class DelDbServer extends ALDAdminActionSupport implements ModelDriven<TDbServer> {

	/**  */
	private static final long serialVersionUID = -4737807020482635413L;

	private TDbServer dbServer = new TDbServer();
	
	public void executeDel(){
		TDbServerService service = ServiceCacheFactory.getServiceCache().getService(TDbServerService.class);
		service.delTDbServer(dbServer.getDbServerId());
		// 记录日志
					AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
					adminChangeConstantLog.setSysNum(0);
					adminChangeConstantLog.setAdminName(super.getAdminUser()
							.getAdminName());
					adminChangeConstantLog.setChangeTime(new Timestamp(System
							.currentTimeMillis()));
					adminChangeConstantLog.setConstantName("DelDbServer");
					adminChangeConstantLog.setChangeType(1);
					adminChangeConstantLog.setChangeDetail("删除");
					AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
							.getServiceCache().getService(
									AdminChangeConstantLogService.class);
					adminChangeConstantLogService.saveOne(adminChangeConstantLog);
	}
	
	@Override
	public TDbServer getModel() {
		return dbServer;
	}

	public void setDbServer(TDbServer dbServer) {
		this.dbServer = dbServer;
	}

	public TDbServer getDbServer() {
		return dbServer;
	}

}
