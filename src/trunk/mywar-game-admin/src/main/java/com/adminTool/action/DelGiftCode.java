package com.adminTool.action;

import com.adminTool.service.GiftCodeService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.constant.ServerConstant;

/**
 * 删除礼包码
 * 
 * @author yezp
 */
public class DelGiftCode extends ALDAdminActionSupport {

	private static final long serialVersionUID = -2682715066119200447L;

	private String code;
	private Integer gameWebId;

	public void executeDel() {
		GiftCodeService service = ServiceCacheFactory.getServiceCache()
				.getService(GiftCodeService.class);
		Integer dbSourceId = gameWebId + ServerConstant.GIFT_DBKEY_BEGIN_NUMBER;
		service.delGiftCode(code, dbSourceId);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

}
