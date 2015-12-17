package com.stats.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.common.SystemStatsDate;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.util.DateUtil;
import com.stats.bo.UserEquipStats;
import com.stats.dao.UserEquipStatsDao;

public class UserEquipStatsService {
	private UserEquipStatsDao userEquipStatsDao;

	public UserEquipStatsDao getUserEquipStatsDao() {
		return userEquipStatsDao;
	}

	public void setUserEquipStatsDao(UserEquipStatsDao userEquipStatsDao) {
		this.userEquipStatsDao = userEquipStatsDao;
	}
	
	public void save(UserEquipStats stats){
		userEquipStatsDao.save(stats, DBSource.ADMIN);
	}
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param equipType
	 * @return
	 */
	public IPage<UserEquipStats> findList(int pageSize, int currentPage, int equipType) {
		Date yesterday = DateUtil.getDiffDate(new Date(), SystemStatsDate.YESTERDAY);
		String yesterdayStr = DateUtil.dateToString(yesterday, DateUtil.LONG_DATE_FORMAT);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(yesterdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		userEquipStatsDao.closeSession(DBSource.ADMIN);
		if (equipType == 0) {
			List<Object> list = new ArrayList<Object>();
			list.add(CustomerContextHolder.getSystemNum());
			list.add(date);
			return userEquipStatsDao.findPage("from UserEquipStats stats where stats.sysNum = ? and stats.time = ?", list, pageSize, currentPage);
		} else {
			Integer systemNum = CustomerContextHolder.getSystemNum();
			List<Object> list = new ArrayList<Object>();
			list.add(systemNum);
			list.add(equipType);
			list.add(date);
			return userEquipStatsDao.findPage("from UserEquipStats stats where stats.sysNum = ? and stats.quality = ? and stats.time = ?", list, pageSize, currentPage);
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
	public IPage<UserEquipStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate, Integer equipType) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		if (equipType == 0) {
			userEquipStatsDao.closeSession(DBSource.ADMIN);
			return userEquipStatsDao.findPage("from UserEquipStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
		} else {
			list.add(equipType);
			userEquipStatsDao.closeSession(DBSource.ADMIN);
			return userEquipStatsDao.findPage("from UserEquipStats stats where stats.sysNum = ? and stats.time between ? and ? and quality = ?", list, pageSize, currentPage);
		}
	}
	
	
	/**
	 * 分页列表, 求总
	 * @param pageSize
	 * @param currentPage
	 * @param equipType
	 * @return
	 */
	public List<UserEquipStats> findListTotal(int equipType) {

		userEquipStatsDao.closeSession(DBSource.ADMIN);
		if (equipType == 0) {
			
			StringBuilder sb = new StringBuilder();
			sb.append("select time, name, sum(equip_number) from user_equip_stats where sys_num = ");
			sb.append(CustomerContextHolder.getSystemNum());
			sb.append(" GROUP BY NAME");
			List<Object> list = userEquipStatsDao.findSQL_(sb.toString());
			List<UserEquipStats> resultList = new ArrayList<UserEquipStats>();
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = (Object[]) list.get(i);
				UserEquipStats stats = new UserEquipStats();
				stats.setName((String) objArr[1]);
				stats.setTime(new Date());
				stats.setEquipNumber(Integer.valueOf(objArr[2].toString()));
				resultList.add(stats);
			}
			return resultList;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("select time, name, sum(equip_number) from user_equip_stats where sys_num = ");
			sb.append(CustomerContextHolder.getSystemNum());
			sb.append(" and quality = ");
			sb.append(equipType);
			sb.append(" GROUP BY NAME");
			List<Object> list = userEquipStatsDao.findSQL_(sb.toString());
			List<UserEquipStats> resultList = new ArrayList<UserEquipStats>();
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = (Object[]) list.get(i);
				UserEquipStats stats = new UserEquipStats();
				stats.setName((String) objArr[1]);
				stats.setTime(new Date());
				stats.setEquipNumber(Integer.valueOf(objArr[2].toString()));
				resultList.add(stats);
			}
			return resultList;
		}
		
	}
	
	/**
	 * 求总
	 * @param startDate
	 * @param endDate
	 * @param equipType
	 * @return
	 */
	public List<UserEquipStats> findListInDateTotal(Date startDate, Date endDate, Integer equipType) {
		
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String s1 = format1.format(startDate);
        String s2 = format1.format(endDate);
		
		userEquipStatsDao.closeSession(DBSource.ADMIN);
		if (equipType == 0) {
			
			StringBuilder sb = new StringBuilder();
			sb.append("select time, name, sum(equip_number) from user_equip_stats where sys_num = ");
			sb.append(CustomerContextHolder.getSystemNum());
			sb.append(" and time between '");
			sb.append(s1);
			sb.append("'");
			sb.append( " and '");
			sb.append(s2);
			sb.append("'");
			sb.append(" GROUP BY NAME");
			List<Object> list = userEquipStatsDao.findSQL_(sb.toString());
			List<UserEquipStats> resultList = new ArrayList<UserEquipStats>();
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = (Object[]) list.get(i);
				UserEquipStats stats = new UserEquipStats();
				stats.setName((String) objArr[1]);
				stats.setTime(new Date());
				stats.setEquipNumber(Integer.valueOf(objArr[2].toString()));
				resultList.add(stats);
			}
			return resultList;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("select time, name, sum(equip_number) from user_equip_stats where sys_num = ");
			sb.append(CustomerContextHolder.getSystemNum());
			sb.append(" and quality = ");
			sb.append(equipType);
			sb.append(" and time between '");
			sb.append(s1);
			sb.append("'");
			sb.append( " and '");
			sb.append(s2);
			sb.append("'");
			sb.append(" GROUP BY NAME");
			List<Object> list = userEquipStatsDao.findSQL_(sb.toString());
			List<UserEquipStats> resultList = new ArrayList<UserEquipStats>();
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = (Object[]) list.get(i);
				UserEquipStats stats = new UserEquipStats();
				stats.setName((String) objArr[1]);
				stats.setTime(new Date());
				stats.setEquipNumber(Integer.valueOf(objArr[2].toString()));
				resultList.add(stats);
			}
			return resultList;
		}
	}
	
	public void delete(String dateStr) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from user_equip_stats where TIME = '");
		sb.append(dateStr);
		sb.append("'");
		sb.append(" and SYS_NUM = ");
		sb.append(CustomerContextHolder.getSystemNum());
		userEquipStatsDao.closeSession(DBSource.ADMIN);
		userEquipStatsDao.executeSQL_(sb.toString());
	}
}
