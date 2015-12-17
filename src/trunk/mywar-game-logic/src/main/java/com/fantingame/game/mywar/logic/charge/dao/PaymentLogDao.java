package com.fantingame.game.mywar.logic.charge.dao;


import java.util.Date;
import java.util.List;

import com.fantingame.game.mywar.logic.charge.model.PaymentLog;


/**
 * 
 * @author jacky
 * 
 */
public interface PaymentLogDao {

	/**
	 * 添加充值记录
	 * 
	 * @param paymentLog
	 */
	public void add(PaymentLog paymentLog);

	/**
	 * 获取充值总数
	 * 
	 * @param userId
	 * @return
	 */
	public int getPaymentTotal(String userId);

	/**
	 * 获取充值金币总数
	 * 
	 * @param userId
	 * @return
	 */
	public int getPaymentTotalGold(String userId);

	/**
	 * 根据时间及金额区间获取用户订单
	 * 
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @param payMoney
	 * @param nextPayMoney
	 * @return
	 */
	public List<PaymentLog> getPaymenList(String userId, Date startTime, Date endTime, int payMoney, int nextPayMoney);

	/**
	 * 获取用户某段时间内的充值总金额
	 * 
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getPaymentTotalByTime(String userId, Date startTime, Date endTime);

	/**
	 * 获取大于或等于payMoney金额的订单
	 * 
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @param payMoney
	 * @return
	 */
	List<PaymentLog> getPaymenList(String userId, Date startTime, Date endTime, int payMoney);
	/**
	 * 获取指定时间内 不重复的充值天数
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<PaymentLog> getPaymentList(String userId, Date startTime, Date endTime);
	
	/**
	 * 获取时间段内充过值的
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<PaymentLog> getPaymentLogList(Date startTime, Date endTime);
}
