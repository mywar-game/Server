package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.IPage;
import com.system.bo.BlackInfo;
import com.system.dao.BlackInfoDao;

/**
 * 黑名单Service
 * 
 * @author yezp
 */
public class BlackInfoService {

	private BlackInfoDao blackInfoDao;

	/**
	 * 分页查找黑名单
	 * 
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public List<BlackInfo> findBlackInfoPageList(int toPage, int defaultPagesize, Integer dbSourceId) {
		blackInfoDao.closeSession(dbSourceId);
		List<BlackInfo> imeiList = blackInfoDao.find("from BlackInfo order by id desc",
				new ArrayList<Object>());

		return imeiList;
	}

	/**
	 * 获取黑名单信息
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public List<BlackInfo> getBlackInfoListByDBSource(Integer dbSourceId) {
		return blackInfoDao.find("from BlackInfo", dbSourceId);
	}

	/**
	 * 添加黑名单
	 * 
	 * @param 
	 */
	public void addInfo(BlackInfo info, Integer dbSourceId) {
		blackInfoDao.save(info, dbSourceId);
	}

	/**
	 * 删除黑名单
	 * 
	 * @param id
	 */
	public void delInfo(Integer id, Integer dbSourceId) {
		BlackInfo info = getInfoById(id, dbSourceId);
		blackInfoDao.remove(info, dbSourceId);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public BlackInfo getInfoById(Integer id, Integer dbSourceId) {
		return blackInfoDao.loadBy("id", id, dbSourceId);
	}

	/**
	 * 修改IMEI
	 * 
	 * @param imei
	 */
	public void updateInfo(BlackInfo info, Integer dbSourceId) {
		blackInfoDao.update(info, dbSourceId);
	}

	public BlackInfoDao getBlackInfoDao() {
		return blackInfoDao;
	}

	public void setBlackInfoDao(BlackInfoDao blackInfoDao) {
		this.blackInfoDao = blackInfoDao;
	}

}
