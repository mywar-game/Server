package com.dataconfig.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.admin.util.Tools;
import com.dataconfig.bo.PaymentLog;
import com.dataconfig.dao.PaymentLogDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class UserPayService {
	private PaymentLogDao paymentLogDao;
	/**
	 * 玩家id和玩家的充值次数和总数(按充值数排序)
	 * @param order 降序还是升序还是不排序
	 * @return
	 */
	public Map<String, String> getEveryUserPayAmount(String order) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT user_id,COUNT(*) as count,ifnull(SUM(amount),0) as mountSum FROM payment_log GROUP BY user_id");
		if (!Tools.isEmpty(order)) {
			sb.append(" ORDER BY mountSum ").append(order);
		}
		paymentLogDao.closeSession(DBSource.LOG);
		String[] arr = new String[3];
		arr[0] = "user_id";
		arr[1] = "count";
		arr[2] = "mountSum";
		List<Object> list = paymentLogDao.findSQLByType(sb.toString(),arr,Hibernate.STRING,Hibernate.INTEGER,Hibernate.INTEGER);
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String userId = ((Object[]) list.get(i))[0].toString();
				Integer payTimes = Integer.valueOf(((Object[]) list.get(i))[1].toString());
				double d = Double.valueOf(((Object[]) list.get(i))[2].toString());
				Integer payAmount = 0;
				if(d!=0){
					payAmount = (int)d;
				}
				map.put(userId, payTimes + "_" +payAmount);
			}
		}
		return map;
	}
	
	/**
	 * 玩家id和玩家的充值次数和总数(按充值数排序)
	 * @param order 降序还是升序还是不排序
	 * @return
	 */
	public Map<String, String> getEveryUserPayAmountByDates(String order, String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT user_id,COUNT(*) as count,ifnull(SUM(amount),0) as mountSum");
		
		sb.append(" FROM payment_log ");
		sb.append(" where CREATED_TIME between '");
		sb.append(dates[0]);
		sb.append("' and '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" GROUP BY user_id");
		if (!Tools.isEmpty(order)) {
			sb.append(" ORDER BY mountSum ").append(order);
		}
		paymentLogDao.closeSession(DBSource.LOG);
		String[] arr = new String[3];
		arr[0] = "user_id";
		arr[1] = "count";
		arr[2] = "mountSum";
		List<Object> list = paymentLogDao.findSQLByType(sb.toString(),arr,Hibernate.STRING,Hibernate.INTEGER,Hibernate.INTEGER);
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String userId = ((Object[]) list.get(i))[0].toString();
				Integer payTimes = Integer.valueOf(((Object[]) list.get(i))[1].toString());
				double d = Double.valueOf(((Object[]) list.get(i))[2].toString());
				Integer payAmount = 0;
				if(d!=0){
					payAmount = (int)d;
				}
				map.put(userId, payTimes + "_" +payAmount);
			}
		}
		return map;
	}
	
	/**
	 * 指定日期内的充值总数---如果dates是null则查全部的
	 * @param dates
	 * @return
	 */
	public Integer getPayAmount(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT IFNULL(SUM(amount),0) FROM payment_log");
		if(dates!=null){
			sb.append(" WHERE created_time BETWEEN '");
			sb.append(dates[0]);
			sb.append("' AND '");
			sb.append(dates[1]);
			sb.append("'");
		}
		paymentLogDao.closeSession(DBSource.LOG);
		List<Object> list =  paymentLogDao.findSQL_(sb.toString());
		if(list!=null && list.size()>0){
			return Double.valueOf(list.get(0).toString()).intValue();
		}
		return 0;
	}
	
	/**
	 * 指定日期内的付费总玩家数、充值总数---如果dates是null则查全部的
	 * @param dates
	 * @return
	 */
	public List<Object> getSomeDayPayAmount(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT IFNULL(COUNT(DISTINCT(USER_ID)),0),IFNULL(SUM(amount),0) FROM payment_log");
		if(dates!=null){
			sb.append(" WHERE created_time BETWEEN '");
			sb.append(dates[0]);
			sb.append("' AND '");
			sb.append(dates[1]);
			sb.append("'");
		}
		paymentLogDao.closeSession(DBSource.LOG);
		return paymentLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 3天内未充值的付费玩家
	 * @return 玩家列表
	 */
	public List<Object> findWithoutPayInThreeDays() {
		String str = "SELECT USER_ID, SUM(AMOUNT), MAX(CREATED_TIME) FROM payment_log GROUP BY USER_ID HAVING UNIX_TIMESTAMP(SUBDATE(now(),INTERVAL 3 DAY)) > UNIX_TIMESTAMP(MAX(CREATED_TIME)) AND SUM(AMOUNT) > 0";
		paymentLogDao.closeSession(DBSource.LOG);
		List<Object> list = paymentLogDao.findSQL_(str);
		return list;
	}
	/**
	 * 付费玩家的数量
	 * @return
	 */
	public Integer findTotalPayUserNum() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(DISTINCT(USER_ID)) FROM payment_log");
		paymentLogDao.closeSession(DBSource.LOG);
		List<Object> list = paymentLogDao.findSQL_(sb.toString());
		Integer totalPayUserNum = Integer.valueOf(list.get(0).toString());
		return totalPayUserNum;
	}
	
	/**
	 * 玩家id和玩家的充值次数和总数及当前等级(按充值数排序)
	 * @param order 降序还是升序还是不排序
	 * @return
	 */
	public Map<Integer, String> getEveryUserPayTimesAndAmountAndNowLevel() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT (USER_LEVEL DIV 10) AS levelIntervalIndex,COUNT(DISTINCT(USER_ID)),COUNT(*) AS payTimes,SUM(AMOUNT) AS payAmount FROM payment_log GROUP BY levelIntervalIndex");
		paymentLogDao.closeSession(DBSource.LOG);
		List<Object> list = paymentLogDao.findSQL_(sb.toString());
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Integer levelIntervalIndex = Integer.valueOf(((Object[]) list.get(i))[0].toString());
				Integer payUserNum = Integer.valueOf(((Object[]) list.get(i))[1].toString());
				Integer payTimes = Integer.valueOf(((Object[]) list.get(i))[2].toString());
				String str = ((Object[]) list.get(i))[3].toString();
				Integer payAmount = Double.valueOf(str).intValue();
				map.put(levelIntervalIndex, payUserNum + "_" +payTimes + "_" +payAmount);
			}
		}
		return map;
	}
	public static void main(String[] args) {
		double d = 300.00d;
		String str = "300.00";
		System.out.print(str.substring(0, str.length()-3)+"\n"+(int)d);
	}
	/**
	 * 获得给定玩家Id的充值总额
	 * @param userIdList
	 * @return
	 */
	public Integer getPayAmountByUserIds(String userId) {
		if (Tools.isEmpty(userId)) {
			return 0;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT IFNULL(SUM(AMOUNT),0) FROM payment_log WHERE USER_ID ='");
		sb.append(userId);
		sb.append("'");
		System.out.println(sb.toString());
		paymentLogDao.closeSession(DBSource.LOG);
		List<Object> list = paymentLogDao.findSQL_(sb.toString());
		Integer payAmount = Double.valueOf(list.get(0).toString()).intValue();
		return payAmount;
	}
	
	/**
	 * 按是否分组渠道来查询付费总人数和付费总额
	 * @return
	 */
	public List<Object> findTotalPayUserNumAndTotalAmount(String[] dates,boolean isPartner) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(DISTINCT(USER_ID)),sum(amount)");
		if(isPartner){
			sb.append(",PARTNER_ID");
		}
		sb.append(" FROM payment_log where created_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		if(isPartner){
			sb.append(" group by PARTNER_ID");
		}
		paymentLogDao.closeSession(DBSource.LOG);
		return paymentLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 渠道分组的当天付费总人数和付费总额
	 * @return
	 */
	public Map<String, Object[]> findTotalPayUserNumAndTotalAmount(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT reg.CHANNEL,(case when reg.SMALL_CHANNEL='null' then 0 else reg.SMALL_CHANNEL end) AS SMALL_CHANNEL1,IFNULL(COUNT(DISTINCT(log.USER_ID)),0),sum(log.amount)");
		sb.append(" FROM payment_log log,user_reg_log reg where log.created_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" AND log.user_id=reg.user_id group by reg.CHANNEL , SMALL_CHANNEL1");
		paymentLogDao.closeSession(DBSource.LOG);
		List<Object> list = paymentLogDao.findSQL_(sb.toString());
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				String smallChannel = arr[1].toString();
				
				map.put(arr[0].toString()+" "+smallChannel,arr);
			}
		}
		return map;
	}
	
	/**
	 * 渠道分组的当天新增付费人数
	 * @return
	 */
	public Map<String, Integer> findNewRegPayUserNum(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT reg.CHANNEL,(case when reg.SMALL_CHANNEL='null' then 0 else reg.SMALL_CHANNEL end) AS SMALL_CHANNEL1,IFNULL(COUNT(DISTINCT(pay.USER_ID)),0)");
		sb.append(" FROM payment_log pay,user_reg_log reg where pay.user_id=reg.user_id");
		sb.append(" and reg.reg_time between '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" and pay.created_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by reg.CHANNEL,SMALL_CHANNEL1");
		paymentLogDao.closeSession(DBSource.LOG);
		List<Object> list = paymentLogDao.findSQL_(sb.toString());
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				String smallChannel = arr[1].toString();
				
				map.put(arr[0].toString()+" "+smallChannel,Integer.valueOf(arr[2].toString()));
			}
		}
		return map;
	}
	
	/**
	 *查询某个时间点之前的付费人数、付费总额,根据时间、渠道分组
	 * @return
	 */
	public Map<String, Object[]> findPayUserNumByDay(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DATE_FORMAT(created_time,'%Y-%m-%d'),PARTNER_ID,IFNULL(COUNT(DISTINCT(USER_ID)),0),IFNULL(sum(amount),0)");
		sb.append(" FROM payment_log where created_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by DATE_FORMAT(created_time,'%Y-%m-%d'),PARTNER_ID");
		paymentLogDao.closeSession(DBSource.LOG);
		List<Object> list = paymentLogDao.findSQL_(sb.toString());
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString()+"_"+arr[1].toString(),arr);
			}
		}
		return map;
	}
	
	/**
	 *查询某个时间点之前的新增付费人数,根据时间、渠道分组
	 * @return
	 */
	public Map<String, Integer> findNewRegPayUserNumByDay(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DATE_FORMAT(created_time,'%Y-%m-%d'),PARTNER_ID,IFNULL(COUNT(DISTINCT(pay.USER_ID)),0)");
		sb.append(" FROM payment_log pay,user_reg_log reg where pay.user_id=reg.user_id");
		sb.append(" and DATE_FORMAT(created_time,'%Y-%m-%d')=DATE_FORMAT(reg_time,'%Y-%m-%d')");
		sb.append(" and created_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by DATE_FORMAT(created_time,'%Y-%m-%d'),PARTNER_ID");
		paymentLogDao.closeSession(DBSource.LOG);
		List<Object> list = paymentLogDao.findSQL_(sb.toString());
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[0].toString()+"_"+arr[1].toString(),Integer.valueOf(arr[2].toString()));
			}
		}
		return map;
	}
	
	/**
	 * 按是否分组渠道来查询付费总人数、付费总次数和付费总额
	 * @return
	 */
	public Map<String, Object[]> findTotalPayUserStrAndTotalAmount(String[] dates) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT GROUP_CONCAT(DISTINCT(USER_ID)),sum(amount),COUNT(USER_ID)");
		sb.append(",PARTNER_ID");
		sb.append(" FROM payment_log where created_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by PARTNER_ID");
		paymentLogDao.closeSession(DBSource.LOG);
		List<Object> list = paymentLogDao.findSQL_(sb.toString());
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				map.put(arr[3].toString(), arr);
			}
		}
		return map;
	}
	
	/**
	 * 按是否分组渠道来查询某天内新用户的新用户付费数和新用户付费总额
	 * @param dates
	 * @param isPartner
	 * @return
	 */
	public List<Object> findNewUserPayNumAndPayAmount(String[] dates,boolean isPartner){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(DISTINCT(pay.USER_ID)),sum(pay.amount)");
		if(isPartner){
			sb.append(",pay.PARTNER_ID");
		}
		sb.append(" FROM payment_log pay,user u where pay.user_id=u.user_id and u.reg_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		if(isPartner){
			sb.append(" group by PARTNER_ID");
		}
		paymentLogDao.closeSession(DBSource.LOG);
		return paymentLogDao.findSQL_(sb.toString());
	}
	
	public List<Object> findPayAmount(String[] dates, String tempChannelStr, String regEndDateStr) {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(log.amount),log.user_id from payment_log log,user u where log.partner_id in (");
		String[] str = tempChannelStr.split(",");
		for (int i = 0; i < str.length; i++) {
			sb.append("'");
			sb.append(str[i]);
			sb.append("'");
			if (i < str.length - 1) {
				sb.append(",");
			}
		}
		sb.append(") and log.created_time between '");
		sb.append(dates[0]);
		sb.append("' and '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" and u.user_id = log.user_id and u.reg_time between '");
		sb.append(dates[0]);
		sb.append("' and '");
		sb.append(regEndDateStr);
		sb.append("'");
		paymentLogDao.closeSession(DBSource.LOG);
		return paymentLogDao.findSQL_(sb.toString());
	}
	
		public List<Object> findNewUserPayNumAndPayAmount2(String[] dates, boolean isPartner){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(DISTINCT(pay.USER_ID)),sum(pay.amount)");
		if(isPartner){
			sb.append(",pay.PARTNER_ID");
		}
		sb.append(" FROM payment_log pay,user u where pay.user_id=u.user_id and u.reg_time BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		
		sb.append(" AND pay.CREATED_TIME BETWEEN '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		
		if(isPartner){
			sb.append(" group by PARTNER_ID");
		}
		paymentLogDao.closeSession(DBSource.LOG);
		return paymentLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 查询某时间段内渠道充值信息
	 * @param dates
	 * @return
	 */
	public List<Object> findChannelRechargeInfo(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("select PARTNER_ID,ifnull(sum(AMOUNT),0) from payment_log where created_time between '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(" group by PARTNER_ID");
		paymentLogDao.closeSession(DBSource.LOG);
		return paymentLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 采集每天的充值数据供平台统计
	 * @param dates
	 * @return
	 */
	public List<Object> collectChannelUserPayInfo(String[] dates){
		StringBuffer sb = new StringBuffer();
		sb.append("select pay.partner_id,pay.order_id,pay.user_id,pay.amount,pay.created_time,pay.user_name,pay.user_level,login.lastLoginTime from");
		sb.append(" (select * from payment_log where created_time between '");
		sb.append(dates[0]);
		sb.append("' AND '");
		sb.append(dates[1]);
		sb.append("'");
		sb.append(") pay left join (SELECT USER_ID,MAX(LOGIN_TIME) as lastLoginTime FROM");
		sb.append(" (select * from user_login_log union all select * from user_login_log_bak) log group by log.user_id)");
		sb.append(" login on pay.user_id=login.user_id");
		paymentLogDao.closeSession(DBSource.LOG);
		return paymentLogDao.findSQL_(sb.toString());
	}
	
	/**
	 * 日志列表
	 * @param user
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<PaymentLog> findUserPayHistoryLogListByCondition(int lodoId,Date startDate,Date endDate, Integer currentPage, Integer pageSize) {
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select new PaymentLog(log.userId, log.userLevel, log.orderId, log.partnerId, log.amount, log.createdTime,user.userName, user.lodoId) from PaymentLog log, User user where log.userId = user.userId");
		if (lodoId!=0) {
			sql.append(" and user.lodoId = ?");
			args.add(lodoId);
		}
		if (startDate != null && endDate != null) {
			sql.append(" and log.createdTime BETWEEN ? and ?");
			args.add(startDate);
			args.add(endDate);
		}
		paymentLogDao.closeSession(DBSource.LOG);
		return paymentLogDao.findPage(sql.toString(), args, pageSize, currentPage);
	}
	
	/**
	 * 日志列表
	 * @param startDate
	 * @param endDate
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<PaymentLog> findUserPayHistoryLogList(Date startDate,Date endDate, Integer currentPage, Integer pageSize) {
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("from PaymentLog log ");
		if (startDate != null && endDate != null) {
			sql.append(" where log.createdTime BETWEEN ? and ?");
			args.add(startDate);
			args.add(endDate);
		}
		paymentLogDao.closeSession(DBSource.LOG);
		return paymentLogDao.findPage(sql.toString(), args, pageSize, currentPage);
	}

	public PaymentLogDao getPaymentLogDao() {
		return paymentLogDao;
	}
	public void setPaymentLogDao(PaymentLogDao paymentLogDao) {
		this.paymentLogDao = paymentLogDao;
	}
	
	public List<Object> getPaymentLogList() {
		
		paymentLogDao.closeSession(DBSource.LOG);
		return  paymentLogDao.findSQL_("select user_id, user_name, sum(amount), partner_id, server_id, max(CREATED_TIME)  from payment_log group by user_id order by sum(amount) desc");
		
	}
	
}
