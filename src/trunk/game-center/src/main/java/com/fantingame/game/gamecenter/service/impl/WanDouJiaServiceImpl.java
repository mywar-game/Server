package com.fantingame.game.gamecenter.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.fantingame.game.gamecenter.bo.UserToken;
import com.fantingame.game.gamecenter.constant.OrderStatus;
import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.exception.ServiceException;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.model.PaymentOrder;
import com.fantingame.game.gamecenter.partener.PaymentObj;
import com.fantingame.game.gamecenter.partener.wandoujia.WDJPaymentObj;
import com.fantingame.game.gamecenter.partener.wandoujia.WanDouJiaSdk;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.BasePartnerService;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 豌豆荚
 * 
 * @author yezp
 */
public class WanDouJiaServiceImpl extends BasePartnerService {
	
	private static final Logger logger = Logger.getLogger(WanDouJiaServiceImpl.class);

	@Override
	public UserToken login(String token, String partnerId, String serverId,
			long timestamp, String sign, Map<String, String> params) {
		if (StringUtils.isBlank(token) || StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId) || timestamp == 0 || StringUtils.isBlank(sign)) {
			throw new ServiceException(PartnerService.PARAM_ERROR, "参数不正确");
		}

		checkSign(token, partnerId, serverId, timestamp, sign);
		try {
			String[] tokenArr = token.split(":");
			
			String uid = tokenArr[0];
			String tokens = tokenArr[1];
			if (WanDouJiaSdk.instance().verifySession(uid, tokens)) {
				GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
				UserToken userToken = GameApiSdk.getInstance().loadUserToken(uid, partnerId, serverId, "0",gameServer,params);
				return userToken;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "登录验证失败");
		}

		throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "登录验证失败");
	}

	@Override
	public boolean doPayment(String orderId, String partnerUserId,
			boolean success, String partnerOrderId, BigDecimal finishAmount,
			Map<String, String> reqInfo) {

		return false;
	}

	@Override
	public boolean doPayment(PaymentObj paymentObj) {
		if (paymentObj == null) {
			logger.error("paymentObj为空");
			return false;
		}

		WDJPaymentObj pb = (WDJPaymentObj) paymentObj;
		logger.info("order id:" + pb.getOut_trade_no());
		PaymentOrder order = paymentOrderDao.get(pb.getOut_trade_no());		
		//单位是分
		BigDecimal finishAmount = new BigDecimal(pb.getMoney()).divide(new BigDecimal(100));
		if (!WanDouJiaSdk.instance().checkPayCallbackSign(pb)) {
			logger.error("签名不正确" + Json.toJson(paymentObj));
			this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR,
							pb.getOrderId(), finishAmount, "");
			return false;
		}

		logger.info("应用订单：" + Json.toJson(order));
		if (order == null) {
			logger.error("订单为空：" + Json.toJson(pb));
			return false;
		}		

		if (order.getStatus() == OrderStatus.STATUS_FINISH) {
			logger.info("订单已经完成" + Json.toJson(pb));
			return true;
		}
		
		// 更新订单号，发放游戏币
		boolean success = updateOrderStatusAndPay(order, pb.getOrderId(), finishAmount, "");
		if (success) {
			logger.info("支付成功：" + Json.toJson(pb));
		} else {
			logger.error("发货失败：" + Json.toJson(pb));
		}
		return false;
	}

	@Override
	public PartenerEnum getPatenerEnum() {
		return PartenerEnum.WanDouJia;
	}

}
