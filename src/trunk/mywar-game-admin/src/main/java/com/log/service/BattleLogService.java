package com.log.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.admin.util.Tools;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;
import com.framework.util.DateUtil;
import com.log.dao.BattleLogDao;
import com.log.dao.UserBattleReportDao;
import com.system.manager.DataSourceManager;

public class BattleLogService {

	private BattleLogDao battleLogDao;
	private UserBattleReportDao userBattleReportDao;
	
	/**
	 * 创建备份日志表 
	 * @param timeStamp
	 */
	public void createTable(String timeStamp){
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE IF NOT EXISTS `user_battle_report_");
		sql.append(timeStamp);
		sql.append("` (`USER_BATTLE_REPORT_ID`  int(10) NOT NULL AUTO_INCREMENT ,`USER_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,`USER_LEVEL`  int(10) NOT NULL ,`TARGET_ID`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,`TYPE`  tinyint(4) NULL DEFAULT NULL COMMENT '1打关卡 2PK争霸赛 3塔赛' ,`FLAG`  int(50) NULL DEFAULT NULL COMMENT '1是赢了2是平局3失败' ,`CREAT_TIME`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,PRIMARY KEY (`USER_BATTLE_REPORT_ID`),INDEX `creat_time` (`CREAT_TIME`),INDEX `user_id` (`USER_ID`))ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=27688");
		userBattleReportDao.closeSession(DBSource.LOG);
		userBattleReportDao.executeSQL_(sql.toString());
	}

	public void deal(){
		DataSourceManager manager = DataSourceManager.getInstatnce();
		userBattleReportDao.closeSession(DBSource.LOG);
		List<Object> list1 = userBattleReportDao.findSQL_("select max(creat_time),min(creat_time) from user_battle_report");
		if(list1!=null && list1.size()>0){
			Object[] arr = (Object[])list1.get(0);
			if(arr[0]!=null && arr[1]!=null){
				Date maxDate = DateUtil.getZeroTime(new Timestamp(DateUtil.stringtoDate(arr[0].toString(), DateUtil.FORMAT_ONE).getTime()));
				Date minDate = DateUtil.getZeroTime(new Timestamp(DateUtil.stringtoDate(arr[1].toString(), DateUtil.FORMAT_ONE).getTime()));
				Date day27 = DateUtil.stringtoDate("2013-11-27 00:00:00", DateUtil.FORMAT_ONE);//27号凌晨
				while(minDate.getTime()<=maxDate.getTime() && minDate.getTime()<day27.getTime()){
					String dateStr1 = DateUtil.dateToString(minDate, DateUtil.LONG_DATE_FORMAT);
					String dateStr2 = DateUtil.dateToString(minDate, DateUtil.FORMAT_FOUR);
					if(!manager.containsTable(minDate)){
						createTable(dateStr2);//创建表结构
					}
					StringBuffer sb = new StringBuffer();
					sb.append("insert into user_battle_report_");
					sb.append(dateStr2);
					sb.append(" select * from user_battle_report where creat_time between '");
					sb.append(dateStr1).append(" 00:00:00");
					sb.append("' AND '");
					sb.append(dateStr1).append(" 23:59:59");
					sb.append("'");
					userBattleReportDao.executeSQL_(sb.toString());//插入数据
					minDate = DateUtil.getDiffDate(minDate, 1);
					LogSystem.info("user_battle_report_"+dateStr2+"表数据分离完成");
				}
			}
		}
		List<Object> list2 = userBattleReportDao.findSQL_("select max(creat_time),min(creat_time) from user_battle_report_bak");
		if(list2!=null && list2.size()>0){
			Object[] arr = (Object[])list2.get(0);
			if(arr[0]!=null && arr[1]!=null){
				Date maxDate = DateUtil.getZeroTime(new Timestamp(DateUtil.stringtoDate(arr[0].toString(), DateUtil.FORMAT_ONE).getTime()));
				Date minDate = DateUtil.getZeroTime(new Timestamp(DateUtil.stringtoDate(arr[1].toString(), DateUtil.FORMAT_ONE).getTime()));
				while(minDate.getTime()<=maxDate.getTime()){//最后一天2013-11-27号的数据分表里面有,先不生成
					String dateStr1 = DateUtil.dateToString(minDate, DateUtil.LONG_DATE_FORMAT);
					String dateStr2 = DateUtil.dateToString(minDate, DateUtil.FORMAT_FOUR);
					if(!manager.containsTable(minDate)){
						createTable(dateStr2);//创建表结构
					}
					StringBuffer sb = new StringBuffer();
					sb.append("insert into user_battle_report_");
					sb.append(dateStr2);
					sb.append(" select * from user_battle_report_bak where creat_time between '");
					sb.append(dateStr1).append(" 00:00:00");
					sb.append("' AND '");
					sb.append(dateStr1).append(" 23:59:59");
					sb.append("'");
					userBattleReportDao.executeSQL_(sb.toString());//插入数据
					minDate = DateUtil.getDiffDate(minDate, 1);
					LogSystem.info("user_battle_report_"+dateStr2+"表数据分离完成");
				}
			}
		}
	}
	
	
	/**
	 * 按照条件查询
	 * @param currentPage
	 * @param pageSize
	 * @param querySql
	 * @return
	 */
	public IPage<Object> findBattleLogByCondition(String userId, Integer battleType,Date startDate, Date endDate, Integer currentPage, Integer pageSize) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		Date[] arr = DateUtil.dayStrDiff(startDate, endDate);
		StringBuffer sql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();
		if(userId != null && !userId.equals("")){
			queryStr.append(" and user_id = '").append(userId).append("'");
		}
		if(battleType != null && battleType > 0){
			queryStr.append(" and type = ").append(battleType);
		}
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and creat_time BETWEEN '").append(sdf.format(startDate)).append("' AND '").append(sdf.format(endDate)).append("'");
		}
		queryStr.append(" order by creat_time desc");
		String items = "user_battle_report_id,user_id,user_level,target_id,type,flag,creat_time";
		sql.append(manager.getUnionSql("user_battle_report", arr,queryStr,items));
		battleLogDao.closeSession(DBSource.LOG);
		IPage<Object> battleLogList = userBattleReportDao.findSQL_PageUnion(sql.toString(), new ArrayList<Object>(), pageSize, currentPage);
		return battleLogList;
	}
	
	/**
	 * 天梯排位赛按战斗次数区间统计各区间的人数，区间总消费钻石数，区间总获得钻石数
	 * @return 
	 */
	public Map<Integer, String> findAttackNumIntervalInfoInLadderRankBattle(Date startDate, Date endDate) {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ((a.attackNum-1) DIV 5) AS attackNumInterval, COUNT(a.ATTACK_USER_ID) AS userNum, IFNULL(SUM(b.userConsumeAmount),0), IFNULL(SUM(c.userReceiveAmount),0) FROM (");
		sql.append("	SELECT ATTACK_USER_ID,COUNT(1) attackNum FROM battle_log WHERE TYPE = 11");
		if (startDate != null && endDate != null) {
			sql.append(" AND BATTLE_BEGIN_TIME BETWEEN '").append(sdf.format(startDate)).append("' AND '").append(sdf.format(endDate)).append("'");
		}
		sql.append(" GROUP BY ATTACK_USER_ID ORDER BY COUNT(1)");
		sql.append(") a LEFT JOIN (");
		sql.append("	SELECT USER_ID,SUM(CHANGE_NUM) AS userConsumeAmount FROM (select * from user_gold_log union all select * from user_gold_log_bak) loga WHERE loga.category = 2 and loga.TYPE = 17");
		if (startDate != null && endDate != null) {
			sql.append(" AND TIME BETWEEN '").append(sdf.format(startDate)).append("' AND '").append(sdf.format(endDate)).append("'");
		}
		sql.append(" GROUP BY USER_ID ORDER BY USER_ID");
		sql.append(") b ON a.ATTACK_USER_ID = b.USER_ID LEFT JOIN (");
		sql.append("	SELECT USER_ID,SUM(CHANGE_NUM) AS userReceiveAmount FROM (select * from user_gold_log union all select * from user_gold_log_bak) logb WHERE logb.category = 1 and logb.TYPE = 5 ");
		if (startDate != null && endDate != null) {
			sql.append(" AND TIME BETWEEN '").append(sdf.format(startDate)).append("' AND '").append(sdf.format(endDate)).append("'");
		}
		sql.append(" GROUP BY USER_ID");
		sql.append(") c ON a.ATTACK_USER_ID = c.USER_ID GROUP BY attackNumInterval ORDER BY attackNumInterval DESC");
		battleLogDao.closeSession(DBSource.LOG);
		List<Object> list = battleLogDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			Integer attackNumInterval;
			String userNum;
			String consumeAmount;
			String receiveAmount;
			int index;
			for (int i = 0; i < list.size(); i++) {
				index = 0;
				attackNumInterval = Integer.parseInt(((Object[]) list.get(i))[index].toString());
				userNum = ((Object[]) list.get(i))[++index].toString();
				consumeAmount = ((Object[]) list.get(i))[++index].toString();
				receiveAmount = ((Object[]) list.get(i))[++index].toString();
				map.put(attackNumInterval, userNum + "_" + consumeAmount + "_" + receiveAmount);
			}
		}
		return map;
	}
	
	/**
	 * 英雄和宠物出战次数
	 * @return
	 */
	public Map<Integer, Long> findHeroAndPetBattleTimes() {
		Map<Integer, Long> map = new HashMap<Integer, Long>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT IFNULL(RED_ROLE,''),IFNULL(BLUE_ROLE,'') FROM battle_log");
		battleLogDao.closeSession(DBSource.LOG);
		List<Object> list = battleLogDao.findSQL_(sql.toString());
		String redRole;
		String blueRole;
		String[] idArr;
		Integer id;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				redRole = ((Object[])list.get(i))[0].toString();
				blueRole = ((Object[])list.get(i))[1].toString();
				if (!Tools.isEmpty(redRole)) {
					idArr = redRole.split(",");
					for (int j = 0; j < idArr.length; j++) {
						id = Integer.valueOf(idArr[j]);
						//如果之前没有这个id
						if (map.get(id) == null) {
							map.put(id, 1L);
						} else {
							map.put(id, map.get(id)+1);
						}
					}
				}
				if (!Tools.isEmpty(blueRole)) {
					idArr = blueRole.split(",");
					for (int j = 0; j < idArr.length; j++) {
						id = Integer.valueOf(idArr[j]);
						//如果之前没有这个id
						if (map.get(id) == null) {
							map.put(id, 1L);
						} else {
							map.put(id, map.get(id)+1);
						}
					}
				}
			}
		}
//		System.out.println(map);
		return map;
//		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT CONCAT_WS(',',GROUP_CONCAT(RED_ROLE),GROUP_CONCAT(BLUE_ROLE)) FROM battle_log");
//		battleLogDao.closeSession(DBSource.LOG);
//		List<Object> list = battleLogDao.findSQL_(sql.toString());
//		String allIds = "";
//		if (list != null && list.size() > 0) {
//			allIds = list.get(0).toString();
//			if (allIds != null && !"".equals(allIds)) {
//				//System.out.println(allIds);
//				String [] allIdArr = allIds.split(",");
//				for (int i = 0; i < allIdArr.length; i++) {
//					System.out.println("a"+allIdArr[i]+"b");
//					int id = Integer.valueOf(allIdArr[i]);
//					map.put(id, map.get(id)==null?1:map.get(id)+1);
//				}
//			}
//		}
////		System.out.println(map);
//		return map;
	}
	
	/**
	 * 采集关卡数据
	 * @param dates
	 * @return
	 */
	public List<Object> collectUserBattleStats(String[] dates){
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_battle_report",dates);
		StringBuffer sb = new StringBuffer();
		sb.append("select a.target_id,a.atkUserNum,a.atkNum,IFNULL(b.winNum,0),IFNULL(c.drawNum,0) from (");
		sb.append("(select target_id,count(distinct(user_id)) as atkUserNum,count(user_id) as atkNum from ");
		sb.append(tableName);
		sb.append(" where type=1 and creat_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by target_id) a left join (select target_id,count(user_id) as winNum from ");
		sb.append(tableName);
		sb.append(" where type=1 and flag=1 and creat_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by target_id) b on b.target_id=a.target_id left join (select target_id,count(user_id) as drawNum from ");
		sb.append(tableName);
		sb.append(" where type=1 and flag=2 and creat_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by target_id) c on c.target_id=a.target_id)");
		userBattleReportDao.closeSession(DBSource.LOG);
		return userBattleReportDao.findSQL_(sb.toString());
	}
	
	
	/**
	 * 备份战斗日志
	 */
	public void backup(String date){
		String backup = "insert into user_battle_report_bak select * from user_battle_report where CREAT_TIME<='"+date+"'";
		String delete = "delete from user_battle_report where CREAT_TIME<='"+date+"'";
		userBattleReportDao.closeSession(DBSource.LOG);
		userBattleReportDao.executeSQL_(backup);
		userBattleReportDao.executeSQL_(delete);
	}
	
	/**
	 * 夺宝统计
	 */
	public List<Object> countPlunder(String[] dates) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_battle_report", dates);
		StringBuffer sb = new StringBuffer();
		sb.append("select count(distinct(USER_ID)), count(*) from ");
		sb.append(tableName);
		sb.append(" where type = 7");
		userBattleReportDao.closeSession(DBSource.LOG);
		return userBattleReportDao.findSQL_(sb.toString());
	}
	
	/**
	 * 争霸统计
	 */
	public List<Object> countZhengba(String[] dates) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_battle_report", dates);
		StringBuffer sb = new StringBuffer();
		sb.append("select count(distinct(USER_ID)), count(*) from ");
		sb.append(tableName);
		sb.append(" where type = 2");
		userBattleReportDao.closeSession(DBSource.LOG);
		return userBattleReportDao.findSQL_(sb.toString());
	}
	
	/**
	 * Boss战统计
	 * @return
	 */
	public List<Object> countBoss(String[] dates) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_battle_report", dates);
		StringBuffer sb = new StringBuffer();
		sb.append("select count(distinct(USER_ID)), count(*) from ");
		sb.append(tableName);
		sb.append(" where type = 8");
		userBattleReportDao.closeSession(DBSource.LOG);
		return userBattleReportDao.findSQL_(sb.toString());
	}

	public UserBattleReportDao getUserBattleReportDao() {
		return userBattleReportDao;
	}

	public void setUserBattleReportDao(UserBattleReportDao userBattleReportDao) {
		this.userBattleReportDao = userBattleReportDao;
	}

	/**
	 * @return the battleLogDao
	 */
	public BattleLogDao getBattleLogDao() {
		return battleLogDao;
	}
	

	/**
	 * @param battleLogDao the battleLogDao to set
	 */
	public void setBattleLogDao(BattleLogDao battleLogDao) {
		this.battleLogDao = battleLogDao;
	}
	
}
