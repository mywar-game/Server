package com.stats.recollector;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.dataconfig.service.UserPayService;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.UserPayDetail;
import com.stats.bo.UserPayStats;
import com.stats.service.UserPayStatsService;

/**
 * 手动采集数据--玩家充值统计
 * @author Administrator
 *
 */
public class ReUserPayStatsCollector {

	public void execute(String dateStr) {
		LogSystem.info("手动--玩家充值统计 Collector开始");
		
		UserPayService userPayService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
//		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		String[] dates = DateUtil.getBeforeDateByDate(dateTemp, 0);
		Integer payAmount = userPayService.getPayAmount(dates);
		
		UserPayStats userPayStats = new UserPayStats(); // 充值统计
		userPayStats.setSysNum(CustomerContextHolder.getSystemNum());
//		userPayStats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		userPayStats.setDate(DateUtil.getSomeDaysDiffDateByDate(dateTemp, 0));
		userPayStats.setPayAmount(payAmount);
		
		UserPayStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserPayStatsService.class);
		// 先删除
		statsService.delete(dateStr);
		statsService.deletePayDetails(dateStr);
		
		statsService.save(userPayStats);
		
		List<Object> list = userPayService.collectChannelUserPayInfo(dates); // 前一天的充值数据
		if(list!=null && list.size()>0){ // 统计充值详情
			List<UserPayDetail> result = new ArrayList<UserPayDetail>();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[])list.get(i);
				UserPayDetail detail = new UserPayDetail();
				detail.setSysNum(CustomerContextHolder.getSystemNum());
				detail.setChannel(arr[0].toString());
				detail.setOrderId(arr[1].toString());
				detail.setUserId(arr[2].toString());
				detail.setAmount(Double.valueOf(arr[3].toString()));
				detail.setPayTime(new Timestamp(DateUtil.stringtoDate(arr[4].toString(), DateUtil.FORMAT_ONE).getTime()));
				detail.setUserName(arr[5].toString());
				detail.setUserLevel(Integer.valueOf(arr[6].toString()));
				detail.setLastLoginTime(arr[7] == null ? detail.getPayTime() : new Timestamp(DateUtil.stringtoDate(arr[7].toString(), DateUtil.FORMAT_ONE).getTime()));
//				detail.setTime(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
				detail.setTime(DateUtil.getSomeDaysDiffDateByDate(dateTemp, 0));
				result.add(detail);
			}
			statsService.saveBatch(result);
		}
		
		LogSystem.info("手动--结束玩家充值统计 Collector完毕");
	}
}
