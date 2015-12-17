package com.stats.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.service.UserService;
import com.dataconfig.service.UserPayService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLoginLogService;
import com.log.service.UserRegLogService;
import com.stats.bo.HomePageStats;
import com.stats.service.HomePageService;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;

public class StatsAction extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private String servers;
	private String date;
	private String toDate;
	@Override
	public String execute() {
		boolean isAllServer = false;
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(servers!=null && !servers.equals("")){
			if(servers.equals("all")){
				isAllServer = true;
			}else{
				String[] serverArr = servers.split(",");
				for(int i=0;i<serverArr.length;i++){
					map.put(serverArr[i], 1);
				}
			}
		}else{
			isAllServer = true;
		}
		LogSystem.info("统计首页数据开始-----");
		TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		UserPayService userPayService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
		HomePageService homePageService = ServiceCacheFactory.getServiceCache().getService(HomePageService.class);
		List<TGameServer> gameServerList = gameServerService.findTGameServerList();
		String[] dates = new String[2];
		if(toDate==null || toDate.equals("")){
			dates[1] = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY)[1];
		}else{
			dates[1] = toDate+" 23:59:59";
		}
		
		for (TGameServer gameServer : gameServerList) {
			if(gameServer.getOfficial().intValue()==0){
				continue;
			}
			if(isAllServer || map.containsKey(gameServer.getServerId()+"")){
				if(date!=null && !date.equals("")){
					dates[0] = date+" 00:00:00";
				}else{
					dates[0] = DateUtil.dateToString(gameServer.getServerOpernTime(), DateUtil.FORMAT_ONE);
				}
				List<HomePageStats> result = new ArrayList<HomePageStats>();
				CustomerContextHolder.setSystemNum(gameServer.getServerId());
				List<Object> activeList = userLoginLogService.findDayActive(dates);//各渠道的日活跃数据
				if(activeList!=null && activeList.size()>0){
					Map<String, Integer> newUserMap = userService.findNewUserBeforeTime(dates);//各渠道的创角数据
					Map<String, Object[]> payMap = userPayService.findPayUserNumByDay(dates);//各渠道的充值人数、充值总额
					Map<String, Integer> newRegPayMap = userPayService.findNewRegPayUserNumByDay(dates);//各渠道的新增付费人数
					for(int i=0;i<activeList.size();i++){
						HomePageStats stats = new HomePageStats();
						Object[] arr = (Object[])activeList.get(i);
						String key = arr[0].toString()+"_"+arr[1].toString();
						String time = arr[0].toString()+" 23:59:59";
						Date endDate = DateUtil.stringtoDate(time, DateUtil.FORMAT_ONE);
						String[] months = DateUtil.getMonthArrStr(DateUtil.getDiffDate(endDate, -30), endDate);
						Integer totalNum = userRegLogService.findTotalRegUserNumBeforeDate(time, arr[1].toString());//累积人数
						int monthActive = userLoginLogService.findActiveUserAmount(months, Integer.valueOf(arr[1].toString()));//月活跃
						stats.setChannel(arr[1].toString());
						stats.setDayActive(Integer.valueOf(arr[2].toString()));
						stats.setMonthActive(monthActive);
						stats.setNewUser(newUserMap.containsKey(key)?newUserMap.get(key):0);
						stats.setNewRegPayUserNum(newRegPayMap.containsKey(key)?newRegPayMap.get(key):0);
						if(payMap.containsKey(key)){
							stats.setPayAmount(Double.valueOf(payMap.get(key)[3].toString()));
							stats.setPayUserNum(Integer.valueOf(payMap.get(key)[2].toString()));
						}else{
							stats.setPayAmount(0d);
							stats.setPayUserNum(0);
						}
						stats.setSysNum(gameServer.getServerId());
						stats.setTotalNum(totalNum);
						stats.setHalfHourIndex(47);
						stats.setTime(endDate);
						result.add(stats);
					}
				}
				if(result.size()>0){
					homePageService.saveBatch(result);
				}
			}
		}
		
		LogSystem.info("统计首页数据结束-----");
		return SUCCESS;
	}
	public String getServers() {
		return servers;
	}
	public void setServers(String servers) {
		this.servers = servers;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
