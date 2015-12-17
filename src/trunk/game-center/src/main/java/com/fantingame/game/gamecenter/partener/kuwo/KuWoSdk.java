package com.fantingame.game.gamecenter.partener.kuwo;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;

public class KuWoSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(KuWoSdk.class);
	
	private static KuWoSdk ins;

	private static Properties prop;

	private String partnerId;
	private String appKey;
	private String appSecret;
	private String appId;

	public static KuWoSdk instance() {
		synchronized (KuWoSdk.class) {
			if (ins == null) {
				ins = new KuWoSdk();
			}
		}

		return ins;
	}

	private KuWoSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appKey = prop.getProperty("KuWoSdk.appKey");
			partnerId = prop.getProperty("KuWoSdk.partnerId");
			appSecret = prop.getProperty("KuWoSdk.appSecret");
			appId = prop.getProperty("KuWoSdk.appId");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public boolean verifySession(String session, String uid,String timestamp){
		if(session.equals(MD5.MD5Encode(uid+timestamp+appKey,"utf-8"))){
			return true;
		}
		return false;
	}
	

	public boolean checkPayCallbackSign(KuWoPaymentObj data) {
		try {
			String sign = "serverid="+data.getServerid()+"&time="+data.getTime()+"&userid="+data.getUserid()+
					"&orderid="+data.getOrderid()+"&amount="+data.getAmount()+"&ext1="+data.getExt1()+"&ext2="+data.getExt2()+
					"&key="+appSecret;
			sign = MD5.MD5Encode(sign,"utf-8").toUpperCase();
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
