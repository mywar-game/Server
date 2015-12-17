package com.system.action;

import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.TDbServer;
import com.system.bo.TSecondaryServer;
import com.system.service.TDbServerService;
import com.system.service.TSecondaryServerService;

public class SecondaryServerList extends ALDAdminPageActionSupport {

	/**  */
	private static final long serialVersionUID = 5063068100385417237L;
	
	private List<TSecondaryServer> secondaryServerList;
	
	private Map<Integer, TDbServer> idAndDbServerMap;
 	
	public String execute(){
		TSecondaryServerService service = ServiceCacheFactory.getServiceCache().getService(TSecondaryServerService.class);
		IPage<TSecondaryServer> list = service.findTSecondaryServerPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			secondaryServerList = (List<TSecondaryServer>) list.getData();
			StringBuffer ids = new StringBuffer();
			for (TSecondaryServer secondaryServer : secondaryServerList) {
				String serverId = secondaryServer.getServerId()+"";
				if(serverId.startsWith("3")){
					//说明是战斗服务器
					secondaryServer.setIsBattleServer(1);
				}
				if (ids.length() > 0) {
					ids.append(",");
				}
				ids.append(secondaryServer.getLogDbServerCode()).append(",").append(secondaryServer.getConfigDbServerCode()).append(",").append(secondaryServer.getCacheDbServerCode());
			}
			TDbServerService dbServerService = ServiceCacheFactory.getServiceCache().getService(TDbServerService.class);
			idAndDbServerMap = dbServerService.findIdAndDbServerMapInIds(ids.toString());
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setSecondaryServerList(List<TSecondaryServer> secondaryServerList) {
		this.secondaryServerList = secondaryServerList;
	}

	public List<TSecondaryServer> getSecondaryServerList() {
		return secondaryServerList;
	}

	public void setIdAndDbServerMap(Map<Integer, TDbServer> idAndDbServerMap) {
		this.idAndDbServerMap = idAndDbServerMap;
	}

	public Map<Integer, TDbServer> getIdAndDbServerMap() {
		return idAndDbServerMap;
	}

}
