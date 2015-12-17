package com.adminTool.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.GiftCode;
import com.adminTool.service.GiftCodeService;
import com.adminTool.service.SystemGiftbagService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.constant.ServerConstant;
import com.system.service.GameWebService;

/**
 * 生成礼包码
 * 
 * @author yezp
 */
public class GiftCodeList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 6939590835512918308L;
	private Map<GameWeb, List<GiftCode>> giftcodeMap;
	private Map<GameWeb, Map<Integer, String>> typeAndNameMap;

	public String execute() {
		GameWebService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		GiftCodeService giftCodeservice = ServiceCacheFactory.getServiceCache()
				.getService(GiftCodeService.class);
		SystemGiftbagService giftbagService = ServiceCacheFactory
				.getServiceCache().getService(SystemGiftbagService.class);

		giftcodeMap = new HashMap<GameWeb, List<GiftCode>>();
		typeAndNameMap = new HashMap<GameWeb, Map<Integer, String>>();
		List<GameWeb> list = service.findGameWebList();

		for (GameWeb gameWeb : list) {
			int dbSourceId = gameWeb.getServerId()
					+ ServerConstant.GIFT_DBKEY_BEGIN_NUMBER;
			IPage<GiftCode> iPage = giftCodeservice.findGiftCodePageList(
					dbSourceId, super.getToPage(),
					ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

			List<GiftCode> giftList = (List<GiftCode>) iPage.getData();
			Map<Integer, String> map = giftbagService
					.getGiftbagTypeAndNameMap(dbSourceId);
			giftcodeMap.put(gameWeb, giftList);
			typeAndNameMap.put(gameWeb, map);
			
			super.setTotalPage(iPage.getTotalPage());
			super.setTotalSize(iPage.getTotalSize());
		}

		return SUCCESS;
	}

	public Map<GameWeb, List<GiftCode>> getGiftcodeMap() {
		return giftcodeMap;
	}

	public void setGiftcodeMap(Map<GameWeb, List<GiftCode>> giftcodeMap) {
		this.giftcodeMap = giftcodeMap;
	}

	public Map<GameWeb, Map<Integer, String>> getTypeAndNameMap() {
		return typeAndNameMap;
	}

	public void setTypeAndNameMap(
			Map<GameWeb, Map<Integer, String>> typeAndNameMap) {
		this.typeAndNameMap = typeAndNameMap;
	}

}
