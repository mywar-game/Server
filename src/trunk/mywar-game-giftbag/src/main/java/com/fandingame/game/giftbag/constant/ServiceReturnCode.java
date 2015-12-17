package com.fandingame.game.giftbag.constant;

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
	 * 校验签名失败
	 */
	public final static int SIGN_CHECK_ERROR = 3004;

	/**
	 * 不存在改礼包码
	 */
	public final static int HAS_NOT_THIS_CODE = 2001;
	
	/**
	 * 已经被领取
	 */
	public final static int REWARD_HAS_RECEIVED = 2002;
	
	/**
	 * 礼包未生效
	 */
	public final static int GIFTBAG_NOT_IN_EFFECTIVE_TIME = 2003;
	
	/**
	 * 该礼包码对该服无效
	 */
	public final static int GIFTCODE_NOT_IN_SERVER = 2004;
	
	/**
	 * 等级不足
	 */
	public final static int LEVEL_NOT_ENOUGH = 2005;
	
	/**
	 * vip等级不足
	 */
	public final static int VIP_LEVEL_NOT_ENOUGH = 2006;
	
	/**
	 *  没有奖励
	 */
	public final static int NOT_AWARD = 4000;
	
	
}
