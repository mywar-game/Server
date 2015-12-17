package com.fantingame.game.gamecenter.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.gamecenter.constant.OrderStatus;
import com.fantingame.game.gamecenter.constant.ServiceReturnCode;
import com.fantingame.game.gamecenter.constant.WebApiCode;
import com.fantingame.game.gamecenter.dao.ActiveCodeDao;
import com.fantingame.game.gamecenter.dao.PartnerRateDao;
import com.fantingame.game.gamecenter.dao.PaymentOrderDao;
import com.fantingame.game.gamecenter.dao.ServerListDao;
import com.fantingame.game.gamecenter.exception.ServiceException;
import com.fantingame.game.gamecenter.help.Assert;
import com.fantingame.game.gamecenter.help.PaymentOrderHelper;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.model.PaymentOrder;
import com.fantingame.game.gamecenter.partener.fantin.util.TradeInfo;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.util.json.Json;

public abstract class BasePartnerService implements PartnerService {
	private static final Logger LOG = Logger.getLogger(BasePartnerService.class);
	
	@Autowired
	protected ActiveCodeDao activeCodeDao;
	@Autowired
	protected PaymentOrderDao paymentOrderDao;	
	@Autowired
	protected ServerListDao serverListDao;
	@Autowired
	protected PartnerRateDao partnerRateDao;

	@Override
	public boolean isActive(String uuid, String partnerId) {
		if (StringUtils.isBlank(uuid) || StringUtils.isBlank(partnerId)) {
			throw new ServiceException(WebApiCode.PARAM_ERROR, "参数不正确");
		}

		return this.activeCodeDao.isActive(uuid, partnerId);
	}

	@Override
	public boolean active(String uuid, String code, String partnerId) {
		if (StringUtils.isBlank(uuid)) {
			throw new ServiceException(WebApiCode.PARAM_ERROR, "参数不正确");
		}

		return this.activeCodeDao.active(uuid, code, partnerId);
	}
	
	@Override
	public boolean addActive(String uuid, String code, String partnerId) {
		if (StringUtils.isBlank(uuid)) {
			throw new ServiceException(WebApiCode.PARAM_ERROR, "参数不正确");
		}

		return this.activeCodeDao.addActive(uuid, code, partnerId);
	}

	@Override
	public String createOrder(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName) {
		TradeInfo tradeInfo = createOrderInfo(partnerId, serverId, partnerUserId, amount, tradeName, "1000");
		return Json.toJson(tradeInfo);
	}

	@Override
	public String createOrder(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName, String qn) {
		TradeInfo tradeInfo = createOrderInfo(partnerId, serverId, partnerUserId, amount, tradeName, qn);
		return Json.toJson(tradeInfo);
	}

	protected TradeInfo createOrderInfo(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName) {
		return createOrderInfo(partnerId, serverId, partnerUserId, amount, tradeName, "1000");
	}

	protected TradeInfo createOrderInfo(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName, String qn) {
		Assert.notEmtpy(partnerId, PARAM_ERROR, "合作商ID不能为空");
		Assert.notEmtpy(serverId, PARAM_ERROR, "服务器ID不能为空");
		Assert.notEmtpy(partnerUserId, PARAM_ERROR, "合作商用户ID不能为空");
		Assert.notEmtpy(amount, PARAM_ERROR, "订单金额不能为空");
		if(partnerUserId.equals("(null)")){
			LOG.error("partenerUserId=(null)======partnerId="+partnerId+",serverId="+serverId+",partnerUserId="+partnerUserId+",amount="+amount);
			throw new ServiceException(ServiceReturnCode.FAILD, "partenerUserId=(null)");
		}
		String gameId = "ftgame";
		PaymentOrder paymentOrder = new PaymentOrder();
		PaymentOrder lastPaymentOrder = this.paymentOrderDao.getLastOrder(gameId, partnerId);
		int seq = 1;
		if (lastPaymentOrder != null) {
			seq = lastPaymentOrder.getSeq() + 1;
		}
		String orderId = PaymentOrderHelper.getOrderId(gameId, partnerId, seq);

		Date now = new Date();

		paymentOrder.setCreatedTime(now);
		paymentOrder.setGameId(gameId);
		paymentOrder.setOrderId(orderId);
		paymentOrder.setPartnerId(partnerId);
		paymentOrder.setPartnerUserId(partnerUserId);
		paymentOrder.setSeq(seq);
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
		tradeInfo.setQn(qn);
		return tradeInfo;
	}

	/**
	 * 比较APK版本号，是否需要升级APK
	 * 
	 * @param apkBigVer
	 * @param apkSmallVer
	 * @param fullVer
	 * @return
	 */
	protected boolean needUpgradeApk(int apkBigVer, int apkSmallVer, String fullVer) {
		String[] verArr = fullVer.split("\\.");
		int apkBigVerTmp = Integer.parseInt(verArr[0]);
		int apkSmallVerTmp = Integer.parseInt(verArr[1]);
		return apkBigVer < apkBigVerTmp || (apkBigVer == apkBigVerTmp && apkSmallVer < apkSmallVerTmp);
	}

	/**
	 * 比较资源包版本号
	 * 
	 * @param resBigVer
	 * @param resSmallVer
	 * @param fullVer
	 * @return
	 */
	protected boolean needUpgradeRes(int resBigVer, int resSmallVer, String fullVer) {
		String[] verArr = fullVer.split("\\.");
		int resBigVerTmp = Integer.parseInt(verArr[2]);
		int resSmallVerTmp = Integer.parseInt(verArr[3]);
		return resBigVer < resBigVerTmp || (resBigVer == resBigVerTmp && resSmallVer < resSmallVerTmp);
	}

	protected void checkSign(String token, String partnerId, String serverId, long timestamp, String sign) {
//		String serverSign = EncryptUtil.getMD5((token + partnerId + serverId + timestamp + Config.ins().getSignKey()));
//		if (!serverSign.toLowerCase().equals(sign.toLowerCase())) {
//			throw new ServiceException(WebApiCode.SIGN_CHECK_ERROR, "签名校验失败");
//		}
	}
	
	/**
	 * 更新订单状态并发放游戏币
	 * 
	 * @param order  订单
	 * @param partnerOrderId  渠道订单号
	 * @param finishAmount   充值金额
	 * @param diamond   发放的钻石
	 * @param extInfo   扩展信息
	 * @return
	 */
	protected boolean updateOrderStatusAndPay(PaymentOrder order, String partnerOrderId, BigDecimal finishAmount, String extInfo) {
		// 更新订单状态
		if (this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_FINISH, partnerOrderId, finishAmount, extInfo)) {
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(order.getServerId(), order.getPartnerId());
			
			// 根据各个渠道获取倍率，默认为十倍
			int rate = partnerRateDao.getRate(order.getPartnerId());
			int diamond = finishAmount.intValue() * 10;
			if (rate != 0)
				diamond = finishAmount.intValue() * rate;
			
			// 请求游戏服，发放游戏货币
			if (!GameApiSdk.getInstance().doPayment(order.getPartnerId(), order.getServerId(), order.getPartnerUserId(), "",
				order.getOrderId(), finishAmount, diamond, "", extInfo, gameServer)) {
				// 如果失败，要把订单置为未支付
				this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_NOT_PAY, partnerOrderId, finishAmount, extInfo);
				return false;
			}
			
			return true;
		}	
		
		this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, partnerOrderId, finishAmount, extInfo);			
		return false;
	}
	
}
