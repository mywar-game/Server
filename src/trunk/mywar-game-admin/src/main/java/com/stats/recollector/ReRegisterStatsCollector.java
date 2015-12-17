package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.service.UserService;
import com.dataconfig.service.UserPayService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserRegLogService;
import com.stats.bo.RegisterStats;
import com.stats.service.RegisterStatsService;

public class ReRegisterStatsCollector {

	public void execute(String dateStr) {
		// TODO Auto-generated method stub
		LogSystem.info("注册数据collector开始");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		String[] today = DateUtil.getTodayStrArr(date);//当天的日期字符串
		RegisterStatsService registerStatsService = ServiceCacheFactory.getServiceCache().getService(RegisterStatsService.class);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
		UserPayService userPayService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		List<Object> newUserList = userService.findCreateUserNumInSomeTime(today, true);//当天根据平台分组的新角色数量
		Map<String, Integer> newRegMap = userRegLogService.findUserCountByChannel(today);//当天根据平台分组的新注册数量
		List<Object> payList = userPayService.findNewUserPayNumAndPayAmount(today, true);//当天根据平台分组的新用户充值信息
		int timeIndex = DateUtil.getIndex(date,SystemStatsDate.HALF_HOUR_INDEX);//获得当前时间半小时的维度
		Map<String, Integer> newUserMap = new HashMap<String, Integer>();//新角色map
		if(newUserList!=null && newUserList.size()>0){
			for(int i=0;i<newUserList.size();i++){
				Object[] arr = (Object[])newUserList.get(i);
				newUserMap.put(arr[1].toString(), Integer.valueOf(arr[0].toString()));
			}
		}
		Map<String, Object[]> payMap = new HashMap<String, Object[]>();//充值map
			if(payList!=null && payList.size()>0){
				for(int i=0;i<payList.size();i++){
					Object[] arr = (Object[])payList.get(i);
					payMap.put(arr[2].toString(), arr);
				}
			}
			Map<String, Integer> map = new HashMap<String, Integer>();//存在的渠道map
			if(newRegMap.size()>0){
				for(String channel : newRegMap.keySet()){
					map.put(channel, 1);
				}
			}
			if(newUserMap.size()>0){
				for(String channel : newUserMap.keySet()){
					map.put(channel, 1);
				}
			}
			if(payMap.size()>0){
				for(String channel : payMap.keySet()){
					map.put(channel, 1);
				}
			}
			if(map.size()>0){
				List<RegisterStats> result = new ArrayList<RegisterStats>();
				for(String channel : map.keySet()){
					RegisterStats stats = new RegisterStats();
					stats.setChannel(channel);
					stats.setSysNum(CustomerContextHolder.getSystemNum());
					stats.setNewReg(newRegMap.get(channel)==null?0:newRegMap.get(channel));
					stats.setNewUser(newUserMap.get(channel)==null?0:newUserMap.get(channel));
					if(payMap.containsKey(channel)){
						Object[] arr = payMap.get(channel);
						stats.setNewRegPayUserNum(Integer.valueOf(arr[0].toString()));
						stats.setNewRegPayAmount(Double.valueOf(arr[1].toString()));
					}else{
						stats.setNewRegPayUserNum(0);
						stats.setNewRegPayAmount(0d);
					}
					stats.setHalfHourIndex(timeIndex);
					stats.setTime(date);
					result.add(stats);
				}
				registerStatsService.saveBatch(result);
			}
		LogSystem.info("注册数据collector结束");
	}
}
