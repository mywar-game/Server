package com.fantingame.game.log.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.log.dao.UnSynDao;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;



public class LogService implements BeanPostProcessor {
	private LinkedBlockingQueue<String> logQueue = new LinkedBlockingQueue<String>();
	
	private List<IGameLog> listGameLog = Lists.newArrayList();
	/**
	 * 插入内部日志库用到的dao
	 */
	private static final Logger errorLog = Logger.getLogger("errorlog");

	@Autowired
	private UnSynDao unSynDao; 

	/**
	 * 异步执行插入日志
	 * @param sql
	 */
	public void unSynInsertLog(String sql){
		try {
			logQueue.add(sql);
		} catch (Exception e) {
			errorLog.debug(sql);
		}
	}
	/**
	 * 同步执行插入日志
	 * @param sql
	 */
	public void synInsertLog(String sql){
		try {
			unSynDao.executeLogSql(sql);
		} catch (Exception e) {
			errorLog.debug(sql);
		}
	}

	/**
	 * 更改日志表的用户名称
	 * 
	 * @param userId
	 * @param name
	 * @return
	 */
	public void updateUserLogName(String sql) {
		try {
			unSynDao.executeLogSql(sql);
		} catch (Exception e) {
			errorLog.debug(sql);
		}
	}
	
	public void userMallLog(String userId, int userLevel, int toolId, int buyNum, int cost, int costCopperNum, Date creatTime) {
		String time = DateUtils.getTimeStr(creatTime);
		String sql = "INSERT INTO user_mall_log(USER_ID,USER_LEVEL,TREASURE_ID,BUY_NUM,COST,COSTCOPPER,TIME) " + "VALUES('" + userId + "'," + userLevel + "," + toolId + "," + buyNum + "," + cost + "," + costCopperNum
				+ ",'" + time + "')";
//		logOperatorDaoRedisImpl.add(sql);
		try {
			unSynDao.executeLogSql(sql);
		} catch (Exception e) {
			errorLog.debug(sql);
		}
	}
	
	public void userEquipMentOperatorLog(String userId, String userEquipMentId, int equipMentId, String userHeroId, int equipMentLevel,int type,int goodsUseType,Date operatorTime) {
		String time = DateUtils.getTimeStr(operatorTime);
		String now = DateUtils.getDateForLog();
		String sql = "INSERT INTO user_equipment_log_" + now + "(USER_ID,USER_EQUIPMENT_ID,EQUIPMENT_ID,USER_HERO_ID,EQUIPMENT_LEVEL,TYPE,SMALL_TYPE,OPERATION_TIME) VALUES" + "('" + userId + "','" + userEquipMentId + "',"
				+ equipMentId + ",'" + userHeroId + "'," + equipMentLevel + "," + type + ","+goodsUseType+",'" + time + "')";
		try {
			logQueue.add(sql);
		} catch (Exception e) {
			errorLog.debug(sql);
		}
	}
	
	public List<String> getAll(){
		List<String> list = new ArrayList<String>();
		logQueue.drainTo(list);
		return list;
	}
	
	public void executeInsertLog(List<String> list){
		String[] strArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			  strArray[i] = list.get(i);
		}
		Joiner joiner = Joiner.on(";");
		String str = joiner.skipNulls().join(strArray);
		try {
			//即将插入下列sql
			LogSystem.debug("即将插入下列sql"+str);
			unSynDao.executeBatchLogSql(strArray);
		} catch (Exception e) {
			LogSystem.error(e,"第一次插入出错"+strArray);
			//重复插一下
			try {
				unSynDao.executeBatchLogSql(strArray);
			} catch (Exception e2) {
				LogSystem.error(e2,"第二次插入出错"+strArray);
				//记录日志
				errorLog.debug(str);
			}
		}
	}
	public static void main(String[] args) {
	}
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if(bean instanceof IGameLog){
			this.listGameLog.add((IGameLog)bean);
		    LogSystem.info("加载创建日志库脚本类："+bean.getClass().getName());
		}
		return bean;
	}
	/**
	 * 创建需要增加的日志表
	 * @param date
	 */
	public void creatLogTable(Date date){
		String now = DateUtils.getDateForLog(date);
		for(IGameLog gameLog:listGameLog){
			String sql = gameLog.createTableSQL(now);
			unSynDao.executeLogSql(sql);
		}
	}
}
