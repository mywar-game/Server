package com.stats.collector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.SystemStatsDate;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserActionLogService;
import com.stats.bo.UserNodeLossStats;
import com.stats.service.UserNodeLossStatsService;

public class UserNodeLossStatsCollector implements Collector {

	@Override
	public void execute(Date time)
			throws Exception {
		// TODO Auto-generated method stub
		UserNodeLossStatsService userNodeLossStatsService = ServiceCacheFactory.getServiceCache().getService(UserNodeLossStatsService.class);
		UserActionLogService userActionLogService = ServiceCacheFactory.getServiceCache().getService(UserActionLogService.class);
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		List<Object> list = userActionLogService.collectUserActionData(dates);
		Date date = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		List<UserNodeLossStats> statsList = new ArrayList<UserNodeLossStats>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				UserNodeLossStats stats = new UserNodeLossStats();
				stats.setActionId(Integer.valueOf(((Object[])list.get(i))[0].toString()));
				stats.setNum(Integer.valueOf(((Object[])list.get(i))[1].toString()));
				stats.setSysNum(CustomerContextHolder.getSystemNum());
				stats.setTime(date);
				statsList.add(stats);
			}
			userNodeLossStatsService.saveBatch(statsList);
		}
	}

}
