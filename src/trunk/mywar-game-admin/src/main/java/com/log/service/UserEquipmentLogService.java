package com.log.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;
import com.framework.util.DateUtil;
import com.log.bo.UserEquipmentLog;
import com.log.dao.UserEquipmentLogDao;
import com.system.manager.DataSourceManager;

public class UserEquipmentLogService {
	
	private UserEquipmentLogDao userEquipmentLogDao;

	public UserEquipmentLogDao getUserEquipmentLogDao() {
		return userEquipmentLogDao;
	}

	public void setUserEquipmentLogDao(UserEquipmentLogDao userEquipmentLogDao) {
		this.userEquipmentLogDao = userEquipmentLogDao;
	}
	
	/**
	 * 创建备份日志表 
	 * @param timeStamp
	 */
	public void createTable(String timeStamp){
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE IF NOT EXISTS `user_equipment_log_");
		sql.append(timeStamp);
		sql.append("` (`USER_EQUIPMENT_LOG_ID`  int(11) NOT NULL AUTO_INCREMENT ,`USER_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,`USER_EQUIPMENT_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,`EQUIPMENT_ID`  int(11) NOT NULL ,`USER_HERO_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '佩戴虎将id' ,`EQUIPMENT_LEVEL`  int(11) NOT NULL COMMENT '装备等级' ,`TYPE`  int(11) NOT NULL COMMENT '类型( 1.强化2.进阶.3.获取 4.出售)' ,`OPERATION_TIME`  datetime NOT NULL COMMENT '操作时间' ,PRIMARY KEY (`USER_EQUIPMENT_LOG_ID`),INDEX `operation_time` (`OPERATION_TIME`),INDEX `user_id` (`USER_ID`))ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=28399");
		userEquipmentLogDao.closeSession(DBSource.LOG);
		userEquipmentLogDao.executeSQL_(sql.toString());
	}
	
	public void deal(){
		DataSourceManager manager = DataSourceManager.getInstatnce();
		userEquipmentLogDao.closeSession(DBSource.LOG);
		List<Object> list1 = userEquipmentLogDao.findSQL_("select max(OPERATION_TIME),min(OPERATION_TIME) from user_equipment_log");
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
					sb.append("insert into user_equipment_log_");
					sb.append(dateStr2);
					sb.append(" select * from user_equipment_log where OPERATION_TIME between '");
					sb.append(dateStr1).append(" 00:00:00");
					sb.append("' AND '");
					sb.append(dateStr1).append(" 23:59:59");
					sb.append("'");
					userEquipmentLogDao.executeSQL_(sb.toString());//插入数据
					minDate = DateUtil.getDiffDate(minDate, 1);
					LogSystem.info("user_equipment_log_"+dateStr2+"表数据分离完成");
				}
			}
		}
		List<Object> list2 = userEquipmentLogDao.findSQL_("select max(OPERATION_TIME),min(OPERATION_TIME) from user_equipment_log_bak");
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
					sb.append("insert into user_equipment_log_");
					sb.append(dateStr2);
					sb.append(" select * from user_equipment_log_bak where OPERATION_TIME between '");
					sb.append(dateStr1).append(" 00:00:00");
					sb.append("' AND '");
					sb.append(dateStr1).append(" 23:59:59");
					sb.append("'");
					userEquipmentLogDao.executeSQL_(sb.toString());//插入数据
					minDate = DateUtil.getDiffDate(minDate, 1);
					LogSystem.info("user_equipment_log_"+dateStr2+"表数据分离完成");
				}
			}
		}
	}
	
	
	/**
	 * 按照玩家ID查询装备操作信息
	 * @param currentPage
	 * @param pageSize
	 * @param querySql
	 * @return
	 */
	public IPage<Object> findPageLogListByCondition(String userId, Date startDate, Date endDate, Integer searchType, Integer currentPage, Integer pageSize) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		Date[] arr = DateUtil.dayStrDiff(startDate, endDate);
		StringBuffer sql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();
		if (userId != null) {
			queryStr.append(" and USER_ID = '").append(userId).append("'");
		}
		if (searchType != null) {
			queryStr.append(" and TYPE = ").append(searchType);
		}
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and OPERATION_TIME BETWEEN '").append(sdf.format(startDate)).append("' AND '").append(sdf.format(endDate)).append("'");
		}
		queryStr.append(" order by OPERATION_TIME DESC");
		String items = "USER_EQUIPMENT_LOG_ID,USER_ID,USER_EQUIPMENT_ID,EQUIPMENT_ID,USER_HERO_ID,EQUIPMENT_LEVEL,TYPE,OPERATION_TIME";
		sql.append(manager.getUnionSql("user_equipment_log", arr,queryStr,items));
		userEquipmentLogDao.closeSession(DBSource.LOG);
		IPage<Object> userEquipmentLogList = userEquipmentLogDao.findSQL_PageUnion(new String(sql), new ArrayList<Object>(), pageSize, currentPage);
		return userEquipmentLogList;
	}
	
	
	/**
	 * 各装备指定类型的次数
	 * @param type 1.强化 2.镶嵌 3.打造
	 * @param order
	 * @return
	 */
	public Map<Integer, Integer> findModifyAndCountMap(int type, int order) {
		Map<Integer, Integer> strengthenMap = new LinkedHashMap<Integer, Integer>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT EQUIPMENT_ID,COUNT(1) FROM user_equipment_log WHERE TYPE = ");
		sb.append(type);
		sb.append(" GROUP BY EQUIPMENT_ID");
		if (order > 0) {
			sb.append(" ORDER BY COUNT(1) ");
			if ((order % 2) == 1) {
				sb.append("ASC");
			}
			if ((order % 2) == 0) {
				sb.append("DESC");
			}
		}
		userEquipmentLogDao.closeSession(DBSource.LOG);
		List<Object> list = userEquipmentLogDao.findSQL_(sb.toString());
		for (int i = 0; i < list.size(); i++) {
			Integer equipmentId = Integer.valueOf(((Object[]) list.get(i))[0].toString());
			Integer amount = Integer.valueOf(((Object[]) list.get(i))[1].toString());
			strengthenMap.put(equipmentId, amount);
		}
		return strengthenMap;
	}
	
	
	public UserEquipmentLog findOneUserEquipmentLog(Integer userEquipmentLogId){
		return this.userEquipmentLogDao.loadBy("userEquipmentLogId", userEquipmentLogId, DBSource.LOG);
	}
	
	/**
	 * 删除动作常量数据
	 * @param keyWord
	 */
	public void delOneUserEquipmentLog(Integer userEquipmentLogId){
		userEquipmentLogDao.remove(this.findOneUserEquipmentLog(userEquipmentLogId), DBSource.LOG);
	}
	/**
	 * 备份日志
	 */
	public void backup(String date){
		String backup = "insert into user_equipment_log_bak select * from user_equipment_log where OPERATION_TIME<='"+date+"'";
		String delete = "delete from user_equipment_log where OPERATION_TIME<='"+date+"'";
		userEquipmentLogDao.closeSession(DBSource.LOG);
		userEquipmentLogDao.executeSQL_(backup);
		userEquipmentLogDao.executeSQL_(delete);
	}
}
