package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserPayDetail;
import com.stats.bo.UserPayStats;
import com.stats.dao.UserPayDetailDao;
import com.stats.dao.UserPayStatsDao;

public class UserPayStatsService {

	private UserPayStatsDao userPayStatsDao;
	private UserPayDetailDao userPayDetailDao;
	
	/**
	 * 保存统计数据
	 * @param userPayStats
	 */
	public void save(UserPayStats userPayStats) {
		userPayStatsDao.save(userPayStats, DBSource.ADMIN);
	}
	
	/**
	 * 保存充值详情
	 * @param userPayDetail
	 */
	public void saveBatch(List<UserPayDetail> list){
		userPayDetailDao.saveBatch(list, DBSource.ADMIN);
	}
	


	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserPayStats> findPageList(int pageSize, int currentPage) {
		userPayStatsDao.closeSession(DBSource.ADMIN);
		return userPayStatsDao.findPage("from UserPayStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserPayStats> findPageListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userPayStatsDao.closeSession(DBSource.ADMIN);
		return userPayStatsDao.findPage("from UserPayStats stats where stats.sysNum = ? and stats.date between ? and ?", list, pageSize, currentPage);
	}
	
	/**
	 * 充值详情分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserPayDetail> findDetailPageList(String channel,int pageSize, int currentPage) {
		userPayDetailDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(channel);
		return userPayDetailDao.findPage("from UserPayDetail stats where stats.channel = ?", list, pageSize, currentPage);
	}
	
	/**
	 * 联运-充值详情分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserPayDetail> aoFindDetailPageList(String channel,Integer sysNum,int pageSize, int currentPage) {
		userPayDetailDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(channel);
		list.add(sysNum);
		return userPayDetailDao.findPage("from UserPayDetail stats where stats.channel = ? and sysNum=?", list, pageSize, currentPage);
	}
	
	/**
	 * 充值详情在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserPayDetail> findDetailPageListInDate(int pageSize, int currentPage,String channel, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(channel);
		list.add(startDate);
		list.add(endDate);
		userPayDetailDao.closeSession(DBSource.ADMIN);
		return userPayDetailDao.findPage("from UserPayDetail stats where stats.channel = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	/**
	 * 联运-充值详情在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserPayDetail> aoFindDetailPageListInDate(int pageSize, int currentPage,String channel,Integer sysNum, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(channel);
		list.add(sysNum);
		list.add(startDate);
		list.add(endDate);
		userPayDetailDao.closeSession(DBSource.ADMIN);
		return userPayDetailDao.findPage("from UserPayDetail stats where stats.channel = ? and stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	/**
	 * 渠道内充值排行榜
	 * @param dates
	 * @param channel
	 * @return
	 */
	public IPage<Object> findChannelPayRank(String[] dates,String channel,int pageSize,int pageIndex){
		StringBuilder sql = new StringBuilder();
		sql.append("select user_id,user_name,sys_num,max(user_level),sum(amount),count(user_id),max(last_login_time) from user_pay_detail where channel='");
		sql.append(channel);
		sql.append("'");
		if(dates!=null){
			sql.append(" and time between '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		sql.append(" group by user_id order by sum(amount) desc");
		userPayDetailDao.closeSession(DBSource.ADMIN);
		return userPayDetailDao.findSQL_Page_Have_Group(sql.toString(), new ArrayList<Object>(), pageSize, pageIndex);
	}
	
	/**
	 * 联运-渠道内充值排行榜
	 * @param dates
	 * @param channel
	 * @return
	 */
	public IPage<Object> aoFindChannelPayRank(String[] dates,String channel,Integer sysNum,int pageSize,int pageIndex){
		StringBuilder sql = new StringBuilder();
		sql.append("select user_id,user_name,sys_num,max(user_level),sum(amount),count(user_id),max(last_login_time) from user_pay_detail where channel='");
		sql.append(channel);
		sql.append("'");
		sql.append(" and sys_num=").append(sysNum);
		if(dates!=null){
			sql.append(" and time between '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		sql.append(" group by user_id order by sum(amount) desc");
		userPayDetailDao.closeSession(DBSource.ADMIN);
		return userPayDetailDao.findSQL_Page_Have_Group(sql.toString(), new ArrayList<Object>(), pageSize, pageIndex);
	}
	
	/**
	 * 删除某天采集的数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserPayStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		userPayDetailDao.closeSession(DBSource.ADMIN);
		userPayDetailDao.execute(sql.toString());
	}
	
	/**
	 * 删除某天采集的数据(详细充值信息)
	 * @param dateStr
	 */
	public void deletePayDetails(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserPayDetail where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		userPayDetailDao.closeSession(DBSource.ADMIN);
		userPayDetailDao.execute(sql.toString());
	}
	
	public void setUserPayStatsDao(UserPayStatsDao userPayStatsDao) {
		this.userPayStatsDao = userPayStatsDao;
	}

	public UserPayStatsDao getUserPayStatsDao() {
		return userPayStatsDao;
	}


	public UserPayDetailDao getUserPayDetailDao() {
		return userPayDetailDao;
	}


	public void setUserPayDetailDao(UserPayDetailDao userPayDetailDao) {
		this.userPayDetailDao = userPayDetailDao;
	}
}
