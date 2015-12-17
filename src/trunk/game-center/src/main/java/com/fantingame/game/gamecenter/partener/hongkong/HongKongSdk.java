package com.fantingame.game.gamecenter.partener.hongkong;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.alibaba.fastjson.JSONObject;
import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.partener.google.Security;

public class HongKongSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(HongKongSdk.class);
	
	private static HongKongSdk ins;
	private final static String PROTOCOL_HEAD = "https://";
	private static Properties prop;
	private String partnerId;
	private String host;
	private String appKey;
	private String publicKey;
	private String privateKey;

	public static HongKongSdk instance() {
		synchronized (HongKongSdk.class) {
			if (ins == null) {
				ins = new HongKongSdk();
			}
		}
		return ins;
	}

	private HongKongSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			partnerId = prop.getProperty("HongKongSdk.partnerId");
			host = prop.getProperty("HongKongSdk.verifyUrl");
			appKey = prop.getProperty("HongKongSdk.appKey");
			publicKey = prop.getProperty("HongKongSdk.publicKey");
			privateKey = prop.getProperty("HongKongSdk.privateKey");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public boolean verifySession(String session){
		if(StringUtils.isNotBlank(session)){
			return true;
		}
		return false;
	}
	

	public JSONObject checkPayCallbackSign(HongKongPaymentObj data) {
		try {
			if(Security.verifyPurchase(getAppKey(),data.getJson(),data.getSignature())){
				return JSONObject.parseObject(data.getJson());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public String getHost() {
		return host;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}
}
