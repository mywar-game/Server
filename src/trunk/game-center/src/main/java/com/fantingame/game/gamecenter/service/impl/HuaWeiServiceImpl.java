package com.fantingame.game.gamecenter.service.impl;

import java.math.BigDecimal;
import java.util.Map;

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
import com.fantingame.game.gamecenter.partener.huawei.HuaWeiPaymentObj;
import com.fantingame.game.gamecenter.partener.huawei.HuaWeiSdk;
import com.fantingame.game.gamecenter.partener.huawei.HwUserInfo;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.BasePartnerService;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 华为
 * 
 * @author yezp
 */
public class HuaWeiServiceImpl extends BasePartnerService {
	
	private static final Logger logger = Logger.getLogger(HuaWeiServiceImpl.class);
	@Autowired
	private ServerListDao serverListDao;

	@Override
	public UserToken login(String token, String partnerId, String serverId,
			long timestamp, String sign, Map<String, String> params) {		
		try {
			checkSign(token, partnerId, serverId, timestamp, sign);
			
			HwUserInfo userInfo = HuaWeiSdk.instance().getUid(token, timestamp);			
			if (userInfo != null) {
				GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
				UserToken userToken = GameApiSdk.getInstance().loadUserToken(userInfo.getUserID(), partnerId, serverId, "0",gameServer, params);
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
		
		HuaWeiPaymentObj pb = (HuaWeiPaymentObj) paymentObj;
//		if (!HuaWeiSdk.instance().checkPayCallbackSign(pb)) {
//			logger.error("签名不正确" + Json.toJson(paymentObj));
//			return false;
//		}
		
		logger.info("game id:" + pb.getRequestId());
		PaymentOrder order = paymentOrderDao.get(pb.getRequestId());

		logger.info("应用订单：" + Json.toJson(order));
		if (order == null) {
			logger.error("订单为空：" + Json.toJson(pb.getRequestId()));
			return false;
		}

		if (order.getStatus() == OrderStatus.STATUS_FINISH) {
			logger.error("订单已经完成" + Json.toJson(pb));
			return true;
		}
		// 以分为单位
		BigDecimal finishAmount = new BigDecimal(pb.getAmount());
//		if (order.getAmount().compareTo(finishAmount) != 0) {
//			logger.error("订单金额不符：" + Json.toJson(pb));
//			return false;
//		}

		if (!pb.getResult().equals("0")) {
			logger.error("充值失败：" + Json.toJson(pb));
			this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, pb.getOrderId(), finishAmount, "");
			return true;
		}

		int gold = (int) (finishAmount.doubleValue() * 10);
		// 更新订单状态
		if (this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_FINISH, pb.getOrderId(), finishAmount, "")) {
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(order.getServerId(), order.getPartnerId());
			// 请求游戏服，发放游戏货币
			if (!GameApiSdk.getInstance().doPayment(order.getPartnerId(), order.getServerId(), order.getPartnerUserId(), "", order.getOrderId(), finishAmount, gold, "", "",gameServer)) {
				// 如果失败，要把订单置为未支付
				this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_NOT_PAY, pb.getOrderId(), finishAmount, "");
				logger.error("发货失败：" + Json.toJson(pb));
				return false;
			} else {
				logger.info("支付成功：" + Json.toJson(pb));
				return true;
			}
		}
		this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, pb.getOrderId(), finishAmount, "");
		logger.error("充值失败：" + Json.toJson(pb));
		return false;
	}

	@Override
	public String createOrder(String partnerId, String serverId,
			String partnerUserId, BigDecimal amount, String tradeName) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId,
				amount, tradeName);
		info.setNotifyUrl("http://wapi.andr.hw.fantingame.com:80/webApi/huaWeiPayment.do");
		return Json.toJson(info);
	}
	
	@Override
	public String createOrder(String partnerId, String serverId,
			String partnerUserId, BigDecimal amount, String tradeName, String qn) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId,
				amount, tradeName, qn);
		info.setNotifyUrl("http://wapi.andr.hw.fantingame.com:80/webApi/huaWeiPayment.do");
		return Json.toJson(info);
	}
	
	@Override
	public PartenerEnum getPatenerEnum() {
		return PartenerEnum.huaWei;
	}
	
//	public static void main(String[] args) {
//		String token = "BlRQ13jTe9J2OaxCY72i+XocH8Tu3nDomY4PC+EkPcUfSiZs08Hw75qudsFMFA==";
//		String partnerId = "1025";
//		String serverId = "s5";
//		long timestamp = 1406809420269l;
//		String sign = "6c7388a810273fa15d1dfc75c1aeea94";
//		
//		HuaWeiServiceImpl i = new HuaWeiServiceImpl();
//		i.checkSign(token, partnerId, serverId, timestamp, sign);
//	}

}
