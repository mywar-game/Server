package com.fantingame.game.gamecenter.partener.meizu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

public class MeizuSdk extends BaseSdk {

	private static final Logger logger = Logger.getLogger(MeizuSdk.class);
	private static MeizuSdk ins;

	private static Properties prop;
	private static String appId;
	private static String appKey;
	private static String infoUrl;
	
	public static MeizuSdk instance() {
		synchronized (MeizuSdk.class) {
			if (ins == null) {
				ins = new MeizuSdk();
			}
		}
		return ins;
	}

	private MeizuSdk() {
		 loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appId = prop.getProperty("MeizuSdk.appId");
			appKey = prop.getProperty("MeizuSdk.appKey");
			infoUrl = prop.getProperty("MeizuSdk.infoUrl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		//String sign = MD5.MD5Encode(MD5.MD5Encode("c8813a080aeda7799b2424d399c5144a" + "_" + "b3066822e277f30638966f3e23719de2"));
		//String s = MD5.digest(MD5.digest("c8813a080aeda7799b2424d399c5144a" + "_" + "b3066822e277f30638966f3e23719de2"));
		//System.out.println(s);
		// <MeizuController   money = 5 hash = df9b4945050d03e9c0d927b48b4d1af3 object = >
		String username = "ftft1234";
		String change_id = "3967028";
		int money = 5;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(username);
			sb.append("|");
			sb.append(change_id);
			sb.append("|");
			sb.append(money);
			sb.append("|");
			sb.append("49be5b26750222917a271a970fc17109");
			String sign = MD5.MD5Encode(sb.toString());
			System.out.println(sign);
		} catch (Throwable e) {
			logger.error(e);
		}
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @param lpsust
	 * @return
	 */
	public MeizuInfo getAccount(String token) {
		String url = infoUrl;
		Map<String, String> paraMap = new HashMap<String, String>();
		String sign = MD5.MD5Encode(MD5.MD5Encode(appKey + "_" + token)); // .digest(MD5.digest(appKey + "_" + token));
		paraMap.put("sign", sign);
		paraMap.put("token", token);
		paraMap.put("app_id", appId);
		logger.info("url=" + url + "?sign=" + sign + "&token=" + token + "&app_id=" + appId);
		String result = UrlRequestUtils.execute(url, paraMap, Mode.POST);
		
		logger.info("getAccount result:" + result);
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> jsonMap = Json.toObject(result, Map.class);
			MeizuInfo meizuInfo = new MeizuInfo();
			meizuInfo.setState(Integer.valueOf((String) jsonMap.get("state")));
			meizuInfo.data = new MeizuInfo().new Data();
			@SuppressWarnings("unchecked")
			Map<String, Object> m = (Map<String, Object>) jsonMap.get("data");
			meizuInfo.data.setUserid((String) m.get("userid"));
			meizuInfo.data.setUsername((String) m.get("username"));
			
			return meizuInfo;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	/**
	 * 支付验证
	 * @param obj
	 * @return
	 */
	public boolean checkPayCallbackSign(MeizuPaymentObj obj) {		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(obj.getUsername());
			sb.append("|");
			sb.append(obj.getChange_id());
			sb.append("|");
			sb.append(obj.getMoney());
			sb.append("|");
			sb.append(appKey);
			String sign = MD5.MD5Encode(sb.toString());
			if (sign.equals(obj.getHash())) {
				return true;
			}
		} catch (Throwable e) {
			logger.error(e);
		}
		return false;
	}

}
