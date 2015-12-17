package com.log.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.log.dao.UserLogoutLogDao;

public class UserLogoutLogService {
	private UserLogoutLogDao userLogoutLogDao;
	
	/**
	 * 日志分页列表
	 * @param user
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<Object> findUserPageLogListByCondition(int lodoId,Date startDate,Date endDate, Integer currentPage, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and logout_time BETWEEN '").append(sdf.format(startDate)).append("' AND '").append(sdf.format(endDate)).append("'");
		}
		sql.append("select log.user_id, log.ip, log.logout_time, log.live_minutes, u.user_name,u.lodo_id from (select * from user_logout_log where 1=1");
		sql.append(queryStr.toString());
		sql.append(" union all select * from user_logout_log_bak where 1=1");
		sql.append(queryStr.toString());
		sql.append(") log, user u where log.user_id = u.user_id");
		if (lodoId!=0) {
			sql.append(" and u.lodo_id = ").append(lodoId);
		}
		userLogoutLogDao.closeSession(DBSource.LOG);
		return userLogoutLogDao.findSQL_Page(sql.toString(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 所有玩家的在线数据
	 * @return 
	 */
	public List<Object> getOnlineData() {
		String str = "select ifnull(sum(LIVE_MINUTES),0),ifnull(count(distinct(USER_ID)),0) from (select user_id,LIVE_MINUTES from user_logout_log union all select user_id,LIVE_MINUTES from user_logout_log_bak) outlog";
		userLogoutLogDao.closeSession(DBSource.LOG);
		return userLogoutLogDao.findSQL_(str);
	}
	
	/**
	 * 查询最后一次登出在给定日期前的用户的userId
	 * @param date
	 * @return
	 */
	public List<Object> findLossUserByDate(String date) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT USER_ID from (select * from user_logout_log union all select * from user_logout_log_bak) outlog GROUP BY USER_ID HAVING MAX(LOGOUT_TIME) <= '");
		sb.append(date);
		sb.append("'");
		userLogoutLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLogoutLogDao.findSQL_(sb.toString());
		return list;
	}
	
	public Integer getTotalLiveMinuteByUserId(String userId) {
		String str = "select case when sum(LIVE_MINUTES) > 0 then sum(LIVE_MINUTES) else 0 end from (select * from user_logout_log union all select * from user_logout_log_bak) outlog where USER_ID = " + userId;
		userLogoutLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLogoutLogDao.findSQL_(str);
		if (list != null && list.size() > 0) {
			return Integer.valueOf(list.get(0).toString());
		}
		return 0;
	}
	/**
	 * 备份日志
	 */
	public void backup(String date){
		String backup = "insert into user_logout_log_bak select * from user_logout_log where LOGOUT_TIME<='"+date+"'";
		String delete = "delete from user_logout_log where LOGOUT_TIME<='"+date+"'";
		userLogoutLogDao.closeSession(DBSource.LOG);
		userLogoutLogDao.executeSQL_(backup);
		userLogoutLogDao.executeSQL_(delete);
	}
	
	/**
	 * 玩家在线时长
	 * @param dates
	 * @return
	 */
	public List<Object> getUserOnLineAvg(String[] dates) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct user_id), sum(LIVE_MINUTES) from (select * from user_logout_log union all select * from user_logout_log_bak) outlog");
		sb.append(" where outlog.logout_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("'");
		sb.append(" and '");
		sb.append(dates[1]);
		sb.append("'");
		userLogoutLogDao.closeSession(DBSource.LOG);
		return userLogoutLogDao.findSQL_(sb.toString());
	}
	
	public void setUserLogoutLogDao(UserLogoutLogDao userLogoutLogDao) {
		this.userLogoutLogDao = userLogoutLogDao;
	}

	public UserLogoutLogDao getUserLogoutLogDao() {
		return userLogoutLogDao;
	}
}
