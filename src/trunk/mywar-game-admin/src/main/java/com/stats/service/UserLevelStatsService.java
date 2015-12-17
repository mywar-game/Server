package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.LevelStats;
import com.stats.bo.UserLevelStats;
import com.stats.dao.LevelStatsDao;
import com.stats.dao.UserLevelStatsDao;

public class UserLevelStatsService {
	private UserLevelStatsDao userLevelStatsDao;
	private LevelStatsDao levelStatsDao;
	public UserLevelStatsDao getUserLevelStatsDao() {
		return userLevelStatsDao;
	}

	public void setUserLevelStatsDao(UserLevelStatsDao userLevelStatsDao) {
		this.userLevelStatsDao = userLevelStatsDao;
	}
	/**
	 * 保存数据
	 * @param stats
	 */
	public void save(UserLevelStats stats){
		userLevelStatsDao.save(stats, DBSource.ADMIN);
	}
	
	/**
	 * 批量保存数据
	 * @param list
	 */
	public void saveBatch(List<LevelStats> list){
		levelStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserLevelStats> findList(int pageSize, int currentPage) {
		userLevelStatsDao.closeSession(DBSource.ADMIN);
		return userLevelStatsDao.findPage("from UserLevelStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserLevelStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userLevelStatsDao.closeSession(DBSource.ADMIN);
		return userLevelStatsDao.findPage("from UserLevelStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}

	/**
	 * 查询等级分布数据
	 * @param channel
	 * @param sysNum
	 * @param order
	 * @param queryDate
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public IPage<Object> findLevelStats(Integer channel,Integer sysNum,String order,String queryDate,int pageSize,int pageIndex){
		StringBuilder sql = new StringBuilder();
		sql.append("select user_level,sum(reg_num),sum(user_num),sum(pay_user_num),sum(pay_amount),sum(pay_times),sum(buy_tool_consume)");
		sql.append(" from level_stats where 1=1");
		if(channel!=null && channel.intValue()!=0){
			sql.append(" and channel=").append(channel);
		}
		if(sysNum!=null && sysNum.intValue()!=0){
			sql.append(" and sys_num=").append(sysNum);
		}
		sql.append(" and time='").append(queryDate).append("'");
		sql.append(" group by user_level");
		if(order==null || order.equals("1")){//按等级排序
			sql.append(" order by user_level");
		}else if(order.equals("2")){//按账号数排序
			sql.append(" order by sum(reg_num) desc");
		}else{//按付费人数排序
			sql.append(" order by sum(pay_user_num) desc");
		}
		return levelStatsDao.findSQL_Page_Have_Group(sql.toString(), new ArrayList<Object>(), pageSize, pageIndex);
	}
	
	/**
	 * 删除某天采集的数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserLevelStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		sql.append(" and sysNum = ");
		sql.append(CustomerContextHolder.getSystemNum());
		levelStatsDao.closeSession(DBSource.ADMIN);
		levelStatsDao.execute(sql.toString());
	}
	
	public LevelStatsDao getLevelStatsDao() {
		return levelStatsDao;
	}

	public void setLevelStatsDao(LevelStatsDao levelStatsDao) {
		this.levelStatsDao = levelStatsDao;
	}
}
