package com.log.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.common.SystemStatsDate;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.util.DateUtil;
import com.log.dao.UserLoginLogDao;
import com.stats.action.UserInSomeTimeLoginStatsList;
import com.stats.bo.UserEverydayLoginStats;
import com.stats.bo.UserIpStats;

public class UserLoginLogService {
	private UserLoginLogDao userLoginLogDao;

	/**
	 * 日志分页列表
	 * 
	 * @param user
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<Object> findUserPageLogListByCondition(int lodoId,
			Date startDate, Date endDate, Integer currentPage, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and login_time BETWEEN '")
					.append(sdf.format(startDate)).append("' AND '")
					.append(sdf.format(endDate)).append("'");
		}
		sql.append("select log.user_id, log.level, log.ip, log.login_time,u.user_name,u.lodo_id from (select * from user_login_log where 1=1");
		sql.append(queryStr.toString());
		sql.append("union all select * from user_login_log_bak where 1=1");
		sql.append(queryStr.toString());
		sql.append(") log, user u where log.user_id = u.user_id");
		if (lodoId != 0) {
			sql.append(" and u.lodo_id = ").append(lodoId);
		}
		userLoginLogDao.closeSession(DBSource.LOG);
		return userLoginLogDao.findSQL_Page(sql.toString(),
				new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 根据时间段查找用户登陆Id
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Object> findUserLoginIdList(Date startDate, Date endDate) {
		StringBuffer sql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();

		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and login_time BETWEEN '")
					.append(sdf.format(startDate)).append("' AND '")
					.append(sdf.format(endDate)).append("'");
		}

		sql.append("select u.lodo_id from (select * from user_login_log where 1=1");
		sql.append(queryStr.toString());
		sql.append("union all select * from user_login_log_bak where 1=1");
		sql.append(queryStr.toString());
		sql.append(") log, user u where log.user_id = u.user_id GROUP BY u.LODO_ID");
		userLoginLogDao.closeSession(DBSource.LOG);

		return userLoginLogDao.findSQL_(sql.toString());
	}

	/**
	 * 以当前时间算活跃玩家总数
	 * 
	 * @param dates
	 * @return
	 */
	public int findNowActiveUserAmount() {
		StringBuffer sb = new StringBuffer();
		String[] dates = new String[2];
		dates[0] = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY
				+ SystemStatsDate.THREE_DAYS_AGO)[1];
		dates[1] = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY)[1];
		;
		sb.append("SELECT COUNT(DISTINCT(USER_ID)) FROM (select user_id,login_time from user_login_log union all select user_id,login_time from user_login_log_bak) log WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sb.toString());
		int activeUserAmount = 0;
		if (list != null && list.size() > 0) {
			activeUserAmount = ((BigInteger) list.get(0)).intValue();
		}
		return activeUserAmount;
	}

	/**
	 * 算某个时间段内的活跃玩家总数
	 * 
	 * @param dates
	 * @return
	 */
	public int findActiveUserAmount(String[] dates, Integer channel) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(DISTINCT(log.USER_ID)) FROM");
		sb.append(" (select user_id,login_time from user_login_log union all select user_id,login_time from user_login_log_bak) log");
		if (channel != null && channel.intValue() != 0) {
			sb.append(",user_reg_log reg");
		}
		sb.append(" WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		if (channel != null && channel.intValue() != 0) {
			sb.append(" AND log.user_id=reg.user_id");
			sb.append(" and reg.channel='");
			sb.append(channel.toString());
			sb.append("'");
		}
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sb.toString());
		int activeUserAmount = 0;
		if (list != null && list.size() > 0) {
			activeUserAmount = ((BigInteger) list.get(0)).intValue();
		}
		return activeUserAmount;
	}

	/**
	 * 算某个时间段内的活跃玩家总数(月活跃)
	 * 
	 * @param dates
	 * @return
	 */
	public Map<String, Integer> findMonthActiveUserAmount(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT reg.channel,(case when reg.SMALL_CHANNEL='null' then 0 else SMALL_CHANNEL end) AS SMALL_CHANNEL1,COUNT(DISTINCT(log.USER_ID)) FROM");
		sb.append(" (select user_id,login_time from user_login_log union all select user_id,login_time from user_login_log_bak) log");
		sb.append(",user_reg_log reg");
		sb.append(" WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" AND log.user_id=reg.user_id");
		sb.append(" group by reg.channel,SMALL_CHANNEL1");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sb.toString());
		Map<String, Integer> map = new HashMap<String, Integer>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String smallChannel = arr[1].toString();
				
				map.put(arr[0].toString()+" "+smallChannel, Integer.valueOf(arr[2].toString()));
			}
		}
		return map;
	}

	/**
	 * 按是否分组渠道来查询玩家某段时间内的活跃数量、登陆总次数
	 * 
	 * @param dates
	 * @return
	 */
	public List<Object> findActiveUserAmount(String[] dates, boolean isPartner) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT IFNULL(COUNT(DISTINCT(log.USER_ID)),0),IFNULL(COUNT(log.ID),0)");
		if (isPartner) {
			sb.append(",reg.CHANNEL");
		}
		sb.append(" FROM user_login_log log");
		if (isPartner) {
			sb.append(",user_reg_log reg");
		}
		sb.append(" WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		if (isPartner) {
			sb.append(" AND log.user_id=reg.user_id group by reg.CHANNEL");
		}
		userLoginLogDao.closeSession(DBSource.LOG);
		return userLoginLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 分组渠道来查询玩家某段时间内的活跃数量、登陆总次数
	 * 
	 * @param dates
	 * @return
	 */
	public Map<String, Integer> findActiveUserAmountByChannel(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT IFNULL(COUNT(DISTINCT(log.USER_ID)),0), reg.CHANNEL");
		sb.append(" FROM user_login_log log,user_reg_log reg");
		sb.append(" WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" AND log.user_id=reg.user_id group by reg.CHANNEL");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sb.toString());
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
	 * 查询玩家某段时间内的活跃数量、登陆总次数(根据idfa)
	 * 
	 * @param dates
	 * @return
	 */
	public List<Object> findActiveUserAmountByIdfa(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT IFNULL(COUNT(DISTINCT(reg.reg_idfa)),0),IFNULL(COUNT(log.ID),0)");
		sb.append(",reg.CHANNEL");
		sb.append(" FROM user_login_log log");
		sb.append(",user_reg_log reg");
		sb.append(" WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" AND log.user_id=reg.user_id");
		userLoginLogDao.closeSession(DBSource.LOG);
		return userLoginLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 查询玩家某段时间内的活跃数量、登陆总次数(根据ip)
	 * 
	 * @param dates
	 * @return
	 */
	public List<Object> findActiveUserAmountByIp(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT IFNULL(COUNT(DISTINCT(reg.reg_ip)),0),IFNULL(COUNT(log.ID),0)");
		sb.append(",reg.CHANNEL");
		sb.append(" FROM user_login_log log");
		sb.append(",user_reg_log reg");
		sb.append(" WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" AND log.user_id=reg.user_id");
		userLoginLogDao.closeSession(DBSource.LOG);
		return userLoginLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 当天按渠道分组的一个日活跃情况
	 * 
	 * @param dates
	 * @return
	 */
	public Map<String, Integer> findActiveUserAmount(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT reg.CHANNEL,(case when reg.SMALL_CHANNEL='null' then 0 else reg.SMALL_CHANNEL end) AS SMALL_CHANNEL1,IFNULL(COUNT(DISTINCT(log.USER_ID)),0)");
		sb.append(" FROM user_login_log log,user_reg_log reg");
		sb.append(" WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" AND log.user_id=reg.user_id group by reg.CHANNEL , SMALL_CHANNEL1");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sb.toString());
		Map<String, Integer> map = new HashMap<String, Integer>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String smallChannel = arr[1].toString();
				
				map.put(arr[0].toString()+" "+smallChannel, Integer.valueOf(arr[2].toString()));
			}
		}
		return map;
	}

	/**
	 * 查询某个渠道内的日活跃数,根据时间分组
	 * 
	 * @param dates
	 * @param channel
	 */
	public IPage<Object> findDayActive(String[] dates, Integer channel,
			int pageSize, int pageIndex) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DATE_FORMAT(log.LOGIN_TIME,'%Y-%m-%d') as loginDay,IFNULL(COUNT(DISTINCT(log.USER_ID)),0)");
		sb.append(" FROM (select USER_ID,LOGIN_TIME from user_login_log union all select USER_ID,LOGIN_TIME from user_login_log_bak) log");
		if (channel != null && channel.intValue() != 0) {
			sb.append(",user_reg_log reg");
		}
		sb.append(" WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		if (channel != null && channel.intValue() != 0) {
			sb.append(" AND log.user_id=reg.user_id");
			sb.append(" and reg.channel='");
			sb.append(channel.toString());
			sb.append("'");
		}
		sb.append(" group by DATE_FORMAT(log.LOGIN_TIME,'%Y-%m-%d')");
		userLoginLogDao.closeSession(DBSource.LOG);
		return userLoginLogDao.findSQL_Page_Have_Group(sb.toString(),
				new ArrayList<Object>(), pageSize, pageIndex);
	}

	/**
	 * 查询某个渠道内的日活跃数,根据时间分组
	 * 
	 * @param dates
	 * @param channel
	 */
	public List<Object> findDayActive(String[] dates, Integer channel) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DATE_FORMAT(log.LOGIN_TIME,'%Y-%m-%d') as loginDay,IFNULL(COUNT(DISTINCT(log.USER_ID)),0)");
		sb.append(" FROM (select USER_ID,LOGIN_TIME from user_login_log union all select USER_ID,LOGIN_TIME from user_login_log_bak) log");
		if (channel != null && channel.intValue() != 0) {
			sb.append(",user_reg_log reg");
		}
		sb.append(" WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		if (channel != null && channel.intValue() != 0) {
			sb.append(" AND log.user_id=reg.user_id");
			sb.append(" and reg.channel='");
			sb.append(channel.toString());
			sb.append("'");
		}
		sb.append(" group by DATE_FORMAT(log.LOGIN_TIME,'%Y-%m-%d')");
		userLoginLogDao.closeSession(DBSource.LOG);
		return userLoginLogDao.findSQL_(sb.toString());
	}

	/**
	 * 查询某个时间点之前的日活跃数,根据时间、渠道分组
	 * 
	 * @param dates
	 * @param channel
	 */
	public List<Object> findDayActive(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DATE_FORMAT(log.LOGIN_TIME,'%Y-%m-%d'),reg.channel,IFNULL(COUNT(DISTINCT(log.USER_ID)),0)");
		sb.append(" FROM (select USER_ID,LOGIN_TIME from user_login_log union all select USER_ID,LOGIN_TIME from user_login_log_bak) log");
		sb.append(",user_reg_log reg");
		sb.append(" WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" AND log.user_id=reg.user_id");
		sb.append(" group by DATE_FORMAT(log.LOGIN_TIME,'%Y-%m-%d'),reg.channel");
		userLoginLogDao.closeSession(DBSource.LOG);
		return userLoginLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 查询某个时间点内活跃用户
	 * @param dates
	 * @return
	 */
	public List<Object> findDayActiveUserId(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("select user_id from (select distinct(user_id), login_time from user_login_log union all select distinct(user_id), login_time from user_login_log_bak) log");
		sb.append(" where log.login_time between '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		userLoginLogDao.closeSession(DBSource.LOG);
		return userLoginLogDao.findSQL_(sb.toString());
	}

	/**
	 * 查询某个渠道内的月活跃数,根据月分组
	 * 
	 * @param dates
	 * @param channel
	 */
	public Map<String, Integer> findMonthActive(String[] dates, Integer channel) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DATE_FORMAT(log.LOGIN_TIME,'%Y-%m') as loginDay,IFNULL(COUNT(DISTINCT(log.USER_ID)),0)");
		sb.append(" FROM (select USER_ID,LOGIN_TIME from user_login_log union all select USER_ID,LOGIN_TIME from user_login_log_bak) log");
		if (channel != null && channel.intValue() != 0) {
			sb.append(",user_reg_log reg");
		}
		sb.append(" WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		if (channel != null && channel.intValue() != 0) {
			sb.append(" AND log.user_id=reg.user_id");
			sb.append(" and reg.channel='");
			sb.append(channel.toString());
			sb.append("'");
		}
		sb.append(" group by DATE_FORMAT(log.LOGIN_TIME,'%Y-%m')");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sb.toString());
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
	 * 给定日期内总登入次数、登入玩家数、登入IP数
	 * 
	 * @param dates
	 * @return
	 */
	public UserEverydayLoginStats findCounts(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(1),COUNT(DISTINCT(USER_ID)),COUNT(DISTINCT(IP)) FROM (select * from user_login_log union all select * from user_login_log_bak) log WHERE log.LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");

		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sb.toString());
		UserEverydayLoginStats userEverydayLoginStats = new UserEverydayLoginStats();
		if (list != null && list.size() > 0) {
			userEverydayLoginStats.setTotalTimes(Integer
					.parseInt(((Object[]) list.get(0))[0].toString()));
			userEverydayLoginStats.setUserNumber(Integer
					.parseInt(((Object[]) list.get(0))[1].toString()));
			userEverydayLoginStats.setIpNumber(Integer
					.parseInt(((Object[]) list.get(0))[2].toString()));
		}
		return userEverydayLoginStats;
	}

	/**
	 * 玩家id和最后一次登录时间的map
	 * 
	 * @param userIds
	 * @return
	 * @throws ParseException
	 */
	public Map<Long, Date> findUserIdAndLastLoginTimeMapByUserIds(String userIds)
			throws ParseException {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT USER_ID,MAX(LOGIN_TIME) FROM (select * from user_login_log union all select * from user_login_log_bak) log WHERE log.USER_ID IN (")
				.append(userIds).append(") GROUP BY log.USER_ID");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sql.toString());
		Map<Long, Date> map = new HashMap<Long, Date>();

		if (list != null && list.size() > 0) {
			Long userId;
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for (int i = 0; i < list.size(); i++) {
				userId = Long.valueOf(((Object[]) list.get(i))[0].toString());
				date = sdf.parse(((Object[]) list.get(i))[1].toString());
				map.put(userId, date);
			}
		}

		return map;
	}

	public List<Object> findUserIdAndLevelIntervalIndexAndActiveDaysList() {
		StringBuffer sql = new StringBuffer();
		// -- (`LEVEL`-1) DIV 5
		// 等级区间索引：从0到19总共20，20是由100除5得来的；0表示1-5级，1表示6-10,以此类推
		// -- COUNT(DISTINCT(DATE(LOGIN_TIME)))
		// 玩家到达某一等级区间的最大值的总活跃天数，其中date函数取不包含时分秒的日期，DISTINCT(DATE(LOGIN_TIME)取不同的登录日期
		// SELECT a.USER_ID,b.levelIntervalIndex,
		// COUNT(DISTINCT(DATE(LOGIN_TIME))) FROM user_login_log a, (
		// SELECT (`LEVEL`-1) DIV 5 AS levelIntervalIndex, USER_ID, MAX(`LEVEL`)
		// AS levelInterval FROM user_login_log GROUP BY (`LEVEL`-1) DIV
		// 5,USER_ID
		// ) b
		// WHERE a.USER_ID = b.USER_ID AND a.`LEVEL` <= b.levelInterval
		// GROUP BY a.USER_ID,b.levelInterval;
		sql.append("SELECT a.USER_ID,b.levelIntervalIndex, COUNT(DISTINCT(DATE(LOGIN_TIME))) FROM (select * from user_login_log union all select * from user_login_log_bak) a, (");
		sql.append("	SELECT (`LEVEL`-1) DIV 5 AS levelIntervalIndex, USER_ID, MAX(`LEVEL`) AS levelInterval FROM (select * from user_login_log union all select * from user_login_log_bak) logb GROUP BY (`LEVEL`-1) DIV 5,USER_ID");
		sql.append(") b ");
		sql.append("WHERE a.USER_ID = b.USER_ID AND a.`LEVEL` <= b.levelInterval ");
		sql.append("GROUP BY a.USER_ID,b.levelInterval ");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sql.toString());
		return list;
	}

	public void findIPStatsInfo() throws ParseException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT IP, COUNT(DISTINCT(USER_ID)) AS userNum, DATE_FORMAT(MIN(LOGIN_TIME),'%Y-%m-%d') AS allIPUserFirstLoginTime, DATE_FORMAT(MAX(LOGIN_TIME),'%Y-%m-%d') AS allIPUserLastLoginTime  FROM (select * from user_login_log union all select * from user_login_log_bak) log GROUP BY IP ORDER BY userNum DESC,allIPUserFirstLoginTime DESC LIMIT 100");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sql.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<UserIpStats> userIPStatsList = new ArrayList<UserIpStats>();
		if (list != null && list.size() > 0) {
			UserIpStats userIpStats;
			for (int i = 0; i < list.size(); i++) {
				userIpStats = new UserIpStats();
				userIpStats.setSysNum(CustomerContextHolder.getSystemNum());
				userIpStats.setIp(((Object[]) list.get(i))[0].toString());
				userIpStats
						.setRoleNum(Integer.valueOf(((Object[]) list.get(i))[1]
								.toString()));
				userIpStats.setFirstDate(sdf.parse(((Object[]) list.get(i))[2]
						.toString()));
				userIpStats.setLastDate(sdf.parse(((Object[]) list.get(i))[3]
						.toString()));
				userIPStatsList.add(userIpStats);
			}
		}
		// UserIPStatsService statsService =
		// ServiceCacheFactory.getServiceCache().getService(UserIPStatsService.class);
		// statsService.saveUserIPStatsList(userIPStatsList);
	}

	/**
	 * 给定日期内所有玩家的登录次数
	 * 
	 * @param dates
	 * @return
	 */
	public Map<String, Integer> findUserInSomeTimeStatsMap(String[] dates) {
		Map<String, Integer> statsMap = new LinkedHashMap<String, Integer>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(ID) FROM (select * from user_login_log union all select * from user_login_log_bak) log WHERE LOGIN_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("' GROUP BY USER_ID order by COUNT(ID)");

		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sb.toString());
		if (list != null && list.size() > 0) {
			int[] loginTimesArr = UserInSomeTimeLoginStatsList.LOGIN_TIMES_ARRAY;
			int loginTimes;
			String key = "";
			// 所有登录次数区间人数的设为0
			for (int k = 0; k < loginTimesArr.length - 1; k++) {
				key = "[" + loginTimesArr[k] + "," + loginTimesArr[k + 1] + ")";
				statsMap.put(key, 0);
			}
			key = "[" + loginTimesArr[loginTimesArr.length - 1] + ",MAX)";
			if (statsMap.get(key) == null) {
				statsMap.put(key, 0);
			}
			for (int i = 0; i < list.size(); i++) {
				loginTimes = Integer.parseInt(list.get(i).toString());
				// 因为已经按次数排序，所以先看登录次数是在哪个区间内
				for (int j = 0; j < loginTimesArr.length - 1; j++) {
					if (loginTimes >= loginTimesArr[j]
							&& loginTimes < loginTimesArr[j + 1]) {
						key = "[" + loginTimesArr[j] + ","
								+ loginTimesArr[j + 1] + ")";
						break;
					}
				}
				// 如果登录次数大于最大区间 说明在最后的那个区间内
				if (loginTimes >= loginTimesArr[loginTimesArr.length - 1]) {
					key = "[" + loginTimesArr[loginTimesArr.length - 1]
							+ ",MAX)";
				}

				// 写到统计Map中去
				statsMap.put(key, statsMap.get(key) + 1);
			}

		}
		return statsMap;
	}

	/**
	 * 查找在线玩家的IP信息
	 * 
	 * @param onlineUserIds
	 * @return
	 */
	// public Map<String, String> findOnlineUserIPInfo(String onlineUserIds){
	// Map<String, String> ipAndUserIdsMap = new HashMap<String, String>();
	// StringBuffer sql = new StringBuffer();
	// // SELECT c.IP,GROUP_CONCAT(c.USER_ID) FROM (
	// // SELECT a.IP, a.USER_ID FROM user_login_log a,(
	// // SELECT USER_ID,MAX(ID) LAST_ID FROM user_login_log WHERE USER_ID IN
	// (804875420030296,804875420031159,804875420028013,804875420030495,869670789558103)
	// GROUP BY USER_ID
	// // ) b
	// // WHERE a.USER_ID = b.USER_ID AND a.ID = b.LAST_ID
	// // ) c GROUP BY c.IP
	// //各IP的玩家id集合
	// sql.append("SELECT c.IP,GROUP_CONCAT(c.USER_ID) FROM (");
	// //在线玩家的id和ip
	// sql.append("	SELECT a.IP, a.USER_ID FROM user_login_log a,(");
	// //在线玩家的最后一条登录记录
	// sql.append(" 		SELECT USER_ID,MAX(ID) LAST_ID FROM user_login_log WHERE USER_ID IN (").append(onlineUserIds).append(") GROUP BY USER_ID");
	// sql.append("	) b");
	// sql.append("	WHERE a.USER_ID = b.USER_ID AND a.ID = b.LAST_ID");
	// sql.append(") c GROUP BY c.IP");
	// userLoginLogDao.closeSession(DBSource.LOG);
	// List<Object> list = userLoginLogDao.findSQL_(sql.toString());
	// if (list != null && list.size() > 0) {
	// String ip;
	// String userIds;
	// for (int i = 0; i < list.size(); i++) {
	// ip = ((Object[]) list.get(i))[0].toString();
	// userIds = ((Object[]) list.get(i))[1].toString();
	// ipAndUserIdsMap.put(ip, userIds);
	// }
	// }
	// return ipAndUserIdsMap;
	// }

	/**
	 * 留存信息
	 */
	public int findUserRemainInfo(String[] loginDateStrArr,
			String[] regDateStrArr) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT(login.USER_ID)) FROM (select USER_ID,LOGIN_TIME from user_login_log union all select USER_ID,LOGIN_TIME from user_login_log_bak) login, `user` u ");
		sql.append("WHERE login.USER_ID = u.USER_ID AND login.LOGIN_TIME BETWEEN '");
		sql.append(loginDateStrArr[0]);
		sql.append("' AND '");
		sql.append(loginDateStrArr[1]);
		sql.append("'");
		sql.append(" AND u.REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			return ((BigInteger) list.get(0)).intValue();
		}
		return 0;
	}
	
	/**
	 * 留存信息(根据渠道)
	 */
	public int findUserRemainInfoByChannel(String[] loginDateStrArr,
			String[] regDateStrArr, String channel) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT bb.uu_id) from (select aa.uid_id, url.user_id as uu_id from (SELECT DISTINCT(login.USER_ID) uid_id FROM (select USER_ID, LOGIN_TIME from user_login_log union all select USER_ID, LOGIN_TIME from user_login_log_bak) login, `user` u ");
		sql.append("WHERE login.USER_ID = u.USER_ID AND login.LOGIN_TIME BETWEEN '");
		sql.append(loginDateStrArr[0]);
		sql.append("' AND '");
		sql.append(loginDateStrArr[1]);
		sql.append("'");
		sql.append(" AND u.REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		sql.append(")aa left join user_reg_log url on aa.uid_id=url.USER_ID");
		sql.append(" AND url.channel = '");
		sql.append(channel);
		sql.append("'");
		sql.append(")bb");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			if (list != null && list.size() > 0) {
				return ((BigInteger) list.get(0)).intValue();
			}
		}
		return 0;
	}

	/**
	 * 查询渠道分组的时间段内的玩家登陆数、登陆ip数
	 * 
	 * @param dates
	 * @return
	 */
	public Map<String, Object[]> findLoginInfoByChannel(String[] dates) {
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		StringBuffer sql = new StringBuffer();
		sql.append("select reg.channel,ifnull(count(distinct(log.user_id)),0),ifnull(count(distinct(log.ip)),0) from user_login_log log,user_reg_log reg where log.login_time between '");
		sql.append(dates[0]);
		sql.append("' and '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and log.user_id=reg.user_id group by reg.channel");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				map.put(arr[0].toString(), arr);
			}
		}
		return map;
	}

	/**
	 * 查询渠道分组的当天内的老玩家的登陆数
	 * 
	 * @param dates
	 * @return
	 */
	public Map<String, Integer> findOldLoginNumByChannel(String[] dates) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		StringBuffer sql = new StringBuffer();
		sql.append("select reg.channel,ifnull(count(distinct(log.user_id)),0) from user_login_log log,user_reg_log reg where log.login_time between '");
		sql.append(dates[0]);
		sql.append("' and '");
		sql.append(dates[1]);
		sql.append("'");
		sql.append(" and log.user_id=reg.user_id and reg.reg_time<'");
		sql.append(dates[0]);
		sql.append("'");
		sql.append(" group by reg.channel");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				map.put(arr[0].toString(), Integer.valueOf(arr[1].toString()));
			}
		}
		return map;
	}

	/**
	 * 备份日志
	 */
	public void backup(String date) {
		String backup = "insert into user_login_log_bak select * from user_login_log where LOGIN_TIME<='"
				+ date + "'";
		String delete = "delete from user_login_log where LOGIN_TIME<='" + date
				+ "'";
		userLoginLogDao.closeSession(DBSource.LOG);
		userLoginLogDao.executeSQL_(backup);
		userLoginLogDao.executeSQL_(delete);
	}

	public void setUserLoginLogDao(UserLoginLogDao userLoginLogDao) {
		this.userLoginLogDao = userLoginLogDao;
	}

	public UserLoginLogDao getUserLoginLogDao() {
		return userLoginLogDao;
	}
	
	/*** 新添加根据idfa, ip留存统计 **/
	
	/**
	 * 留存信息(根据idfa)
	 */
	public int findUserRemainInfoByIdfa(String[] loginDateStrArr,
			String[] regDateStrArr) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT bb.bb_idfa) from (select aa.uid_id, url.reg_idfa as bb_idfa from (SELECT DISTINCT(login.USER_ID) uid_id FROM (select USER_ID, LOGIN_TIME from user_login_log union all select USER_ID, LOGIN_TIME from user_login_log_bak) login, `user` u ");
		sql.append("WHERE login.USER_ID = u.USER_ID AND login.LOGIN_TIME BETWEEN '");
		sql.append(loginDateStrArr[0]);
		sql.append("' AND '");
		sql.append(loginDateStrArr[1]);
		sql.append("'");
		sql.append(" AND u.REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		sql.append(")aa left join user_reg_log url on aa.uid_id=url.USER_ID)bb");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			if (list != null && list.size() > 0) {
				return ((BigInteger) list.get(0)).intValue();
			}
		}
		return 0;
	}
	
	/**
	 * 留存信息(根据ip)
	 */
	public int findUserRemainInfoByIp(String[] loginDateStrArr,
			String[] regDateStrArr) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT bb.reg_ip) from (select aa.uid_id, url.reg_ip as reg_ip from (SELECT DISTINCT(login.USER_ID) uid_id FROM (select USER_ID, LOGIN_TIME from user_login_log union all select USER_ID, LOGIN_TIME from user_login_log_bak) login, `user` u ");
		sql.append("WHERE login.USER_ID = u.USER_ID AND login.LOGIN_TIME BETWEEN '");
		sql.append(loginDateStrArr[0]);
		sql.append("' AND '");
		sql.append(loginDateStrArr[1]);
		sql.append("'");
		sql.append(" AND u.REG_TIME BETWEEN '");
		sql.append(regDateStrArr[0]);
		sql.append("' AND '");
		sql.append(regDateStrArr[1]);
		sql.append("'");
		sql.append(")aa left join user_reg_log url on aa.uid_id=url.USER_ID)bb");
		userLoginLogDao.closeSession(DBSource.LOG);
		List<Object> list = userLoginLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			if (list != null && list.size() > 0) {
				return ((BigInteger) list.get(0)).intValue();
			}
		}
		return 0;
	}
	/*** 结束添加根据idfa, ip留存统计 **/
}
