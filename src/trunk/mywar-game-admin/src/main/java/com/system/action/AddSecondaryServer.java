package com.system.action;

import java.util.Map;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TSecondaryServer;
import com.system.service.TSecondaryServerService;

public class AddSecondaryServer extends ALDAdminActionSupport implements ModelDriven<TSecondaryServer> {

	/**  */
	private static final long serialVersionUID = 2018622827799724404L;

	private TSecondaryServer secondaryServer = new TSecondaryServer();
	
	private String isCommit = "F";
	
	private Map<Integer, Integer> typeAndLastServerIdMap; 
	
	public String execute(){
		TSecondaryServerService service = ServiceCacheFactory.getServiceCache().getService(TSecondaryServerService.class);
		if ("F".equals(this.isCommit)) {
			typeAndLastServerIdMap = service.findTypeAndLastServerIdMap();
			return INPUT;
		} else {
			service.addTSecondaryServer(secondaryServer);
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

	public void setTypeAndLastServerIdMap(Map<Integer, Integer> typeAndLastServerIdMap) {
		this.typeAndLastServerIdMap = typeAndLastServerIdMap;
	}

	public Map<Integer, Integer> getTypeAndLastServerIdMap() {
		return typeAndLastServerIdMap;
	}

}
