package com.fantingame.game.gamecenter.help;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fantingame.game.gamecenter.partener.fantin.util.ServerBuildUrl;
import com.fantingame.game.gamecenter.partener.fantin.util.TradeInfo;

/**
 * 支付订单帮助类
 * 
 * @author jacky
 * 
 */
public class PaymentOrderHelper {

	private final static SimpleDateFormat ORDER_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 生成订单号 ' [游戏ID-2][合作商ID-4][年月日-8][流水号-8]'
	 * 
	 * @param gameId
	 * @param partnerId
	 * @param seq
	 * @return
	 */
	public static String getOrderId(String gameId, String partnerId, int seq) {

		String gameCode = "01";
		Date date = new Date();
		String dateStr = ORDER_DATE_FORMAT.format(date);

		String sseq = String.valueOf(seq);

		String seqNo = "00000000".substring(0, 8 - sseq.length()) + sseq;

		return gameCode + partnerId + dateStr + seqNo;
	}
	
	/**
	 * 生成支付链接
	 * 
	 * @param amount
	 * @return
	 */
	public static String getPaymentUrl(String orderId, BigDecimal amount, String tradeName, String partnerId, String partnerUserId) {

		TradeInfo tradeInfo = new TradeInfo();
		tradeInfo.setExectInfo("");
		tradeInfo.setPayerId(partnerUserId);
		tradeInfo.setReqFee(String.valueOf(amount));
		tradeInfo.setTradeDesc(tradeName);
		tradeInfo.setTradeId(orderId);
		tradeInfo.setTradeName(tradeName);
		
		/*
		 * TradeInfo testInfo = new TradeInfo();
		 * testInfo.setExectInfo("123456"); testInfo.setPayerId("123456");
		 * testInfo.setReqFee("0.01"); testInfo.setTradeDesc("100宜搜书城币只卖1分钱");
		 * testInfo.setTradeId("1368773664902");// 此处由CP提供用于标识自己的订单ID
		 * testInfo.setTradeName("100宜搜书城币");
		 */

		// return ServerBuildUrl.buildPayUrl(testInfo);
		return ServerBuildUrl.buildPayUrl(tradeInfo);
	}

	public static void main(String[] args) {
		System.out.println(getOrderId("ldsg", "3001", 1));
	}

}
