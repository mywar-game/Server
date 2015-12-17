package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.service.UserService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLoginLogService;
import com.log.service.UserRegLogService;
import com.stats.bo.LoginStats;
import com.stats.service.LoginStatsService;

/**
 * 手动采集--登入统计
 * @author Administrator
 *
 */
public class ReLoginStatsCollector {

	private ReLoginStatsCollector() {}
	private static ReLoginStatsCollector instance;
	public static ReLoginStatsCollector getInstance() {
		if (instance == null) {
			instance = new ReLoginStatsCollector();
		}
		return instance;
	}
	
	public void execute(String dateStr) {
		// TODO Auto-generated method stub
		LogSystem.info("手动--登陆数据collector开始");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String[] today = DateUtil.getTodayStrArr(date);//当天的日期字符串
		
		LoginStatsService loginStatsService = ServiceCacheFactory.getServiceCache().getService(LoginStatsService.class);
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
		
		// 先删除
		loginStatsService.delete(dateStr);
		
		Map<String, Object[]> loginMap = userLoginLogService.findLoginInfoByChannel(today); // 当天根据平台分组的登陆账号数、登陆ip数
		Map<String, Integer> newRegMap = userRegLogService.findUserCountByChannel(today); // 当天根据平台分组的新注册数量
		List<Object> newUserList = userService.findCreateUserNumInSomeTime(today, true); // 当天根据平台分组的新角色数量
		Map<String, Integer> oldLoginMap = userLoginLogService.findOldLoginNumByChannel(today); // 当天的老玩家登陆数
		int timeIndex = DateUtil.getIndex(date, SystemStatsDate.HALF_HOUR_INDEX); // 获得当前时间半小时的维度
		Map<String, Integer> newUserMap = new HashMap<String, Integer>(); // 新角色map
		if (newUserList != null && newUserList.size() > 0) {
			for (int i = 0; i < newUserList.size(); i++) {
				Object[] arr = (Object[])newUserList.get(i);
				newUserMap.put(arr[1].toString(), Integer.valueOf(arr[0].toString()));
			}
		}
		Map<String, Integer> map = new HashMap<String, Integer>();//存在的渠道map
		if (loginMap.size() > 0) {
			for (String channel : loginMap.keySet()) {
				map.put(channel, 1);
			}
		}
		if (newRegMap.size() > 0) {
			for (String channel : newRegMap.keySet()) {
				map.put(channel, 1);
			}
		}
		if (newUserMap.size() > 0) {
			for(String channel : newUserMap.keySet()) {
				map.put(channel, 1);
			}
		}
		if (oldLoginMap.size() > 0) {
			for(String channel : oldLoginMap.keySet()){
				map.put(channel, 1);
			}
		}
		if (map.size() > 0) {
			List<LoginStats> result = new ArrayList<LoginStats>();
			for (String channel : map.keySet()) {
				LoginStats stats = new LoginStats();
				stats.setChannel(channel);
				stats.setSysNum(CustomerContextHolder.getSystemNum());
				if (loginMap.containsKey(channel)) {
					Object[] arr = loginMap.get(channel);
					stats.setLoginNum(Integer.valueOf(arr[1].toString()));
					stats.setLoginIpNum(Integer.valueOf(arr[2].toString()));
				} else {
					stats.setLoginNum(0);
					stats.setLoginIpNum(0);
				}
				stats.setNewReg(newRegMap.get(channel) == null ? 0 : newRegMap.get(channel));
				stats.setNewUser(newUserMap.get(channel) == null ? 0 : newUserMap.get(channel));
				stats.setOldLoginNum(oldLoginMap.get(channel) == null ? 0 : oldLoginMap.get(channel));
				stats.setHalfHourIndex(timeIndex);
				stats.setTime(date);
				result.add(stats);
			}
			loginStatsService.saveBatch(result);
		}
		LogSystem.info("手动--登陆数据collector结束");
	}
}
