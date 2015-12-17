package com.log.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.log.dao.UserActionLogBakDao;
import com.stats.bo.UserNodeStats;

/**
 * 用户买点备份表
 * 
 * @author yezp
 */
public class UserActionLogBakService {

	private UserActionLogBakDao userActionLogBakDao;

	/**
	 * 根据actionId获取节点统计
	 * 
	 * @param startTime
	 * @param endTime
	 * @param actionId
	 * @return
	 */
	public UserNodeStats findActionLogBakList(Date startTime, Date endTime,
			Integer actionId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(action_id) as times, count(distinct user_id) as userNum from user_action_log_bak where action_id = "
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
		
		userActionLogBakDao.closeSession(DBSource.LOG);
		List<Object> list = userActionLogBakDao.findSQL_(sql.toString());
		
		UserNodeStats node = new UserNodeStats();
		node.setActionId(actionId);
		if (list.size() == 1) {
			node.setTimes(Integer.parseInt(((Object[]) list.get(0))[0].toString()));
			node.setUserNum(Integer.parseInt(((Object[]) list.get(0))[1].toString()));
		}
		return node;
	}

	public UserActionLogBakDao getUserActionLogBakDao() {
		return userActionLogBakDao;
	}

	public void setUserActionLogBakDao(UserActionLogBakDao userActionLogBakDao) {
		this.userActionLogBakDao = userActionLogBakDao;
	}

}
