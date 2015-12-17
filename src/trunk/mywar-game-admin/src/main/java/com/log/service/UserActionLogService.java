package com.log.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.util.DateUtil;
import com.log.dao.UserActionLogDao;
import com.stats.bo.UserNodeStats;
import com.system.manager.DataSourceManager;

public class UserActionLogService {
	private UserActionLogDao userActionLogDao;

	/**
	 * 指定日期内指定动作的数量
	 * 
	 * @param actionId
	 * @param dates
	 * @return
	 */
	// public int findAmountByActionAndDate(Integer actionId, String[] dates) {
	// StringBuffer sb = new StringBuffer();
	// sb.append("SELECT COUNT(1) FROM user_action_log WHERE ACTION_ID = ");
	// sb.append(actionId);
	// sb.append(" AND ");
	// sb.append("TIME BETWEEN '");
	// sb.append(dates[0]);
	// sb.append("' AND '");
	// sb.append(dates[1]);
	// sb.append("'");
	//
	// userActionLogDao.closeSession(DBSource.LOG);
	// List<Object> list = userActionLogDao.findSQL_(sb.toString());
	// int amount = Integer.parseInt(list.get(0).toString());
	// return amount;
	// }

	/**
	 * 截止到给定日期的玩家注册总数
	 * 
	 * @param dates
	 * @return
	 */
	public int findRegAmountByDate(String date) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(1) from user_action_log WHERE ACTION_ID = 3 AND TIME <= '");
		sb.append(date);
		sb.append("'");

		userActionLogDao.closeSession(DBSource.LOG);
		List<Object> list = userActionLogDao.findSQL_(sb.toString());
		int amount = Integer.parseInt(list.get(0).toString());
		return amount;
	}

	/**
	 * 采集玩家节点日志
	 * 
	 * @param dates
	 * @return
	 */
	public List<Object> collectUserActionData(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ACTION_ID,COUNT(USER_ID) from user_action_log WHERE TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" GROUP BY ACTION_ID");
		userActionLogDao.closeSession(DBSource.LOG);
		return userActionLogDao.findSQL_(sb.toString());
	}

	/**
	 * 备份日志
	 */
	public void backup(String date) {
		String backup = "insert into user_action_log_bak select * from user_action_log where TIME<='"
				+ date + "'";
		String delete = "delete from user_action_log where TIME<='" + date
				+ "'";
		userActionLogDao.closeSession(DBSource.LOG);
		userActionLogDao.executeSQL_(backup);
		userActionLogDao.executeSQL_(delete);
	}

	/**
	 * 统计打点信息
	 * 
	 * @param startTime
	 * @param endTime
	 * @param actionId
	 * @return
	 */
	public UserNodeStats statsActionLog(Date startTime, Date endTime,
			Integer actionId) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		Date[] arr = DateUtil.dayStrDiff(startTime, endTime);
		StringBuffer sql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();

		String items = "count(action_id) as times, count(distinct user_id) as userNum";

		if (startTime != null && endTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and action_id = " + actionId)
					.append(" and TIME BETWEEN '")
					.append(sdf.format(startTime)).append("' AND '")
					.append(sdf.format(endTime)).append("'");
		}

		sql.append(manager.getUnionSql("user_action_log", arr, queryStr, items));

		userActionLogDao.closeSession(DBSource.LOG);
		List<Object> list = userActionLogDao.findSQL_(sql.toString());

		UserNodeStats node = new UserNodeStats();
		node.setActionId(actionId);

		for (int i = 0; i < list.size(); i++) {
			node.setTimes(node.getTimes()
					+ Integer.parseInt(((Object[]) list.get(i))[0].toString()));
		}

		node.setUserNum(getStatsUserNum(startTime, endTime, actionId));
		return node;
	}

	/**
	 * 统计打点的用户数
	 * 
	 * @param startTime
	 * @param endTime
	 * @param actionId
	 * @return
	 */
	public int getStatsUserNum(Date startTime, Date endTime, Integer actionId) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		Date[] arr = DateUtil.dayStrDiff(startTime, endTime);
		StringBuffer sql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();

		String items = " user_id ";

		if (startTime != null && endTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and action_id = " + actionId)
					.append(" and TIME BETWEEN '")
					.append(sdf.format(startTime)).append("' AND '")
					.append(sdf.format(endTime)).append("'");
		}

		sql.append(manager.getUnionSql("user_action_log", arr, queryStr, items));

		userActionLogDao.closeSession(DBSource.LOG);
		List<Object> list = userActionLogDao.findSQL_(sql.toString());

		Map<String, String> userIdMap = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			String userId = (String) list.get(i);
			userIdMap.put(userId, userId);
		}

		return userIdMap.size();
	}

	/**
	 * 根据actionId获取节点统计
	 * 
	 * @param startTime
	 * @param endTime
	 * @param actionId
	 * @return
	 */
	public UserNodeStats findActionLoglist(Date startTime, Date endTime,
			Integer actionId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(action_id) as times, count(distinct user_id) as userNum from user_action_log where action_id = "
				+ actionId);
		if (startTime != null && endTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sql.append(" and TIME BETWEEN '");
			sql.append(sdf.format(startTime));
			sql.append("'");
			sql.append(" and '");
			sql.append(sdf.format(endTime));
			sql.append("'");
		}

		userActionLogDao.closeSession(DBSource.LOG);
		List<Object> list = userActionLogDao.findSQL_(sql.toString());

		UserNodeStats node = new UserNodeStats();
		node.setActionId(actionId);
		if (list.size() == 1) {
			node.setTimes(Integer.parseInt(((Object[]) list.get(0))[0]
					.toString()));
			node.setUserNum(Integer.parseInt(((Object[]) list.get(0))[1]
					.toString()));
		}
		return node;
	}
	
	/**
	 * 大富翁抽奖
	 * @param startTime
	 * @param endTime
	 * @param actionId
	 * @return
	 */
	public List<Object> loginDrawActionLog(String[] dates,
			Integer actionId) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_action_log", dates);
		
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct(user_id)), count(user_id) from ");
		sb.append(tableName);
		sb.append(" where action_id = ");
		sb.append(actionId);
		
		userActionLogDao.closeSession(DBSource.LOG);
		List<Object> list = userActionLogDao.findSQL_(sb.toString());
		return list;
	}

	public void setUserActionLogDao(UserActionLogDao userActionLogDao) {
		this.userActionLogDao = userActionLogDao;
	}

	public UserActionLogDao getUserActionLogDao() {
		return userActionLogDao;
	}
}
