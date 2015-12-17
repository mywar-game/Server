package com.stats.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.dataconfig.service.ActivationCodeService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserActivationCodeLogService;

public class ActivationCodePlatformStats extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = 2964142929266111231L;
	
	private Map<String, String> statsMap  = new HashMap<String, String>();
	
	public String execute() {
		ActivationCodeService activationCodeService = ServiceCacheFactory.getServiceCache().getService(ActivationCodeService.class);
		Map<String, String> platformAndCodesMap = activationCodeService.findPlatformAndCodesMap();
		
		UserActivationCodeLogService logService = ServiceCacheFactory.getServiceCache().getService(UserActivationCodeLogService.class);
		Iterator<Entry<String, String>>  ite = platformAndCodesMap.entrySet().iterator();
		while (ite.hasNext()) {
			Entry<String, String> entry = ite.next();
			String platform = entry.getKey();
			String codes = entry.getValue();
			Integer useNum = logService.findPlatformCodesUseNum(codes);
			statsMap.put(platform, codes.split(",").length + "_" + (useNum==null?0:useNum));
		}
		
		return SUCCESS;
	}

	public void setStatsMap(Map<String, String> statsMap) {
		this.statsMap = statsMap;
	}

	public Map<String, String> getStatsMap() {
		return statsMap;
	}

}
