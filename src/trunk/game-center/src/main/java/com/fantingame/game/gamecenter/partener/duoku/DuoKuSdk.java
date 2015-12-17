package com.fantingame.game.gamecenter.partener.duoku;

import java.io.IOException;
import java.net.URLEncoder;
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

public class DuoKuSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(DuoKuSdk.class);
	
	private static DuoKuSdk ins;
	private final static String PROTOCOL_HEAD = "http://";
	private static Properties prop;
	private String appId;
	private String appKey;
	private String partnerId;
	private String appSecret;
	private String host;

	public static DuoKuSdk instance() {
		synchronized (DuoKuSdk.class) {
			if (ins == null) {
				ins = new DuoKuSdk();
			}
		}
		return ins;
	}

	private DuoKuSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appId = prop.getProperty("DuoKuSdk.appId");
			appKey = prop.getProperty("DuoKuSdk.appKey");
			appSecret = prop.getProperty("DuoKuSdk.appSecret");
			partnerId = prop.getProperty("DuoKuSdk.partnerId");
			host = prop.getProperty("DuoKuSdk.host");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean verifySession(String session, String uid){
		String url = PROTOCOL_HEAD + host;
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid",appId);
		params.put("appkey",appKey);
		params.put("uid", uid);
		params.put("sessionid",session);
		String sign = appId+appKey+uid+session+appSecret;
		try {
			sign = MD5.MD5Encode(sign);
			params.put("clientsecret",sign);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		String jsonStr = UrlRequestUtils.execute(url, params, Mode.POST);
		Map<String, Object> ret = Json.toObject(jsonStr, Map.class);
		if(ret != null && ret.containsKey("error_code") && 
				"0".equals(ret.get("error_code"))){
			return true;
		}
		logger.error("DUOKU login error_code:" + ret.get("error_code"));
		return false;
	}
	

	@SuppressWarnings("deprecation")
	public boolean checkPayCallbackSign(DuokuPaymentObj data) {
		try {
			String sign = data.getAmount()+data.getCardtype()+data.getOrderid()
					+data.getResult()+data.getTimetamp()+appSecret+URLEncoder.encode(data.getAid());
			logger.info("DUOKU sign:" + data.getAmount() + "|" + data.getCardtype() + "|" + data.getOrderid()
					 + "|" + data.getResult() + "|" + data.getTimetamp() + "|" + appSecret+data.getAid());
			sign = MD5.MD5Encode(sign);
			
			logger.info("DUOKU MD5Sign:" + sign);
			logger.info("DUOKU getClient_secret:" + data.getClient_secret()) ;
			if("1".equals(data.getResult())
					&& sign.equals(data.getClient_secret())){
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		logger.info("DUOKU doPayment result: " + data.getResult());
		return false;
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public String getHost() {
		return host;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getAppId() {
		return appId;
	}

	public String getAppKey() {
		return appKey;
	}
}
