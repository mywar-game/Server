package com.log.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.adminTool.bo.UserRankSnapshot;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.LoginLog;
import com.log.bo.LogoutLog;
import com.log.bo.PaymentLog;
import com.log.bo.UserLog;
import com.log.bo.UserPayHistoryLog;
import com.log.dao.UserPayHistoryLogDao;
import com.stats.bo.PayUserInfoStats;
import com.stats.service.PayUserInfoStatsService;

public class UserPayHistoryLogService {

	private UserPayHistoryLogDao userPayHistoryLogDao;
	
	/**
	 * 根据玩家Id确定玩家是否充值过
	 * @param userId
	 * @return
	 */
	public boolean isUserPay(long userId) {
		UserPayHistoryLog userPayHistoryLog = userPayHistoryLogDao.loadBy("userId", userId, DBSource.LOG);
		if (userPayHistoryLog != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 给定的玩家中有多少玩家是充值玩家
	 * @param userIdStr
	 * @return
	 */
	public Integer findPayUserAmountByUserIdStr(String userIdStr) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(DISTINCT(USER_ID)) from payment_log WHERE USER_ID IN (");
		sb.append(userIdStr);
		sb.append(")");
		
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sb.toString());
		Integer payAmount = Integer.valueOf(list.get(0).toString());
		return payAmount;
	}
	
	/**
	 * 给定的玩家中有多少玩家是充值玩家(根据时间，重新采集数据)
	 * @param userIdStr
	 * @return
	 */
	public Integer findPayUserAmountByUserIdStrWithTime(String userIdStr, String time) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(DISTINCT(USER_ID)) from payment_log WHERE USER_ID IN (");
		sb.append(userIdStr);
		sb.append(")");
		sb.append(" AND CREATED_TIME <= '");
		sb.append(time);
		sb.append("'");
		
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sb.toString());
		Integer payAmount = Integer.valueOf(list.get(0).toString());
		return payAmount;
	}
	
	/**
	 * 玩家id和玩家的充值次数和总数(按充值数排序)
	 * @param order 降序还是升序还是不排序
	 * @return
	 */
	public Map<Long, String> getEveryUserPayAmount(String order) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT USER_ID,COUNT(*),SUM(AMOUNT) FROM user_pay_history_log GROUP BY USER_ID");
		if (!Tools.isEmpty(order)) {
			sb.append(" ORDER BY SUM(AMOUNT) ").append(order);
		}
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sb.toString());
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				long userId = Long.valueOf(((Object[]) list.get(i))[0].toString());
				Integer payTimes = Integer.valueOf(((Object[]) list.get(i))[1].toString());
				Integer payAmount = Integer.valueOf(((Object[]) list.get(i))[2].toString());
				map.put(userId, payTimes + "_" +payAmount);
			}
		}
		return map;
	}
	
	/**
	 * 玩家id和玩家的充值次数和总数及当前等级(按充值数排序)
	 * @param order 降序还是升序还是不排序
	 * @return
	 */
	public Map<Integer, String> getEveryUserPayTimesAndAmountAndNowLevel() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT (USER_LEVEL DIV 10) AS levelIntervalIndex,COUNT(DISTINCT(USER_ID)),COUNT(*) AS payTimes,SUM(AMOUNT) AS payAmount FROM user_pay_history_log GROUP BY levelIntervalIndex");
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sb.toString());
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Integer levelIntervalIndex = Integer.valueOf(((Object[]) list.get(i))[0].toString());
				Integer payUserNum = Integer.valueOf(((Object[]) list.get(i))[1].toString());
				Integer payTimes = Integer.valueOf(((Object[]) list.get(i))[2].toString());
				Integer payAmount = Integer.valueOf(((Object[]) list.get(i))[3].toString());
				map.put(levelIntervalIndex, payUserNum + "_" +payTimes + "_" +payAmount);
			}
		}
		return map;
	}

	/**
	 * 获得给定玩家Id中所有玩家的充值总额
	 * @param userIdList
	 * @return
	 */
	public Integer getPayAmountByUserIds(String userIds) {
//		String userIds = userIdList.toString().substring(1, userIdList.toString().length()-1);
		if (Tools.isEmpty(userIds)) {
			return 0;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT IFNULL(SUM(AMOUNT),0) FROM user_pay_history_log WHERE USER_ID IN (");
		sb.append(userIds);
		sb.append(")");
		System.out.println(sb.toString());
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sb.toString());
		Integer payAmount = Integer.valueOf(list.get(0).toString());
		return payAmount;
	}
	
	/**
	 * 指定的玩家的各自的充值数
	 * @param userIds
	 * @return
	 */
	public Map<Long, Integer> findUserIdAndPayAmountMap(String userIds) {
		Map<Long, Integer> userIdAndPayAmountMap = new HashMap<Long, Integer>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_ID, IFNULL(SUM(AMOUNT), 0) FROM user_pay_history_log WHERE USER_ID IN (").append(userIds).append(") GROUP BY USER_ID");
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sql.toString());
		Long userId;
		Integer payAmount;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				userId = Long.valueOf(((Object[]) list.get(i))[0].toString());
				payAmount = Integer.valueOf(((Object[]) list.get(i))[1].toString());
				userIdAndPayAmountMap.put(userId, payAmount);
			}
		}
		return userIdAndPayAmountMap;
	}
	
	/**
	 * 日志列表
	 * @param user
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<UserPayHistoryLog> findUserPayHistoryLogListByCondition(String name, Integer currentPage, Integer pageSize) {
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select new UserPayHistoryLog(log.userId, log.userLevel, log.orderId, log.orderNum, log.amount, log.payTime, user.name) from UserPayHistoryLog log, User user where log.userId = user.userId");
		if (!Tools.isEmpty(name)) {
			sql.append(" and user.name = ?");
			args.add(name);
		}
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		return userPayHistoryLogDao.findPage(sql.toString(), args, pageSize, currentPage);
	}
	
	
	/**
	 * 付费用户的某些信息(性能不太好，可以改用inner join优化, 性能可以提高10倍)
	 * @throws ParseException
	 */
	public void findPayUserInfo() throws ParseException{
		StringBuffer sql = new StringBuffer();
		//角色名，账户名，regTime注册时间，lastPayTime最后一次充值时间， latLoginTime最后一次登陆时间，，总在线时长，总在线时长，登录天数
		sql.append("SELECT u.`NAME`, u.USER_NAME, u.REG_TIME, payLog.lastPayTime, login.lastLoginTime, IFNULL(live.onlineTime,0), IFNULL(login.loginDays,0), payLog.payNum, payLog.USER_ID  FROM (SELECT USER_ID, MAX(CREATED_TIME) AS lastPayTime, count(PAYMENT_LOG_ID) AS payNum FROM payment_log GROUP BY USER_ID) payLog LEFT JOIN `user` u ON payLog.USER_ID = u.USER_ID LEFT JOIN ( SELECT b.USER_ID, b.LOGIN_TIME AS lastLoginTime, a.loginDays FROM (SELECT MAX(ID) AS lastId, COUNT(DISTINCT(DATE_FORMAT(LOGIN_TIME,'%Y-%m-%d'))) AS loginDays FROM (select * from user_login_log union all select * from user_login_log_bak) loga GROUP BY loga.USER_ID) a LEFT JOIN (select * from user_login_log union all select * from user_login_log_bak) b ON a.lastId = b.ID ) login ON payLog.USER_ID = login.USER_ID LEFT JOIN (select USER_ID, sum(LIVE_MINUTES) AS onlineTime from (select * from user_logout_log union all select * from user_logout_log_bak) outlog GROUP BY USER_ID) live ON payLog.USER_ID = live.USER_ID"); 
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sql.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<PayUserInfoStats> payUserInfoStatsList = new ArrayList<PayUserInfoStats>();
		if (list != null && list.size() > 0) {
			PayUserInfoStats payUserInfoStats;
			Date regTime;
			Date lastPayTime;
			Date lastLoginTime;
			int loginDays;
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				payUserInfoStats = new PayUserInfoStats();
				payUserInfoStats.setSysNum(CustomerContextHolder.getSystemNum());
				payUserInfoStats.setName(arr[0].toString());
				payUserInfoStats.setUserName(arr[1].toString());
				regTime = sdf.parse(arr[2].toString());
				payUserInfoStats.setRegTime(new Timestamp(regTime.getTime()));
				lastPayTime = sdf.parse(arr[3].toString());
				if(arr[4]==null){
					lastLoginTime = lastPayTime;
				}else{
					lastLoginTime = sdf.parse(arr[4].toString());
				}
				payUserInfoStats.setLastLoginTime(new Timestamp(lastLoginTime.getTime()));
				payUserInfoStats.setLogCondition(new Integer(DateUtil.dayDiff(new Date(System.currentTimeMillis()), lastLoginTime)+""));
				payUserInfoStats.setTotalOnlineTime(Integer.valueOf(arr[5].toString()));
				loginDays = Integer.valueOf(arr[6].toString());
				payUserInfoStats.setAverageOnlineTime(loginDays==0?0:payUserInfoStats.getTotalOnlineTime()/loginDays);
				payUserInfoStats.setAveragePayPeriod(Integer.valueOf((lastPayTime.getTime() - regTime.getTime())/(Integer.valueOf(arr[7].toString())*1000*60)+""));
				//军团名
				payUserInfoStats.setGuildName("");
				payUserInfoStatsList.add(payUserInfoStats);
			}
		}
		PayUserInfoStatsService statsService = ServiceCacheFactory.getServiceCache().getService(PayUserInfoStatsService.class);
		statsService.savePayUserInfoStatsList(payUserInfoStatsList);
		
	}
	
	/**
	 * 充值总额前20的玩家
	 */
	public List<UserRankSnapshot> findPayAmountTop20(String createName){
		List<UserRankSnapshot> userRankSnapshotList = new ArrayList<UserRankSnapshot>();
		StringBuffer sql = new StringBuffer();
//		SELECT a.USER_ID, b.`NAME`,  a.payAmount FROM (
//				SELECT USER_ID, SUM(AMOUNT) AS payAmount FROM user_pay_history_log GROUP BY USER_ID ORDER BY payAmount DESC LIMIT 20
//		) a LEFT JOIN `user` b ON a.USER_ID = b.USER_ID
		sql.append("SELECT a.USER_ID, b.`NAME`,  a.payAmount FROM (");
		sql.append(" SELECT USER_ID, SUM(AMOUNT) AS payAmount FROM user_pay_history_log GROUP BY USER_ID ORDER BY payAmount DESC LIMIT 20");
		sql.append(") a LEFT JOIN `user` b ON a.USER_ID = b.USER_ID");
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			Timestamp createTime = new Timestamp(System.currentTimeMillis());
			for (int i = 0; i < list.size(); i++) {
				UserRankSnapshot userRankSnapshot = new UserRankSnapshot();
				userRankSnapshot.setSysNum(CustomerContextHolder.getSystemNum());
				userRankSnapshot.setCreateName(createName);
				userRankSnapshot.setCreateTime(createTime);
				userRankSnapshot.setType(6);
				userRankSnapshot.setUserId(Long.valueOf(((Object[]) list.get(i))[0].toString()));
				userRankSnapshot.setName(((Object[]) list.get(i))[1].toString());
				userRankSnapshot.setRank(i+1);
				userRankSnapshot.setNote(((Object[]) list.get(i))[2].toString());
				userRankSnapshotList.add(userRankSnapshot);
			}
		}
		return userRankSnapshotList;
	}
	
	/**
	 * 查询payment_log表
	 * @return
	 */
	public List<PaymentLog> findPayLog()  throws ParseException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (SELECT USER_ID, MAX(CREATED_TIME) AS lastPayTime, count(PAYMENT_LOG_ID) AS payNum FROM payment_log GROUP BY USER_ID) payLog");
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sb.toString());
		List<PaymentLog> resultList = new ArrayList<PaymentLog>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = (Object[]) list.get(i);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String userId = objArr[0].toString();
				Date lastPayTime = sdf.parse(objArr[1].toString());
				Integer payNum = Integer.valueOf(objArr[2].toString());
				PaymentLog paymentLog = new PaymentLog();
				paymentLog.setUserId(userId);
				paymentLog.setLastPayTime(lastPayTime);
				paymentLog.setPayNum(payNum);
				resultList.add(paymentLog);
			}
		}
		return resultList;
	}
	
	/**
	 * 根据付费查询到的userId, 查找用户信息
	 * @param userId
	 * @throws ParseException
	 */
	public Map<String, UserLog> findUserInfo(String[] userIds) throws ParseException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (SELECT USER_ID, NAME, USER_NAME, REG_TIME FROM `user` where USER_ID IN ");
		sb.append("(");
		int index = 0;
		for (String userId : userIds) {
			sb.append("'");
			sb.append(userId);
			sb.append("'");
			if (index < userIds.length - 1) {
				sb.append(",");
			}	
			index ++;
		}
		sb.append(")");
		sb.append(") u");
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sb.toString());
		Map<String, UserLog> resultMap = new HashMap<String, UserLog>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = (Object[]) list.get(i);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String userId = objArr[0].toString();
				String name = objArr[1].toString();
				String userName = objArr[2].toString();
				Date regTime = sdf.parse(objArr[3].toString());
				UserLog userLog = new UserLog();
				userLog.setUserId(userId);
				userLog.setName(name);
				userLog.setUserName(userName);
				userLog.setRegTime(regTime);
				resultMap.put(userId, userLog);
			}
		}
		return resultMap;
	}
	
	/**
	 * 根据付费查询到的userId, 查找用户登入信息
	 * @param userIds
	 * @throws ParseException 
	 */
	public Map<String, LoginLog> findLoginInfo(String[] userIds) throws ParseException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM (SELECT b.USER_ID, b.LOGIN_TIME AS lastLoginTime, a.loginDays FROM (SELECT MAX(ID) AS lastId, COUNT(DISTINCT(DATE_FORMAT(LOGIN_TIME,'%Y-%m-%d'))) AS loginDays FROM"); 
		sb.append(" (select * from user_login_log union all select * from user_login_log_bak) loga where USER_ID IN ");
		sb.append("(");
		int index = 0;
		for (String userId : userIds) {
			sb.append("'");
			sb.append(userId);
			sb.append("'");
			if (index < userIds.length - 1) {
				sb.append(",");
			}	
			index ++;
		}
		sb.append(")");
		sb.append(" GROUP BY loga.USER_ID) a ");
		sb.append(" inner JOIN ");
		sb.append(" (select * from user_login_log union all select * from user_login_log_bak) b ");
		sb.append(" ON a.lastId = b.ID ) login ");
		
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sb.toString());
		Map<String, LoginLog> resultMap = new HashMap<String, LoginLog>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = (Object[]) list.get(i);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String userId = objArr[0].toString();
				Date lastLoginTime = sdf.parse(objArr[1].toString());
				Integer loginDays = Integer.valueOf(objArr[2].toString());
				LoginLog loginLog = new LoginLog();
				loginLog.setUserId(userId);
				loginLog.setLastLoginTime(lastLoginTime);
				loginLog.setLoginDays(loginDays);
				resultMap.put(userId, loginLog);
			}
		}
		return resultMap;
	}
	
	/**
	 * 根据付费查询到的userId, 查找用户登出信息
	 * @param userIds
	 * @throws ParseException 
	 */
	public Map<String, LogoutLog> findLogoutInfo(String[] userIds) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("select * from (select USER_ID, sum(LIVE_MINUTES) AS onlineTime from (select * from user_logout_log union all select * from user_logout_log_bak) outlog where USER_ID IN ");
		sb.append("(");
		int index = 0;
		for (String userId : userIds) {
			sb.append("'");
			sb.append(userId);
			sb.append("'");
			if (index < userIds.length - 1) {
				sb.append(",");
			}	
			index ++;
		}
		sb.append(")");
		sb.append(" GROUP BY USER_ID) live");
		
		userPayHistoryLogDao.closeSession(DBSource.LOG);
		List<Object> list = userPayHistoryLogDao.findSQL_(sb.toString());
		Map<String, LogoutLog> resultMap = new HashMap<String, LogoutLog>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = (Object[]) list.get(i);
				String userId = objArr[0].toString();
				Integer onlineTime = Integer.valueOf(objArr[1].toString());
				LogoutLog logoutLog = new LogoutLog();
				logoutLog.setUserId(userId);
				logoutLog.setOnlineTime(onlineTime);
				resultMap.put(userId, logoutLog);
			}
		}
		return resultMap;
	}
	
	public UserPayHistoryLogDao getUserPayHistoryLogDao() {
		return userPayHistoryLogDao;
	}

	public void setUserPayHistoryLogDao(UserPayHistoryLogDao userPayHistoryLogDao) {
		this.userPayHistoryLogDao = userPayHistoryLogDao;
	}
}
