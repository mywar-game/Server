package com.stats.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.stats.bo.LoginStats;
import com.stats.dao.LoginStatsDao;

public class LoginStatsService {
	private LoginStatsDao loginStatsDao;

	public LoginStatsDao getLoginStatsDao() {
		return loginStatsDao;
	}

	public void setLoginStatsDao(LoginStatsDao loginStatsDao) {
		this.loginStatsDao = loginStatsDao;
	}
	/**
	 * 批量保存数据
	 * @param list
	 */
	public void saveBatch(List<LoginStats> list){
		loginStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	/**
	 * 删除某天数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from LoginStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		loginStatsDao.closeSession(DBSource.ADMIN);
		loginStatsDao.execute(sql.toString());
	}
	
	/**
	 * 今日登陆数据查询
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> findLoginData(String[] dates){
		StringBuilder sql = new StringBuilder();
		//根据渠道分组的登陆账号数、登陆ip数、新注册账号数、新角色、老账号登陆数
		sql.append("select channel,sum(login_num),sum(login_ip_num),sum(new_reg),sum(new_user),sum(old_login_num) from login_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and half_hour_index = (select max(half_hour_index) from login_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("')");
		sql.append(" group by channel");
		loginStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = loginStatsDao.findSQL_(sql.toString());
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), arr);
			}
		}
		return map;
	}
	
	/**
	 * 联运-今日登陆数据查询
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> aoFindLoginData(String[] dates,String channel){
		StringBuilder sql = new StringBuilder();
		//根据渠道分组的登陆账号数、登陆ip数、新注册账号数、新角色、老账号登陆数
		sql.append("select sys_num,login_num,login_ip_num,new_reg,new_user,old_login_num from login_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and half_hour_index = (select max(half_hour_index) from login_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("')");
		sql.append(" and channel='").append(channel).append("'");
		loginStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = loginStatsDao.findSQL_(sql.toString());
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), arr);
			}
		}
		return map;
	}
	
	/**
	 * 历史登陆数据查询
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> findLoginDataHistory(String[] dates){
		StringBuilder sql = new StringBuilder();
		//根据渠道分组的登陆账号数、登陆ip数、新注册账号数、新角色、老账号登陆数
		sql.append("select channel,sum(login_num),sum(login_ip_num),sum(new_reg),sum(new_user),sum(old_login_num) from login_stats a,");
		sql.append("(select max(half_hour_index) as halfHourIndex,time as bTime from login_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("' group by time) b");
		sql.append(" where a.half_hour_index=b.halfHourIndex and a.time=b.bTime");
		sql.append(" and time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" group by channel");
		loginStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = loginStatsDao.findSQL_(sql.toString());
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), arr);
			}
		}
		return map;
	}
	
	/**
	 * 联运-历史登陆数据查询
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> aoFindLoginDataHistory(String[] dates,String channel){
		StringBuilder sql = new StringBuilder();
		//根据渠道分组的登陆账号数、登陆ip数、新注册账号数、新角色、老账号登陆数
		sql.append("select sys_num,sum(login_num),sum(login_ip_num),sum(new_reg),sum(new_user),sum(old_login_num) from login_stats a,");
		sql.append("(select max(half_hour_index) as halfHourIndex,time as bTime from login_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("' group by time) b");
		sql.append(" where a.half_hour_index=b.halfHourIndex and a.time=b.bTime");
		sql.append(" and time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and channel='").append(channel).append("'");
		sql.append(" group by sys_num");
		loginStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = loginStatsDao.findSQL_(sql.toString());
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), arr);
			}
		}
		return map;
	}
}
