package com.fantingame.game.gamecenter.partener.fantin;

import java.util.HashMap;
import java.util.Map;

import com.fantingame.game.gamecenter.partener.fantin.httpclient.EucHttpClient;
import com.fantingame.game.gamecenter.partener.fantin.service.ClientConfig;
import com.fantingame.game.gamecenter.partener.fantin.service.Md5SignUtil;
import com.fantingame.game.gamecenter.partener.fantin.service.PayBean;
import com.fantingame.game.gamecenter.partener.fantin.util.GsonUtil;

public class PayAPI {

	/**
	 * 获得用户e币余额
	 * @param userId
	 * @return
	 */
	public static PayBean getUserCurrency(String userId) {
		StringBuffer urlBuffer = new StringBuffer(
				ClientConfig.getProperty("payment.url"));
		urlBuffer.append("/json/userEb.e");
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("userId", userId);
		paraMap.put("partnerId", ClientConfig.getProperty("partnerId"));
		String sign = Md5SignUtil.sign(paraMap, ClientConfig.getProperty("secertKey"));
		paraMap.put("sign", sign);
		String json = EucHttpClient.httpGet(urlBuffer.toString(), paraMap);
		return GsonUtil.fromJson(json, PayBean.class);
	}
}
