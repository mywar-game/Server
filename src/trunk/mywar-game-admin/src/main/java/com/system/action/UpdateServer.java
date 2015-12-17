package com.system.action;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.bo.Partner;
import com.adminTool.service.AdminChangeConstantLogService;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.Server;
import com.system.service.GameWebServerService;
import com.system.service.ServerService;

/**
 * 修改服务器
 * 
 * @author yezp
 */
public class UpdateServer extends ALDAdminActionSupport implements
		ModelDriven<Server> {

	private static final long serialVersionUID = -2441816543026064944L;

	private Server server = new Server();

	private String isCommit = "F";

	private String gameWebId;
	private List<String> serverIdList;
	private List<Partner> partnerList;
	private String oldServerId;
	private String oldPartnerId;
	private String oldAliaServerId;

	public String execute() {
		ServerService service = ServiceCacheFactory.getServiceCache()
				.getService(ServerService.class);
		GameWebServerService gameService = ServiceCacheFactory
				.getServiceCache().getService(GameWebServerService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);

		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}

		partnerList = partnerService.findPartnerList();
		serverIdList = gameService.findAllServerId(dbSourceId);
		if ("F".equals(isCommit)) {
			server = service.findOneServer(server.getServerId(),
					server.getPartenerId(), server.getAliaServerId(),
					dbSourceId);
			return INPUT;
		}

		List<Server> serverList = service.getServerList(dbSourceId);
		// 若为原数据，直接update
		if (!server.getServerId().equals(oldServerId) && !server
				.getPartenerId().equals(oldPartnerId)) {
			Server old = new Server();
			old.setServerId(oldServerId);
			old.setPartenerId(oldPartnerId);
			old.setAliaServerId(oldAliaServerId);

			for (Server s : serverList) {
				// TODO 服务器别名唯一
				if (!server.getAliaServerId().equals(oldAliaServerId)
						&& s.getAliaServerId().equals(server.getAliaServerId())) {
					setErroDescrip("存在相同的服务器别名：" + server.getAliaServerId());
					return INPUT;
				}

				if (s.getServerId().equals(server.getServerId())
						&& s.getPartenerId().equals(server.getPartenerId())) {
					Map<String, Partner> partnerMap = partnerService
							.findAllPartnerMap();
					setErroCodeNum(100001);
					setErroDescrip("已存在该服务器名称为：" + server.getServerId()
							+ "，合作方："
							+ partnerMap.get(server.getPartenerId()).getPName());

					return INPUT;
				}
			}
			service.updateServer(server, old, dbSourceId);
			// 记录日志
			AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
			adminChangeConstantLog.setSysNum(0);
			adminChangeConstantLog
					.setAdminName(super.getAdminUser().getAdminName());
			adminChangeConstantLog.setChangeTime(new Timestamp(System
					.currentTimeMillis()));
			adminChangeConstantLog.setConstantName("UpdateServer");
			adminChangeConstantLog.setChangeType(1);
			adminChangeConstantLog.setChangeDetail("修改 ");
			AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
					.getServiceCache().getService(
							AdminChangeConstantLogService.class);
			adminChangeConstantLogService.saveOne(adminChangeConstantLog);
			return SUCCESS;
		}

		if (!server.getAliaServerId().equals(oldAliaServerId)) {
			for (Server s : serverList) {
				// TODO 服务器别名唯一
				if (s.getAliaServerId().equals(server.getAliaServerId())) {
					setErroDescrip("存在相同的服务器别名：" + server.getAliaServerId());
					return INPUT;
				}
			}
		}

		service.updateServer(server, server, dbSourceId);
		return SUCCESS;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public List<String> getServerIdList() {
		return serverIdList;
	}

	public void setServerIdList(List<String> serverIdList) {
		this.serverIdList = serverIdList;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

	public String getOldServerId() {
		return oldServerId;
	}

	public void setOldServerId(String oldServerId) {
		this.oldServerId = oldServerId;
	}

	public String getOldPartnerId() {
		return oldPartnerId;
	}

	public void setOldPartnerId(String oldPartnerId) {
		this.oldPartnerId = oldPartnerId;
	}

	public String getOldAliaServerId() {
		return oldAliaServerId;
	}

	public void setOldAliaServerId(String oldAliaServerId) {
		this.oldAliaServerId = oldAliaServerId;
	}

	@Override
	public Server getModel() {
		// TODO Auto-generated method stub
		return server;
	}

}
