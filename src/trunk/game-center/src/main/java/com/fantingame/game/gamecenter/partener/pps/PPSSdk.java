package com.fantingame.game.gamecenter.partener.pps;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;

public class PPSSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(PPSSdk.class);
	
	private static PPSSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String partnerId;
	private String appKey;
	private String appSecret;
	private String appId;

	public static PPSSdk instance() {
		synchronized (PPSSdk.class) {
			if (ins == null) {
				ins = new PPSSdk();
			}
		}

		return ins;
	}

	private PPSSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appKey = prop.getProperty("PPSSdk.appKey");
			partnerId = prop.getProperty("PPSSdk.partnerId");
			appSecret = prop.getProperty("PPSSdk.appSecret");
			appId = prop.getProperty("PPSSdk.appId");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public boolean verifySession(String sign, String uid, String timestamp) {
		String md5Sign = MD5.MD5Encode(uid+"&"+timestamp+"&"+appKey);
		logger.info("PPS verifySession md5Sign = " + md5Sign + " sign = " + sign);
		if(sign.equals(MD5.MD5Encode(uid+"&"+timestamp+"&"+appKey))){
			return true;
		}
		return false;
	}
	

	public boolean checkPayCallbackSign(PPSPaymentObj data) {
		try {
			String sign = MD5.MD5Encode(data.getUser_id()+data.getRole_id()+data.getOrder_id()+data.getMoney()+data.getTime()+appSecret);
			logger.info("PPS checkPayCallbackSign md5Sign = " + sign + " sign = " + data.getSign());
			if(data.getSign().equals(sign)){
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getAppId() {
		return appId;
	}
}
