package com.stats.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserNodeLossStats;
import com.stats.dao.UserNodeLossStatsDao;

public class UserNodeLossStatsService {
	private UserNodeLossStatsDao userNodeLossStatsDao;
	
	/**
	 * 查询玩家节点流失数据
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Map<Integer, Integer> findActionIdAndUserAmount(Date startTime, Date endTime) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ACTION_ID,SUM(NUM) FROM user_node_loss_stats where SYS_NUM = ");
		sb.append(CustomerContextHolder.getSystemNum());
		if(startTime!=null && endTime!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sb.append(" and TIME BETWEEN '");
			sb.append(sdf.format(startTime));
			sb.append("'");
			sb.append(" and '");
			sb.append(sdf.format(endTime));
			sb.append("'");
		}
		sb.append(" group by ACTION_ID");
		userNodeLossStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = userNodeLossStatsDao.findSQL_(sb.toString());
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < list.size(); i++) {
			int actionId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
			int userAmount = Integer.parseInt(((Object[]) list.get(i))[1].toString());
			map.put(actionId, userAmount);
		}
		return map;
	}
	/**
	 * 批量保存
	 * @param list
	 */
	public void saveBatch(List<UserNodeLossStats> list){
		userNodeLossStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from user_node_loss_stats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		sql.append(" and SYS_NUM = ");
		sql.append(CustomerContextHolder.getSystemNum());
		userNodeLossStatsDao.closeSession(DBSource.ADMIN);
		userNodeLossStatsDao.execute(sql.toString());
	}
	
	public UserNodeLossStatsDao getUserNodeLossStatsDao() {
		return userNodeLossStatsDao;
	}

	public void setUserNodeLossStatsDao(UserNodeLossStatsDao userNodeLossStatsDao) {
		this.userNodeLossStatsDao = userNodeLossStatsDao;
	}
}
