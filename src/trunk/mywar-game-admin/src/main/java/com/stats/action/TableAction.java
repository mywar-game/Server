package com.stats.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.BattleLogService;
import com.log.service.UserEquipmentLogService;
import com.log.service.UserHeroLogService;
import com.log.service.UserResourceLogService;
import com.log.service.UserTreasureLogService;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;

public class TableAction extends ALDAdminStatsDatePageActionSupport {
private static final long serialVersionUID = 1L;
	private String servers;
	@Override
	public String execute() {
		boolean isAllServer = false;
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(servers!=null && !servers.equals("")){
			if(servers.equals("all")){
				isAllServer = true;
			}else{
				String[] serverArr = servers.split(",");
				for(int i=0;i<serverArr.length;i++){
					map.put(serverArr[i], 1);
				}
			}
		}else{
			isAllServer = true;
		}
		LogSystem.info("拆分英雄、道具、装备、资源、战斗表开始-----");
		TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		UserResourceLogService userResourceLogService = ServiceCacheFactory.getServiceCache().getService(UserResourceLogService.class);
		BattleLogService battleLogService = ServiceCacheFactory.getServiceCache().getService(BattleLogService.class);
		UserEquipmentLogService userEquipmentLogService = ServiceCacheFactory.getServiceCache().getService(UserEquipmentLogService.class);
		UserHeroLogService userHeroLogService = ServiceCacheFactory.getServiceCache().getService(UserHeroLogService.class);
		UserTreasureLogService userTreasureLogService = ServiceCacheFactory.getServiceCache().getService(UserTreasureLogService.class);
		List<TGameServer> gameServerList = gameServerService.findTGameServerList();
		for (TGameServer gameServer : gameServerList) {
			if(gameServer.getOfficial().intValue()==0){//测试服不管
				continue;
			}
			if(isAllServer || map.containsKey(gameServer.getServerId()+"")){
				CustomerContextHolder.setSystemNum(gameServer.getServerId());
				userResourceLogService.deal();
				battleLogService.deal();
				userEquipmentLogService.deal();
				userHeroLogService.deal();
				userTreasureLogService.deal();
			}
		}
		LogSystem.info("拆分英雄、道具、装备、资源、战斗表结束-----");
		return SUCCESS;
	}
	public String getServers() {
		return servers;
	}
	public void setServers(String servers) {
		this.servers = servers;
	}
	
}
