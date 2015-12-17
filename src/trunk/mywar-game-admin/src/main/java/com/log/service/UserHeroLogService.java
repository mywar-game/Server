package com.log.service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;
import com.framework.util.DateUtil;
import com.log.bo.UserHeroLog;
import com.log.dao.UserHeroLogDao;
import com.system.manager.DataSourceManager;

public class UserHeroLogService {
	
	private UserHeroLogDao userHeroLogDao;

	public UserHeroLogDao getUserHeroLogDao() {
		return userHeroLogDao;
	}

	public void setUserHeroLogDao(UserHeroLogDao userHeroLogDao) {
		this.userHeroLogDao = userHeroLogDao;
	}
	
	/**
	 * 创建备份日志表 
	 * @param timeStamp
	 */
	public void createTable(String timeStamp){
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE IF NOT EXISTS `user_hero_log_");
		sql.append(timeStamp);
		sql.append("` (`USER_HERO_LOG_ID`  bigint(64) NOT NULL AUTO_INCREMENT COMMENT '英雄日志ID' ,`USER_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '玩家ID' ,`USER_HERO_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '玩家英雄ID' ,`HERO_ID`  int(11) NOT NULL COMMENT '英雄常量编号' ,`TYPE`  int(11) NOT NULL COMMENT '（1.获取 2.吞噬 3.被吞噬 4.进阶  5.出售）' ,`EXP`  bigint(20) NOT NULL DEFAULT 0 COMMENT '玩家目前经验值' ,`LEVEL`  int(11) NOT NULL COMMENT '等级' ,`POS`  tinyint(4) NOT NULL COMMENT '英雄布阵' ,`EXP_NUM`  int(11) NOT NULL DEFAULT 0 COMMENT '增加的经验值' ,`OPERATION_TIME`  datetime NOT NULL COMMENT '玩家操作时间' ,`HERO_NAME`  varchar(21) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '英雄姓名' ,PRIMARY KEY (`USER_HERO_LOG_ID`),INDEX `operation_time` (`OPERATION_TIME`),INDEX `user_id` (`USER_ID`))ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=1881235");
		userHeroLogDao.closeSession(DBSource.LOG);
		userHeroLogDao.executeSQL_(sql.toString());
	}
	
	
	public void deal(){
		DataSourceManager manager = DataSourceManager.getInstatnce();
		userHeroLogDao.closeSession(DBSource.LOG);
		List<Object> list1 = userHeroLogDao.findSQL_("select max(OPERATION_TIME),min(OPERATION_TIME) from user_hero_log");
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
					sb.append("insert into user_hero_log_");
					sb.append(dateStr2);
					sb.append(" select * from user_hero_log where OPERATION_TIME between '");
					sb.append(dateStr1).append(" 00:00:00");
					sb.append("' AND '");
					sb.append(dateStr1).append(" 23:59:59");
					sb.append("'");
					userHeroLogDao.executeSQL_(sb.toString());//插入数据
					minDate = DateUtil.getDiffDate(minDate, 1);
					LogSystem.info("user_hero_log_"+dateStr2+"表数据分离完成");
				}
			}
		}
		List<Object> list2 = userHeroLogDao.findSQL_("select max(OPERATION_TIME),min(OPERATION_TIME) from user_hero_log_bak");
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
					sb.append("insert into user_hero_log_");
					sb.append(dateStr2);
					sb.append(" select * from user_hero_log_bak where OPERATION_TIME between '");
					sb.append(dateStr1).append(" 00:00:00");
					sb.append("' AND '");
					sb.append(dateStr1).append(" 23:59:59");
					sb.append("'");
					userHeroLogDao.executeSQL_(sb.toString());//插入数据
					minDate = DateUtil.getDiffDate(minDate, 1);
					LogSystem.info("user_hero_log_"+dateStr2+"表数据分离完成");
				}
			}
		}
	}
	
	
	public UserHeroLog findOneUserHeroLog(Long userHeroLogId){
		return this.userHeroLogDao.loadBy("userHeroLogId", userHeroLogId, DBSource.LOG);
	}
	
	public void delOneUserHeroLog(Long userHeroLogId) {
		userHeroLogDao.remove(this.findOneUserHeroLog(userHeroLogId), DBSource.LOG);
	}
	
	/**
	 * 根据玩家ID查询英雄信息
	 * @param currentPage
	 * @param pageSize
	 * @param querySql
	 * @return
	 */
	public IPage<Object> findUserHeroLogByCondition(String userId,Integer logType,Date startDate,Date endDate, Integer currentPage, Integer pageSize) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		Date[] arr = DateUtil.dayStrDiff(startDate, endDate);
		StringBuffer sb = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();
		if (userId != null) {
			queryStr.append(" and user_id = '").append(userId).append("'");
		}
		if(logType!=null&&logType!=0){
			queryStr.append(" and type = ").append(logType).append("");
		}
		queryStr.append(" order by operation_time DESC");
		String items = "user_hero_log_id,user_id,user_hero_id,hero_id,type,exp,level,pos,exp_num,operation_time,hero_name";
		sb.append(manager.getUnionSql("user_hero_log", arr,queryStr,items));
		userHeroLogDao.closeSession(DBSource.LOG);
		IPage<Object> userHeroLogList = userHeroLogDao.findSQL_PageUnion(new String(sb), new ArrayList<Object>(), pageSize, currentPage);
		return userHeroLogList;
	}
	
	/**
	 * 备份日志
	 */
	public void backup(String date){
		String backup = "insert into user_hero_log_bak select * from user_hero_log where OPERATION_TIME<='"+date+"'";
		String delete = "delete from user_hero_log where OPERATION_TIME<='"+date+"'";
		userHeroLogDao.closeSession(DBSource.LOG);
		userHeroLogDao.executeSQL_(backup);
		userHeroLogDao.executeSQL_(delete);
	}
	
}
