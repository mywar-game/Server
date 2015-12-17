package com.system.action;

import java.util.Map;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TDbServer;
import com.system.bo.TSecondaryServer;
import com.system.service.TDbServerService;
import com.system.service.TSecondaryServerService;

public class UpdateSecondaryServer extends ALDAdminActionSupport implements ModelDriven<TSecondaryServer> {

	/**  */
	private static final long serialVersionUID = 1L;

	private TSecondaryServer secondaryServer = new TSecondaryServer();
	
	private String isCommit = "F";
	
	private Map<Integer, TDbServer> idAndDbServerMap;
	
	public String execute(){
		TSecondaryServerService service = ServiceCacheFactory.getServiceCache().getService(TSecondaryServerService.class);
		if ("F".equals(isCommit)) {
			secondaryServer = service.findOneTSecondaryServer(secondaryServer.getServerId());
			String serverId = secondaryServer.getServerId()+"";
			if(serverId.startsWith("3")){
				//说明是战斗服务器
				secondaryServer.setIsBattleServer(1);
			}
			
			StringBuffer ids = new StringBuffer();
			ids.append(secondaryServer.getLogDbServerCode()).append(",").append(secondaryServer.getConfigDbServerCode()).append(",").append(secondaryServer.getCacheDbServerCode());
			TDbServerService dbServerService = ServiceCacheFactory.getServiceCache().getService(TDbServerService.class);
			idAndDbServerMap = dbServerService.findDbServerMap();
			
			return INPUT;
		} else {
			service.updateOneTSecondaryServer(secondaryServer);
			return SUCCESS;
		}
	}
	
	@Override
	public TSecondaryServer getModel() {
		return secondaryServer;
	}

	public void setSecondaryServer(TSecondaryServer secondaryServer) {
		this.secondaryServer = secondaryServer;
	}

	public TSecondaryServer getSecondaryServer() {
		return secondaryServer;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIdAndDbServerMap(Map<Integer, TDbServer> idAndDbServerMap) {
		this.idAndDbServerMap = idAndDbServerMap;
	}

	public Map<Integer, TDbServer> getIdAndDbServerMap() {
		return idAndDbServerMap;
	}

}
