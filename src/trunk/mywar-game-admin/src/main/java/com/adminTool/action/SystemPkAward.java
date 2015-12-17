package com.adminTool.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.AdminMarquee;
import com.adminTool.service.AdminMarqueeService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.service.GameWebService;
import com.system.service.TGameServerService;

/**
 * 跑马灯列表
 * 
 * @author yezp
 */
public class SystemPkAward extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -3936077220435901304L;

	private List<AdminMarquee> marqueeList;

	public String execute() {
		AdminMarqueeService service = ServiceCacheFactory.getServiceCache()
				.getService(AdminMarqueeService.class);
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		TGameServerService serverService = ServiceCacheFactory
				.getServiceCache().getService(TGameServerService.class);

		Map<Integer, GameWeb> gameWebMap = gameWebService
				.findGameWebServerIdMap();
		Map<Integer, Map<Integer, String>> serverMap = new HashMap<Integer, Map<Integer, String>>();

		List<GameWeb> list = gameWebService.findGameWebList();
		for (GameWeb gameWeb : list) {
			Map<Integer, String> map = serverService.findServerIdNameMap();
			serverMap.put(gameWeb.getServerId(), map);
		}

		IPage<AdminMarquee> iPage = service.findMarqueePageList(
				super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

		if (iPage == null) {
			marqueeList = new ArrayList<AdminMarquee>();
			return SUCCESS;
		}

		marqueeList = (List<AdminMarquee>) iPage.getData();
		for (AdminMarquee marquee : marqueeList) {
			String name = getNameByServerIds(marquee.getServerIds(),
					gameWebMap, serverMap);
			marquee.setServerNames(name);
		}

		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}

	public String getNameByServerIds(String serverIds,
			Map<Integer, GameWeb> gameWebMap,
			Map<Integer, Map<Integer, String>> serverMap) {
		String[] serverArr = serverIds.split(",");
		String serverNames = "";
		for (String str : serverArr) {
			String[] server = str.split("_");
			String name = "";
			if (server.length == 2) {
				int gameWebId = Integer.parseInt(server[0]);
				if (gameWebMap.containsKey(gameWebId)) {
					name = gameWebMap.get(gameWebId).getServerName() + "_";
				}

				if (serverMap.containsKey(gameWebId)) {
					Map<Integer, String> map = serverMap.get(gameWebId);
					if (map.containsKey(Integer.parseInt(server[1]))) {
						name = name + map.get(Integer.parseInt(server[1]));
					}

				}
			}

			if (serverNames.length() == 0) {
				serverNames = name;
			} else {
				serverNames = serverNames + "," + name;
			}
		}

		return serverNames;
	}

	public List<AdminMarquee> getMarqueeList() {
		return marqueeList;
	}

	public void setMarqueeList(List<AdminMarquee> marqueeList) {
		this.marqueeList = marqueeList;
	}

}
