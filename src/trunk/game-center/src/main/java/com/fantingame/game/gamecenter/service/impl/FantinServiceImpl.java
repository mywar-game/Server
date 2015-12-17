package com.fantingame.game.gamecenter.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.gamecenter.bo.UserToken;
import com.fantingame.game.gamecenter.constant.OrderStatus;
import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.constant.ServiceReturnCode;
import com.fantingame.game.gamecenter.constant.WebApiCode;
import com.fantingame.game.gamecenter.dao.PaymentOrderDao;
import com.fantingame.game.gamecenter.dao.ServerListDao;
import com.fantingame.game.gamecenter.exception.ServiceException;
import com.fantingame.game.gamecenter.help.Assert;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.model.PaymentOrder;
import com.fantingame.game.gamecenter.partener.PaymentObj;
import com.fantingame.game.gamecenter.partener.fantin.AuthAPI;
import com.fantingame.game.gamecenter.partener.fantin.FanDinSdk;
import com.fantingame.game.gamecenter.partener.fantin.service.AuthBean;
import com.fantingame.game.gamecenter.partener.fantin.service.EucAPIException;
import com.fantingame.game.gamecenter.partener.fantin.service.EucApiResult;
import com.fantingame.game.gamecenter.partener.fantin.service.JUser;
import com.fantingame.game.gamecenter.partener.fantin.util.TradeInfo;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.BasePartnerService;
import com.fantingame.game.gamecenter.util.IDGenerator;
import com.fantingame.game.gamecenter.util.json.Json;
/**
 * 梵町android service
 * @author Administrator
 *
 */
public class FantinServiceImpl extends BasePartnerService {

	public static Logger LOG = Logger.getLogger(FantinServiceImpl.class);

	@Autowired
	private PaymentOrderDao paymentOrderDao;
	@Autowired
	private ServerListDao serverListDao;
	@Override
	public UserToken login(String token, String partnerId, String serverId, long timestamp, String sign, Map<String, String> params) {
		if (StringUtils.isBlank(token) || StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId) || timestamp == 0 || StringUtils.isBlank(sign)) {
			throw new ServiceException(WebApiCode.PARAM_ERROR, "参数不正确");
		}

		checkSign(token, partnerId, serverId, timestamp, sign);

		try {
			EucApiResult<AuthBean> result = AuthAPI.validate(token, null, null);
			JUser juser = null;
			if (result == null || result.getResult() == null) {
				if (juser == null) {
					// TODO 测试代码
					throw new ServiceException(WebApiCode.LOGIN_VALID_FAILD, "登录验证失败");
				}
			}
			juser = result.getResult().getUser();
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
			return GameApiSdk.getInstance().loadUserToken(juser.getId().toString(), partnerId, serverId, juser.getQn(),gameServer,params);
		} catch (EucAPIException e) {
			throw new ServiceException(WebApiCode.LOGIN_VALID_FAILD, "登录验证失败");
		}

	}

	@Override
	public String createOrder(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName) {
		return this.createOrder(partnerId, serverId, partnerUserId, amount, tradeName, "0");
	}

	@Override
	public String createOrder(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName, String qn) {

		Assert.notEmtpy(partnerId, PARAM_ERROR, "合作商ID不能为空");
		Assert.notEmtpy(serverId, PARAM_ERROR, "服务器ID不能为空");
		Assert.notEmtpy(partnerUserId, PARAM_ERROR, "合作商用户ID不能为空");
		Assert.notEmtpy(amount, PARAM_ERROR, "订单金额不能为空");

		// GameApiSdk.getInstance().loadUserToken(partnerUserId, partnerId,
		// serverId)

		String gameId = "ftgame";

		PaymentOrder paymentOrder = new PaymentOrder();
		// PaymentOrder lastPaymentOrder =
		// this.paymentOrderDao.getLastOrder(gameId, partnerId);
		// int seq = 1;
		// if (lastPaymentOrder != null) {
		// seq = lastPaymentOrder.getSeq() + 1;
		// }
		String orderId = IDGenerator.getID();// PaymentOrderHelper.getOrderId(gameId,
												// partnerId, seq);

		Date now = new Date();

		paymentOrder.setCreatedTime(now);
		paymentOrder.setGameId(gameId);
		paymentOrder.setOrderId(orderId);
		paymentOrder.setPartnerId(partnerId);
		paymentOrder.setPartnerUserId(partnerUserId);
		paymentOrder.setSeq(0);
		paymentOrder.setAmount(amount);
		paymentOrder.setFinishAmount(new BigDecimal(0));
		paymentOrder.setServerId(serverId);
		paymentOrder.setStatus(OrderStatus.STATUS_NEW);
		paymentOrder.setUpdatedTime(now);

		boolean success = this.paymentOrderDao.add(paymentOrder);
		if (!success) {
			throw new ServiceException(ServiceReturnCode.FAILD, "订单创建失败");
		}

		TradeInfo tradeInfo = new TradeInfo();
		tradeInfo.setExectInfo("");
		tradeInfo.setPayerId(partnerUserId);
		tradeInfo.setReqFee(String.valueOf(amount));
		tradeInfo.setTradeDesc(tradeName);
		tradeInfo.setTradeId(orderId);
		tradeInfo.setTradeName(tradeName);
		tradeInfo.setNotifyUrl(FanDinSdk.instance().getPayBackUrl());
		tradeInfo.setAppId(FanDinSdk.instance().getAppId());
		tradeInfo.setPartnerId(FanDinSdk.instance().getFtPartnerId());
		tradeInfo.setQn(qn);
//		String url = ServerBuildUrl.buildPayUrl(tradeInfo);
//		Json.toJson(tradeInfo);
//		LOG.info(url);

		return Json.toJson(tradeInfo);
	}

	@Override
	public boolean doPayment(String orderId, String partnerUserId, boolean success, String partnerOrderId, BigDecimal finishAmount, Map<String, String> reqInfo) {

		PaymentOrder paymentOrder = this.paymentOrderDao.get(orderId);
		if (paymentOrder == null) {
			LOG.error("订单支付回调失败，订单不存在.orderId[" + orderId + "]");
			return false;
		}

		if (paymentOrder.getStatus() != OrderStatus.STATUS_NEW) {
			LOG.error("订单支付回调失败，订单不在可修改状态.orderId[" + orderId + "]");
			return false;
		}

		String extInfo = "";
		if (reqInfo != null) {
			extInfo = Json.toJson(reqInfo);
		}

		if (success) {// 成功
			int gold = (int) (finishAmount.doubleValue() * 10);

			// 更新订单状态
			if (this.paymentOrderDao.updateStatus(orderId, OrderStatus.STATUS_FINISH, partnerOrderId, finishAmount, extInfo)) {
				GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(paymentOrder.getServerId(), paymentOrder.getPartnerId());
				// 请求游戏服，发放游戏货币
				if (!GameApiSdk.getInstance().doPayment(paymentOrder.getPartnerId(), paymentOrder.getServerId(), partnerUserId, "", partnerOrderId, finishAmount, gold, "", "",gameServer)) {
					// 如果失败，要把订单置为未支付
					this.paymentOrderDao.updateStatus(orderId, OrderStatus.STATUS_NOT_PAY, partnerOrderId, finishAmount, extInfo);
				}
			}

		} else {
			this.paymentOrderDao.updateStatus(orderId, OrderStatus.STATUS_ERROR, partnerOrderId, finishAmount, extInfo);
		}

		return true;
	}

	@Override
	public boolean doPayment(PaymentObj paymentObj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public PartenerEnum getPatenerEnum() {
		return PartenerEnum.FanTin;
	}
}
