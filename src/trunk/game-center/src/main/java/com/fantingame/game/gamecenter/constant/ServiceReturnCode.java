package com.fantingame.game.gamecenter.constant;

/**
 * 业务状态码，通用的状态码在此定义，非通用的在业务接口中定义，并且在2000 - 2999之间 成功类的状态码定义为1000 - 1999之间
 * 通用失败的状态码定义在3000以后
 * 
 * @author CJ
 * 
 */
public class ServiceReturnCode {

	/**
	 * 成功
	 */
	public final static int SUCCESS = 1000;

	/**
	 * 失败
	 */
	public final static int FAILD = 3000;

	/**
	 * 参数不正确
	 */
	public final static int PARAM_ERROR = 3001;

	/**
	 * 用户未登录
	 */
	public final static int IS_NOT_LOGIN = 3002;

	/**
	 * 被踢下线
	 */
	public final static int KICKOUT = 3003;

	/**
	 * 校验签名失败
	 */
	public final static int SIGN_CHECK_ERROR = 3004;

	/**
	 * 武将背包超出限制
	 */
	public final static int USER_HERO_BAG_LIMIT_EXCEED = 2101;

	/**
	 * 装备背包超出限制
	 */
	public final static int USER_EQUIP_BAG_LIMIT_EXCEED = 2102;

	/**
	 * 活动未开放
	 */
	public final static int ACTIVITY_NOT_OPEN_EXCTPION = 2103;
	
	/**
	 * 道具背包超出限制
	 */
	public final static int USER_TOOL_BAG_LIMIT_EXCEED = 2104;
	/**
	 *  没有奖励
	 */
	public final static int NOT_AWARD = 4000;
	
	/**
	 * 奖励以领取
	 */
	public final static int AWARD_RECEIVE = 4001;
	
	/**
	 * partnerId 非法
	 * 这个 partnerId 不能调用这个接口
	 */
	public final static int PARTNER_ID_ILLEGAL = 5000;
	
	/**
	 * 没有这个 messge 类型
	 * 在 MessageType 中没有找到对应的类型
	 */
	public final static int NO_MESSAGE_TYPE_FOUND = 6000;
}
