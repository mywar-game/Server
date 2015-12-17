package com.fantingame.game.gamecenter.partener.yxcq.tengxun;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.json.Json;
import com.google.gson.JsonObject;

/**
 * 腾讯
 * @author Administrator
 *
 */
public class YXCQTengxunSdk extends BaseSdk {

	private final static Logger logger = Logger.getLogger(YXCQTengxunSdk.class);
	
	private static YXCQTengxunSdk ins;
	
	private static Properties prop;

	private String qqAppId;
	private String qqAppKey;
	private String weixinAppId;
	private String weixinAppKey;
	private String url;
	private String auth_login_url;
	private String refresh_token_url;
	private String check_token_url;
	private String get_balance_url;
	private String get_balance_URL;
	private String pay_m_url;
	private String pay_m_URL;

	public static YXCQTengxunSdk instance() {
		synchronized (YXCQTengxunSdk.class) {
			if (ins == null) {
				ins = new YXCQTengxunSdk();
			}
		}
		return ins;
	}
	
	private YXCQTengxunSdk() {
		loadSdkProperties();
	}
	
	public void reload() {
		loadSdkProperties();
	}

	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("yxcqsdk.properties"));
			qqAppId = prop.getProperty("TengxunSdk.qqAppId");
			qqAppKey = prop.getProperty("TengxunSdk.qqAppKey");
			weixinAppId = prop.getProperty("TengxunSdk.weixinAppId");
			weixinAppKey = prop.getProperty("TengxunSdk.weixinAppKey");
			url = prop.getProperty("TengxunSdk.url");
			auth_login_url = prop.getProperty("TengxunSdk.auth_login_url");
			refresh_token_url = prop.getProperty("TengxunSdk.refresh_token_url");
			check_token_url = prop.getProperty("TengxunSdk.check_token_url");
			get_balance_url = prop.getProperty("TengxunSdk.get_balance_url");
			get_balance_URL = prop.getProperty("TengxunSdk.get_balance_URL");
			pay_m_url = prop.getProperty("TengxunSdk.pay_m_url");
			pay_m_URL = prop.getProperty("TengxunSdk.pay_m_URL");
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 验证QQ登录
	 * @param token
	 */
	public boolean verfiyLogin(String token, String ip) {
		if (token == null) {
			return false;
		}
		String[] tokenArr = token.split(":");
		if (Integer.valueOf(tokenArr[0]) == 1) {
			// QQ
			return qqLogin(token, ip);
		} else if (Integer.valueOf(tokenArr[0]) == 2) {
			// 微信
			return weixinLoginNew(token, ip);
		} else {
			// 其它不支持
			return false;
		}
	}
	
	/**
	 * test
	 * @param token
	 * @param ip
	 * @return
	 */
	private boolean weixinLoginNew(String token, String ip) {
		
		Log.info("YXCQTengxunSdk weixinLoginNew token=" + token);
		String[] tokenArr = token.split(":");
		if (tokenArr.length != 3) {
			return false;
		}
		String openid = tokenArr[1];
		String accessToken = tokenArr[2];
		long timestamp = new Date().getTime();
		int encode = 1;
		
		String sig = MD5.MD5Encode(weixinAppKey + timestamp);
		String linkUrl = url + auth_login_url + "/?timestamp=" + timestamp + "&appid=" + weixinAppId + "&sig=" + sig + "&openid=" + openid + "&encode=" + encode;
		logger.info("YXCQTengxunSdk weixinLoginNew linkUrl = " + linkUrl);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("openid", openid);
		jsonObject.addProperty("accessToken", accessToken);

		String response = UrlRequestUtils.executeHttpByJson(linkUrl, jsonObject);
		
		logger.info("YXCQTengxunSdk response = " + response);
		if (response == null) {
			return false;
		}
		Map<String, Object> jsonMap = Json.toObject(response, Map.class);
		if (jsonMap.get("ret") != null && (Integer) jsonMap.get("ret") == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 微信登录
	 * @param token
	 * @param ip
	 * @return
	 */
	private boolean weixinLogin(String token, String ip) {
		
		Log.info("YXCQTengxunSdk weixinLogin token=" + token);
		String[] tokenArr = token.split(":");
		if (tokenArr.length != 3) {
			return false;
		}
		String openid = tokenArr[1];
		String refreshToken = tokenArr[2];
		long timestamp = new Date().getTime();
		int encode = 1;
		
		String sig = MD5.MD5Encode(weixinAppKey + timestamp);
		String linkUrl = url + refresh_token_url + "/?timestamp=" + timestamp + "&appid=" + weixinAppId + "&sig=" + sig + "&openid=" + openid + "&encode=" + encode;
		logger.info("YXCQTengxunSdk weixinLogin linkUrl = " + linkUrl);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("appid", Integer.valueOf(weixinAppId));
		jsonObject.addProperty("refreshToken", refreshToken);

		String response = UrlRequestUtils.executeHttpByJson(linkUrl, jsonObject);
		logger.info("YXCQTengxunSdk response = " + response);
		if (response == null) {
			return false;
		}
		Map<String, Object> jsonMap = Json.toObject(response, Map.class);
		if (jsonMap.get("ret") != null && (Integer) jsonMap.get("ret") == 0) {
			String msg = (String) jsonMap.get("msg");
			String accessToken = (String) jsonMap.get("accessToken");
			long expiresIn = (Long) jsonMap.get("expiresIn");
			String refreshToken_new = (String) jsonMap.get("refreshToken");
			String openid_new = (String) jsonMap.get("openid");
			String scope = (String) jsonMap.get("scope");
			String sig_new = MD5.MD5Encode(qqAppKey + timestamp);
			String linkUrl_new = url + check_token_url + "/?timestamp=" + timestamp + "&appid=" + weixinAppId + "&sig=" + sig_new + "&openid=" + openid_new + "&encode=" + encode;
			logger.info("YXCQTengxunSdk verifyLogin linkUrl_new = " + linkUrl_new);
			
			JsonObject jsonObject_new = new JsonObject();
			jsonObject_new.addProperty("accessToken", accessToken);
			jsonObject_new.addProperty("openid", openid_new);
			String response_new = UrlRequestUtils.executeHttpByJson(linkUrl_new, jsonObject_new);
			logger.info("YXCQTengxunSdk response_new = " + response_new);
			if (response_new == null) {
				return false;
			}
			Map<String, Object> jsonMap_new = Json.toObject(response_new, Map.class);
			if (jsonMap_new.get("ret") != null && (Integer) jsonMap_new.get("ret") == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * QQ登录
	 * @param token
	 * @param ip
	 * @return
	 */
	private boolean qqLogin(String token, String ip) {
		
		String[] tokenArr = token.split(":");
		if (tokenArr.length != 3) {
			return false;
		}
		String openid = tokenArr[1];
		String openkey = tokenArr[2];
		String userip = ip;
		long timestamp = new Date().getTime();
		int encode = 1;
		
		String sig = MD5.MD5Encode(qqAppKey + timestamp);
		String linkUrl = url + auth_login_url + "/?timestamp=" + timestamp + "&appid=" + qqAppId + "&sig=" + sig + "&openid=" + openid + "&encode=" + encode;
		logger.info("YXCQTengxunSdk qqLogin linkUrl = " + linkUrl);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("appid", Integer.valueOf(qqAppId));
		jsonObject.addProperty("openid", openid);
		jsonObject.addProperty("openkey", openkey);
		jsonObject.addProperty("userip", userip);

		String response = UrlRequestUtils.executeHttpByJson(linkUrl, jsonObject);
		
		logger.info("YXCQTengxunSdk response = " + response);
		if (response == null) {
			return false;
		}
		Map<String, Object> jsonMap = Json.toObject(response, Map.class);
		if (jsonMap.get("ret") != null && (Integer) jsonMap.get("ret") == 0) {
			return true;
		} else {
			return false;
		}
	}
	
//	public boolean verifyPay(TengxunPayObj obj) {
//		Map<String, String> params = new HashMap<String, String>();
//    	params.put("appid", obj.getAppid());
//    	params.put("appmeta", obj.getAppmeta());
//    	params.put("billno", obj.getBillno());
//    	params.put("channel_id", obj.getChannel_id());
//    	params.put("clientver", obj.getClientver());
//    	params.put("discountid", obj.getDiscountid());
//    	params.put("giftcoins", obj.getGiftcoins());
//    	params.put("openid", obj.getOpenid());
//    	params.put("paychannel", obj.getPaychannel());
//    	params.put("paychannelsubid", obj.getPaychannelsubid());
//    	params.put("payitem", obj.getPayitem());
//    	params.put("providetype", obj.getProvidetype());
//    	params.put("token", obj.getToken());
//    	params.put("ts", Long.toString(obj.getTs()));
//    	params.put("version", obj.getVersion());
//    	params.put("zoneid", obj.getZoneid());
//		return SnsSigCheck.verifySig("GET", "/webApi/yxcqTengxunPayment.do", params, qqAppKey + "&", obj.getSig());
//	}
	
	public boolean verifyPay(TengxunPayParamsObj obj) {
		Map<String, String> params = new HashMap<String, String>();
		Set<Map.Entry<String, String>> set = obj.getParams().entrySet();
		for (Map.Entry<String, String> entry : set) {
			if (entry.getKey().equalsIgnoreCase("sig")) {
				// 不参与签名
				continue;
			}
			params.put(entry.getKey(), entry.getValue());
		}
		return SnsSigCheck.verifySig("GET", "/webApi/yxcqTengxunPayment.do", params, qqAppKey + "&", obj.getParams().get("sig"));
	}
	
	public static void main(String[] args) {
		System.out.println(MD5.MD5Encode("111111"));
		//Appkey: Lf6AtMEB1QlE8BYS&
		//method:GET
		//url_path: /mpay/get_balance_m

		//[openid] => F11669C63D76BAB0BC2F6CC869B19E53
		//[openkey] => 3968DD5F3F14427EF103A05E00AB59B4
		//[pf] => desktop_m_qq-10000144-android-2002-
		//[pfkey] => 5971dce2d3035669e49a1496d590f1ba
		//[pay_token] => 6554E0A9225B05A1CE4C4AF215A8C369
		//[ts] => 1396522716
		//[zoneid] => 1
		//[format] => json
		//[appid] => 1101255891
		Map<String, String> params = new HashMap<String, String>();
		params.put("appkey", "Lf6AtMEB1QlE8BYS&");
		String openid = "F11669C63D76BAB0BC2F6CC869B19E53";
		String openkey = "3968DD5F3F14427EF103A05E00AB59B4";
		String pf = "desktop_m_qq-10000144-android-2002-";
		String pfkey = "5971dce2d3035669e49a1496d590f1ba";
		String pay_token = "6554E0A9225B05A1CE4C4AF215A8C369";
		String ts = "1396522716";
		String zoneid = "1";
		String format = "json";
		String appid = "1101255891";
	}
	
	public String getQqAppId() {
		return qqAppId;
	}

	public void setQqAppId(String qqAppId) {
		this.qqAppId = qqAppId;
	}

	public String getQqAppKey() {
		return qqAppKey;
	}

	public void setQqAppKey(String qqAppKey) {
		this.qqAppKey = qqAppKey;
	}

	public String getWeixinAppId() {
		return weixinAppId;
	}

	public void setWeixinAppId(String weixinAppId) {
		this.weixinAppId = weixinAppId;
	}

	public String getWeixinAppKey() {
		return weixinAppKey;
	}

	public void setWeixinAppKey(String weixinAppKey) {
		this.weixinAppKey = weixinAppKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuth_login_url() {
		return auth_login_url;
	}

	public void setAuth_login_url(String auth_login_url) {
		this.auth_login_url = auth_login_url;
	}

	public String getRefresh_token_url() {
		return refresh_token_url;
	}

	public void setRefresh_token_url(String refresh_token_url) {
		this.refresh_token_url = refresh_token_url;
	}

	public String getCheck_token_url() {
		return check_token_url;
	}

	public void setCheck_token_url(String check_token_url) {
		this.check_token_url = check_token_url;
	}

	public String getGet_balance_url() {
		return get_balance_url;
	}

	public void setGet_balance_url(String get_balance_url) {
		this.get_balance_url = get_balance_url;
	}

	public String getGet_balance_URL() {
		return get_balance_URL;
	}

	public void setGet_balance_URL(String get_balance_URL) {
		this.get_balance_URL = get_balance_URL;
	}
	
	public String getPay_m_url() {
		return pay_m_url;
	}

	public void setPay_m_url(String pay_m_url) {
		this.pay_m_url = pay_m_url;
	}

	public String getPay_m_URL() {
		return pay_m_URL;
	}

	public void setPay_m_URL(String pay_m_URL) {
		this.pay_m_URL = pay_m_URL;
	}

}
