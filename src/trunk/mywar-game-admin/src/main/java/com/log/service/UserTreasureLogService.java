package com.log.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;
import com.framework.util.DateUtil;
import com.log.dao.UserTreasureLogDao;
import com.system.manager.DataSourceManager;

public class UserTreasureLogService {
	
	/** **/
	private UserTreasureLogDao userTreasureLogDao;
	
	/**
	 * 创建备份日志表 
	 * @param timeStamp
	 */
	public void createTable(String timeStamp){
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE IF NOT EXISTS `user_treasure_log_");
		sql.append(timeStamp);
		sql.append("` (`USER_TREASURE_LOG_ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '玩家道具日志编号' ,`USER_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '玩家编号' ,`TREASURE_ID`  int(11) NOT NULL COMMENT '道具编号' ,`TREASURE_TYPE`  int(11) NOT NULL COMMENT '道具类型' ,`CATEGORY`  int(2) NOT NULL COMMENT '类别（1获得 2使用）' ,`OPERATION`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作类型(任务掉落，战斗掉落，npc产出，商城购买)' ,`CHANGE_NUM`  int(11) NOT NULL COMMENT '改变的数量' ,`EXTINFO`  mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '扩展信息' ,`CREATE_TIME`  datetime NOT NULL COMMENT '创建时间' ,PRIMARY KEY (`USER_TREASURE_LOG_ID`),INDEX `create_time` (`CREATE_TIME`),INDEX `user_id` (`USER_ID`))ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=471140");
		userTreasureLogDao.closeSession(DBSource.LOG);
		userTreasureLogDao.executeSQL_(sql.toString());
	}
	
	public void deal(){
		DataSourceManager manager = DataSourceManager.getInstatnce();
		userTreasureLogDao.closeSession(DBSource.LOG);
		List<Object> list1 = userTreasureLogDao.findSQL_("select max(CREATE_TIME),min(CREATE_TIME) from user_treasure_log");
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
					sb.append("insert into user_treasure_log_");
					sb.append(dateStr2);
					sb.append(" select * from user_treasure_log where CREATE_TIME between '");
					sb.append(dateStr1).append(" 00:00:00");
					sb.append("' AND '");
					sb.append(dateStr1).append(" 23:59:59");
					sb.append("'");
					userTreasureLogDao.executeSQL_(sb.toString());//插入数据
					minDate = DateUtil.getDiffDate(minDate, 1);
					LogSystem.info("user_treasure_log_"+dateStr2+"表数据分离完成");
				}
			}
		}
		List<Object> list2 = userTreasureLogDao.findSQL_("select max(CREATE_TIME),min(CREATE_TIME) from user_treasure_log_bak");
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
					sb.append("insert into user_treasure_log_");
					sb.append(dateStr2);
					sb.append(" select * from user_treasure_log_bak where CREATE_TIME between '");
					sb.append(dateStr1).append(" 00:00:00");
					sb.append("' AND '");
					sb.append(dateStr1).append(" 23:59:59");
					sb.append("'");
					userTreasureLogDao.executeSQL_(sb.toString());//插入数据
					minDate = DateUtil.getDiffDate(minDate, 1);
					LogSystem.info("user_treasure_log_"+dateStr2+"表数据分离完成");
				}
			}
		}
	}
	
	
	/**
	 * 获取玩家道具列表
	 * @param name
	 */
	public IPage<Object> getUserTreasureLogList(String userId, Integer searchTreasureId, String searchCategory, String searchOperation, Date startDate, Date endDate, Integer currentPage, Integer pageSize) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		Date[] arr = DateUtil.dayStrDiff(startDate, endDate);
		StringBuffer sql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and CREATE_TIME BETWEEN '").append(sdf.format(startDate)).append("' AND '").append(sdf.format(endDate)).append("'");
		}
		if (userId != null) {
			queryStr.append(" and user_id = '").append(userId).append("'");
		}
		if (searchTreasureId != null) {
			queryStr.append(" and treasure_id = ").append(searchTreasureId);
		}
		if (searchCategory != null && !"".equals(searchCategory)) {
			queryStr.append(" and category = '").append(searchCategory).append("'");
		}
		if (searchOperation != null && !"".equals(searchOperation)) {
			queryStr.append(" and operation = '").append(searchOperation).append("'");
		}
		queryStr.append(" order by CREATE_TIME DESC");
		String items = "*";
		sql.append(manager.getUnionSql("user_treasure_log", arr,queryStr,items));
		userTreasureLogDao.closeSession(DBSource.LOG);
		return userTreasureLogDao.findSQL_PageUnion(sql.toString(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	
	/**
	 * 各个道具的各个操作的总数
	 * @return
	 */
	public Map<Integer, Map<String, Integer>> findTreasureIdAndOperationAmountMap() {
		Map<Integer, Map<String, Integer>> map = new LinkedHashMap<Integer, Map<String, Integer>> ();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT TREASURE_ID, OPERATION, SUM(CHANGE_NUM) FROM user_treasure_log log GROUP BY TREASURE_ID, OPERATION");
		userTreasureLogDao.closeSession(DBSource.LOG);
		List<Object> list = userTreasureLogDao.findSQL_(sb.toString());
		for (int i = 0; i < list.size(); i++) {
			Integer treasureId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
			String operation = ((Object[]) list.get(i))[1].toString();
			Integer amount = Integer.parseInt(((Object[]) list.get(i))[2].toString());
			
			Map<String, Integer> tempMap;
			//还没这个id
			if (map.get(treasureId) == null) {
				tempMap = new HashMap<String, Integer>();
				tempMap.put(operation, amount);
				map.put(treasureId, tempMap);
			//有这个id，但是没有这个operation
			} else if (map.get(treasureId).get(operation) == null) {
				tempMap = map.get(treasureId);
				tempMap.put(operation, amount);
				map.put(treasureId, tempMap);
			//重复
			} else {
				LogSystem.warn("treasureId:"+treasureId+" operation:"+operation+" amount:"+amount);
			}
		}
		return map;
	}
	
	/**
	 * 查询推图掉落各装备的次数
	 * @param dates
	 * @return
	 */
	public Map<Integer, Integer> findEquipDropNum(String[] dates,String operation,Integer treasureType,Integer category){
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_treasure_log",dates);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		StringBuffer sql = new StringBuffer();
		sql.append("select treasure_id,count(change_num) from ");
		sql.append(tableName);
		sql.append(" where create_time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		if(operation!=null){
			sql.append(" AND operation='");
			sql.append(operation);
			sql.append("'");
		}
		if(treasureType!=null){
			sql.append(" and treasure_type=");
			sql.append(treasureType);
		}
		if(category!=null){
			sql.append(" and CATEGORY=");
			sql.append(category);
		}
		sql.append(" group by treasure_id");
		userTreasureLogDao.closeSession(DBSource.LOG);
		List<Object> list = userTreasureLogDao.findSQL_(sql.toString()); 
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(Integer.valueOf(arr[0].toString()), Integer.valueOf(arr[1].toString()));
			}
		}
		return map;
	}
	/**
	 * 备份日志
	 */
	public void backup(String date){
		String backup = "insert into user_treasure_log_bak select * from user_treasure_log where CREATE_TIME<='"+date+"'";
		String delete = "delete from user_treasure_log where CREATE_TIME<='"+date+"'";
		userTreasureLogDao.closeSession(DBSource.LOG);
		userTreasureLogDao.executeSQL_(backup);
		userTreasureLogDao.executeSQL_(delete);
	}
	/**
	 * 测试数据是否正确
	 * @param dates
	 * @throws Exception
	 */
	public void test(String[] dates) throws Exception {
	
	}
	
	/**
	 * 夺宝消耗耐力
	 * @return
	 */
	public List<Object> countEndrance(String[] dates) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_treasure_log", dates);
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(CHANGE_NUM), TREASURE_TYPE from ");
		sb.append(tableName);
		sb.append(" where OPERATION = 20051 and TREASURE_TYPE = 9");
		userTreasureLogDao.closeSession(DBSource.LOG);
		return userTreasureLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 争霸消耗耐力
	 * @return
	 */
	public List<Object> countZhengba(String[] dates) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_treasure_log", dates);
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(CHANGE_NUM), TREASURE_TYPE from ");
		sb.append(tableName);
		sb.append(" where OPERATION = 20045 and TREASURE_TYPE = 9");
		userTreasureLogDao.closeSession(DBSource.LOG);
		return userTreasureLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 作弊记录
	 * @param dates
	 * @return
	 */
	public List<Object> computeCheatLog(String[] dates) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_treasure_log", dates);
		StringBuilder sb = new StringBuilder();
		sb.append("select user_id, operation, TREASURE_ID, TREASURE_TYPE, create_time, (select count(*) from ");
		sb.append(tableName);
		sb.append(" where user_id = a.user_id and CREATE_TIME = a.create_time and OPERATION = a.operation and TREASURE_ID = a.TREASURE_ID and TREASURE_TYPE = a.TREASURE_TYPE) as count from ");
		sb.append(tableName);
		sb.append(" a group by create_time having count >= 30");
		
		userTreasureLogDao.closeSession(DBSource.LOG);
		return userTreasureLogDao.findSQL_(sb.toString());

	}
	public UserTreasureLogDao getUserTreasureLogDao() {
		return userTreasureLogDao;
	}

	public void setUserTreasureLogDao(UserTreasureLogDao userTreasureLogDao) {
		this.userTreasureLogDao = userTreasureLogDao;
	}
	
}
