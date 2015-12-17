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
import com.stats.bo.UserRemainByIdfaStats;
import com.stats.dao.UserRemainByIdfaStatsDao;


public class UserRemainByIdfaStatsService {

	private UserRemainByIdfaStatsDao userRemainByIdfaStatsDao;
	
	/**
	 * 当前服的数据
	 * @return
	 */
	public Map<String, UserRemainByIdfaStats> findNowServerData(){
		Map<String, UserRemainByIdfaStats> map = new HashMap<String, UserRemainByIdfaStats>();
		userRemainByIdfaStatsDao.closeSession(DBSource.ADMIN);
		List<UserRemainByIdfaStats> list = userRemainByIdfaStatsDao.find("from UserRemainByIdfaStats where sysNum = " + CustomerContextHolder.getSystemNum(), DBSource.ADMIN);
		if (list != null && list.size() > 0) {
			for(UserRemainByIdfaStats stats : list){
				map.put(DateUtil.dateToString(stats.getTime(), DateUtil.LONG_DATE_FORMAT), stats);
			}
		}
		return map;
	}
	
	
	public void save(UserRemainByIdfaStats stats) {
		userRemainByIdfaStatsDao.closeSession(DBSource.ADMIN);
		userRemainByIdfaStatsDao.saveOrUpdate(stats);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserRemainByIdfaStats> findList(int pageSize, int currentPage) {
		userRemainByIdfaStatsDao.closeSession(DBSource.ADMIN);
		return userRemainByIdfaStatsDao.findPage("from UserRemainByIdfaStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserRemainByIdfaStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userRemainByIdfaStatsDao.closeSession(DBSource.ADMIN);
		return userRemainByIdfaStatsDao.findPage("from UserRemainByIdfaStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	

	public UserRemainByIdfaStatsDao getUserRemainByIdfaStatsDao() {
		return userRemainByIdfaStatsDao;
	}

	public void setUserRemainByIdfaStatsDao(
			UserRemainByIdfaStatsDao userRemainByIdfaStatsDao) {
		this.userRemainByIdfaStatsDao = userRemainByIdfaStatsDao;
	}

}
