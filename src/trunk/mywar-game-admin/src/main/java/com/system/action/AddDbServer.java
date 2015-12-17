package com.system.action;

import java.sql.Timestamp;
import java.util.Map;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TDbServer;
import com.system.service.TDbServerService;

public class AddDbServer extends ALDAdminActionSupport implements
		ModelDriven<TDbServer> {

	/** * */
	private static final long serialVersionUID = 7757875352424529829L;

	private TDbServer dbServer = new TDbServer();

	private String isCommit = "F";

	private Map<Integer, Integer> typeAndLastSuffixMap;

	public String execute() {
		TDbServerService service = ServiceCacheFactory.getServiceCache()
				.getService(TDbServerService.class);
		if ("F".equals(this.isCommit)) {
			typeAndLastSuffixMap = service.findTypeAndLastSuffixMap();
			return INPUT;
		} else {
			service.addTDbServer(dbServer);
			// 记录日志
			AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
			adminChangeConstantLog.setSysNum(0);
			adminChangeConstantLog.setAdminName(super.getAdminUser()
					.getAdminName());
			adminChangeConstantLog.setChangeTime(new Timestamp(System
					.currentTimeMillis()));
			adminChangeConstantLog.setConstantName("AddDbServer");
			adminChangeConstantLog.setChangeType(1);
			adminChangeConstantLog.setChangeDetail("添加");
			AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
					.getServiceCache().getService(
							AdminChangeConstantLogService.class);
			adminChangeConstantLogService.saveOne(adminChangeConstantLog);
			return SUCCESS;
		}
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

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setTypeAndLastSuffixMap(
			Map<Integer, Integer> typeAndLastSuffixMap) {
		this.typeAndLastSuffixMap = typeAndLastSuffixMap;
	}

	public Map<Integer, Integer> getTypeAndLastSuffixMap() {
		return typeAndLastSuffixMap;
	}

}
