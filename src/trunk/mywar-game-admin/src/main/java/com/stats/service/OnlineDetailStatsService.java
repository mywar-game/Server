package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.util.DateUtil;
import com.stats.bo.OnlineDetailStats;
import com.stats.dao.OnlineDetailStatsDao;

public class OnlineDetailStatsService {
	private OnlineDetailStatsDao onlineDetailStatsDao;
	public OnlineDetailStatsDao getOnlineDetailStatsDao() {
		return onlineDetailStatsDao;
	}
	public void setOnlineDetailStatsDao(OnlineDetailStatsDao onlineDetailStatsDao) {
		this.onlineDetailStatsDao = onlineDetailStatsDao;
	}
	/**
	 * 批量保存
	 * @param list
	 */
	public void saveBatch(List<OnlineDetailStats> list){
		onlineDetailStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	/**
	 * 根据渠道分组查询玩家在某个时间段内的最高在线人数
	 * @param dates
	 * @return
	 */
	public Map<String, Double> findMaxOnlineAmount(String[] dates){
		StringBuilder sql = new StringBuilder();
		//当日不同维度在线人数总和的最大值
		sql.append("select log.channel,ifnull(max(log.total),0) from (select channel,time,five_minute_index,sum(online_amount) as total from online_detail_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" group by channel,time,five_minute_index) log group by log.channel");
		onlineDetailStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = onlineDetailStatsDao.findSQL_(sql.toString());
		Map<String, Double> map = new HashMap<String, Double>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), Double.valueOf(arr[1].toString()));
			}
		}
		return map;
	}
	
	/**
	 * 根据渠道分组查询玩家在某个时间段内的最高在线时间
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> findMaxOnlineTimeAmount(String[] dates){
		StringBuilder sql = new StringBuilder();
		sql.append("select log1.channel,log1.time,log1.five_minute_index from (");
		sql.append("select channel,time,five_minute_index,sum(online_amount) as sumTotal from online_detail_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" group by channel,time,five_minute_index) log1");
		sql.append(" right join(select log.channel,max(log.total) as maxTotal from (");
		sql.append("select channel,time,five_minute_index,sum(online_amount) as total from online_detail_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" group by channel,time,five_minute_index) log group by log.channel) log2");
		sql.append(" on log1.channel=log2.channel and log1.sumTotal=log2.maxTotal");
		onlineDetailStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = onlineDetailStatsDao.findSQL_(sql.toString());
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), arr);
			}
		}
		return map;
	}
	
	/**
	 * 根据平台分组查询玩家某段时间内的平均在线人数
	 * @param dates
	 * @return
	 */
	public Map<String, Integer> findAvgOnlineAmount(String[] dates){
		StringBuilder sql = new StringBuilder();
		sql.append("select channel,ifnull(avg(online_amount),0) from online_detail_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" group by channel");
		onlineDetailStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = onlineDetailStatsDao.findSQL_(sql.toString());
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), Double.valueOf(arr[1].toString()).intValue());
			}
		}
		return map;
	}
	
	/**
	 * 联运-在线数据
	 * @param dates
	 * @param channel
	 * @return
	 */
	public Map<String, Object[]> aoFindOnlineData(String[] dates,String channel){
		StringBuilder sql = new StringBuilder();
		sql.append("select log1.sys_num,log2.time,log2.five_minute_index,log3.maxOnline,log3.avgOnline from (");
		sql.append("select sys_num,max(online_amount) as maxOnline from online_detail_stats where time between'");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and channel='").append(channel).append("'");
		sql.append(" group by sys_num) log1");
		sql.append(" left join (select sys_num,time,five_minute_index,online_amount from online_detail_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and channel='").append(channel).append("'");
		sql.append(") log2 on log1.sys_num=log2.sys_num and log1.maxOnline=log2.online_amount left join (");
		sql.append("select sys_num,ifnull(max(online_amount),0) as maxOnline,ifnull(avg(online_amount),0) as avgOnline from online_detail_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and channel='").append(channel).append("'");
		sql.append(" group by sys_num) log3 on log1.sys_num=log3.sys_num");
		onlineDetailStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = onlineDetailStatsDao.findSQL_(sql.toString());
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), arr);
			}
		}
		return map;
	}
	
	/**
	 * 联运-根据平台分组查询玩家某段时间内的最高在线人数、平均在线人数
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> aoFindAvgOnlineAmount(String[] dates,String channel){
		StringBuilder sql = new StringBuilder();
		sql.append("select sys_num,ifnull(max(online_amount),0),ifnull(avg(online_amount),0) from online_detail_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and channel='").append(channel).append("'");
		sql.append(" group by sys_num");
		onlineDetailStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = onlineDetailStatsDao.findSQL_(sql.toString());
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), arr);
			}
		}
		return map;
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<OnlineDetailStats> findListInDate(int pageSize, int currentPage, Date queryDate,Integer serverId,String pid) {
		List<Object> list = new ArrayList<Object>();
		list.add(queryDate);
		list.add(serverId);
		list.add(pid);
		onlineDetailStatsDao.closeSession(DBSource.ADMIN);
		return onlineDetailStatsDao.findPage("from OnlineDetailStats stats where stats.time = ? and stats.sysNum = ? and stats.channel = ?", list, pageSize, currentPage);
	}
	
	/**
	 * 联运-在线数据
	 * @param pageSize
	 * @param currentPage
	 * @param queryDate
	 * @param sysNum
	 * @return
	 */
	public IPage<Object> aoFindList(int pageSize, int currentPage, String queryDate,Integer sysNum){
		StringBuilder sql = new StringBuilder();
		sql.append("select sys_num,five_minute_index,sum(online_amount),sum(ip_num) from online_detail_stats where time='");
		sql.append(queryDate);
		sql.append("'");
		sql.append(" and sys_num=").append(sysNum);
		sql.append(" group by sys_num,five_minute_index order by five_minute_index");
		onlineDetailStatsDao.closeSession(DBSource.ADMIN);
		return onlineDetailStatsDao.findSQL_Page_Have_Group(sql.toString(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	public List<Object> findOnlineStats(Date queryDate,Integer serverId,String pid){
		StringBuilder sb = new StringBuilder();
		sb.append("select ifnull(max(online_amount),0),ifnull(avg(online_amount),0),ifnull(max(ip_num),0),ifnull(avg(ip_num),0) from online_detail_stats where time='");
		sb.append(DateUtil.dateToString(queryDate, DateUtil.LONG_DATE_FORMAT));
		sb.append("'");
		sb.append(" and sys_num=");
		sb.append(serverId);
		sb.append(" and channel='");
		sb.append(pid);
		sb.append("'");
		onlineDetailStatsDao.closeSession(DBSource.ADMIN);
		return onlineDetailStatsDao.findSQL_(sb.toString());
	}
	
	public List<Object> aoFindOnlineStats(Date queryDate,Integer sysNum){
		StringBuilder sb = new StringBuilder();
		sb.append("select ifnull(max(log.amount),0),ifnull(avg(log.amount),0),ifnull(max(log.num),0),ifnull(avg(log.num),0) from (");
		sb.append("select sum(online_amount) as amount,sum(ip_num) as num from online_detail_stats where time='");
		sb.append(DateUtil.dateToString(queryDate, DateUtil.LONG_DATE_FORMAT));
		sb.append("'");
		sb.append(" and sys_num=").append(sysNum);
		sb.append(" group by sys_num,five_minute_index) log");
		onlineDetailStatsDao.closeSession(DBSource.ADMIN);
		return onlineDetailStatsDao.findSQL_(sb.toString());
	}
}
