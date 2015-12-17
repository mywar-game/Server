package com.log.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.User;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;
import com.log.dao.UserGoldLogDao;

public class UserGoldLogService {

	private UserGoldLogDao userGoldLogDao;

	/**
	 * action查分页日志列表
	 * 
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param searchCategory
	 * @param searchType
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<Object> findPageLogListByCondition(String userId,
			Date startDate, Date endDate, Integer searchCategory,
			Integer searchType, Integer currentPage, Integer pageSize) {
		System.out.println("searchType = " + searchType);
		StringBuffer hql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();
		if (userId != null) {
			queryStr.append(" and user_id = '").append(userId).append("'");
		}
		if (searchCategory != null) {
			queryStr.append(" and category = ").append(searchCategory);
		}
		if (searchType != null) {
			queryStr.append(" and type = ").append(searchType);
		}
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and time BETWEEN '")
					.append(sdf.format(startDate)).append("' AND '")
					.append(sdf.format(endDate)).append("'");
		}
		hql.append("select log.user_id, u.user_name, u.lodo_id, log.user_level, log.category, log.type, log.change_num, log.time from (select * from user_gold_log where 1=1");
		hql.append(queryStr.toString());
		hql.append(" union all select * from user_gold_log_bak where 1=1");
		hql.append(queryStr.toString());
		hql.append(") log, user u where log.user_id = u.user_id");
		hql.append(" order by time");
		userGoldLogDao.closeSession(DBSource.LOG);
		return userGoldLogDao.findSQL_Page(hql.toString(),
				new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 各个类型的次数、总数
	 * 
	 * @param user
	 *            某个玩家
	 * @param dates
	 *            时间范围
	 * @return map(类型, 总数_次数)
	 */
	public Map<Integer, String> findEveryTypeAmount(Integer category,
			User user, String[] dates) {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT TYPE, COUNT(*), SUM(CHANGE_NUM) FROM  (select * from user_gold_log union all select * from user_gold_log_bak) log where ");
		sql.append(" log.CATEGORY = ").append(category);
		if (user != null && user.getUserId() != null) {
			sql.append(" AND log.USER_ID = ").append(user.getUserId());
		}
		if (dates != null) {
			sql.append(" AND log.TIME BETWEEN '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		sql.append(" GROUP BY log.TYPE order by SUM(log.CHANGE_NUM) desc");
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Integer receiveType = Integer
						.valueOf(((Object[]) list.get(i))[0].toString());
				Integer times = Integer.valueOf(((Object[]) list.get(i))[1]
						.toString());
				String amount = ((Object[]) list.get(i))[2].toString();
				map.put(receiveType, amount + "_" + times);
			}
		}
		return map;
	}

	public Map<Integer, Integer> findEveryTypeAmountVIP(Integer category, String[] dates, List<String> userIds) {
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		StringBuffer sql = new StringBuffer();
		if (userIds != null && userIds.size() >= 1) {
			sql.append("SELECT TYPE, SUM(CHANGE_NUM) FROM  user_gold_log log where ");
			sql.append(" log.CATEGORY = ").append(category);
			if (dates != null) {
				sql.append(" AND log.TIME BETWEEN '");
				sql.append(dates[0]);
				sql.append("' AND '");
				sql.append(dates[1]);
				sql.append("'");
			}
			sql.append(" and log.user_id in (");
			for (int i = 0; i < userIds.size(); i++) {
				sql.append("'");
				sql.append(userIds.get(i));
				sql.append("'");
				if (i < userIds.size() - 1) {
					sql.append(",");
				}
			}
			sql.append(")");
			sql.append(" GROUP BY log.TYPE");
		} else {
			return new HashMap<Integer, Integer>();
		}
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Integer receiveType = Integer
						.valueOf(((Object[]) list.get(i))[0].toString());
				Integer amount = Integer.valueOf(((Object[]) list.get(i))[1]
						.toString());
				map.put(receiveType, amount);
			}
		}
		return map;
	}
	
	/**
	 * 各个类型的次数、总数
	 * 
	 * @param dates
	 *            时间范围(前一天)
	 * @return map(类型, 总数_次数)
	 */
	public Map<Integer, Integer> findEveryTypeAmount(Integer category,
			String[] dates) {
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT TYPE, SUM(CHANGE_NUM) FROM  user_gold_log log where ");
		sql.append(" log.CATEGORY = ").append(category);
		if (dates != null) {
			sql.append(" AND log.TIME BETWEEN '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		sql.append(" GROUP BY log.TYPE");
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Integer receiveType = Integer
						.valueOf(((Object[]) list.get(i))[0].toString());
				Integer amount = Integer.valueOf(((Object[]) list.get(i))[1]
						.toString());
				map.put(receiveType, amount);
			}
		}
		return map;
	}

	/**
	 * 各个类型的次数、总数
	 * 
	 * @param user
	 *            某个玩家
	 * @param dates
	 *            时间范围
	 * @return map(类型, 总数_次数)
	 */
	public Map<Integer, String> findEveryTypeAmountBeforeDate(Integer category,
			User user, String time) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT TYPE, COUNT(*), SUM(CHANGE_NUM) FROM  (select * from user_gold_log union all select * from user_gold_log_bak) log where ");
		sql.append(" log.CATEGORY = ").append(category);
		if (user != null && user.getUserId() != null) {
			sql.append(" AND log.USER_ID = ").append(user.getUserId());
		}
		if (time != null) {
			sql.append(" AND log.TIME <= '");
			sql.append(time);
			sql.append("'");
		}
		sql.append(" GROUP BY log.TYPE");
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Integer receiveType = Integer
						.valueOf(((Object[]) list.get(i))[0].toString());
				Integer times = Integer.valueOf(((Object[]) list.get(i))[1]
						.toString());
				Integer amount = Integer.valueOf(((Object[]) list.get(i))[2]
						.toString());
				map.put(receiveType, amount + "_" + times);
			}
		}
		return map;
	}
	
	/**
	 * 查询总消耗，产出
	 * @param category
	 * @param user
	 * @param time
	 * @return
	 */
	public Integer countAll(Integer category, String searchUserId, Date startDate, Date endDate) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(CHANGE_NUM),USER_ID FROM (select * from user_gold_log union all select * from user_gold_log_bak) log where ");
		sql.append(" log.CATEGORY = ").append(category);
		sql.append(" AND log.USER_ID = '");
		sql.append(searchUserId);
		sql.append("'");
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sql.append(" and time BETWEEN '")
			.append(sdf.format(startDate)).append("' AND '")
			.append(sdf.format(endDate)).append("'");
		}
		
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == null) {
					return 0;
				}
				Object[] objArr = (Object[]) list.get(i);
				if (objArr[0] != null) {
					Integer amount = Integer.valueOf(objArr[0].toString());
					return amount;
				}
			}
		}
		return 0;
	}

	/**
	 * 总变动的数量
	 * 
	 * @param category
	 *            类别
	 * @param user
	 *            某个玩家
	 * @param dates
	 *            时间范围
	 * @return
	 */
	public Integer findCategoryAmount(Integer category, User user,
			String[] dates) {
		Integer amount = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT IFNULL(SUM(CHANGE_NUM), 0) FROM  (select * from user_gold_log union all select * from user_gold_log_bak) log where ");
		sql.append(" log.CATEGORY = ").append(category);
		if (user != null && user.getUserId() != null) {
			sql.append(" AND log.USER_ID = ").append(user.getUserId());
		}
		if (dates != null) {
			sql.append(" AND log.TIME BETWEEN '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			amount = Integer.valueOf(list.get(0).toString());
		}
		return amount;
	}

	/**
	 * 剩余钻石总数
	 * 
	 * @return
	 */
	// public Integer findSurplusAmount(){
	// Integer amount = 0;
	// StringBuffer sql = new StringBuffer();
	// sql.append("SELECT IFNULL(SUM(GOLD_NUM), 0) FROM ( SELECT MAX(LOG_ID) AS maxId FROM user_gold_log GROUP BY USER_ID) a LEFT JOIN user_gold_log b ON a.maxId = b.LOG_ID");
	// userGoldLogDao.closeSession(DBSource.LOG);
	// List<Object> list = userGoldLogDao.findSQL_(sql.toString());
	// if (list != null && list.size() > 0) {
	// amount = Integer.valueOf(list.get(0).toString());
	// }
	// return amount;
	// }

	/**
	 * 指定时间前的剩余钻石总数
	 * 
	 * @return
	 */
	// public Integer findSurplusAmountBeforeTime(String time){
	// Integer amount = 0;
	// StringBuffer sql = new StringBuffer();
	// sql.append("SELECT IFNULL(SUM(GOLD_NUM), 0) FROM ( SELECT MAX(LOG_ID) AS maxId FROM user_gold_log where TIME <='").append(time).append("' GROUP BY USER_ID) a LEFT JOIN user_gold_log b ON a.maxId = b.LOG_ID");
	// userGoldLogDao.closeSession(DBSource.LOG);
	// List<Object> list = userGoldLogDao.findSQL_(sql.toString());
	// if (list != null && list.size() > 0) {
	// amount = Integer.valueOf(list.get(0).toString());
	// }
	// return amount;
	// }

	public Map<Long, Date> findUserIdAndLastConsumeTimeMap(String userIds) {
		Map<Long, Date> userIdAndLastConsumeTimeMap = new HashMap<Long, Date>();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT USER_ID, MAX(TIME) FROM (select * from user_gold_log union all select * from user_gold_log_bak) log WHERE log.USER_ID IN (")
				.append(userIds).append(") GROUP BY log.USER_ID");
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		Long userId;
		String timeStr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date consumeTime = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				userId = Long.valueOf(((Object[]) list.get(i))[0].toString());
				timeStr = ((Object[]) list.get(i))[1].toString();
				try {
					consumeTime = sdf.parse(timeStr.substring(0,
							timeStr.length() - 2));
				} catch (ParseException e) {
					LogSystem.error(e, "");
				}
				userIdAndLastConsumeTimeMap.put(userId, consumeTime);
			}
		}
		return userIdAndLastConsumeTimeMap;
	}

	/**
	 * 各玩家等级区间索引和消费数量的map
	 * 
	 * @return
	 */
	public Map<String, Map<Integer, Integer>> findUserIdAndLevelIntervalIndexAndConsumeNumMap() {
		Map<String, Map<Integer, Integer>> userIdAndLevelIntervalIndexAndConsumeNumMap = new LinkedHashMap<String, Map<Integer, Integer>>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT (USER_LEVEL-1) DIV 5, USER_ID, SUM(CHANGE_NUM) AS consumeNum FROM (select * from user_gold_log union all select * from user_gold_log_bak) log WHERE log.CATEGORY = 2 GROUP BY (log.USER_LEVEL-1) DIV 5,log.USER_ID");
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());

		Map<Integer, Integer> map;
		Integer levelIntervalIndex;
		String userId;
		Integer consumeNum;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = new LinkedHashMap<Integer, Integer>();
				levelIntervalIndex = Integer
						.valueOf(((Object[]) list.get(i))[0].toString());
				userId = ((Object[]) list.get(i))[1].toString();
				consumeNum = Integer.valueOf(((Object[]) list.get(i))[2]
						.toString());
				map.put(levelIntervalIndex, consumeNum);
				userIdAndLevelIntervalIndexAndConsumeNumMap.put(userId, map);
			}
		}
		return userIdAndLevelIntervalIndexAndConsumeNumMap;
	}

	/**
	 * 在指定日期之间查找每天的消费总额和测试号的消费总额
	 * 
	 * @param startDate
	 * @param endDate
	 * @return 如果指定日期为空，
	 */
	public Map<String, Integer> findUserIdAndCategoryAmountMap(
			Integer category, String[] dates) {
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_ID,SUM(CHANGE_NUM) FROM (select * from user_gold_log union all select * from user_gold_log_bak) log ");
		sql.append(" where log.category = ").append(category);
		if (dates != null) {
			sql.append(" AND log.TIME BETWEEN '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		sql.append(" GROUP BY log.USER_ID");
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String userId = ((Object[]) list.get(i))[0].toString();
				int amount = Integer.valueOf(((Object[]) list.get(i))[1]
						.toString());
				resultMap.put(userId, amount);
			}
		}
		return resultMap;
	}

	/**
	 * 昨天的各个等级区间，消费类型对应的消费金额
	 * 
	 * @return
	 */
	public String findUserLevelIntervalConsumeAmountInYesterday(boolean isTest,
			String[] dates) {
		StringBuffer result = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT CONCAT_WS('_',a.levelIntervalIndexAndType, SUM(a.CHANGE_NUM)) FROM ( ");
		sql.append(
				" SELECT CONCAT_WS('-',(FLOOR((USER_LEVEL-1) / 5)+1), TYPE) AS levelIntervalIndexAndType, CHANGE_NUM FROM (select * from user_gold_log union all select * from user_gold_log_bak) log WHERE log.CATEGORY = 2 AND log.TIME BETWEEN '")
				.append(dates[0]).append("' AND '").append(dates[1])
				.append("'");

		// if (isTest) {
		// UserTypeService userTypeService =
		// ServiceCacheFactory.getServiceCache().getService(UserTypeService.class);
		// String userIds =
		// userTypeService.findUserIdsBySysNum(CustomerContextHolder.getSystemNum());
		// if (userIds != null && !"".equals(userIds)) {
		// sql.append(" AND USER_ID IN (").append(userIds).append(")");
		// } else { //没有测试号，直接返回
		// return "";
		// }
		// }

		sql.append(") a GROUP BY a.levelIntervalIndexAndType");
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				result.append(new String((byte[]) list.get(i)));
				if (i < list.size() - 1) {
					result.append(",");
				}
			}
		}
		return result.toString();
	}

	/**
	 * 备份日志
	 */
	public void backup(String date) {
		String backup = "insert into user_gold_log_bak select * from user_gold_log where TIME<='"
				+ date + "'";
		String delete = "delete from user_gold_log where TIME<='" + date + "'";
		userGoldLogDao.closeSession(DBSource.LOG);
		userGoldLogDao.executeSQL_(backup);
		userGoldLogDao.executeSQL_(delete);
	}

	/**
	 * 钻石消耗
	 * 
	 * @param startDate
	 * @param endDate
	 * @return 如果指定日期为空，
	 */
	public Map<String, Double> findConsume(Integer category, String[] dates) {

		// BigDecimal amount = null;
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT reg.CHANNEL, (case when reg.SMALL_CHANNEL='null' then 0 else SMALL_CHANNEL end) AS SMALL_CHANNEL1, IFNULL(SUM(log.CHANGE_NUM),0) FROM (select * from user_gold_log union all select * from user_gold_log_bak) log, user_reg_log reg");
		sql.append(" where log.category = ").append(category);
		if (dates != null) {
			sql.append(" AND log.TIME BETWEEN '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
			sql.append("  and log.USER_ID = reg.USER_ID GROUP BY reg.CHANNEL , SMALL_CHANNEL1");
		}

		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		Map<String, Double> map = new HashMap<String, Double>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);
				String smallChannel = arr[1].toString();
				
				map.put(arr[0].toString()+" "+smallChannel, Double.valueOf(arr[2].toString()));
			}
		}

		return map;
	}
	
	/**
	 * 查询鼓舞钻石消耗
	 * @param category
	 * @param user
	 * @param time
	 * @return
	 */
	public List<Object> countGuwu(String[] dates) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(distinct(user_id)), count(user_id) FROM (select * from user_gold_log union all select * from user_gold_log_bak) log where ");
		sql.append(" log.type = ").append(20053);
		sql.append(" and log.TIME between '");
		sql.append(dates[0]);
		sql.append("'");
		sql.append(" and '");
		sql.append(dates[1]);
		sql.append("'");
		
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		return list;
	}
	
	/**
	 * 查询鼓舞重生消耗
	 * @param category
	 * @param user
	 * @param time
	 * @return
	 */
	public List<Object> countRelife(String[] dates) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(distinct(user_id)), count(user_id) FROM (select * from user_gold_log union all select * from user_gold_log_bak) log where ");
		sql.append(" log.type = ").append(20054);
		sql.append(" and log.TIME between '");
		sql.append(dates[0]);
		sql.append("'");
		sql.append(" and '");
		sql.append(dates[1]);
		sql.append("'");
		
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		return list;
	}
	
	/**
	 * 大富翁
	 * @param dates
	 * @return
	 */
	public List<Object> countLoginDraw(String[] dates) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(distinct(user_id)), count(user_id) FROM (select * from user_gold_log union all select * from user_gold_log_bak) log where ");
		sql.append(" log.type = ").append(20059);
		sql.append(" and log.TIME between '");
		sql.append(dates[0]);
		sql.append("'");
		sql.append(" and '");
		sql.append(dates[1]);
		sql.append("'");
		
		userGoldLogDao.closeSession(DBSource.LOG);
		List<Object> list = userGoldLogDao.findSQL_(sql.toString());
		return list;
	}
	
//	/**
//	 * 作弊记录
//	 * @param dates
//	 * @return
//	 */
//	public List<Object> computeCheatLog(String[] dates) {
//		DataSourceManager manager = DataSourceManager.getInstatnce();
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append("select user_id, type, time, (select count(*) from ");
//		sb.append(tableName);
//		sb.append(" where user_id = a.user_id and CREATE_TIME = a.create_time and OPERATION = a.operation and TREASURE_ID = a.TREASURE_ID and TREASURE_TYPE = a.TREASURE_TYPE) as count from ");
//		sb.append(tableName);
//		sb.append(" a group by create_time having count >= 30");
//		
//		userTreasureLogDao.closeSession(DBSource.LOG);
//		return userTreasureLogDao.findSQL_(sb.toString());
//	}

	/**
	 * 测试钻石数据是否正确
	 * 
	 * @param dates
	 * @throws Exception
	 */
	public void test(String[] dates) throws Exception {

	}

	public void setUserGoldLogDao(UserGoldLogDao userGoldLogDao) {
		this.userGoldLogDao = userGoldLogDao;
	}

	public UserGoldLogDao getUserGoldLogDao() {
		return userGoldLogDao;
	}

}
