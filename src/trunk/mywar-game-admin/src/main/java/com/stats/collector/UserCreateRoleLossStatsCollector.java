package com.stats.collector;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.adminTool.service.UserService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserRegLogService;
import com.stats.bo.UserCreateRoleLossStats;
import com.stats.service.UserCreateRoleLossStatsService;

/**
 * 玩家创建角色流失统计
 * @author hzy
 * 2012-4-12
 */
public class UserCreateRoleLossStatsCollector implements Collector {

	@Override
	public void execute(Date date) {
		LogSystem.info("玩家创建角色流失统计Collector开始");
		
		//记录昨天的创建角色相关的数据
		UserCreateRoleLossStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserCreateRoleLossStatsService.class);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
		
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		int regAmount = userRegLogService.findRegUserNumInSomeTime(dates);
		List<Object> newRegList = userService.findCreateUserNumInSomeTime(dates,false);
		
		UserCreateRoleLossStats stats = new UserCreateRoleLossStats();
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		stats.setRegAmount(regAmount);
		if(newRegList==null || newRegList.size()==0){
			stats.setCreateAmount(0);
		}else{
			stats.setCreateAmount(Integer.valueOf(((BigInteger)newRegList.get(0)).toString()));
		}
		statsService.save(stats);
		
		LogSystem.info("玩家创建角色流失统计Collector完毕");
	}

}
