package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adminTool.service.UserService;
import com.dataconfig.service.UserPayService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserGoldLogService;
import com.log.service.UserLoginLogService;
import com.log.service.UserRegLogService;
import com.stats.bo.HomePageStats;
import com.stats.service.HomePageService;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

/**
 * 首页数据重新采集
 * @author Administrator
 *
 */
public class ReHomePageStatsCollector {

	public void execute(String dateStr) {

		TGameServer gameServer = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (gameServer != null && gameServer.getOfficial().intValue() != 0) { // 测试服不要统计首页数据
			
			int timeIndex = DateUtil.getIndex(date, SystemStatsDate.HALF_HOUR_INDEX); // 获得当前时间半小时的维度
			LogSystem.info("服务器" + gameServer.getServerId() + "在时间点" + timeIndex + "的首页数据collector开始");
			String[] today = DateUtil.getTodayStrArr(date); // 当天的日期字符串
			
			UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
			UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
			UserPayService userPayService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
			UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
			HomePageService homePageService = ServiceCacheFactory.getServiceCache().getService(HomePageService.class);
			UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);	
			
			List<HomePageStats> result = new ArrayList<HomePageStats>();
			List<Object> totalList = userRegLogService.findRegUserNumInSomeTime(today[1]); // 截止到现在为止的各渠道累计用户
			Date d = DateUtil.stringtoDate(today[1], DateUtil.FORMAT_ONE);
			String[] months = DateUtil.getMonthArrStr(DateUtil.getDiffDate(d, -30), d);
			if (totalList != null && totalList.size() > 0) {
				Map<String, Integer> activeMap = userLoginLogService.findActiveUserAmount(today); // 当天的日活跃情况
				Map<String, Integer> newUserMap = userService.findCreateUserNumInSomeTime(today); // 当天各渠道的创角数据
				Map<String, Object[]> payMap = userPayService.findTotalPayUserNumAndTotalAmount(today); // 当天各渠道的充值人数、充值总额
				Map<String, Integer> newRegPayMap = userPayService.findNewRegPayUserNum(today); // 当天各渠道的新增付费人数
				Map<String, Integer> monthMap = userLoginLogService.findMonthActiveUserAmount(months); // 截止到现在为止各渠道的月活跃
				Map<String, Double> userGoldLogMap = userGoldLogService.findConsume(2, today); 
				
				for (int i = 0; i < totalList.size(); i++) {
					HomePageStats stats = new HomePageStats();
					Object[] arr = (Object[]) totalList.get(i);
					String channel = arr[0].toString();
					String smallChannel = arr[1].toString();
					stats.setChannel(channel);
					stats.setQn(smallChannel);
					String key = channel + " " + smallChannel;
					stats.setDayActive(activeMap.containsKey(key) ? activeMap.get(key) : 0);
					stats.setMonthActive(monthMap.containsKey(key) ? monthMap.get(key) : 0);
					stats.setNewUser(newUserMap.containsKey(key) ? newUserMap.get(key) : 0);
					stats.setNewRegPayUserNum(newRegPayMap.containsKey(key) ? newRegPayMap.get(key) : 0);
					stats.setDiamondUsed(userGoldLogMap.containsKey(key) ? userGoldLogMap.get(key) : 0);
					if (payMap.containsKey(key)) {
						stats.setPayAmount(Double.valueOf(payMap.get(key)[3].toString()));
						stats.setPayUserNum(Integer.valueOf(payMap.get(key)[2].toString()));
					}else{
						stats.setPayAmount(0d);
						stats.setPayUserNum(0);
					}
					stats.setSysNum(CustomerContextHolder.getSystemNum());
					stats.setTotalNum(Integer.valueOf(arr[2].toString()));
					stats.setHalfHourIndex(timeIndex);
					stats.setTime(d);
					
					result.add(stats);
				}
			}
			if (result.size() > 0) {
				int maxIndex = homePageService.getMaxIndex(DateUtil.dateToString(date, DateUtil.LONG_DATE_FORMAT), gameServer.getServerId());
				// 可能出现时间节点靠后的采集线程先处理完成，所以要查出数据库的当前服务器的最大时间节点
				// 如果此次时间节点大于数据库最大时间节点才保存(零点的时候会出现等于的情况)
				if ((maxIndex == 0 && timeIndex == 0) || maxIndex < timeIndex) {
					// 新增当前节点的数据
					homePageService.saveBatch(result);
					// 删除之前节点的数据
					homePageService.delete(DateUtil.dateToString(date, DateUtil.LONG_DATE_FORMAT), gameServer.getServerId(), timeIndex);
				}
			}
			LogSystem.info("服务器" + gameServer.getServerId() + "在时间点" + timeIndex + "的首页数据collector结束");
		}
	}
}
