package com.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.bo.TGameServer;
import com.system.domain.ServerNameAndIP;
import com.system.service.GameWebService;
import com.system.service.TDbServerService;
import com.system.service.TGameServerService;

public class TGameServerList extends ALDAdminPageActionSupport {

	/**
	 * 查询服务器信息
	 */
	private static final long serialVersionUID = -7945245077103211838L;

	private List<TGameServer> tgameServerList;

	private Map<Integer, ServerNameAndIP> dbServerNameMap = new HashMap<Integer, ServerNameAndIP>();

	private Map<Integer, ServerNameAndIP> serverNameMap = new HashMap<Integer, ServerNameAndIP>();

	private Map<GameWeb, List<TGameServer>> gameWebMap = new HashMap<GameWeb, List<TGameServer>>();

	public List<TGameServer> getTgameServerList() {
		return tgameServerList; 
	}

	public void setTgameServerList(List<TGameServer> tgameServerList) {
		this.tgameServerList = tgameServerList;
	}

	public String execute() {
		TGameServerService tGameServerService = ServiceCacheFactory
				.getServiceCache().getService(TGameServerService.class);
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);

		tgameServerList = tGameServerService.findTGameServerList(
				1, 2000);
		// ALDAdminPageActionSupport.DEFAULT_PAGESIZE
		if (1 == 1) {
//			tgameServerList = (List<TGameServer>) iPage.getData();
//			super.setTotalPage(iPage.getTotalPage());
//			super.setTotalSize(iPage.getTotalSize());

			// 查询数据库ID对应名称
			TDbServerService tdbServerService = ServiceCacheFactory
					.getServiceCache().getService(TDbServerService.class);
			List<Object> obects = tdbServerService.findAllDBNameAndIDServer();
			if (obects != null && obects.size() > 0) {
				for (Object object : obects) {
					Object[] objs = (Object[]) object;
					ServerNameAndIP serverNameAndIP = new ServerNameAndIP();
					serverNameAndIP.setServerName(objs[1].toString().trim());
					serverNameAndIP.setServerIP(objs[2].toString().trim() + " "
							+ objs[3].toString().trim());
					dbServerNameMap.put(Integer.parseInt(objs[0].toString()),
							serverNameAndIP);
					;
				}
			}

			List<GameWeb> list = gameWebService.findGameWebList();
			for (GameWeb gameWeb : list) {

				List<TGameServer> l = new ArrayList<TGameServer>();
				for (TGameServer gameServer : tgameServerList) {
					if (gameServer.getGameWebServerId() == gameWeb
							.getServerId()) {
						l.add(gameServer);
					}
				}

				/*if (l.size() > 0) {
					gameWebMap.put(gameWeb, l);
				}*/
				gameWebMap.put(gameWeb, l);
			}

			// 查询其他从服务器对应名称
			// TSecondaryServerService tsecondaryServerService =
			// ServiceCacheFactory.getServiceCache().getService(TSecondaryServerService.class);
			// List<Object> serverObects =
			// tsecondaryServerService.findAllServerListNameAndID();
			// if(serverObects != null && serverObects.size() > 0){
			// for (Object object : serverObects) {
			// Object [] objs = (Object [])object;
			// ServerNameAndIP serverNameAndIP = new ServerNameAndIP();
			// serverNameAndIP.setServerName(objs[1].toString().trim());
			// serverNameAndIP.setServerIP(objs[2].toString().trim());
			// serverNameMap.put(Integer.parseInt(objs[0].toString()),
			// serverNameAndIP);;
			// }
			// }
		}
		return SUCCESS;
	}

	public Map<Integer, ServerNameAndIP> getDbServerNameMap() {
		return dbServerNameMap;
	}

	public void setDbServerNameMap(Map<Integer, ServerNameAndIP> dbServerNameMap) {
		this.dbServerNameMap = dbServerNameMap;
	}

	public Map<Integer, ServerNameAndIP> getServerNameMap() {
		return serverNameMap;
	}

	public void setServerNameMap(Map<Integer, ServerNameAndIP> serverNameMap) {
		this.serverNameMap = serverNameMap;
	}

	public Map<GameWeb, List<TGameServer>> getGameWebMap() {
		return gameWebMap;
	}

	public void setGameWebMap(Map<GameWeb, List<TGameServer>> gameWebMap) {
		this.gameWebMap = gameWebMap;
	}

}
