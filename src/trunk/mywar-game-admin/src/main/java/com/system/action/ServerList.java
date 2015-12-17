package com.system.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.bo.Server;
import com.system.service.GameWebService;
import com.system.service.ServerService;

/**
 * 服务器列表
 * 
 * @author yezp
 */
public class ServerList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -4455735953878649821L;

	private List<GameWeb> gameWebList;
	private Map<GameWeb, Integer> sizeMap;
	private Map<String, Partner> partnerMap;
	private Map<GameWeb, Map<String, List<Server>>> gameWebMap;

	public String execute() {
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		ServerService serverService = ServiceCacheFactory.getServiceCache()
				.getService(ServerService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);

		sizeMap = new HashMap<GameWeb, Integer>();
		gameWebMap = new HashMap<GameWeb, Map<String, List<Server>>>();
		partnerMap = partnerService.getPartnerMap();

		gameWebList = gameWebService.findGameWebList();
		for (GameWeb gameWeb : gameWebList) {
			Map<String, List<Server>> partnerServerMap = new HashMap<String, List<Server>>();
			IPage<Server> iPage = serverService.findServerPageList(
					super.getToPage(),
					ALDAdminPageActionSupport.DEFAULT_PAGESIZE,
					gameWeb.getServerId());

			List<Server> serverList = (List<Server>) iPage.getData();
			
			// 按照创建时间排序
			Collections.sort(serverList, new Comparator<Server>() {

				@Override
				public int compare(Server o1, Server o2) {			
					return o1.getOpenTime().compareTo(o2.getOpenTime());
				}
				
			});
			
			int size = 1;
			for (Partner partner : partnerMap.values()) {
				String pid = partner.getPid().toString();
				List<Server> list = new ArrayList<Server>();

				for (Server sI : serverList) {
					if (pid.equals(sI.getPartenerId())) {
						list.add(sI);
					}
				}

				if (list.size() > 0) {
					size = size + list.size();
					partnerServerMap.put(pid, list);
				}
			}

			sizeMap.put(gameWeb, size);
			gameWebMap.put(gameWeb, partnerServerMap);

			super.setTotalPage(iPage.getTotalPage());
			super.setTotalSize(iPage.getTotalSize());
		}

		// IPage<Server> iPage = service.findServerPageList(super.getToPage(),
		// ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		//
		// if (iPage != null) {
		// partnerMap = partnerService.getPartnerMap();
		// serverList = (List<Server>) iPage.getData();
		// super.setTotalPage(iPage.getTotalPage());
		// super.setTotalSize(iPage.getTotalSize());
		// }

		return SUCCESS;
	}

	public List<GameWeb> getGameWebList() {
		return gameWebList;
	}

	public void setGameWebList(List<GameWeb> gameWebList) {
		this.gameWebList = gameWebList;
	}

	public Map<GameWeb, Integer> getSizeMap() {
		return sizeMap;
	}

	public void setSizeMap(Map<GameWeb, Integer> sizeMap) {
		this.sizeMap = sizeMap;
	}

	public Map<GameWeb, Map<String, List<Server>>> getGameWebMap() {
		return gameWebMap;
	}

	public void setGameWebMap(Map<GameWeb, Map<String, List<Server>>> gameWebMap) {
		this.gameWebMap = gameWebMap;
	}

	public Map<String, Partner> getPartnerMap() {
		return partnerMap;
	}

	public void setPartnerMap(Map<String, Partner> partnerMap) {
		this.partnerMap = partnerMap;
	}

}
