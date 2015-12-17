package com.log.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.framework.common.DBSource;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.UserOnlineLog;
import com.log.dao.UserOnlineLogDao;
import com.stats.bo.OnlineDetailStats;
import com.stats.bo.UserOnlineStats;
import com.stats.service.OnlineDetailStatsService;

public class UserOnlineLogService {
	
	private UserOnlineLogDao userOnlineLogDao;

	/**
	 * 开服前三天的在线人数日志
	 * @param date 开服日期
	 */
	public List<Object> findThreeDaysBeforOpenServer(Date date) {
		userOnlineLogDao.closeSession(DBSource.LOG);
		return userOnlineLogDao.findSQL_("select * from user_online_log where LEFT(TIME,10) >= LEFT('" + date + "',10)");
	}
	
	/**
	 * 给定日期内平均在线人数、在线人数峰值、付费玩家在线人数峰值
	 * @param dates
	 * @return
	 */
	public UserOnlineStats findOnlineAmountInSomeTime(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT SUM(ONLINE_AMOUNT),MAX(ONLINE_AMOUNT),count(*) FROM user_online_log WHERE TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		
		userOnlineLogDao.closeSession(DBSource.LOG);
		List<Object> list = userOnlineLogDao.findSQL_(sb.toString());
		
		UserOnlineStats userOnlineStats = new UserOnlineStats();
		if (list != null && list.size() > 0) {
			int value1 = ((Object[]) list.get(0))[0]==null?0:Integer.parseInt(((Object[]) list.get(0))[0].toString());
			int value2 = ((Object[]) list.get(0))[1]==null?0:Integer.parseInt(((Object[]) list.get(0))[1].toString());
			int value3 = ((Object[]) list.get(0))[2]==null?0:Integer.parseInt(((Object[]) list.get(0))[2].toString());
			userOnlineStats.setOnlinePeak(value2);
			userOnlineStats.setPayPlayerOnlinePeak(0);
			userOnlineStats.setOnlineAmount(value3==0?0:value1/value3);
		} else {
			userOnlineStats.setOnlineAmount(0);
			userOnlineStats.setOnlinePeak(0);
			userOnlineStats.setPayPlayerOnlinePeak(0);
		}
		return userOnlineStats;
	}
	
	/**
	 * 查询某天的最高在线人数、在线总时长、平均在线人数
	 * @param dates
	 * @return
	 */
	public List<Object> findOnlineDataForNormalDataStats(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT MAX(ONLINE_AMOUNT),SUM(ONLINE_AMOUNT),AVG(ONLINE_AMOUNT)");
		sb.append(" FROM user_online_log log");
		sb.append(" WHERE log.TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		userOnlineLogDao.closeSession(DBSource.LOG);
		return userOnlineLogDao.findSQL_(sb.toString());
	}
	
	
	/**
	 * 某天的最高在线时间
	 * @param dates
	 * @return
	 */
	public String findMaxAmountTime(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT TIME FROM user_online_log WHERE ONLINE_AMOUNT =(SELECT MAX(ONLINE_AMOUNT) FROM user_online_log WHERE TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("')");
		sb.append(" AND TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		userOnlineLogDao.closeSession(DBSource.LOG);
		List<Object> list =  userOnlineLogDao.findSQL_(sb.toString());
		return ((Timestamp)list.get(0)).toString();//多条记录取第一个
	}
	
	/**
	 * 最后一条日志
	 */
	public UserOnlineLog findLastLog() {
		List<UserOnlineLog> list = userOnlineLogDao.find("from UserOnlineLog where id = (select max(id) from UserOnlineLog)", DBSource.LOG);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查找某一天的玩家在线日志
	 * @param chooseDate
	 * @return
	 */
	public List<UserOnlineLog> findOneDayLogList(Date chooseDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<UserOnlineLog> list = new ArrayList<UserOnlineLog>();
		StringBuffer hql = new StringBuffer();
		hql.append("from UserOnlineLog where datediff(time,'");
		hql.append(sdf.format(chooseDate));
		hql.append("') = 0");
		
//		hql.append("from UserOnlineLog where TIME BETWEEN '");
//		hql.append(dates[0]);
//		hql.append("' AND '");
//		hql.append(dates[1]);
//		hql.append("'");
		list = userOnlineLogDao.find(hql.toString(), DBSource.LOG);
		Date d;
		Date next;
		UserOnlineLog userOnlineLog;
		//一小时12条记录，一天24小时，则一天应该有12*24=288条记录
		//如果当天的记录数小于288则有错漏的记录
		if (list != null && list.size() > 0 && list.size() < 288) {
			if (list.size() > 288) {
				LogSystem.warn(chooseDate+"在线记录数大于288！！！");
			}
			List<UserOnlineLog> resultList = new ArrayList<UserOnlineLog>();
			for (int i = 0; i < list.size(); i++) {
				resultList.add(list.get(i));
				if (i == list.size()-1) {
					break;
				}
				d = list.get(i).getTime();
				next = list.get(i+1).getTime();
				//下一次的记录是本次记录的5分钟的几倍
				int j = (int)((next.getTime() - d.getTime()) / (5*60*1000));
				if (j > 1) {
					LogSystem.warn(d+"后面少了"+(j-1)+"条在线记录！！");
				}
				//如果是一倍以上，添加记录
				for (int k = 1; k < j; k++) {
					userOnlineLog = new UserOnlineLog();
					userOnlineLog.setOnlineAmount(0);
					userOnlineLog.setTime(new Timestamp(d.getTime() + 5*k*60*1000));
					resultList.add(userOnlineLog);
				}
			}
			return resultList;
		}
		return list;
	}
	
	/**
	 * 分析半小时之内的玩家在线数据 转化成平台在线玩家数据
	 * @param dates
	 */
	public void findOnlineChannelSummaryData(String[] dates){
		OnlineDetailStatsService onlineDetailStatsService = ServiceCacheFactory.getServiceCache().getService(OnlineDetailStatsService.class);
		StringBuffer hql = new StringBuffer();
		hql.append("from UserOnlineLog where TIME BETWEEN '");
		hql.append(dates[0]);
		hql.append("' AND '");
		hql.append(dates[1]);
		hql.append("'");
		Date date = new Date();
		List<UserOnlineLog> list = userOnlineLogDao.find(hql.toString(), DBSource.LOG);//时间段内的玩家在线日志
		if(list!=null && list.size()>0){
			for(UserOnlineLog log : list){
				if(!log.getUserStr().equals("")){
					StringBuilder sb = new StringBuilder();
					String[] arr = log.getUserStr().substring(0,log.getUserStr().length()-1).split(",");
					for(int i=0;i<arr.length;i++){
						if(i!=arr.length-1){
							sb.append("'").append(arr[i]).append("'").append(",");
						}else{
							sb.append("'").append(arr[i]).append("'");
						}
					}
					userOnlineLogDao.closeSession(DBSource.LOG);
					//渠道-对应的玩家数-玩家ids
					
					StringBuilder sbl = new StringBuilder();
					sbl.append("SELECT COUNT(log1.USER_ID),GROUP_CONCAT(log1.USER_ID) AS userIds,log1.CHANNEL,count(distinct(log2.ip)) FROM");
					sbl.append(" (select user_id,channel from user_reg_log reg WHERE reg.USER_ID IN(");
					sbl.append(sb.toString());
					sbl.append(")) log1 left join (select user_id,ip from user_login_log where user_id in(");
					sbl.append(sb.toString());
					sbl.append(") group by user_id) log2 on log1.user_id=log2.user_id GROUP BY log1.CHANNEL");
					List<Object> onlineList = userOnlineLogDao.findSQL_(sbl.toString());
					if(onlineList!=null && onlineList.size()>0){
						List<OnlineDetailStats> onlineDetailList = new ArrayList<OnlineDetailStats>();
						for(int i=0;i<onlineList.size();i++){
							Object[] array = (Object[])onlineList.get(i);
							OnlineDetailStats stats = new OnlineDetailStats();
							stats.setChannel(array[2].toString());
							stats.setFiveMinuteIndex(DateUtil.getIndex(log.getTime(), SystemStatsDate.FIVE_MINUTE_INDEX));
							stats.setOnlineAmount(Integer.valueOf(array[0].toString()));
							stats.setOnlineUserStr(array[1].toString());
							stats.setIpNum(Integer.valueOf(array[3].toString()));
							stats.setTime(date);
							stats.setSysNum(CustomerContextHolder.getSystemNum());
							onlineDetailList.add(stats);
						}
						onlineDetailStatsService.saveBatch(onlineDetailList);
					}
				}
			}
		}
	}
	
	/**
	 * 查询渠道对应的当前在线人数
	 * @return
	 */
	public Map<String, Integer> findChannelCurrentUserAmount(){
		Map<String, Integer> map = new HashMap<String, Integer>();
		UserOnlineLog onlineLog = findLastLog();
		if(onlineLog!=null){
			if(!onlineLog.getUserStr().equals("")){
				StringBuilder sb = new StringBuilder();
				String[] arr = onlineLog.getUserStr().substring(0,onlineLog.getUserStr().length()-1).split(",");
				for(int i=0;i<arr.length;i++){
					if(i!=arr.length-1){
						sb.append("'").append(arr[i]).append("'").append(",");
					}else{
						sb.append("'").append(arr[i]).append("'");
					}
				}
				userOnlineLogDao.closeSession(DBSource.LOG);
				//渠道-对应的玩家数
				List<Object> onlineList = userOnlineLogDao.findSQL_("SELECT COUNT(USER_ID),CHANNEL FROM user_reg_log WHERE USER_ID IN("+sb.toString()+")"+" GROUP BY CHANNEL");
				if(onlineList!=null && onlineList.size()>0){
					for(int i=0;i<onlineList.size();i++){
						Object[] array = (Object[])onlineList.get(i);
						map.put(array[1].toString(), Integer.valueOf(array[0].toString()));
					}
				}
			}
		}
		return map;
	}
	
	public void setUserOnlineLogDao(UserOnlineLogDao userOnlineLogDao) {
		this.userOnlineLogDao = userOnlineLogDao;
	}

	public UserOnlineLogDao getUserOnlineLogDao() {
		return userOnlineLogDao;
	}
}
