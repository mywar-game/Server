package com.system.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.BlackInfo;
import com.system.bo.GameWeb;
import com.system.service.BlackInfoService;
import com.system.service.GameWebService;

/**
 * 获取黑名单列表
 * 
 * @author yezp
 */
public class BlackList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -8417124778822874510L;

	private Map<GameWeb, List<BlackInfo>> infoMap;

	public String execute() {
		GameWebService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		BlackInfoService infoService = ServiceCacheFactory.getServiceCache()
				.getService(BlackInfoService.class);

		infoMap = new HashMap<GameWeb, List<BlackInfo>>();
		List<GameWeb> list = service.findGameWebList();
		for (GameWeb gameWeb : list) {
//			IPage<BlackInfo> iPage = infoService.findBlackInfoPageList(
//					super.getToPage(),
//					ALDAdminPageActionSupport.DEFAULT_PAGESIZE,
//					gameWeb.getServerId());
//
			List<BlackInfo> infoList = infoService.findBlackInfoPageList(
					super.getToPage(),
					ALDAdminPageActionSupport.DEFAULT_PAGESIZE,
					gameWeb.getServerId());
			
//			Collections.sort(infoList, new Comparator<BlackInfo>() {
//	            public int compare(BlackInfo arg0, BlackInfo arg1) {
//	            	return arg1.getId().compareTo(arg0.getId());
//	            }
//	        });
			
			List<BlackInfo> resultInfoList = new ArrayList<BlackInfo>();
			if (infoList != null) {
				if (infoList.size() > 100) {
					for (int i = 0; i < 100; i++) {
						resultInfoList.add(infoList.get(i));
					}
				} else {
					resultInfoList.addAll(infoList);
				}
			}
			infoMap.put(gameWeb, resultInfoList);
			
//			super.setTotalPage(iPage.getTotalPage());
//			super.setTotalSize(iPage.getTotalSize());
		}

		return SUCCESS;
	}

	public Map<GameWeb, List<BlackInfo>> getInfoMap() {
		return infoMap;
	}

	public void setInfoMap(Map<GameWeb, List<BlackInfo>> infoMap) {
		this.infoMap = infoMap;
	}

}
