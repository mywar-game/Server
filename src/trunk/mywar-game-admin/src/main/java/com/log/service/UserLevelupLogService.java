package com.log.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.admin.util.Tools;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserLevelupLog;
import com.log.dao.UserLevelupLogDao;

/**
 * 玩家升级日志Service
 * @author hzy
 * 2012-4-18
 */
public class UserLevelupLogService {

	private UserLevelupLogDao userLevelupLogDao;
	
	public IPage<UserLevelupLog> findUserLevelUpLogByCondition(String userId,Date startDate,Date endDate, int currentPage, int pageSize) {
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select new UserLevelupLog(log.time, log.userId, log.level, user.userName, user.lodoId)  from UserLevelupLog log, User user where log.userId = user.userId");
		if (userId != null) {
			sql.append(" and log.userId = ?");
			args.add(userId);
		}
		if (startDate != null && endDate != null) {
			sql.append(" and log.time BETWEEN ? and ?");
			args.add(startDate);
			args.add(endDate);
		}
		sql.append(" order by log.time DESC");
		userLevelupLogDao.closeSession(DBSource.LOG);
		return userLevelupLogDao.findPage(sql.toString(), args, pageSize, currentPage);
		
	}

	/**
	 * 等级及到达当前等级的玩家数 
	 */
	public Map<Integer, Integer> findLevelAndUserAmount() {
//		StringBuffer sb = new StringBuffer();
//		sb.append("SELECT LEVEL, COUNT(USER_ID) FROM user_levelup_log GROUP BY LEVEL");
//		userLevelupLogDao.closeSession(DBSource.LOG);
//		List<Object> list = userLevelupLogDao.findSQL_(sb.toString());
//		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
//		for (int i = 0; i < list.size(); i++) {
//			int level = Integer.parseInt(((Object[]) list.get(i))[0].toString());
//			int userAmount = Integer.parseInt(((Object[]) list.get(i))[1].toString());
//			map.put(level, userAmount);
//		}
//		return map;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT USER_ID, MAX(LEVEL) FROM user_levelup_log GROUP BY USER_ID");
		userLevelupLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLevelupLogDao.findSQL_(sb.toString());
		Map<Integer, Integer> allLevelMap = new LinkedHashMap<Integer, Integer>();
		for (int i = 1; i <= 120; i++) {
			allLevelMap.put(i, 0);
		}
		for (int i = 0; i < list.size(); i++) {
//			long userId = Long.parseLong(((Object[]) list.get(i))[0].toString());
			int level = Integer.parseInt(((Object[]) list.get(i))[1].toString());
			
//			if (resultMap.get(level) == 0) {
//				resultMap.put(level, 1);
//			} else {
				for (int j = 1; j <= level && level<=120; j++) {
//					System.out.println("j===========================================" + j);
					allLevelMap.put(j, allLevelMap.get(j) + 1);
				}
//			}
		}
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
		allLevelMap.put(1, userRegLogService.findTotalRegUserNum());
		Map<Integer, Integer> resultMap = new LinkedHashMap<Integer, Integer>();
		Set<Entry<Integer, Integer>> set = allLevelMap.entrySet();
		Iterator<Entry<Integer, Integer>> ite = set.iterator();
		while (ite.hasNext()) {
			Entry<Integer, Integer> entry = ite.next();
			if (entry.getValue() != 0) {
				resultMap.put(entry.getKey(), entry.getValue());
			}
		}
//		System.out.println(resultMap);
		return resultMap;
		
	}
	
	/**
	 * 等级及到达当前等级的玩家数 , 某段时间范围内注册的玩家
	 */
	public Map<Integer, Integer> findLevelAndUserAmountBetweenDay(String bTime, String eTime) {

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT USER_ID, MAX(LEVEL) FROM user_levelup_log WHERE USER_ID IN (SELECT USER_ID FROM `user` WHERE REG_TIME BETWEEN '");
		sb.append(bTime);
		sb.append("'");
		sb.append(" AND '");
		sb.append(eTime);
		sb.append("')");
		sb.append(" GROUP BY USER_ID");
		userLevelupLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLevelupLogDao.findSQL_(sb.toString());
		Map<Integer, Integer> allLevelMap = new LinkedHashMap<Integer, Integer>();
		for (int i = 1; i <= 120; i++) {
			allLevelMap.put(i, 0);
		}
		for (int i = 0; i < list.size(); i++) {
			int level = Integer.parseInt(((Object[]) list.get(i))[1].toString());
			for (int j = 1; j <= level && level<=120; j++) {
				allLevelMap.put(j, allLevelMap.get(j) + 1);
			}
		}
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
//		allLevelMap.put(1, userRegLogService.findTotalRegUserNum());
		String[] timeArr = new String[]{bTime,eTime};
		allLevelMap.put(1, userRegLogService.findRegUserNumInSomeTime(timeArr));
		Map<Integer, Integer> resultMap = new LinkedHashMap<Integer, Integer>();
		Set<Entry<Integer, Integer>> set = allLevelMap.entrySet();
		Iterator<Entry<Integer, Integer>> ite = set.iterator();
		while (ite.hasNext()) {
			Entry<Integer, Integer> entry = ite.next();
			if (entry.getValue() != 0) {
				resultMap.put(entry.getKey(), entry.getValue());
			}
		}
		return resultMap;
	}
	
	/**
	 * 各等级的玩家id字符串, 各个userId之间用, 隔开
	 * @return
	 */
	public Map<Integer, String> findUserIdStrEveryLevel() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT MAX(`LEVEL`) AS LEVEL, USER_ID FROM user_levelup_log GROUP BY USER_ID");
		userLevelupLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLevelupLogDao.findSQL_(sb.toString());
		for (int i = 0; i < list.size(); i++) {
			int level = Integer.parseInt(((Object[]) list.get(i))[0].toString());
			String userId = ((Object[]) list.get(i))[1].toString();
			
			//如果没有此等级
			if (Tools.isEmpty(map.get(level))) {
				map.put(level, userId);
			} else {
				map.put(level, map.get(level) + "," + userId);
			}
		}
		return map;
	}
	
	/**
	 * 指定玩家在指定时间后升到整10级的升级日志列表
	 * @param userId
	 * @return
	 */
	public List<UserLevelupLog> findTenLevelLogByUserIdAndTime(String userId, Date d){
		List<UserLevelupLog> logList = new ArrayList<UserLevelupLog>();
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sb.append("SELECT LEVEL FROM user_levelup_log where (LEVEL MOD 10 ) = 0  AND TIME >= '").append(sdf.format(d)).append("' AND USER_ID = '").append(userId).append("'");
//		System.out.println(" == "+ sb.toString());
		userLevelupLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLevelupLogDao.findSQL_(sb.toString());
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				UserLevelupLog userLevelupLog = new UserLevelupLog(); 
				Integer level = Integer.valueOf(list.get(i).toString());
				userLevelupLog.setLevel(level);
				logList.add(userLevelupLog);
			}
		}
		return logList;
	}
	
	/**
	 * 玩家不同等级区间的人数(0-10,1-20,....23-40)
	 * @return
	 */
	public Map<Integer, Integer> findUserLevelIndexCount(){
		StringBuffer sb = new StringBuffer();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		sb.append("select (a.uLevel-1) DIV 5 as levelIndex,count(a.user_id) from(");
		sb.append("select u.user_id,ifnull(log.mLevel,1) as uLevel from user u left join");
		sb.append(" (select user_id,max(level) as mLevel from user_levelup_log group by user_id) log on u.user_id=log.user_id) a");
		sb.append(" group by levelIndex");
		userLevelupLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLevelupLogDao.findSQL_(sb.toString());
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(Integer.valueOf(arr[0].toString()), Integer.valueOf(arr[1].toString()));
			}
		}
		return map;
	}
	
	/**
	 * 渠道分组的玩家等级分布数据
	 * @return
	 */
	public List<Object> findLevelDataByChannel(){
		StringBuffer sb = new StringBuffer();
		sb.append("select a.userLevel,a.channel,count(a.user_id),sum(a.userNum),sum(a.payUserNum),sum(a.userAmount),sum(a.userPayTimes),sum(a.userBuyConsume) from");
		sb.append(" (select reg.user_id as user_id,reg.channel as channel,ifnull(u.hasU,0) as userNum,ifnull(log.mLevel,1) as userLevel,");
		sb.append("ifnull(pay.pUser,0) as payUserNum,ifnull(pay.payAmount,0) as userAmount,ifnull(pay.payTimes,0) as userPayTimes,ifnull(mall.buyConsume,0) as userBuyConsume");
		sb.append(" from user_reg_log reg left join (select user_id,1 as hasU from user) u on reg.user_id=u.user_id");
		sb.append(" left join (select user_id,max(level) as mLevel from user_levelup_log group by user_id) log on reg.user_id=log.user_id");
		sb.append(" left join (select user_id,1 as pUser,sum(amount) as payAmount,count(user_id) as payTimes from payment_log group by user_id) pay on reg.user_id=pay.user_id");
		sb.append(" left join (select user_id,sum(cost) as buyConsume from");
		sb.append(" (select user_id,cost from user_mall_log union all select user_id,cost from user_mall_log_bak) mallLog group by user_id) mall");
		sb.append(" on reg.user_id=mall.user_id) a group by a.userLevel,a.channel");
		userLevelupLogDao.closeSession(DBSource.LOG);
		return userLevelupLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 玩家不同等级区间的人数(0-10,1-20,....23-40),根据日期。手动采集使用
	 * @return
	 */
	public Map<Integer, Integer> findUserLevelIndexCountByDate(String dateStr){
		StringBuffer sb = new StringBuffer();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		sb.append("select (a.uLevel-1) DIV 5 as levelIndex,count(a.user_id) from(");
		sb.append("select u.user_id,ifnull(log.mLevel,1) as uLevel from user u left join");
		sb.append(" (select user_id,max(level) as mLevel from user_levelup_log where time < '");
		sb.append(dateStr);
		sb.append("'");		
		sb.append(" group by user_id) log on u.user_id=log.user_id) a");
		sb.append(" group by levelIndex");
		userLevelupLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLevelupLogDao.findSQL_(sb.toString());
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(Integer.valueOf(arr[0].toString()), Integer.valueOf(arr[1].toString()));
			}
		}
		return map;
	}
	
	public void setUserLevelupLogDao(UserLevelupLogDao userLevelupLogDao) {
		this.userLevelupLogDao = userLevelupLogDao;
	}

	public UserLevelupLogDao getUserLevelupLogDao() {
		return userLevelupLogDao;
	}
}
