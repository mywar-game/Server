package com.system.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.bo.VersionManagerRes;
import com.system.service.GameWebService;
import com.system.service.VersionManagerResService;

/**
 * 资源包版本信息
 * 
 * @author yezp
 */
public class VersionManagerResList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -6428502040671700575L;

	private Map<String, Partner> partnerMap;
	private Map<GameWeb, List<VersionManagerRes>> resMap;

	public String execute() {
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		VersionManagerResService service = ServiceCacheFactory
				.getServiceCache().getService(VersionManagerResService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);

		List<GameWeb> list = gameWebService.findGameWebList();
		partnerMap = partnerService.findAllPartnerMap();

		resMap = new HashMap<GameWeb, List<VersionManagerRes>>();
		for (GameWeb gameWeb : list) {
			List<VersionManagerRes> resList = service.findVersionResList(gameWeb.getServerId());
			Collections.sort(resList, new Comparator<VersionManagerRes>() {
	            public int compare(VersionManagerRes arg0, VersionManagerRes arg1) {
	            	// 比较大版本
	            	if (arg1.getResBigVersion() > arg0.getResBigVersion()) {
	            		return 1;
	            	} else if (arg1.getResBigVersion() > arg0.getResBigVersion()) {
	            		return -1;
	            	} else {
	            		// 比较小版本
	            		if (arg1.getResSmallVersion() > arg0.getResSmallVersion()) {
	            			return 1;
	            		} else {
	            			return -1;
	            		}
	            	}
	            }
	        });
			
			if (resList != null && resList.size() > 5) {			
				List<VersionManagerRes> apkListTemp = new ArrayList<VersionManagerRes>();		
				for (int i = 0; i < 5; i++) {
					apkListTemp.add(resList.get(i));
				}
				resMap.put(gameWeb, apkListTemp);
			} else {
				resMap.put(gameWeb, resList);
			}
			
		}

		return SUCCESS;
	}

	public Map<String, Partner> getPartnerMap() {
		return partnerMap;
	}

	public void setPartnerMap(Map<String, Partner> partnerMap) {
		this.partnerMap = partnerMap;
	}

	public Map<GameWeb, List<VersionManagerRes>> getResMap() {
		return resMap;
	}

	public void setResMap(Map<GameWeb, List<VersionManagerRes>> resMap) {
		this.resMap = resMap;
	}

}
