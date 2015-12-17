package com.fantingame.game.gamecenter.service.impl.yxcq;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.gamecenter.bo.UserToken;
import com.fantingame.game.gamecenter.constant.OrderStatus;
import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.dao.PaymentOrderDao;
import com.fantingame.game.gamecenter.dao.ServerListDao;
import com.fantingame.game.gamecenter.exception.ServiceException;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.model.PaymentOrder;
import com.fantingame.game.gamecenter.partener.PaymentObj;
import com.fantingame.game.gamecenter.partener.fantin.util.TradeInfo;
import com.fantingame.game.gamecenter.partener.yxcq.tengxun.TengxunPayParamsObj;
import com.fantingame.game.gamecenter.partener.yxcq.tengxun.YXCQTengxunSdk;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.BasePartnerService;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 腾讯
 * @author Administrator
 *
 */
public class YXCQTengxunServiceImpl extends BasePartnerService {
	
	private final static Logger logger = Logger.getLogger(YXCQTengxunServiceImpl.class);

	@Autowired
	private PaymentOrderDao paymentOrderDao;
	@Autowired
	private ServerListDao serverListDao;
	
	@Override
	public UserToken login(String token, String partnerId, String serverId, long timestamp, String sign, Map<String, String> params) {
		if (StringUtils.isBlank(token) || StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId) || timestamp == 0 || StringUtils.isBlank(sign)) {
			throw new ServiceException(PartnerService.PARAM_ERROR, "参数不正确");
		}
		checkSign(token, partnerId, serverId, timestamp, sign);

		try {
			String ip = params.get("ip");
			logger.info("yxcqTengxun token:" + token + " ip:" + ip);
			boolean flag = YXCQTengxunSdk.instance().verfiyLogin(token, ip);
			if (flag) {
				GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
				String[] tokenArr = token.split(":");
				UserToken userToken = GameApiSdk.getInstance().loadUserToken(tokenArr[1], partnerId, serverId, "0", gameServer, params);
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
		return false;
	}

//	@Override
//	public boolean doPayment(PaymentObj paymentObj) {
//		if (paymentObj == null) {
//			logger.error("paymentObj为空");
//			return false;
//		}
//		TengxunPayObj cb = (TengxunPayObj) paymentObj;
//
//		String channel_id = cb.getChannel_id();
//		String[] tempChannelIds = channel_id.split("-");
//		String orderId = "";
//		for (String s : tempChannelIds) {
//			if (s.contains("myor:")) {
//				orderId = s.split(":")[1];
//				break;
//			}
//		}
//		PaymentOrder order = paymentOrderDao.get(orderId); // 封装订单号
//
//		logger.info("应用订单：" + Json.toJson(order));
//		if (order == null) {
//			logger.error("订单为空：" + Json.toJson(cb));
//			return false;
//		}
//
//		if (order.getStatus() == OrderStatus.STATUS_FINISH) {
//			logger.info("订单已经完成" + Json.toJson(cb));
//			return true;
//		}
//		int payMoney = 0;
//		if (cb.getPaychannel().equals("qqpoint")) {
//			payMoney = Integer.valueOf(cb.getPayitem()) / 10;
//		} else {
//			
//		}
//
//		BigDecimal finishAmount = new BigDecimal(payMoney);
//
//		if (YXCQTengxunSdk.instance().verifyPay(cb) == false) {
//			logger.error("充值失败：" + Json.toJson(cb));
//			this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, cb.getBillno(), finishAmount, "");
//			return false;
//		}
//
//		int gold = (int) (finishAmount.doubleValue() * 10);
//		// 更新订单状态
//		if (this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_FINISH, cb.getBillno(), finishAmount, null)) {
//			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(order.getServerId(), order.getPartnerId());
//			// 请求游戏服，发放游戏货币
//			if (!GameApiSdk.getInstance().doPayment(order.getPartnerId(), order.getServerId(), order.getPartnerUserId(), "", order.getOrderId(), finishAmount, gold, "", "", gameServer)) {
//				// 如果失败，要把订单置为未支付
//				this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_NOT_PAY, cb.getBillno(), finishAmount, null);
//				logger.error("发货失败：" + Json.toJson(cb));
//				return false;
//			} else {
//				logger.info("支付成功：" + Json.toJson(cb));
//				return true;
//			}
//		}
//		this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, cb.getBillno(), finishAmount, "");
//		logger.error("充值失败：" + Json.toJson(cb));
//		return false;
//	}
	
	/**
	 * 参数变化频繁，不能把参数写死
	 */
	@Override
	public boolean doPayment(PaymentObj paymentObj) {
		if (paymentObj == null) {
			logger.error("paymentObj为空");
			return false;
		}
		TengxunPayParamsObj cb = (TengxunPayParamsObj) paymentObj;

		String orderId = cb.getParams().get("order");

		PaymentOrder order = paymentOrderDao.get(orderId); // 封装订单号

		logger.info("应用订单：" + Json.toJson(order));
		if (order == null) {
			logger.error("订单为空：" + Json.toJson(cb));
			return false;
		}

		if (order.getStatus() == OrderStatus.STATUS_FINISH) {
			logger.info("订单已经完成" + Json.toJson(cb));
			return true;
		}

		double payMoney = Double.valueOf((String) cb.getParams().get("money"));

		BigDecimal finishAmount = new BigDecimal(payMoney);

		int gold = (int) (finishAmount.doubleValue() * 10);
		logger.info("YXCQTengxunServiceImpl 订单状态: " + order.getOrderId() + " " + OrderStatus.STATUS_FINISH + " " + orderId + " " + finishAmount + " " + null);
		// 更新订单状态
		if (this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_FINISH, orderId, finishAmount, null)) {
			logger.info("YXCQTengxunServiceImpl updateStatus");
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(order.getServerId(), order.getPartnerId());
			// 请求游戏服，发放游戏货币
			if (!GameApiSdk.getInstance().doPayment(order.getPartnerId(), order.getServerId(), order.getPartnerUserId(), "", order.getOrderId(), finishAmount, gold, "", "", gameServer)) {
				// 如果失败，要把订单置为未支付
				this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_NOT_PAY, orderId, finishAmount, null);
				logger.error("发货失败：" + Json.toJson(cb));
				return false;
			} else {
				logger.info("支付成功：" + Json.toJson(cb));
				return true;
			}
		}
		this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, orderId, finishAmount, "");
		logger.error("充值失败 updateStatus：" + Json.toJson(cb));
		return false;
	}

	@Override
	public String createOrder(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId, amount, tradeName);
		logger.info("YXCQTengxunService createOrder");
		info.setNotifyUrl("http://wapi.andr.hw.fantingame.com:80/webApi/yxcqTengxunPayment.do");
		//info.setNotifyUrl("http://203.195.190.121:8089/webApi/yxcqTengxunPayment.do");
		info.setReqFee(Integer.toString(new BigDecimal(100).multiply(new BigDecimal(info.getReqFee())).intValue()));
		return Json.toJson(info);
	}
	
	@Override
	public String createOrder(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName, String qn) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId, amount, tradeName, qn);
		logger.info("YXCQTengxunService createOrder");
		info.setNotifyUrl("http://wapi.andr.hw.fantingame.com:80/webApi/yxcqTengxunPayment.do");
		//info.setNotifyUrl("http://203.195.190.121:8089/webApi/yxcqTengxunPayment.do");
		info.setReqFee(Integer.toString(new BigDecimal(100).multiply(new BigDecimal(info.getReqFee())).intValue()));
		return Json.toJson(info);
	}

	@Override
	public PartenerEnum getPatenerEnum() {
		return PartenerEnum.YXCQ_Tengxun;
	}
}
