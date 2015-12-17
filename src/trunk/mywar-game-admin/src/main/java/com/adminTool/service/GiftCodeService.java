package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.GiftCode;
import com.adminTool.dao.GiftCodeDao;
import com.framework.common.IPage;

/**
 * 礼包码Service
 * 
 * @author yezp
 */
public class GiftCodeService {

	private GiftCodeDao giftCodeDao;

	/**
	 * 分页查找
	 * 
	 * @param dbSourceId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<GiftCode> findGiftCodePageList(int dbSourceId, int toPage,
			int defaultPagesize) {
		giftCodeDao.closeSession(dbSourceId);
		IPage<GiftCode> giftCodeList = giftCodeDao.findPage("from GiftCode",
				new ArrayList<Object>(), defaultPagesize, toPage);
		return giftCodeList;
	}

	/**
	 * 获取礼包码
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public List<GiftCode> getGiftCodeList(int dbSourceId) {
		return giftCodeDao.find("from GiftCode", dbSourceId);
	}

	/**
	 * 根据礼包Id查找礼包码
	 * 
	 * @param giftBagId
	 * @param dbSourceId
	 * @return
	 */
	public List<GiftCode> getGiftCodeListById(int giftBagId, int dbSourceId) {
		return giftCodeDao.find("from GiftCode where giftBagId = " + giftBagId + " order by createdTime",
				dbSourceId);
	}

	/**
	 * 生成礼包码
	 * 
	 * @param giftCode
	 */
	public void addGiftCode(GiftCode giftCode, int dbSourceId) {
		giftCodeDao.save(giftCode, dbSourceId);
	}

	/**
	 * 批量生成礼包码
	 * 
	 * @param list
	 * @param dbSourceId
	 */
	public void addGiftCodeBatch(List<GiftCode> list, int dbSourceId) {
		giftCodeDao.saveBatch(list, dbSourceId);
	}

	public void addGiftCodeBatch(String sql, int dbSourceId) {
		giftCodeDao.closeSession(dbSourceId);
		giftCodeDao.execute(sql);
	}
	
	/**
	 * 删除礼包码
	 * 
	 * @param code
	 * @param dbSourceId
	 */
	public void delGiftCode(String code, int dbSourceId) {
		giftCodeDao.closeSession(dbSourceId);
		giftCodeDao.execute("delete from GiftCode where code = '" + code + "'");
	}

	/**
	 * 根据类型查找礼包码数量
	 * 
	 * @param giftBagId
	 * @param dbSourceId
	 * @return
	 */
	public int getGiftCodeCountById(Integer giftBagId, int dbSourceId) {
		giftCodeDao.closeSession(dbSourceId);
		StringBuffer sql = new StringBuffer();
		sql.append("select COUNT(code) as num from gift_code where gift_bag_id = "
				+ giftBagId);
		List<Object> list = giftCodeDao.findSQL_(sql.toString());

		int count = 0;
		if (list.size() == 1) {
			count = Integer.parseInt(((Object) list.get(0)).toString());
		}

		return count;
	}

	/**
	 * 获取礼包码个数
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public int getGiftCodeCount(int dbSourceId) {
		giftCodeDao.closeSession(dbSourceId);
		StringBuffer sql = new StringBuffer();
		sql.append("select COUNT(code) as num from gift_code");
		List<Object> list = giftCodeDao.findSQL_(sql.toString());

		int count = 0;
		if (list.size() == 1) {
			count = Integer.parseInt(((Object) list.get(0)).toString());
		}

		return count;
	}

	/**
	 * 根据礼包类型删除礼包码
	 * 
	 * @param type
	 * @param subType
	 * @param dbSourceId
	 */
	public void delGiftCodeByType(Integer giftBagId, int dbSourceId) {
		giftCodeDao.closeSession(dbSourceId);
		giftCodeDao.execute("delete from GiftCode where giftBagId = "
				+ giftBagId);
	}

	public GiftCodeDao getGiftCodeDao() {
		return giftCodeDao;
	}

	public void setGiftCodeDao(GiftCodeDao giftCodeDao) {
		this.giftCodeDao = giftCodeDao;
	}

}
