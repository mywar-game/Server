package com.stats.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.stats.bo.RegisterStats;
import com.stats.dao.RegisterStatsDao;

public class RegisterStatsService {
	private RegisterStatsDao registerStatsDao;

	public RegisterStatsDao getRegisterStatsDao() {
		return registerStatsDao;
	}

	public void setRegisterStatsDao(RegisterStatsDao registerStatsDao) {
		this.registerStatsDao = registerStatsDao;
	}
	/**
	 * 批量保存数据
	 * @param list
	 */
	public void saveBatch(List<RegisterStats> list){
		registerStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	/**
	 * 今日注册数据查询
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> findRegisterData(String[] dates){
		StringBuilder sql = new StringBuilder();
		//根据渠道分组的新注册、新角色、新注册充值人数、新注册充值数
		sql.append("select channel,sum(new_reg),sum(new_user),sum(new_reg_pay_user_num),sum(new_reg_pay_amount) from register_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and half_hour_index = (select max(half_hour_index) from register_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("')");
		sql.append(" group by channel");
		registerStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = registerStatsDao.findSQL_(sql.toString());
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
	 * 联运-今日注册数据查询
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> aoFindRegisterData(String[] dates){
		StringBuilder sql = new StringBuilder();
		//根据服务器分组的新注册、新角色、新注册充值人数、新注册充值数
		sql.append("select sys_num,sum(new_reg),sum(new_user),sum(new_reg_pay_user_num),sum(new_reg_pay_amount) from register_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and half_hour_index = (select max(half_hour_index) from register_stats where time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("')");
		sql.append(" group by sys_num");
		registerStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = registerStatsDao.findSQL_(sql.toString());
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
	 * 历史注册数据查询
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> findRegisterDataHistory(String[] dates){
		StringBuilder sql = new StringBuilder();
		//根据渠道分组的新注册、新角色、新注册充值人数、新注册充值数
		sql.append("select channel,sum(new_reg),sum(new_user),sum(new_reg_pay_user_num),sum(new_reg_pay_amount) from register_stats a,");
		sql.append("(select max(half_hour_index) as halfHourIndex,time as bTime from register_stats where time between '");
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
		registerStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = registerStatsDao.findSQL_(sql.toString());
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
	 * 联运-历史注册数据查询
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> aoFindRegisterDataHistory(String[] dates){
		StringBuilder sql = new StringBuilder();
		//根据服务器分组的新注册、新角色、新注册充值人数、新注册充值数
		sql.append("select sys_num,sum(new_reg),sum(new_user),sum(new_reg_pay_user_num),sum(new_reg_pay_amount) from register_stats a,");
		sql.append("(select max(half_hour_index) as halfHourIndex,time as bTime from register_stats where time between '");
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
		sql.append(" group by sys_num");
		registerStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = registerStatsDao.findSQL_(sql.toString());
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
