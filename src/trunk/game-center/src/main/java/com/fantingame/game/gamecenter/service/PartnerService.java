package com.fantingame.game.gamecenter.service;

import java.math.BigDecimal;
import java.util.Map;

import com.fantingame.game.gamecenter.bo.UserToken;
import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.partener.PaymentObj;

public interface PartnerService {
	/**
	 * 成功
	 */
	public static final int SUCCESS = 1000;
	/**
	 * 未知错误
	 */
	public static final int UNKNOWN_ERROR = 2000;
	/**
	 * 参数错误
	 */
	public static final int PARAM_ERROR = 2001;
	/**
	 * 校验签名错误
	 */
	public static final int SIGN_CHECK_ERROR = 2002;
	/**
	 * 登录验证失败
	 */
	public static final int LOGIN_VALID_FAILD = 2003;
	/**
	 * 游戏服务器登录失败
	 */
	public static final int LOGIN_GAME_FAILD = 2004;

	/**
	 * 版本失效，无法升级以及登陆
	 */
	public static final int VERSION_EXPIRE = 2005;

	/**
	 * 服务器关闭
	 */
	public static final int SERVER_CLOSE = 2006;

	/**
	 * 激活码无效
	 */
	public static final int ACTIVE_FAILD = 2005;

	/**
	 * 登录接口： 1、去平台商的UserCenter进行授权校验
	 * 2、校验成功后，如果用户注册过，则创建一个UserToken保存在redis中，否则就先创建用户mapper信息，再创建UserToken
	 * 3、返回token信息
	 * 
	 * @param token
	 *            合作商提供用于校验的token数据
	 * @param partnerId
	 *            合作商ID
	 * @param serverId
	 *            服务器ID
	 * @param timestamp
	 *            时间戳
	 * @param sign
	 *            签名
	 */
	public UserToken login(String token, String partnerId, String serverId, long timestamp, String sign, Map<String, String> params);

	/**
	 * 创建订单
	 * 
	 * @param partnerId
	 * @param serverId
	 * @param partnerUserId
	 * @param amount
	 * @param timestamp
	 * @param sign
	 * @return
	 */
	public String createOrder(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName, String qn);

	/**
	 * 创建订单
	 * 
	 * @param partnerId
	 * @param serverId
	 * @param partnerUserId
	 * @param amount
	 * @param tradeName
	 * @return
	 */
	public String createOrder(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName);

	/**
	 * 完成订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param partnerUserId
	 *            合作商订单ID
	 * @param success
	 *            是否成功
	 * @param partnerOrderId
	 *            合作商用户ID
	 * @param finishAmount
	 *            完成的金额
	 * @param reqInfo
	 *            回调时的所有参数
	 * @return
	 */
	public boolean doPayment(String orderId, String partnerUserId, boolean success, String partnerOrderId, BigDecimal finishAmount, Map<String, String> reqInfo);

	/**
	 * 充值回调时执行
	 * 
	 * @param paymentObj
	 * @return
	 */
	public boolean doPayment(PaymentObj paymentObj);

	/**
	 * 用户是否活
	 * 
	 * @param uuid
	 * @param partnerId
	 * @param timestamp
	 * @param sign
	 * @return
	 */
	boolean isActive(String uuid, String partnerId);

	/**
	 * 用户激活
	 * 
	 * @param uuid
	 * @param code
	 * @param timestamp
	 * @param sign
	 * @return
	 */
	boolean active(String uuid, String code, String partnerId);
	
	boolean addActive(String uuid, String code, String partnerId);
	
	/**
	 * 获取合作商枚举
	 * @return
	 */
	public PartenerEnum getPatenerEnum();

}
