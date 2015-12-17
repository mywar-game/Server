package com.fantingame.game.gamecenter.service.impl;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.fantingame.game.gamecenter.bo.UserToken;
import com.fantingame.game.gamecenter.constant.OrderStatus;
import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.dao.ServerListDao;
import com.fantingame.game.gamecenter.exception.ServiceException;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.model.PaymentOrder;
import com.fantingame.game.gamecenter.partener.PaymentObj;
import com.fantingame.game.gamecenter.partener.fantin.util.TradeInfo;
import com.fantingame.game.gamecenter.partener.google.Base64;
import com.fantingame.game.gamecenter.partener.google.GooGlePlaySdk;
import com.fantingame.game.gamecenter.partener.google.GooglePaymentObj;
import com.fantingame.game.gamecenter.partener.google.RSAHelper;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.BasePartnerService;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

public class GooGlePlayServiceImpl extends BasePartnerService {
	private static final Logger logger = Logger.getLogger(GooGlePlayServiceImpl.class);
	@Autowired
	private ServerListDao serverListDao;
	@Override
	public UserToken login(String token, String partnerId, String serverId, long timestamp, String sign, Map<String, String> params) {
		if (StringUtils.isBlank(token) || StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId) || timestamp == 0 || StringUtils.isBlank(sign)) {
			throw new ServiceException(PartnerService.PARAM_ERROR, "参数不正确");
		}

		checkSign(token, partnerId, serverId, timestamp, sign);

		try {
			if (GooGlePlaySdk.instance().verifySession(token)) {
				GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
				UserToken userToken = GameApiSdk.getInstance().loadUserToken(token, partnerId, serverId, "0",gameServer, params);
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
		GooglePaymentObj cb = (GooglePaymentObj) paymentObj;
		JSONObject jsonObject = GooGlePlaySdk.instance().checkPayCallbackSign(cb);
		if (jsonObject == null) {
			logger.error("签名不正确" + Json.toJson(paymentObj));
			return false;
		}
		logger.info("game id:" +jsonObject.getString("developerPayload"));
		String orderId = null;
		try {
			PrivateKey privateKey = RSAHelper.getPrivateKey(GooGlePlaySdk.instance().getPrivateKey());
			//加解密类
	        Cipher cipher = Cipher.getInstance("RSA");
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[]deBytes = cipher.doFinal(Base64.decode(jsonObject.getString("developerPayload")));
            orderId = new String(deBytes);
            logger.info("game decode id:"+orderId);
		} catch (Exception e) {
			logger.error("decode error!",e);
		}
		PaymentOrder order = paymentOrderDao.get(orderId);

		logger.info("应用订单：" + Json.toJson(order));
		if (order == null) {
			logger.error("订单为空：" + Json.toJson(cb));
			return false;
		}

		if (order.getStatus() == OrderStatus.STATUS_FINISH) {
			logger.error("订单已经完成" + Json.toJson(cb));
			return true;
		}
		// 以分为单位
		BigDecimal finishAmount = order.getAmount();
		if (!"0".equals(jsonObject.getString("purchaseState"))) {
			logger.error("充值失败：" + Json.toJson(cb));
			this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, order.getOrderId(), finishAmount, "");
			return false;
		}

		int gold = (int) (finishAmount.doubleValue() * 10);
		// 更新订单状态
		if (this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_FINISH,jsonObject.getString("orderId"), finishAmount, "")) {
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(order.getServerId(), order.getPartnerId());
			// 请求游戏服，发放游戏货币
			if (!GameApiSdk.getInstance().doPayment(order.getPartnerId(), order.getServerId(), order.getPartnerUserId(), "", order.getOrderId(), finishAmount, gold, "", "",gameServer)) {
				// 如果失败，要把订单置为未支付
				this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_NOT_PAY, jsonObject.getString("orderId"), finishAmount, "");
				logger.error("发货失败：" + Json.toJson(cb));
				return false;
			} else {
				logger.info("支付成功：" + Json.toJson(cb));
				return true;
			}
		}
		this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, jsonObject.getString("orderId"), finishAmount, "");
		logger.error("充值失败：" + Json.toJson(cb));
		return false;
	}
	
	
	@Override
	public String createOrder(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName, String qn) {
		qn = "tkp_money"+qn;
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId, amount, tradeName,qn);
		try {
			PublicKey publicKey = RSAHelper.getPublicKey(GooGlePlaySdk.instance().getPublicKey());
			//加解密类
            Cipher cipher = Cipher.getInstance("RSA");//Cipher.getInstance("RSA/ECB/PKCS1Padding");
            //明文
            byte[] plainText = info.getTradeId().getBytes();
            //加密
           cipher.init(Cipher.ENCRYPT_MODE, publicKey);
           byte[] enBytes = cipher.doFinal(plainText);
           info.setTradeId(Base64.encode(enBytes));
		} catch (Exception e) {
			logger.error("encode error!",e);
		}
		return Json.toJson(info);
	}
	@Override
	public PartenerEnum getPatenerEnum() {
		return PartenerEnum.GooglePlay;
	}
}
