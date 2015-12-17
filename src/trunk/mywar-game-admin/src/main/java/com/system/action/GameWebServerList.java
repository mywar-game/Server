package com.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.bo.GameWebServer;
import com.system.service.GameServerStatusService;
import com.system.service.GameWebServerService;
import com.system.service.GameWebService;
import com.system.service.LoginServerStatusService;

/**
 * GameWeb服务器列表
 * 
 * @author yezp
 */
public class GameWebServerList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -426378114264808110L;

	private List<GameWebServer> gameWebServerList;

	private Map<GameWeb, List<GameWebServer>> gameWebServerMap;
	private List<GameWeb> list;

	public String execute() {
		GameWebService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		GameWebServerService gameWebServerService = ServiceCacheFactory
				.getServiceCache().getService(GameWebServerService.class);
		GameServerStatusService statusService = ServiceCacheFactory
				.getServiceCache().getService(GameServerStatusService.class);
		LoginServerStatusService loginStatusService = ServiceCacheFactory
				.getServiceCache().getService(LoginServerStatusService.class);
		long maxTotalPage = 0;
		long maxTotalSize = 0;
		list = service.findGameWebList();
		gameWebServerMap = new HashMap<GameWeb, List<GameWebServer>>();
		for (GameWeb gameWeb : list) {
			int openStatus = loginStatusService.getLoginServerStatusInt(gameWeb
					.getServerId());
			gameWeb.setOpenStatus(openStatus);

			IPage<GameWebServer> iPage = gameWebServerService
					.findGameWebServerPageList(super.getToPage(),
							ALDAdminPageActionSupport.DEFAULT_PAGESIZE,
							gameWeb.getServerId());

			List<GameWebServer> gameWebServerList = gameWebServerService
					.getServerListByDBSource(gameWeb.getServerId());
			gameWebServerList = (List<GameWebServer>) iPage.getData();

			for (GameWebServer server : gameWebServerList) {
				openStatus = statusService.getStatusByServerId(
						server.getServerId(), gameWeb.getServerId());
				server.setOpenStatus(openStatus);
			}

			gameWebServerMap.put(gameWeb, gameWebServerList);

			if (maxTotalPage < iPage.getTotalPage()) {
				maxTotalPage = iPage.getTotalPage();
				super.setTotalPage(maxTotalPage);
			}
			if (maxTotalSize < iPage.getTotalSize()) {
				maxTotalSize = iPage.getTotalSize();
				super.setTotalSize(maxTotalSize);
			}
//			super.setTotalPage(iPage.getTotalPage());
//			super.setTotalSize(iPage.getTotalSize());
		}

		return SUCCESS;
	}

	public List<GameWebServer> getGameWebServerList() {
		return gameWebServerList;
	}

	public void setGameWebServerList(List<GameWebServer> gameWebServerList) {
		this.gameWebServerList = gameWebServerList;
	}

	public Map<GameWeb, List<GameWebServer>> getGameWebServerMap() {
		return gameWebServerMap;
	}

	public void setGameWebServerMap(
			Map<GameWeb, List<GameWebServer>> gameWebServerMap) {
		this.gameWebServerMap = gameWebServerMap;
	}

	public List<GameWeb> getList() {
		return list;
	}

	public void setList(List<GameWeb> list) {
		this.list = list;
	}

}
