package com.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.system.bo.TGameServer;
import com.system.dao.TGameServerDao;

public class TGameServerService {

	private TGameServerDao tGameServerDao;

	public TGameServerDao gettGameServerDao() {
		return tGameServerDao;
	}

	public void settGameServerDao(TGameServerDao tGameServerDao) {
		this.tGameServerDao = tGameServerDao;
	}

	/**
	 * 查询服务器配置分页列表
	 * 
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<TGameServer> findTGameServerPageList(int toPage,
			int defaultPagesize) {
		tGameServerDao.closeSession(DBSource.ADMIN);
		IPage<TGameServer> tGameServers = tGameServerDao.findPage(
				"from TGameServer", new ArrayList<Object>(), defaultPagesize,
				toPage);
		return tGameServers;
	}
	
	public List<TGameServer> findTGameServerList(int toPage,
			int defaultPagesize) {
		tGameServerDao.closeSession(DBSource.ADMIN);
		List<TGameServer> tGameServers = tGameServerDao.findAll();
//		IPage<TGameServer> tGameServers = tGameServerDao.findPage(
//				"from TGameServer", new ArrayList<Object>(), defaultPagesize,
//				toPage);
		return tGameServers; 
	}

	/**
	 * 查询服务器配置分页列表
	 * 
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public List<TGameServer> findTGameServerPageList() {
		tGameServerDao.closeSession(DBSource.ADMIN);
		List<TGameServer> tGameServers = tGameServerDao.find(
				"from TGameServer", new ArrayList<Object>());
		return tGameServers;
	}

	/**
	 * 查询所有服务器配置列表
	 * 
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public List<TGameServer> findTGameServerList() {
		List<TGameServer> list = tGameServerDao.find("from TGameServer",
				DBSource.ADMIN);
		return list;
	}

	/**
	 * 
	 * @param gameWebId
	 * @return
	 */
	public List<TGameServer> findTGameServerListByGameWebId(int gameWebId) {
		return tGameServerDao.find("from TGameServer where gameWebServerId = "
				+ gameWebId, DBSource.ADMIN);
	}

	/**
	 * 查询服务器id-名称map
	 * 
	 * @return
	 */
	public Map<Integer, String> findServerIdNameMap() {
		List<TGameServer> list = tGameServerDao.find("from TGameServer",
				DBSource.ADMIN);
		Map<Integer, String> map = new HashMap<Integer, String>();
		if (list != null && list.size() > 0) {
			for (TGameServer server : list) {
				map.put(server.getServerId(), server.getServerAlias());
			}
		}
		return map;
	}

	/**
	 * 查询正式服务器id-名称map
	 * 
	 * @return
	 */
	public Map<Integer, String> findServerIdNameMapForOfficialServer() {
		List<TGameServer> list = tGameServerDao.find(
				"from TGameServer where official=1", DBSource.ADMIN);
		Map<Integer, String> map = new HashMap<Integer, String>();
		if (list != null && list.size() > 0) {
			for (TGameServer server : list) {
				map.put(server.getServerId(), server.getServerAlias());
			}
		}
		return map;
	}

	/**
	 * 查询服务器组数据
	 * 
	 * @param groupNum
	 * @return
	 */
	public TGameServer findOneTGameServer(Integer serverID) {
		return tGameServerDao.loadBy("serverId", serverID, DBSource.ADMIN);
	}

	/**
	 * 添加服务器组数据
	 * 
	 * @param gameServer
	 */
	public void addOneTGameServer(TGameServer gameServer) {
		tGameServerDao.save(gameServer, DBSource.ADMIN);
	}

	/**
	 * 删除服务器组数据
	 * 
	 * @param groupNum
	 */
	public void delOneTGameServer(Integer serverID) {
		tGameServerDao.remove(this.findOneTGameServer(serverID), DBSource.ADMIN);
	}

	/**
	 * 更新服务器组数据
	 * 
	 * @param gameServer
	 */
	public void updateOneTGameServer(TGameServer gameServer) {
		tGameServerDao.update(gameServer, DBSource.ADMIN);
	}

	/**
	 * 查询最后一条记录
	 */
	public TGameServer findLastTGameServer() {
		List<TGameServer> tGameServers = tGameServerDao.find(
				"FROM TGameServer ORDER BY serverId DESC LIMIT 1 ",
				DBSource.ADMIN);
		if (tGameServers == null || tGameServers.size() <= 0) {
			return null;
		}
		return tGameServers.get(0);
	}

}
