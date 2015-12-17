package com.system.service;

import java.util.List;

import com.system.bo.GameServerStatus;
import com.system.constant.ServerConstant;
import com.system.dao.GameServerStatusDao;

/**
 * 服务器列表Service
 * 
 * @author yezp
 */
public class GameServerStatusService {

	private GameServerStatusDao gameServerStatusDao;

	/**
	 * 根据服务器Id获取服务器状态
	 * 
	 * @param serverId
	 * @param dbSourceId
	 * @return
	 */
	public int getStatusByServerId(String serverId, int dbSourceId) {
		List<GameServerStatus> statusList = gameServerStatusDao.find(
				"from GameServerStatus where serverId = '" + serverId + "'",
				dbSourceId);
		if (statusList.size() == 1) {
			GameServerStatus status = statusList.get(0);
			return status.getStatus();
		}

		return ServerConstant.SERVER_STATUS_OPEN;
	}
	
	public GameServerStatus getServerStatusByServerId(String serverId, int dbSourceId) {
		List<GameServerStatus> statusList = gameServerStatusDao.find(
				"from GameServerStatus where serverId = '" + serverId + "'",
				dbSourceId);
		if (statusList.size() == 1) {
			GameServerStatus status = statusList.get(0);
			return status;
		}

		return null;
	}

	/**
	 * 设置服务器开启关闭状态
	 * 
	 * @param status
	 * @param dbSourceId
	 */
	public void setServerStatus(GameServerStatus status, int dbSourceId) {
		delServerStatus(status, dbSourceId);
		addServerStatus(status, dbSourceId);
	}

	/**
	 * 添加状态
	 * 
	 * @param status
	 * @param dbSourceId
	 */
	public void addServerStatus(GameServerStatus status, int dbSourceId) {
		gameServerStatusDao.save(status, dbSourceId);
	}

	/**
	 * 删除服务器状态
	 * 
	 * @param status
	 * @param dbSourceId
	 */
	public void delServerStatus(GameServerStatus status, int dbSourceId) {
		gameServerStatusDao.remove(status, dbSourceId);
	}

	public GameServerStatusDao getGameServerStatusDao() {
		return gameServerStatusDao;
	}

	public void setGameServerStatusDao(GameServerStatusDao gameServerStatusDao) {
		this.gameServerStatusDao = gameServerStatusDao;
	}

}
