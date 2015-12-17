package com.fantingame.game.gamecenter.dao.impl.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fantingame.game.gamecenter.dao.ServerListDao;
import com.fantingame.game.gamecenter.dao.impl.mysql.ServerListDaoMysqlImpl;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.model.ServerConfig;
import com.fantingame.game.gamecenter.model.ServerListConfig;

public class ServerListDaoCacheImpl implements ServerListDao {

	private Map<String,List<GameServer>> cache;
	
	private List<GameServer> serverlist = new ArrayList<GameServer>();
	
	private ServerListDaoMysqlImpl serverListDaoMysqlImpl;
	@Override
	public List<GameServer> getServerListByPartenerId(String partenerId) {
		List<GameServer> list =  cache.get(partenerId);
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return serverlist;
		}
	}

	@Override
	public GameServer getServerByServerIdAndPartenerId(String serverId,
			String partenerId) {
		List<GameServer> list = cache.get(partenerId);
		if(list!=null&&list.size()>0){
			for(GameServer gameServer:list){
				if(gameServer.getServerId().equals(serverId)){
					return gameServer;
				}
			}
		}else{
			for(GameServer gameServer:serverlist){
				if(serverId.equals(gameServer.getServerId())){
					return gameServer;
				}
			}
		}
		return null;
	}

	@Override
	public void reload() {
		init();
	}

	public void init(){
		List<ServerListConfig> allList = serverListDaoMysqlImpl.getAll();
		List<ServerConfig> serverConfigList = serverListDaoMysqlImpl.getAllServer();
		serverlist.clear();
		Map<String,ServerConfig> serverConfigMap = new HashMap<String,ServerConfig>();
		for(ServerConfig serverConfig:serverConfigList){
			GameServer tempGameServer = new GameServer();
			tempGameServer.setApiPort(serverConfig.getApiPort());
			tempGameServer.setOpenTime(serverConfig.getOpenTime());
			tempGameServer.setServerId(serverConfig.getServerId());
			tempGameServer.setServerName(serverConfig.getServerName());
			tempGameServer.setServerPort(serverConfig.getServerPort());
			tempGameServer.setServerUrl(serverConfig.getServerUrl());
			tempGameServer.setStatus(serverConfig.getStatus());
			tempGameServer.setRealServerId(serverConfig.getServerId());
			serverlist.add(tempGameServer);
			serverConfigMap.put(serverConfig.getServerId(), serverConfig);
		}
		cache = new ConcurrentHashMap<String,List<GameServer>>();
		if(allList!=null&&allList.size()>0){
			for(ServerListConfig serverListConfig:allList){
				ServerConfig tempConfig = serverConfigMap.get(serverListConfig.getServerId());
				GameServer tempGameServer = new GameServer();
				tempGameServer.setApiPort(tempConfig.getApiPort());
				tempGameServer.setOpenTime(serverListConfig.getOpenTime());
				tempGameServer.setPartenerId(serverListConfig.getPartenerId());
				tempGameServer.setServerId(serverListConfig.getAliaServerId());
				tempGameServer.setServerName(serverListConfig.getServerName());
				tempGameServer.setServerPort(tempConfig.getServerPort());
				tempGameServer.setServerUrl(tempConfig.getServerUrl());
				tempGameServer.setStatus(serverListConfig.getStatus());
				tempGameServer.setRealServerId(serverListConfig.getServerId());
				if(cache.containsKey(tempGameServer.getPartenerId())){
					cache.get(tempGameServer.getPartenerId()).add(tempGameServer);
				}else{
					List<GameServer> tempList = new ArrayList<GameServer>();
					tempList.add(tempGameServer);
					cache.put(tempGameServer.getPartenerId(), tempList);
				}
			}
		}
	}

	public ServerListDaoMysqlImpl getServerListDaoMysqlImpl() {
		return serverListDaoMysqlImpl;
	}

	public void setServerListDaoMysqlImpl(
			ServerListDaoMysqlImpl serverListDaoMysqlImpl) {
		this.serverListDaoMysqlImpl = serverListDaoMysqlImpl;
	}

	@Override
	public List<GameServer> getAllServerList() {
		return serverlist;
	}
	
	
}
