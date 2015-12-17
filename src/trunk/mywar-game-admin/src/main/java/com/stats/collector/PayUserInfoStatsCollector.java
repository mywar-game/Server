package com.stats.collector;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.LoginLog;
import com.log.bo.LogoutLog;
import com.log.bo.PaymentLog;
import com.log.bo.UserLog;
import com.log.service.UserPayHistoryLogService;
import com.stats.bo.PayUserInfoStats;
import com.stats.service.PayUserInfoStatsService;

public class PayUserInfoStatsCollector implements Collector {

	@Override
	public void execute(Date date){
		LogSystem.info("付费用户信息统计Collector开始");
		PayUserInfoStatsService statsService = ServiceCacheFactory.getServiceCache().getService(PayUserInfoStatsService.class);
		statsService.deleteBeforeStatsData();
		
		UserPayHistoryLogService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayHistoryLogService.class);

		// 分语句查询
//		try {
//			
//			userPayHistoryLogService.findPayUserInfo();
//		} catch (ParseException e) {
//			LogSystem.error(e, "PayUserInfoStatsCollector");
//		}
		
		try {
			// 先查找数据最少的表payment_log
			List<PaymentLog> paymentLogList = userPayHistoryLogService.findPayLog();
			if (paymentLogList == null || paymentLogList.size() == 0) {
				// 如果没有数据，则跳过
				return;
			}
			String[] userIds = new String[paymentLogList.size()];
			int i = 0;
			for (PaymentLog log : paymentLogList) {
				userIds[i] = new String("");
				userIds[i] = log.getUserId();
				i++;
			}
			// 查询user表
			Map<String,UserLog> userLogMap = userPayHistoryLogService.findUserInfo(userIds);
			
			// 查询登入表
			Map<String,LoginLog> loginLogMap = userPayHistoryLogService.findLoginInfo(userIds);
			
			// 查询登出表
			Map<String, LogoutLog> logoutLogMap = userPayHistoryLogService.findLogoutInfo(userIds);
			
			List<PayUserInfoStats> payUserInfoStatsList = new ArrayList<PayUserInfoStats>();
			PayUserInfoStats payUserInfoStats;
			// 
			for (PaymentLog log : paymentLogList) {
				String userId = log.getUserId();
				payUserInfoStats = new PayUserInfoStats();
				payUserInfoStats.setSysNum(CustomerContextHolder.getSystemNum());
				
				UserLog userLog = userLogMap.get(userId);
				LoginLog loginLog = loginLogMap.get(userId);
				LogoutLog logoutLog = logoutLogMap.get(userId);
				String name = "";
				String userName = "";
				Date regTime = null;
				Date lastLoginTime = null;
				Integer loginDays = 0;
				Integer onlineTime = 0;
				if (userLog != null) {
					name = userLog.getName();
					userName = userLog.getUserName();
					regTime = userLog.getRegTime();
					payUserInfoStats.setName(name);
					payUserInfoStats.setUserName(userName);
					payUserInfoStats.setRegTime(new Timestamp(regTime.getTime()));
				}
				if (logoutLog != null) {
					onlineTime = logoutLog.getOnlineTime();
					if (onlineTime == null) {
						onlineTime = 0;
					}
					payUserInfoStats.setTotalOnlineTime(onlineTime);
				}
				if (loginLog != null) {
					lastLoginTime = loginLog.getLastLoginTime();
					if (lastLoginTime == null) {
						lastLoginTime = log.getLastPayTime();
					}
					payUserInfoStats.setLastLoginTime(new Timestamp(lastLoginTime.getTime()));
					payUserInfoStats.setLogCondition(new Integer(DateUtil.dayDiff(new Date(System.currentTimeMillis()), lastLoginTime)+""));
					loginDays = loginLog.getLoginDays();
					payUserInfoStats.setAverageOnlineTime(loginDays==0?0:payUserInfoStats.getTotalOnlineTime()/loginDays);
					payUserInfoStats.setAveragePayPeriod(Integer.valueOf((log.getLastPayTime().getTime() - regTime.getTime())/(Integer.valueOf(log.getPayNum().toString())*1000*60)+""));
				}
				
				// 军团名
				payUserInfoStats.setGuildName("");
				payUserInfoStatsList.add(payUserInfoStats);
			}
			// 保存
			statsService.savePayUserInfoStatsList(payUserInfoStatsList);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LogSystem.info("付费用户信息统计Collector完毕");
	}

}
