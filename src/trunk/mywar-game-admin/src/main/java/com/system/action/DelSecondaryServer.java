package com.system.action;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TSecondaryServer;
import com.system.service.TSecondaryServerService;

public class DelSecondaryServer extends ALDAdminActionSupport implements ModelDriven<TSecondaryServer> {

	/**  */
	private static final long serialVersionUID = 1281260441116708537L;

	private TSecondaryServer secondaryServer = new TSecondaryServer();
	
	public void executeDel(){
		TSecondaryServerService service = ServiceCacheFactory.getServiceCache().getService(TSecondaryServerService.class);
		service.delTSecondaryServer(secondaryServer.getServerId());
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

}
