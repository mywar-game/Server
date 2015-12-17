package com.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.system.bo.GameWeb;
import com.system.dao.GameWebDao;

/**
 * 区服务器
 * 
 * @author yezp
 */
public class GameWebService {

	private GameWebDao gameWebDao;

	public GameWebDao getGameWebDao() {
		return gameWebDao;
	}

	public void setGameWebDao(GameWebDao gameWebDao) {
		this.gameWebDao = gameWebDao;
	}

	/**
	 * 根据serverId获取区服务器
	 * 
	 * @param serverId
	 * @return
	 */
	public GameWeb getGameWebById(Integer serverId) {
		return gameWebDao.loadBy("serverId", serverId, DBSource.ADMIN);
	}

	/**
	 * 获取所有的区服务器
	 * 
	 * @return
	 */
	public List<GameWeb> findGameWebList() {
		gameWebDao.closeSession(DBSource.ADMIN);
		return gameWebDao.find("from GameWeb", DBSource.ADMIN);
	}

	/**
	 * 
	 * @return
	 */
	public Map<Integer, GameWeb> findGameWebServerIdMap() {
		Map<Integer, GameWeb> map = new HashMap<Integer, GameWeb>();
		List<GameWeb> list = findGameWebList();
		for (GameWeb gameWeb : list) {
			map.put(gameWeb.getServerId(), gameWeb);
		}
		
		return map;
	}
	
	/**
	 * 分页查找所有的区服务器
	 * 
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<GameWeb> findGameWebPageList(int toPage, int defaultPagesize) {
		gameWebDao.closeSession(DBSource.ADMIN);
		IPage<GameWeb> gameWebs = gameWebDao.findPage("from GameWeb",
				new ArrayList<Object>(), defaultPagesize, toPage);
		return gameWebs;
	}

}
