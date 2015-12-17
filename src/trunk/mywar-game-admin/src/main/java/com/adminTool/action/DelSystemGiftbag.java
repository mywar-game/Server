package com.adminTool.action;

import com.adminTool.bo.SystemGiftbag;
import com.adminTool.dao.impl.mysql.GiftCodeDaoMysqlImpl;
import com.adminTool.service.GiftCodeLogService;
import com.adminTool.service.GiftbagDropToolService;
import com.adminTool.service.GiftbagTypeLimitService;
import com.adminTool.service.SystemGiftbagService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.constant.ServerConstant;

/**
 * 删除系统礼包
 * 
 * @author yezp
 */
public class DelSystemGiftbag extends ALDAdminActionSupport {

	private static final long serialVersionUID = -5281281146492359698L;

	private Integer giftbagId;
	private Integer gameWebId;

	public void executeDel() {
		SystemGiftbagService giftbagService = ServiceCacheFactory
				.getServiceCache().getService(SystemGiftbagService.class);
		GiftbagDropToolService dropToolService = ServiceCacheFactory
				.getServiceCache().getService(GiftbagDropToolService.class);
		GiftbagTypeLimitService limitService = ServiceCacheFactory
				.getServiceCache().getService(GiftbagTypeLimitService.class);
		GiftCodeDaoMysqlImpl giftCodeDaoMysqlImpl = ServiceCacheFactory.getServiceCache()
				.getService(GiftCodeDaoMysqlImpl.class);
		GiftCodeLogService codeLogService = ServiceCacheFactory
				.getServiceCache().getService(GiftCodeLogService.class);

		Integer dbSourceId = gameWebId + ServerConstant.GIFT_DBKEY_BEGIN_NUMBER;
		SystemGiftbag giftbag = giftbagService.getSystemGiftbag(giftbagId,
				dbSourceId);

		giftbagService.delSystemGiftbag(giftbagId, dbSourceId);
		dropToolService.delGiftbagDropTool(dbSourceId, giftbagId);
		limitService.delGiftbagTypeLimit(giftbag.getType(), dbSourceId);
		
		String tableName = "gift_code_" + giftbag.getTablePrefix();		
		codeLogService.removeLogByGiftbagId(giftbagId, dbSourceId);
		giftCodeDaoMysqlImpl.delGiftCodeById(tableName, giftbagId);
	}

	public Integer getGiftbagId() {
		return giftbagId;
	}

	public void setGiftbagId(Integer giftbagId) {
		this.giftbagId = giftbagId;
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

}
