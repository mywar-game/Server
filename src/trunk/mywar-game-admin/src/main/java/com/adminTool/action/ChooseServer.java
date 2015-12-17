package com.adminTool.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.bo.TGameServer;
import com.system.service.GameWebService;
import com.system.service.TGameServerService;

public class ChooseServer extends ALDAdminActionSupport{

	private Map<GameWeb, List<TGameServer>> gameWebMap;
	
	public Map<GameWeb, List<TGameServer>> getGameWebMap() {
		return gameWebMap;
	}

	public void setGameWebMap(Map<GameWeb, List<TGameServer>> gameWebMap) {
		this.gameWebMap = gameWebMap;
	}

	@Override
	public String execute() {
		
		GameWebService gameWebService = ServiceCacheFactory
				.getServiceCache().getService(GameWebService.class);
		TGameServerService serverService = ServiceCacheFactory
				.getServiceCache().getService(TGameServerService.class);

		gameWebMap = new HashMap<GameWeb, List<TGameServer>>();
		List<GameWeb> gameWebList = gameWebService.findGameWebList();
		for (GameWeb gameWeb : gameWebList) {
			List<TGameServer> list = serverService
					.findTGameServerListByGameWebId(gameWeb.getServerId());
			gameWebMap.put(gameWeb, list);
		}
		
		return INPUT;
	}
}
