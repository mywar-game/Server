package com.log.service;

import java.util.List;

import com.framework.common.DBSource;
import com.log.dao.UserVipLogDao;

public class UserVipLogService {
	private UserVipLogDao userVipLogDao;

	public UserVipLogDao getUserVipLogDao() {
		return userVipLogDao;
	}

	public void setUserVipLogDao(UserVipLogDao userVipLogDao) {
		this.userVipLogDao = userVipLogDao;
	}
	
	/**
	 * 查询某个时间段内按渠道分组的不同vip等级的玩家数量
	 * @param dates
	 * @return
	 */
	public List<Object> findUserCoutnByDiffVipLevel(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("select reg.channel,log.vLevel,count(log.user_id) from (select user_id,max(vip_level) as vLevel from user_vip_log where time between '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by user_id) log, user_reg_log reg where log.user_id=reg.user_id group by reg.channel,log.vLevel;");
		userVipLogDao.closeSession(DBSource.LOG);
		return userVipLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 查询某个时间段内按渠道分组的不同vip等级的玩家数量(应运营那边要求，添加一个总的统计，就是当天之前的所有vip)
	 * @param dates
	 * @return
	 */
	public List<Object> findUserCoutnByDiffVipLevelTotal(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("select reg.channel,log.vLevel,count(log.user_id) from (select user_id,max(vip_level) as vLevel from user_vip_log where time <= '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by user_id) log,user_reg_log reg where log.user_id=reg.user_id group by reg.channel,log.vLevel;");
		userVipLogDao.closeSession(DBSource.LOG);
		return userVipLogDao.findSQL_(sb.toString());
	}
}
