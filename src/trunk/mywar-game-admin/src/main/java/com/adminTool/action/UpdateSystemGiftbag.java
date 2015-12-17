package com.adminTool.action;

import com.adminTool.bo.SystemGiftbag;
import com.adminTool.service.SystemGiftbagService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.constant.ServerConstant;

/**
 * 修改系统礼包
 * 
 * @author yezp
 */
public class UpdateSystemGiftbag extends ALDAdminActionSupport implements
		ModelDriven<SystemGiftbag> {

	private static final long serialVersionUID = -1506875356419409686L;
	private SystemGiftbag giftbag = new SystemGiftbag();
	private String isCommit = "F";
	private Integer gameWebId;

	public String execute() {
		SystemGiftbagService service = ServiceCacheFactory.getServiceCache()
				.getService(SystemGiftbagService.class);
		Integer dbSourceId = gameWebId + ServerConstant.GIFT_DBKEY_BEGIN_NUMBER;
		if ("F".equals(isCommit)) {
			giftbag = service.getSystemGiftbag(giftbag.getGiftbagId(),
					dbSourceId);
			return INPUT;
		}

		service.updateSystemGiftbag(giftbag, dbSourceId);
		
		giftbag = service.getSystemGiftbag(giftbag.getGiftbagId(),
				dbSourceId);
		return INPUT;
	}

	@Override
	public SystemGiftbag getModel() {
		// TODO Auto-generated method stub
		return giftbag;
	}

	public SystemGiftbag getGiftbag() {
		return giftbag;
	}

	public void setGiftbag(SystemGiftbag giftbag) {
		this.giftbag = giftbag;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

}
