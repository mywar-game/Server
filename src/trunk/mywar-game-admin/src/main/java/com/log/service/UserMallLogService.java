package com.log.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.log.dao.UserMallLogDao;

public class UserMallLogService {
	private UserMallLogDao userMallLogDao;

	public UserMallLogDao getUserMallLogDao() {
		return userMallLogDao;
	}

	public void setUserMallLogDao(UserMallLogDao userMallLogDao) {
		this.userMallLogDao = userMallLogDao;
	}
	
	/**
	 * 查询商城元宝花费的排行
	 * @param dates
	 * @param rank
	 * @return
	 */
	public List<Object> findCostRank(String[] dates,int rank){
		StringBuffer sb = new StringBuffer();
		sb.append("select treasure_id,sum(cost) from user_mall_log where time between '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by treasure_id order by sum(cost) desc limit 0,");
		sb.append(rank);
		userMallLogDao.closeSession(DBSource.LOG);
		return userMallLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 查询商城购买次数的排行
	 * @param dates
	 * @param rank
	 * @return
	 */
	public List<Object> findBuyNumRank(String[] dates,int rank){
		StringBuffer sb = new StringBuffer();
		sb.append("select treasure_id,count(user_id) from user_mall_log where time between '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by treasure_id order by count(user_id) desc limit 0,");
		sb.append(rank);
		userMallLogDao.closeSession(DBSource.LOG);
		return userMallLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 查询商城购买人数的排行
	 * @param dates
	 * @param rank
	 * @return
	 */
	public List<Object> findBuyUserNumRank(String[] dates,int rank){
		StringBuffer sb = new StringBuffer();
		sb.append("select treasure_id,count(DISTINCT(user_id)) from user_mall_log where time between '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by treasure_id order by count(DISTINCT(user_id)) desc limit 0,");
		sb.append(rank);
		userMallLogDao.closeSession(DBSource.LOG);
		return userMallLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 根据渠道分组查询各渠道在某时间段内的总消耗
	 * @param dates
	 * @return
	 */
	public Map<String, Integer> findBuyToolConsumeByChannel(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("select reg.channel,sum(log.cost) from user_mall_log log,user_reg_log reg where log.time between '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" and log.user_id=reg.user_id");
		sb.append(" group by reg.channel");
		userMallLogDao.closeSession(DBSource.LOG);
		List<Object> list = userMallLogDao.findSQL_(sb.toString());
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), Integer.valueOf(arr[1].toString()));
			}
		}
		return map;
	}
	
	/**
	 * 查询时间段内商城道具的售卖情况--根据渠道分组
	 * @param dates
	 * @return
	 */
	public List<Object> findMallStatsDataByChannel(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("select reg.channel,mall.treasure_id,ifnull(sum(mall.buy_num),0),ifnull(sum(mall.cost),0),ifnull(count(distinct(mall.user_id)),0)");
		sb.append(" from user_mall_log mall,user_reg_log reg where mall.time between '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" and mall.cost>0");
		sb.append(" and mall.user_id=reg.user_id");
		sb.append(" group by reg.channel,mall.treasure_id");
		userMallLogDao.closeSession(DBSource.LOG);
		return userMallLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 备份日志
	 */
	public void backup(String date){
		String backup = "insert into user_mall_log_bak select * from user_mall_log where TIME<='"+date+"'";
		String delete = "delete from user_mall_log where TIME<='"+date+"'";
		userMallLogDao.closeSession(DBSource.LOG);
		userMallLogDao.executeSQL_(backup);
		userMallLogDao.executeSQL_(delete);
	}
}
