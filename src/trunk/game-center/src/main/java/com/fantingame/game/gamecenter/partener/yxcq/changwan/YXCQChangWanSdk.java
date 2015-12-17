package com.fantingame.game.gamecenter.partener.yxcq.changwan;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;

public class YXCQChangWanSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(YXCQChangWanSdk.class);
	
	private static YXCQChangWanSdk ins;

	private static Properties prop;

	private String appKey;
	private String appId;
	private String appSecret;
	private String partnerId;

	public static YXCQChangWanSdk instance() {
		synchronized (YXCQChangWanSdk.class) {
			if (ins == null) {
				ins = new YXCQChangWanSdk();
			}
		}
		return ins;
	}

	private YXCQChangWanSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appId = prop.getProperty("ChangWanSdk.appId");
			appKey = prop.getProperty("ChangWanSdk.appKey");
			appSecret = prop.getProperty("ChangWanSdk.appSecret");
			partnerId = prop.getProperty("ChangWanSdk.partnerId");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean verifySession(String session,String uid,String ip,String port,String timestamp){
		String sign = uid+"|"+ip+"|"+port+"|"+timestamp+"|"+appKey;
		String encodeSign = MD5.MD5Encode(sign);
		if(encodeSign.equals(session)){
			return true;
		}
		return false;
	}
	

	public boolean checkPayCallbackSign(ChangWanPaymentObj cb) {
		try {
			String str = cb.getServerid()+"|"+cb.getCustominfo()+"|"+cb.getOpenid()+"|"+cb.getOrdernum()
					+"|"+cb.getStatus()+"|"+cb.getPaytype()+"|"+cb.getAmount()+"|"+cb.getErrdesc()+"|"
					+cb.getPaytime()+"|"+getAppKey();
			str = MD5.MD5Encode(str);
			if(cb.getSign().equals(str)){
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
}
