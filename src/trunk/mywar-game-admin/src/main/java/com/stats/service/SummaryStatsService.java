package com.stats.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.stats.bo.SummaryStats;
import com.stats.dao.SummaryStatsDao;

public class SummaryStatsService {
	private SummaryStatsDao summaryStatsDao;

	public SummaryStatsDao getSummaryStatsDao() {
		return summaryStatsDao;
	}

	public void setSummaryStatsDao(SummaryStatsDao summaryStatsDao) {
		this.summaryStatsDao = summaryStatsDao;
	}
	/**
	 * 批量保存
	 * @param list
	 */
	public void saveBatch(List<SummaryStats> list){
		summaryStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	/**
	 * 今日汇总数据查询
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> findSummaryData(String[] dates){
		StringBuilder sql = new StringBuilder();
		//根据渠道分组的当前在线人数、充值总数、充值总人数、购买道具消耗
		sql.append("select channel,sum(current_online_amount),sum(pay_num),sum(pay_user_num),sum(buy_tool_consume) from summary_stats a,");
		sql.append("(select max(half_hour_index) as maxIndex from summary_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("') b");
		sql.append(" where a.half_hour_index=b.maxIndex");
		sql.append(" and time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" group by channel");
		summaryStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = summaryStatsDao.findSQL_(sql.toString());
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
	 * 联运-今日汇总数据查询
	 * @param dates
	 * @param channel
	 * @return
	 */
	public Map<String, Object[]> aoFindSummaryData(String[] dates,String channel){
		StringBuilder sql = new StringBuilder();
		//各服务器的当前在线人数、充值总数、充值总人数、购买道具消耗
		sql.append("select sys_num,current_online_amount,pay_num,pay_user_num,buy_tool_consume from summary_stats a,");
		sql.append("(select max(half_hour_index) as maxIndex from summary_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("') b");
		sql.append(" where a.half_hour_index=b.maxIndex");
		sql.append(" and time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and channel='").append(channel).append("'");
		summaryStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = summaryStatsDao.findSQL_(sql.toString());
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
	 * 历史汇总数据查询
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> findSummaryDataHistory(String[] dates){
		StringBuilder sql = new StringBuilder();
		//根据渠道分组的充值总数、购买道具消耗
		sql.append("select channel,sum(pay_num),sum(buy_tool_consume),group_concat(pay_user_str),sum(pay_total_times) from summary_stats a,");
		sql.append("(select max(half_hour_index) as halfHourIndex,time as bTime from summary_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("' group by time) b");
		sql.append(" where a.half_hour_index=b.halfHourIndex and a.time=b.bTime");
		sql.append(" and time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" group by channel");
		summaryStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = summaryStatsDao.findSQL_(sql.toString());
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
	 * 联运-历史汇总数据查询
	 * @param dates
	 * @param channel
	 * @return
	 */
	public Map<String, Object[]> aoFindSummaryDataHistory(String[] dates,String channel){
		StringBuilder sql = new StringBuilder();
		//根据渠道分组的充值总数、购买道具消耗
		sql.append("select sys_num,sum(pay_num),sum(buy_tool_consume),group_concat(pay_user_str),sum(pay_total_times) from summary_stats a,");
		sql.append("(select max(half_hour_index) as halfHourIndex,time as bTime from summary_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("' group by time) b");
		sql.append(" where a.half_hour_index=b.halfHourIndex and a.time=b.bTime");
		sql.append(" and time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and channel='").append(channel).append("'");
		sql.append(" group by sys_num");
		summaryStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = summaryStatsDao.findSQL_(sql.toString());
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), arr);
			}
		}
		return map;
	}
	
	
}
