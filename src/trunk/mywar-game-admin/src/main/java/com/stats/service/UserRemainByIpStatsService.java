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
import com.stats.bo.UserRemainByIpStats;
import com.stats.dao.UserRemainByIpStatsDao;


public class UserRemainByIpStatsService {

	private UserRemainByIpStatsDao userRemainByIpStatsDao;
	
	/**
	 * 当前服的数据
	 * @return
	 */
	public Map<String, UserRemainByIpStats> findNowServerData(){
		Map<String, UserRemainByIpStats> map = new HashMap<String, UserRemainByIpStats>();
		userRemainByIpStatsDao.closeSession(DBSource.ADMIN);
		List<UserRemainByIpStats> list = userRemainByIpStatsDao.find("from UserRemainByIpStats where sysNum = " + CustomerContextHolder.getSystemNum(), DBSource.ADMIN);
		if (list != null && list.size() > 0) {
			for(UserRemainByIpStats stats : list){
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
	public IPage<UserRemainByIpStats> findList(int pageSize, int currentPage) {
		userRemainByIpStatsDao.closeSession(DBSource.ADMIN);
		return userRemainByIpStatsDao.findPage("from UserRemainByIpStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserRemainByIpStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userRemainByIpStatsDao.closeSession(DBSource.ADMIN);
		return userRemainByIpStatsDao.findPage("from UserRemainByIpStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	
	public void save(UserRemainByIpStats stats) {
		userRemainByIpStatsDao.closeSession(DBSource.ADMIN);
		userRemainByIpStatsDao.saveOrUpdate(stats);
	}
	

	public UserRemainByIpStatsDao getUserRemainByIpStatsDao() {
		return userRemainByIpStatsDao;
	}

	public void setUserRemainByIpStatsDao(
			UserRemainByIpStatsDao userRemainByIpStatsDao) {
		this.userRemainByIpStatsDao = userRemainByIpStatsDao;
	}

}
