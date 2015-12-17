package com.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.system.bo.GameWebServer;
import com.system.dao.GameWebServerDao;

/**
 * GameWebServer Service
 * 
 * @author yezp
 */
public class GameWebServerService {

	private GameWebServerDao gameWebServerDao;

	public GameWebServerDao getGameWebServerDao() {
		return gameWebServerDao;
	}

	public void setGameWebServerDao(GameWebServerDao gameWebServerDao) {
		this.gameWebServerDao = gameWebServerDao;
	}

	/**
	 * 查询GameWeb列表
	 * 
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<GameWebServer> findGameWebServerPageList(int toPage,
			int defaultPagesize, Integer dbSourceId) {
		gameWebServerDao.closeSession(dbSourceId);
		IPage<GameWebServer> gameWebServerList = gameWebServerDao.findPage(
				"from GameWebServer", new ArrayList<Object>(), defaultPagesize,
				toPage);

		return gameWebServerList;
	}

	/**
	 * 获取GameWebMap
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public Map<String, GameWebServer> getGameWebServerMap(int dbSourceId) {
		Map<String, GameWebServer> map = new HashMap<String, GameWebServer>();
		List<GameWebServer> list = getServerListByDBSource(dbSourceId);
		for (GameWebServer server : list) {
			map.put(server.getServerId(), server);
		}

		return map;
	}

	/**
	 * 添加gameWeb服务器
	 * 
	 * @param gameWebServer
	 */
	public void addGameWebServer(GameWebServer gameWebServer, Integer dbSourceId) {
		gameWebServerDao.save(gameWebServer, dbSourceId);
	}

	/**
	 * 更新gameWeb服务器
	 * 
	 * @param gameWebServer
	 */
	public void updateGameWebServer(GameWebServer gameWebServer,
			Integer dbSourceId) {
		gameWebServerDao.update(gameWebServer, dbSourceId);
	}

	/**
	 * 删除服务
	 * 
	 * @param serverId
	 */
	public void delGameWebServer(String serverId, Integer dbSourceId) {
		gameWebServerDao
				.remove(findOneServer(serverId, dbSourceId), dbSourceId);
	}

	/**
	 * 根据条件查找服务
	 * 
	 * @param serverId
	 * @return
	 */
	public GameWebServer findOneServer(String serverId, Integer dbSourceId) {
		return this.gameWebServerDao.loadBy("serverId", serverId, dbSourceId);
	}

	/**
	 * 查找所有数据
	 * 
	 * @return
	 */
	public List<GameWebServer> findAllServer(Integer dbSourceId) {
		return getServerListByDBSource(dbSourceId);
	}

	/**
	 * 查找服务ID
	 * 
	 * @return
	 */
	public List<String> findAllServerId(Integer dbSourceId) {
		List<GameWebServer> serverList = findAllServer(dbSourceId);

		List<String> serverIdList = new ArrayList<String>();
		for (GameWebServer server : serverList) {
			serverIdList.add(server.getServerId());
		}

		return serverIdList;
	}

	/**
	 * 根据数据源，查找所属的服务器
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public List<GameWebServer> getServerListByDBSource(Integer dbSourceId) {
		gameWebServerDao.closeSession(dbSourceId);
		return this.gameWebServerDao.find("from GameWebServer", dbSourceId);
	}
	
	/**
	 * 获取以_stats结尾的表格
	 * @param serverId
	 * @return
	 */
	public List<Object> getEndStatsTables(String serverId) {
		// gameWebServerDao.closeSession(DBSource.ADMIN);
		// return this.gameWebServerDao.execute("select * from )
		List<Object> list = this.gameWebServerDao.findSQL_("select * from end_stats_table");
		return list;
	}
	
	/**
	 * 清除以_stats结束的服务器数据
	 * @param tableName
	 * @param serverId
	 */
	public void clearEndWithStatsTable(String tableName, String serverId) {
		// this.gameWebServerDao.closeSession(DBSource.ADMIN);
		this.gameWebServerDao.executeSQL_("delete from " + tableName + " where SYS_NUM = " +  Integer.valueOf(serverId) + "");
	}
}
