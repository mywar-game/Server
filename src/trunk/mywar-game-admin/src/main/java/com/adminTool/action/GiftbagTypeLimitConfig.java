package com.adminTool.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.GiftbagTypeLimit;
import com.adminTool.bo.SystemGiftbag;
import com.adminTool.constant.AdminToolConstant;
import com.adminTool.service.GiftbagTypeLimitService;
import com.adminTool.service.SystemGiftbagService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.GameWeb;
import com.system.bo.GameWebServer;
import com.system.bo.TGameServer;
import com.system.constant.ServerConstant;
import com.system.service.GameWebServerService;
import com.system.service.GameWebService;
import com.system.service.TGameServerService;

/**
 * 礼包类型领取次数限制
 * 
 * @author yezp
 */
public class GiftbagTypeLimitConfig extends ALDAdminActionSupport implements
		ModelDriven<GiftbagTypeLimit> {

	private static final long serialVersionUID = -6771555523430130918L;
	private String isCommit = "F";
	private GiftbagTypeLimit giftbagTypeLimit = new GiftbagTypeLimit();
//	private List<TGameServer> serverList;
	private List<GameWebServer> gameWebServerList = new ArrayList<GameWebServer>();
	private Map<String, String> serverMap;

	private Integer giftBagType;
	private String giftBagName;
	private Integer gameWebId;
	private Integer allServer;
	private String[] sid;
	private String[] serverIds;
	

	// 修改规则
	private List<TGameServer> tgameServerList;
	private Map<GameWeb, List<GameWebServer>> gameWebMap = new HashMap<GameWeb, List<GameWebServer>>();

	public String execute() {
		GiftbagTypeLimitService service = ServiceCacheFactory.getServiceCache()
				.getService(GiftbagTypeLimitService.class);
		Integer dbSourceId = gameWebId + ServerConstant.GIFT_DBKEY_BEGIN_NUMBER;

		if (isCommit.equals("F")) {
			TGameServerService serverService = ServiceCacheFactory
					.getServiceCache().getService(TGameServerService.class);
			SystemGiftbagService systemGiftbagService = ServiceCacheFactory
					.getServiceCache().getService(SystemGiftbagService.class);
			GameWebServerService gameWebServerService = ServiceCacheFactory
					.getServiceCache().getService(GameWebServerService.class);
			// 修改规则
			TGameServerService tgs = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
			GameWebService gameWebService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
			
			Map<Integer, SystemGiftbag> map = systemGiftbagService
					.getSystemGiftbagMap(dbSourceId);

			int type = giftbagTypeLimit.getGiftBagType();
//			serverList = serverService
//					.findTGameServerListByGameWebId(gameWebId);
			
			// 修改规则
			List<GameWeb> gameWebList = gameWebService.findGameWebList();

			for (GameWeb gameWeb : gameWebList) {
				int tempGameWebId = gameWeb.getServerId();
				List<GameWebServer> tempList = gameWebServerService.getServerListByDBSource(tempGameWebId);
				gameWebMap.put(gameWeb, tempList);
			}
			
			giftbagTypeLimit = service
					.findOneGiftbagTypeLimit(type, dbSourceId);
			if (map.containsKey(type)) {
				giftBagName = map.get(type).getName();
			}

			// 是否为全服
			if (giftbagTypeLimit == null
					|| (giftbagTypeLimit != null && (giftbagTypeLimit
							.getServerIds() == null || giftbagTypeLimit
							.getServerIds().equals("")))) {
				allServer = AdminToolConstant.SERVER;
			} else {
				allServer = AdminToolConstant.USER;
			}

			serverMap = new HashMap<String, String>();
			if (giftbagTypeLimit != null
					&& giftbagTypeLimit.getServerIds() != null
					&& !giftbagTypeLimit.getServerIds().equals("")) {
				String serverIds = giftbagTypeLimit.getServerIds();
				String[] sidArr = serverIds.split(",");
				for (String serverId : sidArr) {
					serverMap.put(serverId, serverId);
				}
			}

			return INPUT;
		}

		String serverIds2 = "";
		if ((allServer == null || allServer == AdminToolConstant.USER)
				&& sid != null) {
			for (String serverId : sid) {
				if (serverIds2.length() == 0) {
					serverIds2 = serverId;
				} else {
					serverIds2 = serverIds2 + "," + serverId;
				}
			}
		}

		giftbagTypeLimit.setServerIds(serverIds2);
		service.delGiftbagTypeLimit(giftbagTypeLimit.getGiftBagType(),
				dbSourceId);
		service.addGiftbagTypeLimit(giftbagTypeLimit, dbSourceId);

		TGameServerService serverService = ServiceCacheFactory
				.getServiceCache().getService(TGameServerService.class);
		SystemGiftbagService systemGiftbagService = ServiceCacheFactory
				.getServiceCache().getService(SystemGiftbagService.class);
		GameWebServerService gameWebServerService = ServiceCacheFactory
				.getServiceCache().getService(GameWebServerService.class);
		// 修改规则
		TGameServerService tgs = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
		
		Map<Integer, SystemGiftbag> map = systemGiftbagService
				.getSystemGiftbagMap(dbSourceId);

		int type = giftbagTypeLimit.getGiftBagType();
//		serverList = serverService
//				.findTGameServerListByGameWebId(gameWebId);
		
		// 修改规则
		List<GameWeb> gameWebList = gameWebService.findGameWebList();

		for (GameWeb gameWeb : gameWebList) {
			int tempGameWebId = gameWeb.getServerId();
			List<GameWebServer> tempList = gameWebServerService.getServerListByDBSource(tempGameWebId);
			gameWebMap.put(gameWeb, tempList);
		}
		
		giftbagTypeLimit = service
				.findOneGiftbagTypeLimit(type, dbSourceId);
		if (map.containsKey(type)) {
			giftBagName = map.get(type).getName();
		}

		// 是否为全服
		if (giftbagTypeLimit == null
				|| (giftbagTypeLimit != null && (giftbagTypeLimit
						.getServerIds() == null || giftbagTypeLimit
						.getServerIds().equals("")))) {
			allServer = AdminToolConstant.SERVER;
		} else {
			allServer = AdminToolConstant.USER;
		}

		serverMap = new HashMap<String, String>();
		if (giftbagTypeLimit != null
				&& giftbagTypeLimit.getServerIds() != null
				&& !giftbagTypeLimit.getServerIds().equals("")) {
			String serverIds = giftbagTypeLimit.getServerIds();
			String[] sidArr = serverIds.split(",");
			for (String serverId : sidArr) {
				serverMap.put(serverId, serverId);
			}
		}

		return INPUT;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Integer getAllServer() {
		return allServer;
	}

	public void setAllServer(Integer allServer) {
		this.allServer = allServer;
	}

	public String[] getSid() {
		return sid;
	}

	public void setSid(String[] sid) {
		this.sid = sid;
	}

	public String getGiftBagName() {
		return giftBagName;
	}

	public void setGiftBagName(String giftBagName) {
		this.giftBagName = giftBagName;
	}

//	public List<TGameServer> getServerList() {
//		return serverList;
//	}
//
//	public void setServerList(List<TGameServer> serverList) {
//		this.serverList = serverList;
//	}

	public List<GameWebServer> getGameWebServerList() {
		return gameWebServerList;
	}

	public void setGameWebServerList(List<GameWebServer> gameWebServerList) {
		this.gameWebServerList = gameWebServerList;
	}

	public Map<String, String> getServerMap() {
		return serverMap;
	}

	public void setServerMap(Map<String, String> serverMap) {
		this.serverMap = serverMap;
	}

	public GiftbagTypeLimit getGiftbagTypeLimit() {
		return giftbagTypeLimit;
	}

	public void setGiftbagTypeLimit(GiftbagTypeLimit giftbagTypeLimit) {
		this.giftbagTypeLimit = giftbagTypeLimit;
	}

	public Integer getGiftBagType() {
		return giftBagType;
	}

	public void setGiftBagType(Integer giftBagType) {
		this.giftBagType = giftBagType;
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

	@Override
	public GiftbagTypeLimit getModel() {
		// TODO Auto-generated method stub
		return giftbagTypeLimit;
	}

	public List<TGameServer> getTgameServerList() {
		return tgameServerList;
	}

	public void setTgameServerList(List<TGameServer> tgameServerList) {
		this.tgameServerList = tgameServerList;
	}

	public Map<GameWeb, List<GameWebServer>> getGameWebMap() {
		return gameWebMap;
	}

	public void setGameWebMap(Map<GameWeb, List<GameWebServer>> gameWebMap) {
		this.gameWebMap = gameWebMap;
	}

	public String[] getServerIds() {
		return serverIds;
	}

	public void setServerIds(String[] serverIds) {
		this.serverIds = serverIds;
	}
}
