package com.log.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.log.bo.UserFirendLog;
import com.log.dao.UserFirendLogDao;
import com.stats.bo.UserFriendVisitStats;

public class UserFirendLogService {
	
	private UserFirendLogDao userFirendLogDao;

	public UserFirendLogDao getUserFirendLogDao() {
		return userFirendLogDao;
	}

	public void setUserFirendLogDao(UserFirendLogDao userFirendLogDao) {
		this.userFirendLogDao = userFirendLogDao;
	}
	
	public void findUserFriendVisitStats(String[] dates, UserFriendVisitStats userFriendVisitStats) {

		Integer visitTimes = 0;
		Integer visitUserNum = 0;
		Integer visitPeak = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(1), COUNT(DISTINCT(OPERRATION_USER_ID)) FROM `user_firend_log` WHERE OPERATION_TYPE = 6 AND OPERATION_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		userFirendLogDao.closeSession(DBSource.LOG);
		List<Object> list = userFirendLogDao.findSQL_(sb.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				visitTimes = Integer.valueOf(((Object[]) list.get(i))[0].toString());
				visitUserNum = Integer.valueOf(((Object[]) list.get(i))[1].toString());
			}
		}

		sb = new StringBuffer();
		sb.append("SELECT IFNULL(MAX(a.visit_num),0) FROM ( SELECT OPERRATION_USER_ID AS user_id, COUNT(1) AS visit_num FROM `user_firend_log` WHERE OPERATION_TYPE = 6 AND OPERATION_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("' GROUP BY OPERRATION_USER_ID) a");
		userFirendLogDao.closeSession(DBSource.LOG);
		list = userFirendLogDao.findSQL_(sb.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				visitPeak = Integer.valueOf(list.get(i).toString());
			}
		}
		userFriendVisitStats.setVisitTimes(visitTimes);
		userFriendVisitStats.setVisitUserNum(visitUserNum);
		userFriendVisitStats.setVisitPeak(visitPeak);
	}
	
	/**
	 * 分页查询
	 * @param currentPage 页面显示总数
	 * @param pageSize 页数
	 * @return
	 */
	public IPage<UserFirendLog> findUserFirendLogByPage(Integer currentPage, Integer pageSize) {
		userFirendLogDao.closeSession(DBSource.LOG);
		IPage<UserFirendLog> userFirendLogList = userFirendLogDao.findPage("select new UserFirendLog(ufl.operrationUserId ,ufl.beInvitedUserId,ufl.operationType,ufl.operationTime,user.userName,user.name) from UserFirendLog ufl, User user where ufl.operrationUserId = user.userId order by ufl.operationTime DESC", new ArrayList<Object>(), pageSize, currentPage);
		return userFirendLogList;
	}
	
	/**
	 * 按照玩家用户ID查询
	 * @param currentPage
	 * @param pageSize
	 * @param querySql
	 * @return
	 */
	public IPage<UserFirendLog> findUserFirendLogByCondition(String userId, Integer currentPage, Integer pageSize) {
		userFirendLogDao.closeSession(DBSource.LOG);
		StringBuffer sb = new StringBuffer();
		sb.append("select new UserFirendLog(ufl.operrationUserId,ufl.beInvitedUserId,ufl.operationType,ufl.operationTime,user.userName,user.name) ");
		sb.append("from UserFirendLog  ufl , User user where 1 = 1 ");
		sb.append(" and ufl.operrationUserId = user.userId");
		if (userId != null) {
			sb.append(" and ufl.operrationUserId = '").append(userId).append("'");
		}
		sb.append(" order by ufl.operationTime DESC");
		IPage<UserFirendLog> userFirendLogList = userFirendLogDao.findPage(new String(sb), new ArrayList<Object>(), pageSize, currentPage);
		return userFirendLogList;
	}
	
}
