package com.stats.collector;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.adminTool.service.UserService;
import com.dataconfig.service.UserPayService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLoginLogService;
import com.log.service.UserLogoutLogService;
import com.log.service.UserOnlineLogService;
import com.stats.bo.NormalDataStats;
import com.stats.service.NormalDataStatsService;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class NormalDataStatsCollector implements Collector {

	@Override
	public void execute(Date time)
			throws Exception {
		// TODO Auto-generated method stub
		LogSystem.info("常规数据统计开始-------------");
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		Date date = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		NormalDataStatsService normalDataStatsService = ServiceCacheFactory.getServiceCache().getService(NormalDataStatsService.class);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		UserOnlineLogService userOnlineLogService = ServiceCacheFactory.getServiceCache().getService(UserOnlineLogService.class);
		UserPayService userPayService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		int userCount = userService.countAllUser();//用户总量
		List<Object> newRegList = userService.findCreateUserNumInSomeTime(dates,false);//新注册数
		List<Object> loginList = userLoginLogService.findActiveUserAmount(dates,false);
		int dayActive = 0;//日活跃
		int loginTotalNum = 0;//当天登陆总次数
		if(loginList!=null && loginList.size()>0){
			dayActive = Integer.valueOf(((Object[])loginList.get(0))[0].toString());
			loginTotalNum = Integer.valueOf(((Object[])loginList.get(0))[1].toString());
		}
		List<Object> onlineList = userOnlineLogService.findOnlineDataForNormalDataStats(dates);
		int maxOnline = Integer.valueOf(((Object[])onlineList.get(0))[0].toString());//最高在线
		int totalOnline = Integer.valueOf(((Object[])onlineList.get(0))[1].toString());//总在线时长
		//最高在线时间
		Timestamp maxOnlineTime = new Timestamp(DateUtil.stringtoDate(userOnlineLogService.findMaxAmountTime(dates), DateUtil.FORMAT_ONE).getTime());
		Map<String, Partner> map = partnerService.findAllPartnerMap();//渠道商信息
		List<Object> list = userPayService.findChannelRechargeInfo(dates);//渠道商充值信息
		StringBuilder channelIncomeInfo = new StringBuilder();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				//新增的渠道可能没有
				channelIncomeInfo.append(map.get(arr[0].toString())==null?arr[0].toString():map.get(arr[0].toString()).getPName()).append("_").append(arr[1].toString());
				if(i!=list.size()-1){
					channelIncomeInfo.append(",");
				}
			}
		}
		List<Object> payList = userPayService.findTotalPayUserNumAndTotalAmount(dates,false);//充值用户数
		// 在线时长normalDataStats.setAgvTime(Tools.decimalFormat((double)normalDataStats.getTotalOnline(),(double)normalDataStats.getDayActive(), "#.00"));
		NormalDataStats stats = new NormalDataStats();
		UserLogoutLogService userLogoutLogService = ServiceCacheFactory.getServiceCache().getService(UserLogoutLogService.class);
		try{
			List<Object> userLogoutLogList = userLogoutLogService.getUserOnLineAvg(dates);
			if (userLogoutLogList != null && userLogoutLogList.size() > 0) {
				int countUser = Integer.valueOf(((Object[])userLogoutLogList.get(0))[0].toString());
				int sumTime = Integer.valueOf(((Object[])userLogoutLogList.get(0))[1].toString());
				stats.setAgvTimeStr(Tools.decimalFormat((double)sumTime, (double)countUser, "#.00"));
			}
		} catch (Exception e) {
			stats.setAgvTime("");
			stats.setAgvTimeStr("");
		}
		stats.setChannelIncomeInfo(channelIncomeInfo.toString());
		stats.setDayActive(dayActive);
		stats.setLoginTotalNum(loginTotalNum);
		stats.setMaxOnline(maxOnline);
		stats.setMaxOnlineTime(maxOnlineTime);
		if(newRegList==null || newRegList.size()==0){
			stats.setNewReg(0);
		}else{
			stats.setNewReg(((BigInteger)newRegList.get(0)).intValue());
		}
		if(payList==null || payList.size()==0){
			stats.setPayNum(0);
		}else{
			stats.setPayNum(Integer.valueOf(((Object[])payList.get(0))[0].toString()));
		}
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		
		int systemNum = CustomerContextHolder.getSystemNum();
		TGameServer tGameServer = DataSourceManager.getInstatnce().getGameServerMap().get(systemNum);
		stats.setServerName(tGameServer.getServerAlias());
		
		stats.setTime(date);
		stats.setTotalOnline(totalOnline);
		stats.setUserTotal(userCount);
		normalDataStatsService.save(stats);
		LogSystem.info("常规数据统计结束-------------");
	}

}
