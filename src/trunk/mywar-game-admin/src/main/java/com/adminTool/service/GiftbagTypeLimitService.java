package com.adminTool.service;

import com.adminTool.bo.GiftbagTypeLimit;
import com.adminTool.dao.GiftbagTypeLimitDao;

/**
 * 礼包领取次数
 * 
 * @author yezp
 */
public class GiftbagTypeLimitService {

	private GiftbagTypeLimitDao giftbagTypeLimitDao;

	/**
	 * 获取礼包领取
	 * 
	 * @param giftBagType
	 * @param dbSourceId
	 * @return
	 */
	public GiftbagTypeLimit findOneGiftbagTypeLimit(Integer giftBagType,
			Integer dbSourceId) {
		return giftbagTypeLimitDao.loadBy("giftBagType", giftBagType,
				dbSourceId);
	}

	/**
	 * 删除礼包领取次数配置
	 * 
	 * @param giftBagType
	 * @param dbSourceId
	 */
	public void delGiftbagTypeLimit(Integer giftBagType, Integer dbSourceId) {
		giftbagTypeLimitDao.closeSession(dbSourceId);
		giftbagTypeLimitDao
				.execute("delete GiftbagTypeLimit where giftBagType = "
						+ giftBagType);
	}

	/**
	 * 添加礼包领取次数配置
	 * 
	 * @param giftbagTypeLimit
	 * @param dbSourceId
	 */
	public void addGiftbagTypeLimit(GiftbagTypeLimit giftbagTypeLimit,
			Integer dbSourceId) {
		giftbagTypeLimitDao.save(giftbagTypeLimit, dbSourceId);
	}

	public GiftbagTypeLimitDao getGiftbagTypeLimitDao() {
		return giftbagTypeLimitDao;
	}

	public void setGiftbagTypeLimitDao(GiftbagTypeLimitDao giftbagTypeLimitDao) {
		this.giftbagTypeLimitDao = giftbagTypeLimitDao;
	}

}
