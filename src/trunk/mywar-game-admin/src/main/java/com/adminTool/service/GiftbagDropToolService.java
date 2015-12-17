package com.adminTool.service;

import java.util.ArrayList;

import com.adminTool.bo.GiftbagDropTool;
import com.adminTool.dao.GiftbagDropToolDao;
import com.framework.common.IPage;

/**
 * 礼包掉落Service
 * 
 * @author yezp
 */
public class GiftbagDropToolService {

	private GiftbagDropToolDao giftbagDropToolDao;

	/**
	 * 根据礼包id获取礼包掉落物品列表
	 * 
	 * @param giftbagId
	 * @param dbSourceId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<GiftbagDropTool> findGiftbagDropToolPageList(int giftbagId,
			int dbSourceId, int toPage, int defaultPagesize) {
		giftbagDropToolDao.closeSession(dbSourceId);
		IPage<GiftbagDropTool> list = giftbagDropToolDao.findPage(
				"from GiftbagDropTool where giftbagId = " + giftbagId,
				new ArrayList<Object>(), defaultPagesize, toPage);

		return list;
	}

	/**
	 * 删除礼包掉落物品
	 * 
	 * @param dbSourceId
	 * @param giftbagId
	 */
	public void delGiftbagDropTool(int dbSourceId, int giftbagId) {
		giftbagDropToolDao.closeSession(dbSourceId);
		giftbagDropToolDao.execute("delete GiftbagDropTool where giftbagId = "
				+ giftbagId);
	}

	/**
	 * 添加礼包掉落
	 * 
	 * @param giftbagDropTool
	 * @param dbSourceId
	 */
	public void addGiftbagDropTool(GiftbagDropTool giftbagDropTool,
			int dbSourceId) {
		giftbagDropToolDao.save(giftbagDropTool, dbSourceId);
	}

	public GiftbagDropToolDao getGiftbagDropToolDao() {
		return giftbagDropToolDao;
	}

	public void setGiftbagDropToolDao(GiftbagDropToolDao giftbagDropToolDao) {
		this.giftbagDropToolDao = giftbagDropToolDao;
	}

}
