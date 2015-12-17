package com.fantingame.game.gamecenter.partener.kudong;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 酷动
 * @author Administrator
 *
 */
public class KudongSdk {
	
	private static final Logger logger = Logger.getLogger(KudongSdk.class);

	private static KudongSdk ins;
	private static Properties prop;

	private static String appid;
	private static String appkey;
	private static String url;
	private static String key;
	
	public static KudongSdk instance() {
		synchronized (KudongSdk.class) {
			if (ins == null) {
				ins = new KudongSdk();
			}
		}
		return ins;
	}

	private KudongSdk() {
		loadSdkProperties();
	}
	
	public void reload() {
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appid = prop.getProperty("KudongSdk.appid");
			appkey = prop.getProperty("KudongSdk.appkey");
			key = prop.getProperty("KudongSdk.key");
			url = prop.getProperty("KudongSdk.url");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 验证登陆
	 * @param mid
	 * @param token
	 * @return
	 */
	public boolean verifyLogin(String mid, String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mid", mid);
		params.put("token", token);
		
		String jsonStr = UrlRequestUtils.executeKudong(url, mid, token, Mode.POST);
		
		Map<String, Object> ret = Json.toObject(jsonStr, Map.class);
		Object head = ret.get("head");
		Object body = ret.get("body");
		Object resInfo = ret.get("res_info");
		logger.info("KudongSdk verifyLogin ret = " + ret);
		if (body == null) {
			Map<String, Object> map = (Map<String, Object>) ret.get("res_info");
			String responseCode = (String) map.get("response_code");
			String responseMsg = (String) map.get("response_msg");
			logger.info("KudongSdk verifyLogin responseCode=" + responseCode + " &responseMsg=" + responseMsg);
			if (responseCode.equals("303")) {
				return false;
			}
			return false;
		} else {
			Map<String, Object> map = (Map<String, Object>) ret.get("res_info");
			String responseCode = (String) map.get("response_code");
			String responseMsg = (String) map.get("response_msg");
			logger.info("KudongSdk verifyLogin responseCode=" + responseCode + " &responseMsg=" + responseMsg);
			if (responseCode.equals("000")) {
				return true;
			}
			return false;
		}
	}
	
	public boolean verifyPay(KudongPaymentObj obj) {
		String uid = URLEncoder.encode(obj.getUid());
		String oid = obj.getOid();
		String gold = obj.getGold();
		String sid = obj.getSid();
		String time = obj.getTime();
		String eif = obj.getEif();
		String sign = obj.getSign();
		String md5Sign = MD5.MD5Encode(uid + "-" + sid + "-" + oid + "-" + gold + "-" + time + "-" + key);
		logger.info("KudongSdk verifyPay md5Sign=" + md5Sign + " &sign=" + sign);
		if (md5Sign.equalsIgnoreCase(sign)) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		// :
		Map<String, String> params = new HashMap<String, String>();
		params.put("mid", "15002");
		params.put("token", "04EFC1EE5BFC22A4FB84A2359E53AD03");
		
		String jsonStr = UrlRequestUtils.executeKudong("http://test.17mogu.com:8080/mogoo2/gameUser/checkUser.do", "15002", "04EFC1EE5BFC22A4FB84A2359E53AD03", Mode.POST);
		
		Map<String, Object> ret = Json.toObject(jsonStr, Map.class);
		Object head = ret.get("head");
		Object body = ret.get("body");
		Object resInfo = ret.get("res_info");
		logger.info("KudongSdk verifyLogin ret = " + ret);
		if (body == null) {
			Map<String, Object> map = (Map<String, Object>) ret.get("res_info");
			String responseCode = (String) map.get("response_code");
			String responseMsg = (String) map.get("response_msg");
			logger.info("KudongSdk verifyLogin responseCode=" + responseCode + " &responseMsg=" + responseMsg);
			if (responseCode.equals("303")) {
				
			}
			
		} else {
			Map<String, Object> map = (Map<String, Object>) ret.get("res_info");
			String responseCode = (String) map.get("response_code");
			String responseMsg = (String) map.get("response_msg");
			logger.info("KudongSdk verifyLogin responseCode=" + responseCode + " &responseMsg=" + responseMsg);
			if (responseCode.equals("000")) {
				
			}
			
		}
		System.out.println(URLEncoder.encode("123asdfasfd海"));
	}
}
