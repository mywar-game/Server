package com.log.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.adminTool.bo.User;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.util.DateUtil;
import com.log.dao.UserResourceLogDao;
import com.system.manager.DataSourceManager;

/**
 * 玩家资源日志（现在只有金钱）
 * @author hzy
 * 2012-7-31
 */
public class UserResourceLogService {
	
	/** **/
	private UserResourceLogDao userResourceLogDao;

	
	/**
	 * 创建备份日志表 
	 * @param timeStamp
	 */
	public void createTable(String timeStamp){
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE IF NOT EXISTS `user_resource_log_");
		sql.append(timeStamp);
		sql.append("` (`USER_RESOURCE_LOG_ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '玩家资源日志' ,`USER_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '玩家编号' ,`OPERATION`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作类型（升级，加速）' ,`MONEY`  int(11) NOT NULL COMMENT '金钱' ,`CREATE_TIME`  datetime NOT NULL COMMENT '创建时间' ,PRIMARY KEY (`USER_RESOURCE_LOG_ID`),INDEX `create_time` (`CREATE_TIME`),INDEX `user_id` (`USER_ID`))ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=393224");
		userResourceLogDao.closeSession(DBSource.LOG);
		userResourceLogDao.executeSQL_(sql.toString());
	}
	
	public void deal(){
		DataSourceManager manager = DataSourceManager.getInstatnce();
		userResourceLogDao.closeSession(DBSource.LOG);
		List<Object> list1 = userResourceLogDao.findSQL_("select max(CREATE_TIME),min(CREATE_TIME) from user_resource_log");
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
					sb.append("insert into user_resource_log_");
					sb.append(dateStr2);
					sb.append(" select * from user_resource_log where CREATE_TIME between '");
					sb.append(dateStr1).append(" 00:00:00");
					sb.append("' AND '");
					sb.append(dateStr1).append(" 23:59:59");
					sb.append("'");
					userResourceLogDao.executeSQL_(sb.toString());//插入数据
					minDate = DateUtil.getDiffDate(minDate, 1);
					LogSystem.info("user_resource_log_"+dateStr2+"表数据分离完成");
				}
			}
		}
		List<Object> list2 = userResourceLogDao.findSQL_("select max(CREATE_TIME),min(CREATE_TIME) from user_resource_log_bak");
		if(list2!=null && list2.size()>0){
			Object[] arr = (Object[])list2.get(0);
			if(arr[0]!=null && arr[1]!=null){
				Date maxDate = DateUtil.getZeroTime(new Timestamp(DateUtil.stringtoDate(arr[0].toString(), DateUtil.FORMAT_ONE).getTime()));
				Date minDate = DateUtil.getZeroTime(new Timestamp(DateUtil.stringtoDate(arr[1].toString(), DateUtil.FORMAT_ONE).getTime()));
				while(minDate.getTime()<=maxDate.getTime()){
					String dateStr1 = DateUtil.dateToString(minDate, DateUtil.LONG_DATE_FORMAT);
					String dateStr2 = DateUtil.dateToString(minDate, DateUtil.FORMAT_FOUR);
					if(!manager.containsTable(minDate)){
						createTable(dateStr2);//创建表结构
					}
					StringBuffer sb = new StringBuffer();
					sb.append("insert into user_resource_log_");
					sb.append(dateStr2);
					sb.append(" select * from user_resource_log_bak where CREATE_TIME between '");
					sb.append(dateStr1).append(" 00:00:00");
					sb.append("' AND '");
					sb.append(dateStr1).append(" 23:59:59");
					sb.append("'");
					userResourceLogDao.executeSQL_(sb.toString());//插入数据
					minDate = DateUtil.getDiffDate(minDate, 1);
					LogSystem.info("user_resource_log_"+dateStr2+"表数据分离完成");
				}
			}
		}
	}
	public static void main(String[] args) {
		System.out.print(DateUtil.dateToString(new Date(), DateUtil.FORMAT_FOUR));
		Date min = DateUtil.getZeroTime(new Date());
		Date max = DateUtil.getZeroTime(DateUtil.getDiffDate(min, 3));
		int i=0;
		while(min.getTime()<max.getTime()){
			System.out.print(i++);
			min = DateUtil.getDiffDate(min, 1);
		}
	}
	
	
	/**
	 * 如果玩家不为null，查找指定玩家的资源日志分页列表，否则是所有玩家的资源日志分页列表
	 * @param user 指定玩家
	 * @param currentPage 当前页
	 * @param pageSize 每页大小
	 * @return 资源日志分页列表
	 */
	public IPage<Object> getUserResourceLogList(String userId, String searchOperation,Date startDate,Date endDate, Integer currentPage, Integer pageSize) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		Date[] arr = DateUtil.dayStrDiff(startDate, endDate);
		StringBuffer sql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();
		if (userId != null) {
			queryStr.append(" and user_id = '").append(userId).append("'");
		}
		if (!Tools.isEmpty(searchOperation)) {
			queryStr.append(" and operation = '").append(searchOperation).append("'");
		}
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and CREATE_TIME BETWEEN '").append(sdf.format(startDate)).append("' AND '").append(sdf.format(endDate)).append("'");
		}
		queryStr.append(" order by create_time DESC");
		String items = "user_resource_log_id, user_id, source_type, operation, change_num, now_num, create_time";
		sql.append(manager.getUnionSql("user_resource_log", arr,queryStr,items));
		userResourceLogDao.closeSession(DBSource.LOG);
		return userResourceLogDao.findSQL_PageUnion(sql.toString(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	public IPage<Object> getUserResourceLogListBySouceType(String userId, String searchOperation, int sourcesType, Date startDate, Date endDate, Integer currentPage, Integer pageSize) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		Date[] arr = DateUtil.dayStrDiff(startDate, endDate);
		StringBuffer sql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();
		if (userId != null) {
			queryStr.append(" and user_id = '").append(userId).append("'");
		}
		if (!Tools.isEmpty(searchOperation)) {
			queryStr.append(" and operation = '").append(searchOperation).append("'");
		}
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and CREATE_TIME BETWEEN '").append(sdf.format(startDate)).append("' AND '").append(sdf.format(endDate)).append("'");
		}
		queryStr.append(" and source_type = " + sourcesType + " order by create_time DESC");
		String items = "user_resource_log_id, user_id, source_type, operation, change_num, now_num, create_time";
		sql.append(manager.getUnionSql("user_resource_log", arr,queryStr,items));
		userResourceLogDao.closeSession(DBSource.LOG);
		return userResourceLogDao.findSQL_PageUnion(sql.toString(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 各个操作所对应的金钱总数
	 * @param user 指定玩家
	 * @param type 1 产出 2消耗
	 * @return 如果玩家不为null，是这个玩家的map，否则是所有玩家的
	 */
	public Map<String, Long> findOperationAndMoneyAmountMap(User user, int type) {
		Map<String, Long> map = new LinkedHashMap<String, Long>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT OPERATION, SUM(CHANGE_NUM) FROM (select * from user_resource_log union all select * from user_resource_log_bak) log WHERE 1=1 and source_type = 3");
		if (user != null && user.getUserId() != null) {
			sql.append(" AND log.USER_ID = ").append(user.getUserId());
		}
		if (type == 1) {
			sql.append(" AND log.CHANGE_NUM > 0");
		} else {
			sql.append(" AND log.CHANGE_NUM < 0");
		}
		sql.append(" GROUP BY OPERATION order by SUM(MONEY)");
		if (type == 1) {
			sql.append(" desc");
		}
		userResourceLogDao.closeSession(DBSource.LOG);
		List<Object> list = userResourceLogDao.findSQL_(sql.toString()); 
		if (list == null || list.size() == 0) {
			return map;
		}
		for (int i = 0; i < list.size(); i++) {
			String operation = ((Object[]) list.get(i))[0].toString(); 
			long amount = Math.abs(Long.valueOf(((Object[]) list.get(i))[1].toString())); 
			map.put(operation, amount);
		}
		return map;
	}
	
	/**
	 * 各个操作所对应的金钱总数
	 * @param type 1 产出 2消耗
	 * @return 如果玩家不为null，是这个玩家的map，否则是所有玩家的
	 */
	public Map<Integer, Long> findOperationAndMoneyAmountMap(String[] dates, int type) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_resource_log",dates);
		Map<Integer, Long> map = new LinkedHashMap<Integer, Long>();
		StringBuffer sql = new StringBuffer();
		sql.append("select OPERATION, SUM(CHANGE_NUM) from ");
		sql.append(tableName);
		sql.append(" where source_type = 3 and create_time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		if (type == 1) {
			sql.append(" AND CHANGE_NUM > 0");
		} else {
			sql.append(" AND CHANGE_NUM < 0");
		}
		sql.append(" GROUP BY OPERATION");
		userResourceLogDao.closeSession(DBSource.LOG);
		List<Object> list = userResourceLogDao.findSQL_(sql.toString()); 
		if (list == null || list.size() == 0) {
			return map;
		}
		for (int i = 0; i < list.size(); i++) {
			String operation = ((Object[]) list.get(i))[0].toString(); 
			long amount = Math.abs(Long.valueOf(((Object[]) list.get(i))[1].toString())); 
			map.put(Integer.valueOf(operation), amount);
		}
		return map;
	}
	/**
	 * 备份日志
	 */
	public void backup(String date){
		String backup = "insert into user_resource_log_bak select * from user_resource_log where CREATE_TIME<='"+date+"'";
		String delete = "delete from user_resource_log where CREATE_TIME<='"+date+"'";
		userResourceLogDao.closeSession(DBSource.LOG);
		userResourceLogDao.executeSQL_(backup);
		userResourceLogDao.executeSQL_(delete);
	}
	
	/**
	 * 声望统计
	 * @return
	 */
	public List<Object> findPrestige() {
		String table = "user_treasure_log_";
		java.text.DateFormat format2 = new java.text.SimpleDateFormat("yyyyMMdd");
        table += format2.format(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		StringBuilder sb = new StringBuilder();
		sb.append("select user_id, treasure_id, treasure_type from ");
		sb.append(table);
		sb.append(" where operation=10006");
		userResourceLogDao.closeSession(DBSource.LOG);
		return userResourceLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * @return the userResourceLogDao
	 */
	public UserResourceLogDao getUserResourceLogDao() {
		return userResourceLogDao;
	}

	/**
	 * @param userResourceLogDao the userResourceLogDao to set
	 */
	public void setUserResourceLogDao(UserResourceLogDao userResourceLogDao) {
		this.userResourceLogDao = userResourceLogDao;
	}
}