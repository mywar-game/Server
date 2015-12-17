package com.stats.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.common.SystemStatsDate;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.util.DateUtil;
import com.stats.bo.UserRemainByChannelStats;
import com.stats.dao.UserRemainByChannelStatsDao;

public class UserRemainByChannelStatsService {

	private UserRemainByChannelStatsDao userRemainByChannelStatsDao;
	
	public UserRemainByChannelStatsDao getUserRemainByChannelStatsDao() {
		return userRemainByChannelStatsDao;
	}

	public void setUserRemainByChannelStatsDao(
			UserRemainByChannelStatsDao userRemainByChannelStatsDao) {
		this.userRemainByChannelStatsDao = userRemainByChannelStatsDao;
	}

	/**
	 * 当前服的数据 (分渠道)
	 * @return
	 */
	public Map<String, List<UserRemainByChannelStats>> findNowServerDataByChannel() {
		userRemainByChannelStatsDao.closeSession(DBSource.ADMIN);
		List<UserRemainByChannelStats> list = userRemainByChannelStatsDao.find("from UserRemainByChannelStats where sysNum = "+CustomerContextHolder.getSystemNum(), DBSource.ADMIN);
		Map<String, List<UserRemainByChannelStats>> map = new HashMap<String, List<UserRemainByChannelStats>>();
		
		if (list != null && list.size() > 0) {
			for (UserRemainByChannelStats stats : list) {
				String channel = stats.getChannel();
				List<UserRemainByChannelStats> tempStatsList = map.get(channel);
				if (tempStatsList == null) {
					tempStatsList = new ArrayList<UserRemainByChannelStats>();
					tempStatsList.add(stats);
					map.put(channel, tempStatsList);
				} else {
					tempStatsList.add(stats);
				}
			}
		}
		return map;
	}
	
	public void save(UserRemainByChannelStats stats) {
		userRemainByChannelStatsDao.closeSession(DBSource.ADMIN);
		userRemainByChannelStatsDao.saveOrUpdate(stats);
	}
	
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<UserRemainByChannelStats> findList(int pageSize, int currentPage, String channel) {
		userRemainByChannelStatsDao.closeSession(DBSource.ADMIN);
		Date yesterday = DateUtil.getDiffDate(new Date(), SystemStatsDate.YESTERDAY);
		String yesterdayStr = DateUtil.dateToString(yesterday, DateUtil.LONG_DATE_FORMAT);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(yesterdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(date);
		if(channel != null && Integer.valueOf(channel) != 0) {
			list.add(channel + "");
			return userRemainByChannelStatsDao.find("from UserRemainByChannelStats stats where stats.sysNum = ? and stats.time = ? and channel = ?", list);
		} else {
			return userRemainByChannelStatsDao.find("from UserRemainByChannelStats stats where stats.sysNum = ? and stats.time = ?", list);
		}
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<UserRemainByChannelStats> findListInDate(Date startDate, Date endDate, String channel) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userRemainByChannelStatsDao.closeSession(DBSource.ADMIN);
		if(channel != null && Integer.valueOf(channel) != 0) {
			list.add(channel + "");
			return userRemainByChannelStatsDao.find("from UserRemainByChannelStats stats where stats.sysNum = ? and stats.time between ? and ? and stats.channel = ?", list);
		} else {
			return userRemainByChannelStatsDao.find("from UserRemainByChannelStats stats where stats.sysNum = ? and stats.time between ? and ?", list);
		}
	}
}
