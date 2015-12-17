package com.fantingame.game.gamecenter.constant;

/**
 * 订单状态
 * 
 * @author jacky
 * 
 */
public class OrderStatus {

	/**
	 * 新订单
	 */
	public final static int STATUS_NEW = 0;

	/**
	 * 成功订单
	 */
	public final static int STATUS_FINISH = 1;

	/**
	 * 失败订单
	 */
	public final static int STATUS_ERROR = 3;

	/**
	 * 已经完成，金币未支付
	 */
	public final static int STATUS_NOT_PAY = 4;
}
