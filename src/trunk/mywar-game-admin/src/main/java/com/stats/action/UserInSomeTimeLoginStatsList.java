package com.stats.action;

import java.util.Map;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLoginLogService;

/**
 * 玩家某段时间内登录统计
 * @author hzy
 * 2012-11-30
 */
public class UserInSomeTimeLoginStatsList extends ALDAdminStatsDatePageActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;
	
	public static final int[] LOGIN_TIMES_ARRAY = {1,2,3,4,5,6,7,8,10,20,25,50};
	
	private Map<String, Integer> statsMap; 
	
	public String execute(){
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		statsMap = userLoginLogService.findUserInSomeTimeStatsMap(new String[]{DateUtil.dateToString(getStartDate(), DateUtil.FORMAT_ONE),DateUtil.dateToString(getEndDate(), DateUtil.FORMAT_ONE)});
		return SUCCESS;
	}

	public void setStatsMap(Map<String, Integer> statsMap) {
		this.statsMap = statsMap;
	}

	public Map<String, Integer> getStatsMap() {
		return statsMap;
	}
	
}
