package com.stats.collector;

import java.util.Date;
import java.util.List;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLogoutLogService;
import com.log.service.UserPayHistoryLogService;
import com.stats.bo.UserTimeLossStats;
import com.stats.service.UserTimeLossStatsService;

/**
 * 随着时间玩家流失统计Collector
 * @author hzy
 * 2012-4-18
 */
public class UserTimeLossStatsCollector implements Collector {
	
	@Override
	public void execute(Date date) {
		LogSystem.info("随着时间玩家流失统计Collector开始");
		
		UserLogoutLogService userLogoutLogService = ServiceCacheFactory.getServiceCache().getService(UserLogoutLogService.class);
		UserPayHistoryLogService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayHistoryLogService.class);
		UserTimeLossStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserTimeLossStatsService.class);
		
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY + SystemStatsDate.THREE_DAYS_AGO);
		//流失玩家id列表
		int lossPayerAmount = 0;
		List<Object> list = userLogoutLogService.findLossUserByDate(dates[1]);
		if(list!=null && list.size()>0){
			String str = list.toString();
			String userIdStr = str.substring(1, str.length() - 1);//去掉收尾的中括号
			//玩家id是字符串类型，在数据库中用in查询的时候需要用单引号括起来
			String[] arr = userIdStr.split(",");
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<arr.length;i++){
				sb.append("'").append(arr[i]).append("'");
				if(i!=arr.length-1){
					sb.append(",");
				}
			}
			if (!"".equals(sb.toString())) {
				//从充值日志中查流失的玩家中有多少玩家是充值玩家
				lossPayerAmount = userPayHistoryLogService.findPayUserAmountByUserIdStr(sb.toString());
			}
		}
		UserTimeLossStats stats = new UserTimeLossStats();
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		stats.setLossAmount(list.size());
		stats.setLossPayerAmount(lossPayerAmount);
		statsService.save(stats);
		
		LogSystem.info("随着时间玩家流失统计Collector完毕");
	}

}
