package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * 手动采集数据--随时间玩家流失统计
 * @author Administrator
 *
 */
public class ReUserTimeLossStatsCollector {

	public void execute(String dateStr) {
		LogSystem.info("手动--随着时间玩家流失统计Collector开始");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		UserLogoutLogService userLogoutLogService = ServiceCacheFactory.getServiceCache().getService(UserLogoutLogService.class);
		UserPayHistoryLogService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayHistoryLogService.class);
		UserTimeLossStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserTimeLossStatsService.class);
		
		// 先删除
		statsService.delete(dateStr);
		
		// 
//		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY + SystemStatsDate.THREE_DAYS_AGO);
		String[] dates = DateUtil.getBeforeDateByDate(dateTemp, SystemStatsDate.THREE_DAYS_AGO); // 时间添加一天
		
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
//				lossPayerAmount = userPayHistoryLogService.findPayUserAmountByUserIdStr(sb.toString());
				lossPayerAmount = userPayHistoryLogService.findPayUserAmountByUserIdStrWithTime(sb.toString(), dateStr + "23:59:59");
			}
		}
		UserTimeLossStats stats = new UserTimeLossStats();
		stats.setSysNum(CustomerContextHolder.getSystemNum());
//		stats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		stats.setDate(DateUtil.getSomeDaysDiffDateByDate(dateTemp, 0));
		stats.setLossAmount(list.size());
		stats.setLossPayerAmount(lossPayerAmount);
		statsService.save(stats);
		
		LogSystem.info("手动--结束随着时间玩家流失统计Collector完毕");
	}
}
