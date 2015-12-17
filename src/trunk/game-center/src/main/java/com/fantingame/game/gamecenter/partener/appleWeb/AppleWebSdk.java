package com.fantingame.game.gamecenter.partener.appleWeb;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;

public class AppleWebSdk extends BaseSdk  {

	private static AppleWebSdk ins;
	
	private static Properties prop;
	
	private String host;
	private String payBackUrl;
	private String appId;
	private String ftPartnerId;
	private String payKey;
	
	public static AppleWebSdk instance() {
		synchronized (AppleWebSdk.class) {
			if (ins == null) {
				ins = new AppleWebSdk();
			}
		}
		
		return ins;
	}
	
	private AppleWebSdk() {
		 loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			payBackUrl = prop.getProperty("AppleWebSdk.payBackUrl");
			appId = prop.getProperty("AppleWebSdk.appId", "1687");
			ftPartnerId = prop.getProperty("AppleWebSdk.partnerId", "1000100010001010");
			payKey = prop.getProperty("AppleWebSdk.payKey", "1000100010001010");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPayBackUrl() {
		return payBackUrl;
	}

	public void setPayBackUrl(String payBackUrl) {
		this.payBackUrl = payBackUrl;
	}

	public String getAppId() {
		return appId;
	}
	public String getFtPartnerId() {
		return ftPartnerId;
	}


	public String getPayKey() {
		return payKey;
	}
	
}
