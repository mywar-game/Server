package com.fantingame.game.gamecenter.dao;


import java.math.BigDecimal;
import java.util.List;

import com.fantingame.game.gamecenter.model.PaymentOrder;

/**
 * 支付订单dao
 * 
 * @author jacky
 * 
 */
public interface PaymentOrderDao {

	/**
	 * 增加订单
	 * 
	 * @param paymentOrder
	 * @return
	 */
	public boolean add(PaymentOrder paymentOrder);

	/**
	 * 获取指定游戏，指定合作商的最扣一个订单
	 * 
	 * @param gameId
	 * @param partnerId
	 * @return
	 */
	public PaymentOrder getLastOrder(String gameId, String partnerId);

	/**
	 * 获取订单
	 * 
	 * @param orderId
	 */
	public PaymentOrder get(String orderId);

	/**
	 * 更新订单状态
	 * 
	 * @param orderId
	 *            订单ID
	 * @param status
	 *            状态(0 新建, 1 完成 2, 错误)
	 * @param partnerOrderId
	 *            合作商订单ID
	 * @return
	 */
	public boolean updateStatus(String orderId, int status, String partnerOrderId, BigDecimal finishAmount, String extInfo);
	
	/**
	 * 判断苹果字符串是否已经有成功的订单
	 * @param extInfo
	 * @return
	 */
	public boolean countOrderByExtInfo(String extInfo);

	/**
	 * 根据partnerUserId查询
	 * @param partnerUserId
	 * @return
	 */
	public List<PaymentOrder> getByPartnerUserId(String partnerUserId);
}
