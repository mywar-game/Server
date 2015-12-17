package com.log.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.util.DateUtil;
import com.log.bo.UserRegLog;
import com.log.dao.UserRegLogDao;

public class UserRegLogService {
	
	private UserRegLogDao userRegLogDao;
	
	/**
	 * 根据玩家id查询玩家注册日志
	 * @param userId
	 * @return
	 */
	public UserRegLog findUserRegLogByUserId(String userId){
		return userRegLogDao.loadBy("userId", userId, DBSource.LOG);
	}
	
	public UserRegLog findUserRegLogByUserName(String userName) {
		return userRegLogDao.loadBy("userName", userName, DBSource.LOG);
	}
	
	/**
	 * 注册总人数
	 * @return
	 */
	public int findTotalRegUserNum(){
		userRegLogDao.closeSession(DBSource.LOG);
		return userRegLogDao.count("from UserRegLog", new ArrayList<Object>());
	}
	
	/**
	 * 指定时间前的注册总人数
	 * @return
	 */
	public int findTotalRegUserNumBeforeDate(String time){
		userRegLogDao.closeSession(DBSource.LOG);
		return userRegLogDao.count("from UserRegLog where regTime <= '" + time + "'", new ArrayList<Object>());
	}
	/**
	 * 指定时间前的某个渠道的注册总人数
	 * @return
	 */
	public Integer findTotalRegUserNumBeforeDate(String time,String channel){
		userRegLogDao.closeSession(DBSource.LOG);
		return userRegLogDao.count("from UserRegLog where regTime <= '" + time + "' and channel = '"+channel+"'", new ArrayList<Object>());
	}

	
	/**
	 * 某段时间内的注册人数
	 * @param dates 起始时间和结束时间
	 * @return
	 */
	public int findRegUserNumInSomeTime(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(user_id) FROM user_reg_log where REG_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		
		userRegLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRegLogDao.findSQL_(sb.toString());
		if (list == null  || list.size() == 0) {
			return 0;
		}
		Integer regNum = Integer.valueOf(list.get(0).toString());
		return regNum;
	}
	
	/**
	 * 渠道分组的累计用户数
	 * @return
	 */
	public List<Object> findRegUserNumInSomeTime(String date){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT channel,(case when SMALL_CHANNEL='null' then 0 else SMALL_CHANNEL end) AS SMALL_CHANNEL1,count(user_id) FROM user_reg_log where REG_TIME <= '");
		sb.append(date);
		sb.append("'");
		sb.append(" group by channel, SMALL_CHANNEL1 having count(user_id)>0");
		userRegLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRegLogDao.findSQL_(sb.toString());
		return list;
	}
	
	/**
	 * 某段时间内的注册人数
	 * @param dates 起始时间和结束时间
	 * @return
	 */
	public int findRegUserNumInSomeTime(String[] dates,Integer channel){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(user_id) FROM user_reg_log where REG_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		if(channel!=null && channel.intValue()!=0){
			sb.append(" and channel='");
			sb.append(channel.toString());
			sb.append("'");
		}
		userRegLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRegLogDao.findSQL_(sb.toString());
		if (list == null  || list.size() == 0) {
			return 0;
		}
		Integer regNum = Integer.valueOf(list.get(0).toString());
		return regNum;
	}
	
	/**
	 * 在A时间段注册的人中在B时间段各种登陆的情况
	 * @param regStartDate 注册开始日期
	 * @param regEndDate 注册结束日期
	 * @param loginStartDate 登录开始日期
	 * @param loginEndDate 登录结束日期
	 * @return
	 */
	public Map<String,Integer> findReLoginMap(Date regStartDate, Date regEndDate, Date loginStartDate, Date loginEndDate){
		Map<String,Integer> map = new HashMap<String, Integer>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sql = new StringBuffer();
		//datestr是用户登录的各个日期的day的拼接字符串，count是对应的人数
		sql.append("SELECT login.dayStr, COUNT(reg.USER_ID)  FROM ");
		sql.append("(SELECT USER_ID FROM `user_reg_log` WHERE DAY(REG_TIME) >= DAY('").append(sdf.format(regStartDate)).append("') AND DAY(REG_TIME) <= DAY('").append(sdf.format(regEndDate)).append("')) reg, ");
		sql.append("(");
		//不用CASThibernate不认识group_concat后的结果。。。
		sql.append(" SELECT USER_ID, CAST(GROUP_CONCAT(DISTINCT(DAY(LOGIN_TIME))) AS CHAR) AS dayStr FROM (select * from user_login_log union all select * from user_login_log_bak) log WHERE DAY(log.LOGIN_TIME) >= DAY('").append(sdf.format(loginStartDate)).append("') AND DAY(log.LOGIN_TIME) <= DAY('").append(sdf.format(loginEndDate)).append("') GROUP BY log.USER_ID");
		sql.append(") login");
		sql.append(" WHERE reg.USER_ID = login.USER_ID GROUP BY login.dayStr");
		userRegLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRegLogDao.findSQL_(sql.toString());
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String dateStr = ((Object[]) list.get(i))[0].toString();
				Integer amount = Integer.parseInt(((Object[]) list.get(i))[1].toString());
				map.put(dateStr, amount);
			}
		}
		
		return map;
	}
	
	/**
	 * 查询某天的渠道分组的注册量数据
	 * @param dates
	 * @return
	 */
	public Map<String, Integer> findUserCountByChannel(String[] dates){
		Map<String, Integer> map = new HashMap<String, Integer>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT channel,count(user_id) from user_reg_log WHERE reg_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by channel");
		userRegLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRegLogDao.findSQL_(sb.toString());
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), Integer.valueOf(arr[1].toString()));
			}
		}
		return map;
	}
	
	/**
	 * 查询各个渠道的总注册人数
	 * @return
	 */
	public Map<String, Integer> findUserCountByChannel(){
		String sql = "select channel,count(user_id) from user_reg_log group by channel";
		List<Object> list = userRegLogDao.findSQL_(sql);
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), Integer.valueOf(arr[1].toString()));
			}
		}
		return map;
	}
	
	/**
	 * 查询各个渠道的总注册人数(根据时间，重新采集数据)
	 * @return
	 */
	public Map<String, Integer> findUserCountByChannelWithTime(String time) {
		String sql = "select channel,count(user_id) from user_reg_log where reg_time <= '";
		sql += time;
		sql += "'";
		sql += " group by channel";
		List<Object> list = userRegLogDao.findSQL_(sql);
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(), Integer.valueOf(arr[1].toString()));
			}
		}
		return map;
	}
	
	
	/**
	 * 开服后某段时间内的注册用户数 (根据idfa)
	 * @param serverOpenTimeStr 开服时间
	 * @param days 第几天
	 * @return
	 */
	public int countSomeDayRegedUserByIdfa(int remainDays){
		String[] regDateStrArr = DateUtil.getOneDayStrArr(remainDays);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT reg_idfa) FROM user_reg_log WHERE REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		System.out.println(sql);
		
		userRegLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRegLogDao.findSQL_(sql.toString());
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				BigInteger countBig = (BigInteger) obj;
				int count = countBig.intValue();
				return count;
			}
		}
		return 0;
		/*return userRegLogDao.count(sql.toString(), new ArrayList<Object>());*/
	}
	
	/**
	 * 开服后某段时间内的注册用户数 (根据ip)
	 * @param serverOpenTimeStr 开服时间
	 * @param days 第几天
	 * @return
	 */
	public int countSomeDayRegedUserByIp(int remainDays){
		String[] regDateStrArr = DateUtil.getOneDayStrArr(remainDays);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT reg_ip) FROM user_reg_log WHERE REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		System.out.println(sql);
		
		userRegLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRegLogDao.findSQL_(sql.toString());
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				BigInteger countBig = (BigInteger) obj;
				int count = countBig.intValue();
				return count;
			}
		}
		return 0;
		/*return userRegLogDao.count(sql.toString(), new ArrayList<Object>());*/
	}
	
	/**
	 * 开服后某段时间内的创建用户数 (根据ip)
	 * @param serverOpenTimeStr 开服时间
	 * @param days 第几天
	 * @return
	 */
	public int countSomeDayCreatedUserByIp(int remainDays){
		String[] regDateStrArr = DateUtil.getOneDayStrArr(remainDays);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(DISTINCT(reg.reg_ip)) FROM `user` u, user_reg_log reg");
		sql.append(" WHERE u.user_id=reg.user_id AND reg.REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		System.out.println(sql);
		
		userRegLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRegLogDao.findSQL_(sql.toString());
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				BigInteger countBig = (BigInteger) obj;
				int count = countBig.intValue();
				return count;
			}
		}
		return 0;
		/*return userRegLogDao.count(sql.toString(), new ArrayList<Object>());*/
	}

	/**
	 * 开服后某段时间内的创建用户数 (根据idfa)
	 * @param serverOpenTimeStr 开服时间
	 * @param days 第几天
	 * @return
	 */
	public int countSomeDayCreatedUserByIdfa(int remainDays){
		String[] regDateStrArr = DateUtil.getOneDayStrArr(remainDays);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(DISTINCT(reg.reg_idfa)) FROM `user` u, user_reg_log reg");
		sql.append(" WHERE u.user_id=reg.user_id AND reg.REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		System.out.println(sql);
		
		userRegLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRegLogDao.findSQL_(sql.toString());
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				BigInteger countBig = (BigInteger) obj;
				int count = countBig.intValue();
				return count;
			}
		}
		return 0;
		/*return userRegLogDao.count(sql.toString(), new ArrayList<Object>());*/
	}
	/** 找出所有渠道 **/
	public List<String> findAllChannel() {
		List<String> channelList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT(channel) FROM user_reg_log");
		userRegLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRegLogDao.findSQL_(sql.toString());
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				String channel = (String) obj;
				channelList.add(channel);
			}
		}
		return channelList;
	}
	
	/**
	 * 开服后某段时间内的创建用户数 (根据ip) 重新采集
	 * @param serverOpenTimeStr 开服时间
	 * @param days 第几天
	 * @return
	 */
	public int reCountSomeDayCreatedUserByIp(int remainDays){
		String[] regDateStrArr = DateUtil.getOneDayStrArr(remainDays);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(DISTINCT(reg.reg_ip)) FROM `user` u, user_reg_log reg");
		sql.append(" WHERE u.user_id=reg.user_id AND reg.REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		sql.append(" AND u.REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		System.out.println(sql);
		
		userRegLogDao.closeSession(DBSource.LOG);
		List<Object> list = userRegLogDao.findSQL_(sql.toString());
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				BigInteger countBig = (BigInteger) obj;
				int count = countBig.intValue();
				return count;
			}
		}
		return 0;
		/*return userRegLogDao.count(sql.toString(), new ArrayList<Object>());*/
	}
	
	public void setUserRegLogDao(UserRegLogDao userRegLogDao) {
		this.userRegLogDao = userRegLogDao;
	}

	public UserRegLogDao getUserRegLogDao() {
		return userRegLogDao;
	}

}
