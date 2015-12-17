package com.stats.collector;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserRegLogService;
import com.log.service.UserVipLogService;
import com.stats.bo.UserVipStats;
import com.stats.service.UserVipStatsService;

public class UserVipStatsCollector implements Collector {
	@Override
	public void execute(Date time)
			throws Exception {
		// TODO Auto-generated method stub
		LogSystem.info("玩家vip统计开始-------------");
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		Date date = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		UserVipStatsService userVipStatsService = ServiceCacheFactory.getServiceCache().getService(UserVipStatsService.class);
		UserVipLogService userVipLogService = ServiceCacheFactory.getServiceCache().getService(UserVipLogService.class);
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
		List<Object> list = userVipLogService.findUserCoutnByDiffVipLevel(dates);//vip分布信息
		Map<String, Integer> regMap = userRegLogService.findUserCountByChannel();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Integer> vipUserCountMap = new HashMap<String, Integer>();//不同渠道v0的人数
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				String key = arr[0].toString();
				String str = arr[1].toString()+"_"+arr[2].toString();
				if(map.containsKey(key)){
					map.put(key, map.get(key)+","+str);
				}else{
					map.put(key, str);
				}
				if(vipUserCountMap.containsKey(key)){
					vipUserCountMap.put(key, vipUserCountMap.get(key)+Integer.valueOf(arr[2].toString()));
				}else{
					vipUserCountMap.put(key, Integer.valueOf(arr[2].toString()));
				}
			}
		}
		if(map.size()>0){
			List<UserVipStats> result = new ArrayList<UserVipStats>();
			for(String key : map.keySet()){
				int v0 = Math.max(0, (regMap.get(key)==null?0:regMap.get(key))-vipUserCountMap.get(key));//当天截止不是vip的人数
				map.put(key, map.get(key)+","+"0_"+v0);//将v0的数据追加上去
				UserVipStats stats = new UserVipStats();
				stats.setPartnerId(key);
				stats.setSysNum(CustomerContextHolder.getSystemNum());
				stats.setVipInfo(map.get(key));
				stats.setTime(date);
				result.add(stats);
			}
			userVipStatsService.saveBatch(result);
		}
		LogSystem.info("玩家vip统计开始-------------");
	}

}
