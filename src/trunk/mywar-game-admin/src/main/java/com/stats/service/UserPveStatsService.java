package com.stats.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserPveStats;
import com.stats.dao.UserPveStatsDao;

public class UserPveStatsService {

	private UserPveStatsDao userPveStatsDao;
	
	/** 删除服务器之前的统计数据 */
	public void delBeforeData(){
		userPveStatsDao.closeSession(DBSource.ADMIN);
		userPveStatsDao.execute("delete from UserPveStats where sysNum = " + CustomerContextHolder.getSystemNum());
	}
	
	/**
	 * 保存统计数据
	 * @param statsList
	 */
	public void save(List<UserPveStats> statsList){
		userPveStatsDao.saveBatch(statsList, DBSource.ADMIN);
	}
	
	public IPage<UserPveStats> findPageList(Integer searchEnterLevel, String orderColumn, String order, int pageSize, int pageIndex) {
		List<Object> args = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from UserPveStats where 1=1");
		hql.append(" and sysNum = ").append(CustomerContextHolder.getSystemNum());
		if (searchEnterLevel != null) {
			hql.append(" and enterLevel = ").append(searchEnterLevel);
		}
		if (!Tools.isEmpty(orderColumn)) {
			hql.append(" order by ").append(orderColumn);
			if (!Tools.isEmpty(order)) {
				hql.append(" ").append(order);
			}
		}
		userPveStatsDao.closeSession(DBSource.ADMIN);
		return userPveStatsDao.findPage(hql.toString(), args, pageSize, pageIndex);
	}
	
	/**
	 * 查询关卡攻打信息列表
	 * @param startTime
	 * @param endTime
	 */
	public Map<Integer, UserPveStats> findUserPveStatsMap(Date startTime, Date endTime){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT FORCES_ID,SUM(ATK_USER_NUM),SUM(ATK_NUM),SUM(WIN_NUM),SUM(FAIL_NUM),SUM(DRAW_NUM) FROM user_pve_stats where SYS_NUM = ");
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
		sb.append(" group by FORCES_ID");
		userPveStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = userPveStatsDao.findSQL_(sb.toString());
		Map<Integer, UserPveStats> map = new HashMap<Integer, UserPveStats>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				UserPveStats stats = new UserPveStats();
				stats.setForcesId(Integer.valueOf(arr[0].toString()));
				stats.setAtkUserNum(Integer.valueOf(arr[1].toString()));
				stats.setAtkNum(Integer.valueOf(arr[2].toString()));
				stats.setWinNum(Integer.valueOf(arr[3].toString()));
				stats.setFailNum(Integer.valueOf(arr[4].toString()));
				stats.setDrawNum(Integer.valueOf(arr[5].toString()));
				map.put(stats.getForcesId(), stats);
			}
		}
		return map;
	}
	
	/**
	 * 批量保存采集数据
	 * @param list
	 */
	public void saveBatch(List<UserPveStats> list){
		userPveStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	public void setUserPveStatsDao(UserPveStatsDao userPveStatsDao) {
		this.userPveStatsDao = userPveStatsDao;
	}

	public UserPveStatsDao getUserPveStatsDao() {
		return userPveStatsDao;
	}
	
}
