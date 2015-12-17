package com.adminTool.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.SystemGiftbag;
import com.adminTool.service.SystemGiftbagService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.constant.ServerConstant;
import com.system.service.GameWebService;

/**
 * 获取礼包列表
 * 
 * @author yezp
 */
public class SystemGiftbagList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 1262782768507967363L;

	private Map<GameWeb, List<SystemGiftbag>> giftbagMap;

	public String execute() {
		GameWebService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		SystemGiftbagService giftbagService = ServiceCacheFactory
				.getServiceCache().getService(SystemGiftbagService.class);

		giftbagMap = new HashMap<GameWeb, List<SystemGiftbag>>();
		List<GameWeb> list = service.findGameWebList();
		Integer dbSourceId = 1;
		GameWeb gameWeb = new GameWeb();
		for (GameWeb gw : list) {
			if (gw.getServerId() == 1) {
				dbSourceId = gw.getServerId() + ServerConstant.GIFT_DBKEY_BEGIN_NUMBER;
				gameWeb = gw;
			}	
		}

		IPage<SystemGiftbag> iPage = giftbagService.findGiftbagPageList(dbSourceId, super.getToPage(), 20);
		List<SystemGiftbag> giftList = (List<SystemGiftbag>) iPage.getData();
		giftbagMap.put(gameWeb, giftList); 

		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}

	public Map<GameWeb, List<SystemGiftbag>> getGiftbagMap() {
		return giftbagMap;
	}

	public void setGiftbagMap(Map<GameWeb, List<SystemGiftbag>> giftbagMap) {
		this.giftbagMap = giftbagMap;
	}

}
