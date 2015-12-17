package com.fantingame.game.gamecenter.partener.shijia;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;

public class ShiJiaSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(ShiJiaSdk.class);
	
	private static ShiJiaSdk ins;

	private static Properties prop;
	private String partnerId;
	private String apiKey;
	private String appSecret;
	private String appId;

	public static ShiJiaSdk instance() {
		synchronized (ShiJiaSdk.class) {
			if (ins == null) {
				ins = new ShiJiaSdk();
			}
		}

		return ins;
	}

	private ShiJiaSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appId = prop.getProperty("ShiJiaSdk.appId");
			apiKey = prop.getProperty("ShiJiaSdk.appKey");
			partnerId = prop.getProperty("ShiJiaSdk.partnerId");
			appSecret = prop.getProperty("ShiJiaSdk.appSecret");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public boolean verifySession(String session, String uid,String timestamp){
		String sign = MD5.MD5Encode(uid+timestamp+apiKey);
		if(sign.equals(session)){
			return true;
		}
		return false;
	}
	

	public boolean checkPayCallbackSign(ShiJiaPaymentObj data) {
		try {
			String sign = MD5.MD5Encode(data.getUid()+data.getBillno()+data.getServer_id()+data.getTimestamp()+apiKey);
			if(data.getSign().equals(sign)){
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

	public String getApiKey() {
		return apiKey;
	}


	public String getAppSecret() {
		return appSecret;
	}

	public String getAppId() {
		return appId;
	}
}
