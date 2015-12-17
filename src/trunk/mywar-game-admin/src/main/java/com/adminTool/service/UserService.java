package com.adminTool.service;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.adminTool.bo.User;
import com.adminTool.bo.UserMapper;
import com.adminTool.dao.UserDao;
import com.adminTool.dao.UserMapperDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.util.DateUtil;
import com.stats.bo.UserSexStats;

/**
 * 
 * @author hzy
 * 2012-7-16
 */
public class UserService {
	
	/** **/
	private UserDao userDao;
	private UserMapperDao userMapperDao;
	
	public String getUserIdByPartnerUserId(String partnerUserId, String partnerId, String serverId) {
		
		List<UserMapper> list = userMapperDao.find("from UserMapper WHERE partnerUserId='"+partnerUserId + "' AND partnerId = '" + partnerId + "' AND serverId = '" + serverId +"'", DBSource.ADMIN);
		return list.get(0).getUserId();
	}
	
	/**
	 * @param userId a
	 * @return a
	 */
	public User findUserByUserId(String userId) {
		return userDao.loadBy("userId", userId, DBSource.LOG);
	}
	
	/**
	 * 玩家id和昵称map
	 * @param userIds a
	 * @return a
	 */
	public Map<String, String> findUserIdNameMapByUserIds(String userIds) {
		Map<String, String> userIdNameMap = new HashMap<String, String>();
		List<User> list = userDao.find("from User WHERE userId in (" + userIds + ")", DBSource.LOG);
		for (int i = 0; i < list.size(); i++) {
			User user = list.get(i);
			userIdNameMap.put(user.getUserId(), user.getName());
		}
		return userIdNameMap;
	}
	
	public Map<String, User> findUserIdAndUserMapByUserIds(String userIds) {
		Map<String, User> userIdUserMap = new HashMap<String, User>();
		List<User> list = userDao.find("from User WHERE userId in (" + userIds + ")", DBSource.LOG);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				User user = list.get(i);
				userIdUserMap.put(user.getUserId(), user);
			}
		}
		return userIdUserMap;
	}
	
	/**
	 * @param name a
	 * @return a
	 */
	public User findUserByName(String name) {
		return userDao.loadBy("name", name, DBSource.LOG);
	}

	/**
	 * 根据用户名查找用户
	 * @param userName 用户名
	 * @return 用户
	 */
	public List<User> findUserByUserName(String userName) {
		return userDao.find(" from User where userName='"+userName+"'", DBSource.LOG);
	}
	
	/**
	 * 根据玩家标识查询玩家信息
	 * @param lodoId
	 * @return
	 */
	public User findUserByLodoId(int lodoId){
		return userDao.loadBy("lodoId", lodoId, DBSource.LOG);
	}
	
	/**
	 * 根据lodoId和userName查询玩家信息(两个条件不能都为空)
	 * @param lodoId
	 * @param userName
	 * @return
	 */
	public List<User> findUserByLodoIdAndUserName(int lodoId,String userName){
		StringBuilder sb = new StringBuilder();
		sb.append(" from User where");
		if(lodoId!=0){
			sb.append(" lodoId=");
			sb.append(lodoId);
		}
		if(userName!=null && !userName.equals("")){
			if(lodoId!=0){
				sb.append(" and");
			}
			sb.append(" userName='");
			sb.append(userName);
			sb.append("'");
		}
		return userDao.find(sb.toString(), DBSource.LOG);
	}
	
	/**
	 * 查询当前剩余钻石数
	 * @return
	 */
	public List<Object> findLeftDiamond() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ifnull(sum(money),0) from user");
		this.userDao.closeSession(DBSource.USER);
		return this.userDao.findSQL_(sb.toString());
	}
	
	public List<Object> findLeftDiamond(List<String> userIds) {
		StringBuffer ss = new StringBuffer();
		for (int i = 0; i < userIds.size(); i++) {
			ss.append(userIds.get(i));
			if (i < userIds.size() -1) {
				ss.append(",");
			}
		}
		StringBuilder sb = new StringBuilder();
		if (userIds != null && userIds.size() > 0) {
			sb.append("select sum(money) from user where user_id in (");
			for (int i = 0; i < userIds.size(); i++) {
				sb.append("'");
				sb.append(userIds.get(i));
				sb.append("'");
				if (i < userIds.size() - 1) {
					sb.append(",");
				}
			}
			sb.append(")");
		} else {
			List<Object> list = new ArrayList<Object>();
			list.add(0l);
			return list;
		}
		this.userDao.closeSession(DBSource.USER);
		return this.userDao.findSQL_(sb.toString());
	}
	
	/**
	 * 根据玩家id和lodoId查询玩家信息
	 * @param userId
	 * @param lodoId
	 * @return
	 */
	public User findUserByLodo(String userId, int lodoId){
		StringBuffer buffer = new StringBuffer();
		buffer.append("from User where 1 = 1");
		if (userId != null && !userId.equals("")) {
			buffer.append(" and userId = '").append(userId).append("'");
		}
		
		if (lodoId!=0) {
			buffer.append(" and lodoId = ").append(lodoId);
		}
		//System.out.println("str = " + buffer.toString());
		userDao.closeSession(DBSource.LOG);
		List<User> userList = userDao.find(buffer.toString(), DBSource.LOG);
		
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}

		return null;
	}
	
	/**
	 * 根据条件查找某一个用户
	 * @param userId 
	 * @param userName 
	 * @param name 
	 * @return 符合条件的用户
	 */
	public User findUserByCondition(String userId, String userName, String name) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("from User where 1 = 1");
		if (userId != null && !userId.equals("")) {
			buffer.append(" and userId = '").append(userId).append("'");
		}
		
		if (userName != null && !userName.equals("")) {
			buffer.append(" and userName = '").append(userName.trim()).append("'");
		} 
		
		if (name != null && !name.equals("")) {
			buffer.append(" and name = '").append(name.trim()).append("'");
		}
		 
		//System.out.println("str = " + buffer.toString());
		userDao.closeSession(DBSource.LOG);
		List<User> userList = userDao.find(buffer.toString(), DBSource.LOG);
		
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}

		return null;
	}
	
	public void collectUserSomeInfo(PreparedStatement prest, int serverIndex){
//		-- 账号、角色名、等级、玩游戏总时长、总充值金额、登录IP、登录时间、创角时间。参考如下。
//		SELECT u.USER_NAME, u.`NAME`, levelTable.lv, live.totalPlayed, pay.totalMoney, ip.lastLoginIP, ip.lastLoginIP, levelTable.regTime  FROM `user` u 
//		LEFT JOIN (
//			SELECT USER_ID,MAX(`LEVEL`) AS lv,MIN(TIME) AS regTime FROM user_levelup_log GROUP BY USER_ID
//		) levelTable ON u.USER_ID = levelTable.USER_ID 
//		LEFT JOIN (
//			SELECT USER_ID,SUM(LIVE_MINUTES) AS totalPlayed FROM user_logout_log GROUP BY USER_ID
//		) live ON u.USER_ID = live.USER_ID 
//		LEFT JOIN (
//		SELECT USER_ID,SUM(AMOUNT) AS totalMoney FROM user_pay_history_log GROUP BY USER_ID
//		) pay ON u.USER_ID = pay.USER_ID 
//		LEFT JOIN (
//			SELECT a.USER_ID,b.IP AS lastLoginIP,b.LOGIN_TIME AS lastLoginTime FROM (
//				SELECT USER_ID, MAX(ID) AS lastId FROM user_login_log GROUP BY USER_ID
//			) a, user_login_log b WHERE a.USER_ID = b.USER_ID AND b.ID = a.lastId
//		) ip  ON u.USER_ID = ip.USER_ID 
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT u.USER_NAME, u.`NAME`, IFNULL(levelTable.lv,0), IFNULL(live.totalPlayed,0), IFNULL(pay.totalMoney,0), IFNULL(u.REG_TIME,''), IFNULL(ip.lastLoginTime,''), IFNULL(ip.lastLoginIP,'')  FROM `user` u ");
			sql.append("LEFT JOIN (");
			sql.append("	SELECT USER_ID,MAX(`LEVEL`) AS lv FROM user_levelup_log GROUP BY USER_ID");
			sql.append(") levelTable ON u.USER_ID = levelTable.USER_ID ");
			sql.append("LEFT JOIN (");
			sql.append("	SELECT USER_ID,SUM(LIVE_MINUTES) AS totalPlayed FROM (select * from user_logout_log union all select * from user_logout_log_bak) outlog GROUP BY USER_ID");
			sql.append(") live ON u.USER_ID = live.USER_ID ");
			sql.append("LEFT JOIN (");
			sql.append("SELECT USER_ID,SUM(AMOUNT) AS totalMoney FROM user_pay_history_log GROUP BY USER_ID");
			sql.append(") pay ON u.USER_ID = pay.USER_ID ");
			sql.append("LEFT JOIN (");
			sql.append("	SELECT a.USER_ID,b.IP AS lastLoginIP,b.LOGIN_TIME AS lastLoginTime FROM (");
			sql.append("		SELECT USER_ID, MAX(ID) AS lastId FROM (select * from user_login_log union all select * from user_login_log_bak) loga GROUP BY loga.USER_ID");
			sql.append("	) a, (select * from user_login_log union all select * from user_login_log_bak) b WHERE a.USER_ID = b.USER_ID AND b.ID = a.lastId");
			sql.append(") ip  ON u.USER_ID = ip.USER_ID");

			userDao.closeSession(DBSource.LOG);
			List<Object> list = userDao.findSQL_(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < list.size(); i++) {
//					System.out.println("======================"+i);
				String userName = ((Object[]) list.get(i))[0].toString();
				String name = ((Object[]) list.get(i))[1].toString();
				Integer level = Integer.valueOf(((Object[]) list.get(i))[2].toString());
				Integer played = Integer.valueOf(((Object[]) list.get(i))[3].toString());
				Float payAmount = Float.valueOf(((Object[]) list.get(i))[4].toString());
				String regTimeStr = ((Object[]) list.get(i))[5].toString();
				String lastLoginTimeStr = ((Object[]) list.get(i))[6].toString();
				String lastLoginIP = ((Object[]) list.get(i))[7].toString();
				
				prest.setString(1, "s"+(serverIndex+1));
				prest.setString(2, userName);
				prest.setString(3, name);
				prest.setInt(4, level);
				prest.setInt(5, played);
				prest.setFloat(6, payAmount);
				if ("".equals(regTimeStr)) {
					prest.setTimestamp(7, new Timestamp(1000));
				} else {
					prest.setTimestamp(7, new Timestamp(sdf.parse(regTimeStr).getTime()));
				}
				if ("".equals(lastLoginTimeStr)) {
					prest.setTimestamp(8, new Timestamp(1000));
				} else {
					prest.setTimestamp(8, new Timestamp(sdf.parse(lastLoginTimeStr).getTime()));
				}
				prest.setString(9, lastLoginIP);
				
				prest.addBatch();  
			}
		} catch (Exception e) {
			LogSystem.error(e, e.getMessage());
		}
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}
	
	/**
	 * 统计游戏世界所有创建了角色的玩家
	 * @return  玩家总数
	 */
	public int countAllUser() {
		userDao.closeSession(DBSource.LOG);
		return userDao.countAll();
	}
	
	/**
	 * 统计游戏世界所有创建了角色的玩家, 日期之前, 用于重新采集
	 * @param beforeDate
	 * @return
	 */
	public int countAllUserBeforeDate(String beforeDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(u.user_id), u.user_id FROM `user` u");
		sb.append(" WHERE u.REG_TIME <= '");
		sb.append(beforeDate);
		sb.append("'");
		userDao.closeSession(DBSource.LOG);
		List<Object> list = userDao.findSQL_(sb.toString());

		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); ) {
				Object[] arr = (Object[]) list.get(i);
				BigInteger b = (BigInteger) arr[0];
				count = b.intValue();
				break;
			}
		}
		return count;
	}
	
	/**
	 * 统计游戏世界所有创建了角色的玩家 (根据渠道)
	 * @return  玩家总数
	 */
	public Map<String, Integer> countAllUserByChannel() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT reg.channel, count(u.user_id) FROM `user` u, user_reg_log reg");
		sb.append(" WHERE reg.user_id=u.user_id group by reg.channel having count(u.user_id)>0");
		userDao.closeSession(DBSource.LOG);
		List<Object> list = userDao.findSQL_(sb.toString());

		Map<String, Integer> map = new HashMap<String, Integer>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				map.put(arr[0].toString(), Integer.valueOf(arr[1].toString()));
			}
		}
		
		return map;
	}
	
	/**
	 * 统计游戏世界所有创建了角色的玩家(根据idfa)
	 * @return  玩家总数
	 */
	public int countAllUserByIdfa() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(DISTINCT(reg.reg_idfa)) FROM `user` u, user_reg_log reg");
		sb.append(" WHERE u.user_id=reg.user_id");
		userDao.closeSession(DBSource.LOG);
		List<Object> list = userDao.findSQL_(sb.toString());
		int count = 0;
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object arr = (Object)list.get(i);
				count = Integer.valueOf(arr.toString());			
			}
		}
		return count;
	}
	
	/**
	 * 统计游戏世界所有创建了角色的玩家(根据ip)
	 * @return  玩家总数
	 */
	public int countAllUserByIp() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(DISTINCT(reg.reg_ip)) FROM `user` u, user_reg_log reg");
		sb.append(" WHERE u.user_id=reg.user_id");
		userDao.closeSession(DBSource.LOG);
		List<Object> list = userDao.findSQL_(sb.toString());
		int count = 0;
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object arr = (Object)list.get(i);
				count = Integer.valueOf(arr.toString());			
			}
		}
		return count;
	}

	/**
	 * 开服后某段时间内的注册用户数
	 * @param serverOpenTimeStr 开服时间
	 * @param days 第几天
	 * @return
	 */
	public int countSomeDayRegedUser(int remainDays){
		String[] regDateStrArr = DateUtil.getOneDayStrArr(-remainDays);
		StringBuffer sql = new StringBuffer();
		sql.append("FROM User WHERE REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		
		userDao.closeSession(DBSource.LOG);
		return userDao.count(sql.toString(), new ArrayList<Object>());
	}
	
	/**
	 * 开服后某段时间内的注册用户数 (根据渠道)
	 * @param serverOpenTimeStr 开服时间
	 * @param days 第几天
	 * @return
	 */
	public int countSomeDayRegedUserByChannel(int remainDays, String channel){
		String[] regDateStrArr = DateUtil.getOneDayStrArr(-remainDays);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT user_id) FROM user_reg_log WHERE REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		sql.append(" AND CHANNEL='");
		sql.append(channel);
		sql.append("'");
		
		userDao.closeSession(DBSource.LOG);
//		return userDao.count(sql.toString(), new ArrayList<Object>());
		List<Object> list = userDao.findSQL_(sql.toString());
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				BigInteger countBig = (BigInteger) obj;
				int count = countBig.intValue();
				return count;
			}
		}
		return 0;
	}
	/**
	 * 开服后某段时间内的创建用户数 (根据渠道)
	 * @param serverOpenTimeStr 开服时间
	 * @param days 第几天
	 * @return
	 */
	public int countSomeDayCreatedUserByChannel(int remainDays, String channel) {
		String[] regDateStrArr = DateUtil.getOneDayStrArr(-remainDays);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT u.user_id) FROM user u, user_reg_log log WHERE log.REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		sql.append(" AND log.CHANNEL='");
		sql.append(channel);
		sql.append("'");
		sql.append(" AND u.user_id = log.user_id");
		userDao.closeSession(DBSource.LOG);
//		return userDao.count(sql.toString(), new ArrayList<Object>());
		List<Object> list = userDao.findSQL_(sql.toString());
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				BigInteger countBig = (BigInteger) obj;
				int count = countBig.intValue();
				return count;
			}
		}
		return 0;
	}
	
	
	public List<UserSexStats> findUserSexStatsList(){
		List<UserSexStats> userSexStatsList = new ArrayList<UserSexStats>();
		//SELECT SEX,COUNT(USER_ID) FROM user_init_info GROUP BY SEX 
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SEX,COUNT(USER_ID) FROM `user` GROUP BY SEX");
		userDao.closeSession(DBSource.LOG);
		List<Object> list = userDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			UserSexStats userSexStats;
			for (int i = 0; i < list.size(); i++) {
				userSexStats = new UserSexStats();
				userSexStats.setSysNum(CustomerContextHolder.getSystemNum());
				userSexStats.setSex(Integer.valueOf(((Object[]) list.get(i))[0].toString()));
				userSexStats.setNum(Integer.valueOf(((Object[]) list.get(i))[1].toString()));
				userSexStatsList.add(userSexStats);
			}
		}
		return userSexStatsList;
	}
	
	/**
	 * 按是否分组渠道来查询某段时间内的创角人数
	 * @param dates 起始时间和结束时间
	 * @return
	 */
	public List<Object> findCreateUserNumInSomeTime(String[] dates,boolean isPartner){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(u.user_id)");
		if(isPartner){
			sb.append(",reg.CHANNEL");
		}
		sb.append(" FROM `user` u");
		if(isPartner){
			sb.append(",user_reg_log reg");
		}
		sb.append(" WHERE u.REG_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		if(isPartner){
			sb.append(" AND u.user_id=reg.user_id group by reg.CHANNEL");
		}
		userDao.closeSession(DBSource.LOG);
		return userDao.findSQL_(sb.toString());
	}
	
	/**
	 * 创建数
	 * @param date
	 * @return
	 */
	public List<Object> findCreateUserNum(String date, String tempChannelStr) {
		String beginDate = date + " 00:00:00";
		String endDate = date + " 23:59:59";
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(u.user_id),u.user_id FROM user u,user_reg_log log WHERE u.reg_time BETWEEN '");
		sb.append(beginDate);
		sb.append("' AND '");
		sb.append(endDate);
		sb.append("'");
		sb.append(" AND log.channel in (");
		String[] str = tempChannelStr.split(",");
		for (int i = 0; i < str.length; i++) {
			sb.append("'");
			sb.append(str[i]);
			sb.append("'");
			if (i < str.length - 1) {
				sb.append(",");
			}
		}
		
		sb.append(") AND u.user_id=log.user_id");
		userDao.closeSession(DBSource.LOG);
		return userDao.findSQL_(sb.toString());
	}
	
	/**
	 * 按是否分组渠道来查询某段时间内的注册人数(根据渠道)
	 * @param dates 起始时间和结束时间
	 * @return
	 */
	public Map<String, Integer> findRegUserNumInSomeTimeByChannel(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(reg.user_id), reg.CHANNEL");
		sb.append(" FROM user_reg_log reg");
		sb.append(" WHERE reg.REG_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by reg.CHANNEL");
		userDao.closeSession(DBSource.LOG);
		List<Object> list = userDao.findSQL_(sb.toString());
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				map.put(arr[1].toString(), Integer.valueOf(arr[0].toString()));
			}
		}
		return map;
	}
	
	/**
	 * 分组渠道来查询某段时间内的创建角色人数(根据渠道)
	 * @param dates 起始时间和结束时间
	 * @return
	 */
	public Map<String, Integer> findCreatedUserNumInSomeTimeByChannel(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(u.user_id), reg.CHANNEL");
		sb.append(" FROM `user` u, user_reg_log reg");
		sb.append(" WHERE u.REG_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" AND u.user_id = reg.user_id group by reg.CHANNEL");
		userDao.closeSession(DBSource.LOG);
		List<Object> list = userDao.findSQL_(sb.toString());
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				map.put(arr[1].toString(), Integer.valueOf(arr[0].toString()));
			}
		}
		return map;
	}
	
//	/**
//	 * 查询某段时间内的注册人数(根据idfa)
//	 * @param dates 起始时间和结束时间
//	 * @return
//	 */
//	public List<Object> findCreateUserNumInSomeTimeByIdfa(String[] dates){
//		StringBuffer sb = new StringBuffer();
//		sb.append("SELECT count(u.user_id)");
//		//sb.append(",reg.CHANNEL");
//		sb.append(" FROM `user_reg_log` reg");
////		sb.append(",user_reg_log reg");
//		sb.append(" WHERE reg.REG_TIME BETWEEN '");
//		sb.append(dates[0]);
//		sb.append("' AND '");
//		sb.append(dates[1]);
//		sb.append("'");
//		sb.append(" AND u.user_id=reg.user_id");
//		userDao.closeSession(DBSource.LOG);
//		return userDao.findSQL_(sb.toString());
//	}
	
	/**
	 * 渠道分组的 当天创角人数
	 * @param dates 起始时间和结束时间
	 * @return
	 */
	public Map<String, Integer> findCreateUserNumInSomeTime(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT reg.CHANNEL,(case when reg.SMALL_CHANNEL='null' then 0 else reg.SMALL_CHANNEL end) AS SMALL_CHANNEL1,count(u.user_id) FROM `user` u,user_reg_log reg");
		sb.append(" WHERE u.REG_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" AND u.user_id=reg.user_id group by reg.CHANNEL,SMALL_CHANNEL1");
		userDao.closeSession(DBSource.LOG);
		List<Object> list = userDao.findSQL_(sb.toString());
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				String smallChannel = arr[1].toString();
				
				map.put(arr[0].toString()+" "+smallChannel, Integer.valueOf(arr[2].toString()));
			}
		}
		return map;
	}
	
	/**
	 * 按时间分组的每天的创角人数
	 * @param dates 起始时间和结束时间
	 * @return
	 */
	public Map<String, Integer> findNewUserInSomeTime(String[] dates,Integer channel){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DATE_FORMAT(u.REG_TIME,'%Y-%m-%d'),ifnull(count(u.user_id),0)");
		sb.append(" FROM `user` u");
		if(channel!=null && channel.intValue()!=0){
			sb.append(",user_reg_log reg");
		}
		sb.append(" WHERE u.REG_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		if(channel!=null && channel.intValue()!=0){
			sb.append(" AND u.user_id=reg.user_id");
			sb.append(" and reg.channel='");
			sb.append(channel.toString());
			sb.append("'");
		}
		sb.append(" group by DATE_FORMAT(u.REG_TIME,'%Y-%m-%d')");
		userDao.closeSession(DBSource.LOG);
		List<Object> list =  userDao.findSQL_(sb.toString());
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString(),Integer.valueOf(arr[1].toString()));
			}
		}
		return map;
	}
	
	/**
	 * 查询某个时间点之前的创角数,根据时间、渠道分组
	 * @return
	 */
	public Map<String, Integer> findNewUserBeforeTime(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DATE_FORMAT(u.REG_TIME,'%Y-%m-%d'),reg.channel,ifnull(count(u.user_id),0)");
		sb.append(" FROM `user` u");
		sb.append(",user_reg_log reg");
		sb.append(" WHERE u.REG_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" AND u.user_id=reg.user_id");
		sb.append(" group by DATE_FORMAT(u.REG_TIME,'%Y-%m-%d'),reg.channel");
		userDao.closeSession(DBSource.LOG);
		List<Object> list =  userDao.findSQL_(sb.toString());
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString()+"_"+arr[1].toString(),Integer.valueOf(arr[2].toString()));
			}
		}
		return map;
	}
	
	/**
	 * 按时间分组的每天的创角人数
	 * @param dates 起始时间和结束时间
	 * @return
	 */
	public int findNewUserNumInSomeTime(String[] dates,Integer channel){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ifnull(count(u.user_id),0)");
		sb.append(" FROM `user` u");
		if(channel!=null && channel.intValue()!=0){
			sb.append(",user_reg_log reg");
		}
		sb.append(" WHERE u.REG_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		if(channel!=null && channel.intValue()!=0){
			sb.append(" AND u.user_id=reg.user_id");
			sb.append(" and reg.channel='");
			sb.append(channel.toString());
			sb.append("'");
		}
		userDao.closeSession(DBSource.LOG);
		List<Object> list =  userDao.findSQL_(sb.toString());
		if(list!=null && list.size()>0){
			return Integer.valueOf(list.get(0).toString());
		}
		return 0;
	}
	
	/**
	 * 所有的玩家角色名
	 * @return
	 */
	public Set<String> findAllUserName(){
		Set<String> nameSet = new HashSet<String>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT name FROM `user`");
		userDao.closeSession(DBSource.LOG);
		List<Object> list = userDao.findSQL_(sb.toString());
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i).toString();
			nameSet.add(name);
		}
		return nameSet;
	}
	
	public List<Object> findVipUser() {
		StringBuffer sb = new StringBuffer();
		sb.append("select user_id from user where vip_level >= 1");
		userDao.closeSession(DBSource.USER);
		return userDao.findNewSQL_(sb.toString());
	}
	
	public int findActityVipUser(String[] dates, List<String> userIds) {
		StringBuffer sb = new StringBuffer();
		if (!userIds.isEmpty()) {
			sb.append("select count(distinct(log.user_id)) from (select * from user_login_log union all select * from ");
			sb.append("user_login_log_bak) log where log.login_time between '");
			sb.append(dates[0]);
			sb.append("'");
			sb.append(" and '");
			sb.append(dates[1]);
			sb.append("'");
			sb.append(" and log.user_id in (");
			for (int i = 0; i < userIds.size(); i++) {
				sb.append("'");
				sb.append(userIds.get(i));
				sb.append("'");
				if (i < userIds.size() - 1) {
					sb.append(",");
				}
			}
			sb.append(")");
			userDao.closeSession(DBSource.LOG);
			List<Object> objList = userDao.findSQL_(sb.toString());
			if (objList != null) {
				for (Object obj : objList) {
					return ((BigInteger) obj).intValue();
				}
				return 0;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	/**
	 * 所有的玩家
	 * @return
	 */
	public List<User> findAllUser(){
		List<User> userList = userDao.find("from User", DBSource.LOG);
		return userList;
	}
	
	public List<Object> findCountByIdfa() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT REG_IDFA FROM `user_reg_log` GROUP BY REG_IDFA");
		userDao.closeSession(DBSource.LOG);
		List<Object> list = userDao.findSQL_(sql.toString());
		return list;
	}
	
	
	public List<Object> findPayList(String startDate, String endDate){
		StringBuffer sb = new StringBuffer();
		sb.append("select u.lodo_id from payment_log log,user u where log.created_time between '");
		sb.append(startDate);
		sb.append("' and '");
		sb.append(endDate);
		sb.append("' and log.user_id=u.user_id group by u.lodo_id");
		userDao.closeSession(DBSource.USER);
		List<Object> list = userDao.findSQL_(sb.toString());
		return list;
	}
	
	public List<Object> findPkRankList(){
		StringBuffer sb = new StringBuffer();
		sb.append("select u.lodo_id from user_pk_info pk,user u where pk.user_id=u.user_id ORDER BY pk.rank ASC limit 10");
		userDao.closeSession(DBSource.USER);
		List<Object> list = userDao.findSQL_(sb.toString());
		return list;
	} 
	
	public List<Object> findLevelRankList(){
		StringBuffer sb = new StringBuffer();
		sb.append("select u.lodo_id from user u  ORDER BY u.level DESC limit 20");
		userDao.closeSession(DBSource.USER);
		List<Object> list = userDao.findSQL_(sb.toString());
		return list;
	} 
	
	/**
	 * 查询军团列表
	 * @return
	 */
	public List<Object> findLegionList(){
		StringBuffer sb = new StringBuffer();
		sb.append("select id,name,notice,level,power,contribution from legion_info  ORDER BY power DESC");
		userDao.closeSession(DBSource.USER);
		List<Object> list = userDao.findSQL_(sb.toString());
		return list;
	} 
	
	/**
	 * 查询军团成员列表
	 * @return
	 */
	public List<Object> findUserLegionInfoList(int legionId){
		StringBuffer sb = new StringBuffer();
		sb.append("select b.lodo_id as lodoId,b.username as name,a.identity as identity,a.total_contribution as contribution from user_legion_info a,user b where a.user_id=b.user_id and a.legion_id = ");
		sb.append(legionId);
		sb.append(" ORDER BY a.total_contribution DESC");
		userDao.closeSession(DBSource.USER);
		List<Object> list = userDao.findSQL_(sb.toString());
		return list;
	} 
	
	public List<Object> findByMobile() {
		userDao.closeSession(DBSource.LOG);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT REG_MOBILE");
		sb.append(" FROM `user_reg_log` u");
		List<Object> list = userDao.findSQL_(sb.toString());
		return list;
//		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT COUNT(USER_ID) FROM `user_reg_log` GROUP BY REG_MOBILE");
//		userDao.closeSession(DBSource.LOG);
//		List<Object> list = userDao.findSQL_(sql.toString());
//		if (list != null && list.size() != 0) {
//			return Integer.valueOf(list.get(0).toString());
//		} else {
//			return 0;
//		}
	}
	
	/**
	 * 开服后某段时间内的注册用户数
	 * @param serverOpenTimeStr 开服时间
	 * @param days 第几天
	 * @return
	 */
	public int countSomeDayRegedUser(String[] regDateStrArr){
		
		StringBuffer sql = new StringBuffer();
		sql.append("FROM User WHERE REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		
		userDao.closeSession(DBSource.LOG);
		return userDao.count(sql.toString(), new ArrayList<Object>());
	}
	
	
	
	/****---------UserRegLog合并到了User里面,以下是UserRegLogService的方法--------****/
	
	/**
	 * 注册总人数
	 * @return
	 */
//	public int findTotalRegUserNum(){
//		userDao.closeSession(DBSource.LOG);
//		return userDao.count("from User", new ArrayList<Object>());
//	}
//	
//	/**
//	 * 指定时间前的注册总人数
//	 * @return
//	 */
//	public int findTotalRegUserNumBeforeDate(String time){
//		userDao.closeSession(DBSource.LOG);
//		return userDao.count("from User where regTime <= '" + time + "'", new ArrayList<Object>());
//	}
//	
//	/**
//	 * 在A时间段注册的人中在B时间段各种登陆的情况
//	 * @param regStartDate 注册开始日期
//	 * @param regEndDate 注册结束日期
//	 * @param loginStartDate 登录开始日期
//	 * @param loginEndDate 登录结束日期
//	 * @return
//	 */
//	public Map<String,Integer> findReLoginMap(Date regStartDate, Date regEndDate, Date loginStartDate, Date loginEndDate){
//		Map<String,Integer> map = new HashMap<String, Integer>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		StringBuffer sql = new StringBuffer();
//		//datestr是用户登录的各个日期的day的拼接字符串，count是对应的人数
//		sql.append("SELECT login.dayStr, COUNT(reg.USER_ID)  FROM ");
//		sql.append("(SELECT USER_ID FROM `user` WHERE DAY(REG_TIME) >= DAY('").append(sdf.format(regStartDate)).append("') AND DAY(REG_TIME) <= DAY('").append(sdf.format(regEndDate)).append("')) reg, ");
//		sql.append("(");
//		//不用CASThibernate不认识group_concat后的结果。。。
//		sql.append(" SELECT USER_ID, CAST(GROUP_CONCAT(DISTINCT(DAY(LOGIN_TIME))) AS CHAR) AS dayStr FROM user_login_log WHERE DAY(LOGIN_TIME) >= DAY('").append(sdf.format(loginStartDate)).append("') AND DAY(LOGIN_TIME) <= DAY('").append(sdf.format(loginEndDate)).append("') GROUP BY USER_ID");
//		sql.append(") login");
//		sql.append(" WHERE reg.USER_ID = login.USER_ID GROUP BY login.dayStr");
//		userDao.closeSession(DBSource.LOG);
//		List<Object> list = userDao.findSQL_(sql.toString());
//		
//		if (list != null && list.size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//				String dateStr = ((Object[]) list.get(i))[0].toString();
//				Integer amount = Integer.parseInt(((Object[]) list.get(i))[1].toString());
//				map.put(dateStr, amount);
//			}
//		}
//		
//		return map;
//	}
	
	/****-----------------------------------------------------------------------****/
	
	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserMapperDao getUserMapperDao() {
		return userMapperDao;
	}

	public void setUserMapperDao(UserMapperDao userMapperDao) {
		this.userMapperDao = userMapperDao;
	}
	
}
