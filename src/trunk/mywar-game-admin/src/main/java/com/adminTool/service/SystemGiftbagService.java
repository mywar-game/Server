package com.adminTool.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.SystemGiftbag;
import com.adminTool.dao.SystemGiftbagDao;
import com.framework.common.IPage;

/**
 * 系统礼包Service
 * 
 * @author yezp
 */
public class SystemGiftbagService {

	private SystemGiftbagDao systemGiftbagDao;

	/**
	 * 分页查找
	 * 
	 * @param dbSourceId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<SystemGiftbag> findGiftbagPageList(int dbSourceId, int toPage,
			int defaultPagesize) {
		systemGiftbagDao.closeSession(dbSourceId);
		IPage<SystemGiftbag> giftbags = systemGiftbagDao.findPage(
				"from SystemGiftbag", new ArrayList<Object>(), defaultPagesize,
				toPage);
		return giftbags;
	}

	public int count(int dbSourceId) {
		systemGiftbagDao.closeSession(dbSourceId);
		return systemGiftbagDao.count("FROM SystemGiftbag", new ArrayList<Object>());
	}
	/**
	 * 查询大类型最大的值
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public SystemGiftbag findLastSystemGiftbag(Integer dbSourceId) {
		List<SystemGiftbag> giftbagList = systemGiftbagDao.find(
				"from  SystemGiftbag order by type desc limit 1", dbSourceId);
		if (giftbagList == null || giftbagList.size() <= 0) {
			return null;
		}

		return giftbagList.get(0);
	}

	/**
	 * 根据类型获取礼包
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public Map<Integer, SystemGiftbag> getSystemGiftbagMap(Integer dbSourceId) {
		Map<Integer, SystemGiftbag> map = new HashMap<Integer, SystemGiftbag>();
		List<SystemGiftbag> list = getGiftbagList(dbSourceId);
		for (SystemGiftbag bag : list) {
			map.put(bag.getType(), bag);
		}

		return map;
	}

	/**
	 * 根据类型获取礼包名称
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public Map<Integer, String> getGiftbagTypeAndNameMap(Integer dbSourceId) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<SystemGiftbag> list = getGiftbagList(dbSourceId);
		for (SystemGiftbag bag : list) {
			map.put(bag.getType(), bag.getName());
		}

		return map;
	}
	
	/**
	 * 根据Id获取礼包名称Map
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public Map<Integer, String> getGiftbagIdAndNameMap(Integer dbSourceId) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<SystemGiftbag> list = getGiftbagList(dbSourceId);
		for (SystemGiftbag bag : list) {
			map.put(bag.getGiftbagId(), bag.getName());
		}

		return map;
	}

	/**
	 * 获取礼包列表
	 * 
	 * @param DBSourceId
	 * @return
	 */
	public List<SystemGiftbag> getGiftbagList(int dbSourceId) {
		return systemGiftbagDao.find("from SystemGiftbag", dbSourceId);
	}

	/**
	 * 添加系统礼包
	 * 
	 * @param giftbag
	 * @param dbSourceId
	 */
	public void addSystemGiftbag(SystemGiftbag giftbag, Integer dbSourceId) {
		systemGiftbagDao.save(giftbag, dbSourceId);
	}

	/**
	 * 删除系统礼包
	 * 
	 * @param giftbagId
	 * @param dbSourceId
	 */
	public void delSystemGiftbag(Integer giftbagId, Integer dbSourceId) {
		SystemGiftbag giftbag = getSystemGiftbag(giftbagId, dbSourceId);
		systemGiftbagDao.remove(giftbag, dbSourceId);
	}

	/**
	 * 获取系统礼包
	 * 
	 * @param giftbagId
	 * @param dbSourceId
	 * @return
	 */
	public SystemGiftbag getSystemGiftbag(Integer giftbagId, Integer dbSourceId) {
		return systemGiftbagDao.loadBy("giftbagId", giftbagId, dbSourceId);
	}

	/**
	 * 修改系统礼包
	 * 
	 * @param giftbag
	 * @param dbSourceId
	 */
	public void updateSystemGiftbag(SystemGiftbag giftbag, Integer dbSourceId) {
		systemGiftbagDao.update(giftbag, dbSourceId);
	}

	public SystemGiftbagDao getSystemGiftbagDao() {
		return systemGiftbagDao;
	}

	public void setSystemGiftbagDao(SystemGiftbagDao systemGiftbagDao) {
		this.systemGiftbagDao = systemGiftbagDao;
	}

}
