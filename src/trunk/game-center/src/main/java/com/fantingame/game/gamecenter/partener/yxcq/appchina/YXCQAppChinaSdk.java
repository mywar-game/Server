package com.fantingame.game.gamecenter.partener.yxcq.appchina;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.alibaba.fastjson.JSONObject;
import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

public class YXCQAppChinaSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(YXCQAppChinaSdk.class);
	
	private static YXCQAppChinaSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String partnerId;
	private String appId;
	private String appKey;
	private String appSecret;
	private String host;
	private String payHost;

	public static YXCQAppChinaSdk instance() {
		synchronized (YXCQAppChinaSdk.class) {
			if (ins == null) {
				ins = new YXCQAppChinaSdk();
			}
		}

		return ins;
	}

	private YXCQAppChinaSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appId = prop.getProperty("AppChinaSdk.appId");
			appKey = prop.getProperty("AppChinaSdk.appKey");
			appSecret = prop.getProperty("AppChinaSdk.appSecret");
			partnerId = prop.getProperty("AppChinaSdk.partnerId");
			host = prop.getProperty("AppChinaSdk.host");
			payHost = prop.getProperty("AppChinaSdk.payHost");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public boolean verifySession(String session,String uid){
		String url = PROTOCOL_HEAD + host;
		Map<String, String> params = new HashMap<String, String>();
		params.put("app_key",appKey);
		params.put("app_id",appId);
		params.put("ticket",session);
		String jsonStr = UrlRequestUtils.execute(url, params, Mode.POST);
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		if(jsonObject.getIntValue("status") == 0
				&& "OK".equals(jsonObject.getString("message"))){
			if(jsonObject.getJSONObject("data").getString("user_id").equals(uid)){
				return true;
			}
		}
		return false;
	}
	

	public boolean checkPayCallbackSign(AppChinaPaymentObj cb) {
		try {
			if(CpTransSyncSignValid.validSign(cb.getTransdata(),cb.getSign(),YXCQAppChinaSdk.instance().getAppSecret())){
				AppChinaPaymentObj data = Json.toObject(cb.getTransdata(),AppChinaPaymentObj.class);
				cb.setAppid(data.getAppid());
				cb.setCount(data.getCount());
				cb.setCpprivate(data.getCpprivate());
				cb.setExorderno(data.getExorderno());
				cb.setFeetype(data.getFeetype());
				cb.setMoney(data.getMoney());
				cb.setResult(data.getResult());
				cb.setTransid(data.getTransid());
				cb.setTranstime(data.getTranstime());
				cb.setTranstype(data.getTranstype());
				cb.setWaresid(data.getWaresid());
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getPayHost() {
		return payHost;
	}

	public void setPayHost(String payHost) {
		this.payHost = payHost;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
}
