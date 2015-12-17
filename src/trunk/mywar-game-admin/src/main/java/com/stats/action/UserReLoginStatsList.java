package com.stats.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.admin.util.DTools;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserRegLogService;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class UserReLoginStatsList extends ALDAdminStatsDatePageActionSupport {

	/** **/
	private static final long serialVersionUID = 997224093491880908L;
	
	/** 选中的要查的服务器id **/
	private String serverIds;
	
	/** 要显示的map **/
	private Map<String,Map<String,Integer>> map = new HashMap<String, Map<String,Integer>>();
	
	/** 注册开始日期 **/
	private Date regStartDate;
	
	/** 注册结束日期 **/
	private Date regEndDate;

	public String execute() {
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
		
		if (DTools.isEmpty(serverIds)) {
			return SUCCESS;
		}
		for (String serverId : serverIds.split(",")) {
			CustomerContextHolder.setSystemNum(Integer.valueOf(serverId.trim()));
			TGameServer gameServer = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum());
			Date openDate = new Date(gameServer.getServerOpernTime().getTime());
			
			if (DateUtil.dayDiff(regStartDate, openDate) > 0) {
				super.setErroDescrip("\""+gameServer.getServerAlias()+"\"在当前注册日期还没开服！");
				continue;
			}
			
			//登录开始日期
			//super.getStartDate();
			//登录结束日期
			//super.getEndDate();
			Map<String,Integer> tempMap = userRegLogService.findReLoginMap(regStartDate, regEndDate, super.getStartDate(), super.getEndDate());
	//		Calendar calendar = Calendar.getInstance();
	//		calendar.get(Calendar.DAY_OF_MONTH);
			
			//再登录人数：在A时间段注册的人中 在B时间段登陆的人数
			Integer reLoginAmount = 0;
			
			//在A时间段注册的人中 B时间段内，各个日期登陆的人数
			Map<String,Integer> dayAndAmountmap = new HashMap<String, Integer>();
			Iterator<Entry<String, Integer>> ite1 = tempMap.entrySet().iterator();
			while (ite1.hasNext()) {
				Entry<String, Integer> entry = ite1.next();
				
				Integer count = entry.getValue();
				reLoginAmount += count;
	
				String[] dayStrArr = entry.getKey().split(",");
				for (String dayStr : dayStrArr) {
					if(dayAndAmountmap.get(dayStr) == null) {
						dayAndAmountmap.put(dayStr, count);
					} else {
						dayAndAmountmap.put(dayStr, count + dayAndAmountmap.get(dayStr));
					}
				}
			}
			//每天的再登录人数（总和）： 在A时间段注册的人中  B时间段内，每天登陆的人数 的总和
			Integer allDayAmount = 0;
			Iterator<Entry<String, Integer>> ite2 = dayAndAmountmap.entrySet().iterator();
			while (ite2.hasNext()) {
				Entry<String, Integer> entry = ite2.next();
				allDayAmount += entry.getValue();
			}
			
			//A时间段注册的人数
			String[] dates = new String[2];
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(regStartDate.getTime());
			dates[0] = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) +" 00:00:00";
			c.setTimeInMillis(regEndDate.getTime());
			dates[1] = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) +" 23:59:59";
			int regAmount = userRegLogService.findRegUserNumInSomeTime(dates);
			
			//当前服务器的统计数据
			Map<String,Integer> serverMap = new HashMap<String, Integer>();
			serverMap.put("regAmount", regAmount);
			serverMap.put("reLoginAmount", reLoginAmount);
			serverMap.put("allDayAmount", allDayAmount);
			
			map.put(gameServer.getServerAlias(), serverMap);
		}
		return SUCCESS;
	}

	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}

	public String getServerIds() {
		return serverIds;
	}

	public void setMap(Map<String,Map<String,Integer>> map) {
		this.map = map;
	}

	public Map<String,Map<String,Integer>> getMap() {
		return map;
	}

	public void setRegStartDate(Date regStartDate) {
		this.regStartDate = regStartDate;
	}

	public Date getRegStartDate() {
		return regStartDate;
	}

	public void setRegEndDate(Date regEndDate) {
		this.regEndDate = regEndDate;
	}

	public Date getRegEndDate() {
		return regEndDate;
	}
}
