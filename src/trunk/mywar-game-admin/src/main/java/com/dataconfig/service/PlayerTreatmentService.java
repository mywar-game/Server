package com.dataconfig.service;

import com.dataconfig.bo.PlayerTreatment;
import com.dataconfig.dao.PlayerTreatmentDao;
import com.framework.common.DBSource;

public class PlayerTreatmentService {
	
	private PlayerTreatmentDao playerTreatmentDao;

	public PlayerTreatmentDao getPlayerTreatmentDao() {
		return playerTreatmentDao;
	}

	public void setPlayerTreatmentDao(PlayerTreatmentDao playerTreatmentDao) {
		this.playerTreatmentDao = playerTreatmentDao;
	}
	
	/**
	 * 保存玩家处理信息
	 * @param playerTreatment 玩家处理信息
	 */
	public  void savePlayerTreatment(PlayerTreatment playerTreatment) {
		playerTreatmentDao.save(playerTreatment, DBSource.CFG);
	}
}
