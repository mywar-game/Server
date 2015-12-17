package com.fantingame.game.gamecenter.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.gamecenter.bo.UserToken;
import com.fantingame.game.gamecenter.constant.OrderStatus;
import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.dao.ServerListDao;
import com.fantingame.game.gamecenter.exception.ServiceException;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.model.PaymentOrder;
import com.fantingame.game.gamecenter.partener.PaymentObj;
import com.fantingame.game.gamecenter.partener.fantin.util.TradeInfo;
import com.fantingame.game.gamecenter.partener.xiaomi.XiaomiPaymentObj;
import com.fantingame.game.gamecenter.partener.xiaomi.XiaomiPaymentObjNew;
import com.fantingame.game.gamecenter.partener.xiaomi.XiaomiSdk;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.BasePartnerService;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

public class XiaomiServiceImpl extends BasePartnerService {
	private static final Logger logger = Logger.getLogger(XiaomiServiceImpl.class);
	@Autowired
	private ServerListDao serverListDao;
	@Override
	public UserToken login(String token, String partnerId, String serverId, long timestamp, String sign, Map<String, String> params) {
		if (StringUtils.isBlank(token) || StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId) || timestamp == 0 || StringUtils.isBlank(sign)) {
			throw new ServiceException(PartnerService.PARAM_ERROR, "参数不正确");
		}

		checkSign(token, partnerId, serverId, timestamp, sign);

		try {
			String[] tokenArr = token.split(":");
			String uid = tokenArr[0];
			String session = tokenArr[1];

			if (XiaomiSdk.instance().verifySession(session, uid)) {
				GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
				UserToken userToken = GameApiSdk.getInstance().loadUserToken(uid, partnerId, serverId, "0",gameServer, params);
				return userToken;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "登录验证失败");
		}

		throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "登录验证失败");
	}

	@Override
	public boolean doPayment(String orderId, String partnerUserId, boolean success, String partnerOrderId, BigDecimal finishAmount, Map<String, String> reqInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doPayment(PaymentObj paymentObj) {
		if (paymentObj == null) {
			logger.error("paymentObj为空");
			return false;
		}

		if (paymentObj.getClass().isAssignableFrom(XiaomiPaymentObjNew.class)) {
			// 使用了礼券
			logger.info("使用了小米礼券");
			XiaomiPaymentObjNew cb = (XiaomiPaymentObjNew) paymentObj;
			PaymentOrder order = paymentOrderDao.get(cb.getCpOrderId());

			logger.info("应用订单：" + Json.toJson(order));
			if (order == null) {
				logger.error("订单为空：" + Json.toJson(cb));
				return false;
			}

			if (order.getStatus() == OrderStatus.STATUS_FINISH) {
				logger.info("订单已经完成" + Json.toJson(cb));
				return true;
			}
			BigDecimal finishAmount = new BigDecimal(cb.getPayFee()).divide(new BigDecimal(100));

			if (!cb.getOrderStatus().equals("TRADE_SUCCESS")) {
				logger.error("充值失败：" + Json.toJson(cb));
				this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, cb.getOrderId(), finishAmount, "");
				return true;
			}
			String extInfo = cb.getCpUserInfo();
			
			// 更新订单号，发放游戏币
			boolean success = updateOrderStatusAndPay(order, cb.getOrderId(), finishAmount, extInfo);
			if (success) {
				logger.info("支付成功：" + Json.toJson(cb));
			} else {
				logger.error("发货失败：" + Json.toJson(cb));
			}
			return false;			
		} 
		
		// 没有使用礼券
		XiaomiPaymentObj cb = (XiaomiPaymentObj) paymentObj;
		PaymentOrder order = paymentOrderDao.get(cb.getCpOrderId());

		logger.info("应用订单：" + Json.toJson(order));
		if (order == null) {
			logger.error("订单为空：" + Json.toJson(cb));
			return false;
		}

		if (order.getStatus() == OrderStatus.STATUS_FINISH) {
			logger.info("订单已经完成" + Json.toJson(cb));
			return true;
		}

		BigDecimal finishAmount = new BigDecimal(cb.getPayFee()).divide(new BigDecimal(100));

		if (!cb.getOrderStatus().equals("TRADE_SUCCESS")) {
			logger.error("充值失败：" + Json.toJson(cb));
			this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, cb.getOrderId(), finishAmount, "");
			return true;
		}
		String extInfo = cb.getCpUserInfo();
		
		// 更新订单号，发放游戏币
		boolean success = updateOrderStatusAndPay(order, cb.getOrderId(), finishAmount, extInfo);
		if (success) {
			logger.info("支付成功：" + Json.toJson(cb));
		} else {
			logger.error("发货失败：" + Json.toJson(cb));
		}
		return false;
	}

	@Override
	public String createOrder(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId, amount, tradeName);
		// 以分为单位
		// info.setReqFee(Integer.toString(new BigDecimal(100).multiply(new
		// BigDecimal(info.getReqFee())).intValue()));
		return Json.toJson(info);
	}
	@Override
	public PartenerEnum getPatenerEnum() {
		return PartenerEnum.XiaoMi;
	}
}
