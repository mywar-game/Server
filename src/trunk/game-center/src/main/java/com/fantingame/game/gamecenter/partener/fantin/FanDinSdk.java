package com.fantingame.game.gamecenter.partener.fantin;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;

public class FanDinSdk extends BaseSdk {
//	private static final Logger logger = Logger.getLogger(FanDinSdk.class);
	private static FanDinSdk ins;

	private static Properties prop;

	private String host;
	private String payBackUrl;
	private String appId;
	private String ftPartnerId;
	private String payKey;

	public static FanDinSdk instance() {
		synchronized (FanDinSdk.class) {
			if (ins == null) {
				ins = new FanDinSdk();
			}
		}

		return ins;
	}
	
	private FanDinSdk() {
		 loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
//			host = prop.getProperty("EasouSdk.host");
			payBackUrl = prop.getProperty("FtSdk.payBackUrl");
			appId = prop.getProperty("FtSdk.appId", "1685");
			ftPartnerId = prop.getProperty("FtSdk.partnerId", "1000100010001010");
			payKey = prop.getProperty("FtSdk.payKey", "1000100010001010");
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
