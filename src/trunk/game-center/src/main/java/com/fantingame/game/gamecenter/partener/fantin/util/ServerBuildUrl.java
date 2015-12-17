package com.fantingame.game.gamecenter.partener.fantin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.fantingame.game.gamecenter.partener.fantin.service.ClientConfig;
import com.fantingame.game.gamecenter.partener.fantin.service.Md5SignUtil;

/**
 * 该类为示例类,供服务端拼接URL时参考,也可复用
 * 
 * @author ted
 * @since 2013-05-16
 * @version 1.0.0
 */
public class ServerBuildUrl {

	public static String buildPayUrl(TradeInfo info) {
		String bodyUrl = "";
		try {
			bodyUrl = "appId=" + URLEncoder.encode(info.getAppId(), "UTF-8") + "&partnerId=" + URLEncoder.encode(info.getPartenerId(), "UTF-8") + "&tradeId=" + URLEncoder.encode(info.getTradeId(), "UTF-8")
					+ "&tradeName=" + URLEncoder.encode(info.getTradeName(), "UTF-8") + "&tradeDesc=" + URLEncoder.encode(info.getTradeDesc(), "UTF-8") + "&reqFee=" + URLEncoder.encode(info.getReqFee(), "UTF-8")
					+ "&notifyUrl=" + URLEncoder.encode(info.getNotifyUrl(), "UTF-8") + "&redirectUrl=" + URLEncoder.encode(info.getRedirectUrl(), "UTF-8") + "&sign=" + URLEncoder.encode(getSign(info), "UTF-8")
					+ "&separable=" + URLEncoder.encode(info.getSeparable(), "UTF-8") + "&payerId=" + URLEncoder.encode(info.getPayerId(), "UTF-8") + "&qn=" + URLEncoder.encode(info.getQn(), "UTF-8") + "&extInfo="
					+ URLEncoder.encode(info.getExectInfo(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bodyUrl;
	}

	/**
	 * 取得签名内容
	 * 
	 * @param info
	 * @return
	 */
	private static String getSign(TradeInfo info) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", info.getAppId());
		map.put("partnerId", info.getPartenerId());
		map.put("tradeId", info.getTradeId());
		map.put("tradeName", info.getTradeName());
		map.put("tradeDesc", info.getTradeDesc());
		map.put("reqFee", info.getReqFee());
		map.put("notifyUrl", info.getNotifyUrl());
		map.put("separable", info.getSeparable());
		map.put("payerId", info.getPayerId());
		map.put("qn", info.getQn());
		map.put("redirectUrl", info.getRedirectUrl());

		String temp = getStringForSign(map);
		return md5(temp, ClientConfig.getProperty("secertKey"));
	}

	/**
	 * 获取待签名字符串
	 * 
	 * @param map
	 * @return
	 */
	public static String getStringForSign(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		TreeMap<String, String> treeMap = new TreeMap<String, String>(map);
		if (treeMap != null) {
			for (Map.Entry<String, String> entity : treeMap.entrySet()) {
				if (entity.getKey() != null && entity.getValue() != null) {
					sb.append(entity.getKey()).append("=").append(String.valueOf(entity.getValue())).append("&");
				}
			}
		}
		if (sb.length() > 0) {// 删除最后的&符
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 对字符穿进行MD5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str, String key) {
		if (str == null)
			return "";
		String sec = Md5SignUtil.digest(str + key);
		return sec;
	}
}
