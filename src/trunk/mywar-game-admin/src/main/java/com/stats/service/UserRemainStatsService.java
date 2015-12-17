package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.util.DateUtil;
import com.stats.bo.UserRemainStats;
import com.stats.dao.UserRemainStatsDao;

public class UserRemainStatsService {
	private UserRemainStatsDao userRemainStatsDao;
	
	/**
	 * 保存或更新数据
	 * @param stats
	 */
	public void save(UserRemainStats stats){
		userRemainStatsDao.closeSession(DBSource.ADMIN);
		userRemainStatsDao.saveOrUpdate(stats);
	}

	/**
	 * 某个服务器在一段时间内的留存数据
	 * @return
	 */
	public Map<String, UserRemainStats> findDataInSomeDay(Date startDate , Date endDate){
		Map<String, UserRemainStats> map = new HashMap<String, UserRemainStats>();
		userRemainStatsDao.closeSession(DBSource.ADMIN);
		List<Object> args = new ArrayList<Object>();
		args.add(CustomerContextHolder.getSystemNum());
		args.add(startDate);
		args.add(endDate);
		List<UserRemainStats> list = userRemainStatsDao.find("from UserRemainStats stats where stats.sysNum = ? and stats.time between ? and ?", args);
		if(list!=null && list.size()>0){
			for(UserRemainStats stats : list){
				map.put(DateUtil.dateToString(stats.getTime(), DateUtil.LONG_DATE_FORMAT), stats);
			}
		}
		return map;
	}
	
	/**
	 * 当前服的数据
	 * @return
	 */
	public Map<String, UserRemainStats> findNowServerData(){
		Map<String, UserRemainStats> map = new HashMap<String, UserRemainStats>();
		userRemainStatsDao.closeSession(DBSource.ADMIN);
		List<UserRemainStats> list = userRemainStatsDao.find("from UserRemainStats where sysNum = "+CustomerContextHolder.getSystemNum(), DBSource.ADMIN);
		if(list!=null && list.size()>0){
			for(UserRemainStats stats : list){
				map.put(DateUtil.dateToString(stats.getTime(), DateUtil.LONG_DATE_FORMAT), stats);
			}
		}
		return map;
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserRemainStats> findList(int pageSize, int currentPage) {
		userRemainStatsDao.closeSession(DBSource.ADMIN);
		return userRemainStatsDao.findPage("from UserRemainStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserRemainStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userRemainStatsDao.closeSession(DBSource.ADMIN);
		return userRemainStatsDao.findPage("from UserRemainStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	/**
	 * 当前服的数据, 日期之前, 用于重新采集数据。
	 * @return
	 */
	public Map<String, UserRemainStats> findNowServerDataBeforeDate(Integer sysNum, String dateBefore){
		Map<String, UserRemainStats> map = new HashMap<String, UserRemainStats>();
		userRemainStatsDao.closeSession(DBSource.ADMIN);
		List<UserRemainStats> list = userRemainStatsDao.find("from UserRemainStats where sysNum = " + sysNum + " and time <= " + dateBefore, DBSource.ADMIN);
		if (list != null && list.size() > 0) {
			for (UserRemainStats stats : list) {
				map.put(DateUtil.dateToString(stats.getTime(), DateUtil.LONG_DATE_FORMAT), stats);
			}
		}
		return map;
	}
	
	
	public void setUserRemainStatsDao(UserRemainStatsDao userRemainStatsDao) {
		this.userRemainStatsDao = userRemainStatsDao;
	}

	public UserRemainStatsDao getUserRemainStatsDao() {
		return userRemainStatsDao;
	}

}
