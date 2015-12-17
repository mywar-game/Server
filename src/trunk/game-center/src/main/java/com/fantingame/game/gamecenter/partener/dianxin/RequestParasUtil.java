package com.fantingame.game.gamecenter.partener.dianxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RequestParasUtil {

	/**
	 * 拼接字符串
	 * @param requestData
	 * @return
	 */
	private static List<String> paramConcat(Map<String, String> requestData) {
		List<String> params = new ArrayList<String>();
		StringBuffer keySort = new StringBuffer();
		StringBuffer values = new StringBuffer();
		if (requestData != null && requestData.size() > 0) {
			for (Entry<String, String> entry : requestData.entrySet()) {
				String name = entry.getKey() == null ? "" : entry.getKey();
				String value = entry.getValue() == null ? "" : entry.getValue();
				keySort.append(name).append("&");
				values.append(value);
			}
			keySort.deleteCharAt(keySort.lastIndexOf("&"));
		}
		params.add(keySort.toString());
		params.add(values.toString());
		return params;
	}
	
	/**
	 * 签名
	 * @param signLevel
	 * @param clientId
	 * @param clientSecret
	 * @param signMethod
	 * @param version
	 * @param requestData
	 */
	public static void signature(String signLevel, String clientId,
			String clientSecret, String signMethod, String version,
			Map<String, String> requestData) {
		String signature = "";
		String timestamp = "" + System.currentTimeMillis();
		if ((signLevel == null) || (clientId == null) || (clientSecret == null) || (signMethod == null) || (version == null) || (requestData == null)) {
			return;
		}
		if (!"1".equals(signLevel) && !"2".equals(signLevel) && !"3".equals(signLevel)) {
			return;
		}
		if (!"MD5".equalsIgnoreCase(signMethod)) {
			return;
		}
		
		if (!"1".equals(signLevel)) {
			requestData.put("client_id", clientId);
			requestData.put("sign_method", signMethod);
			requestData.put("version", version);
			requestData.put("teimstamp", timestamp);
			
			List<String> concatList = null;
			Map<String, String> signMap = new HashMap<String, String>();
			
			if ("2".equals(signLevel)) {
				signMap.put("client_id", clientId);
				signMap.put("sign_method", signMethod);
                signMap.put("version", version);
                signMap.put("timestamp", timestamp);
                signMap.put("client_secret", clientSecret);
			} else if ("3".equals(signLevel)) {
				signMap.putAll(requestData);
				signMap.put("client_secret", clientSecret);
			}
			
			concatList = paramConcat(signMap);
			String signSort = concatList.get(0);
			String signContent = concatList.get(1);
			try {
				signature = HmacSignature.encodeMD5(signContent);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			requestData.put("sign_sort", signSort);
			requestData.put("signature", signature);
		}
	}
}
