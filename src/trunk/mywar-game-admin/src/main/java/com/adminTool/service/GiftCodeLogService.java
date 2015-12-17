package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.GiftCode;
import com.adminTool.bo.GiftCodeLog;
import com.adminTool.dao.GiftCodeLogDao;

/**
 * 礼包领取日志
 * 
 * @author yezp
 */
public class GiftCodeLogService {

	private GiftCodeLogDao giftCodeLogDao;

	/**
	 * 批量删除礼包领取日志
	 * 
	 * @param list
	 * @param dbSourceId
	 */
	public void delGiftCodeLogBatch(List<GiftCodeLog> list, Integer dbSourceId) {
		giftCodeLogDao.closeSession(dbSourceId);
		giftCodeLogDao.removeBatch(list);
	}

	/**
	 * 根据礼包码批量删除礼包日志
	 * 
	 * @param list
	 * @param dbSourceId
	 */
	public void removeLogBatchByCode(List<GiftCode> list, Integer dbSourceId) {
		List<GiftCodeLog> logList = getGiftCodeLogList(list, dbSourceId);
		delGiftCodeLogBatch(logList, dbSourceId);
	}

	public List<GiftCodeLog> getGiftCodeLogList(List<GiftCode> l,
			Integer dbSourceId) {
		List<GiftCodeLog> list = new ArrayList<GiftCodeLog>();
		for (GiftCode code : l) {
			List<GiftCodeLog> logList = getGiftCodeLogList(code.getCode(),
					dbSourceId);
			list.addAll(logList);
		}

		return list;
	}
	
	public void removeLogByGiftbagId(Integer giftBagId, Integer dbSourceId) {
		String sql = "delete from GiftCodeLog where giftBagId = " + giftBagId;
		giftCodeLogDao.execute(sql);
	}

	/**
	 * 根据code获取礼包码的日志
	 * 
	 * @param code
	 * @param dbSourceId
	 * @return
	 */
	public List<GiftCodeLog> getGiftCodeLogList(String code, Integer dbSourceId) {
		return giftCodeLogDao.find("from GiftCodeLog where code = '" + code + "'",
				dbSourceId);
	}

	public GiftCodeLogDao getGiftCodeLogDao() {
		return giftCodeLogDao;
	}

	public void setGiftCodeLogDao(GiftCodeLogDao giftCodeLogDao) {
		this.giftCodeLogDao = giftCodeLogDao;
	}

}
