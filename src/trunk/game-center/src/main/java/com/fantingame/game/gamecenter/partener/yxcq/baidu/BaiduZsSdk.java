package com.fantingame.game.gamecenter.partener.yxcq.baidu;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.EncryptUtil;

public class BaiduZsSdk extends BaseSdk {
	private static final Logger logger = Logger.getLogger(BaiduZsSdk.class);
	private static BaiduZsSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String appKey;
	private String partnerId;
	private String payBackUrl;

	public static BaiduZsSdk instance() {
		synchronized (BaiduZsSdk.class) {
			if (ins == null) {
				ins = new BaiduZsSdk();
			}
		}

		return ins;
	}

	private BaiduZsSdk() {
		loadSdkProperties();
	}
	
	public void reload(){
		loadSdkProperties();
	}

	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appKey = prop.getProperty("BaiduZsSdk.appKey");
			partnerId = prop.getProperty("BaiduZsSdk.partnerId");
			payBackUrl = prop.getProperty("BaiduZsSdk.payBackUrl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	public boolean vertifySign(String transdata, String sign) {
		String signTmp = EncryptUtil.getMD5(transdata + appKey);
		if (signTmp.equalsIgnoreCase(sign)) {
			return true;
		}
		return false;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPayBackUrl() {
		return payBackUrl;
	}

	public void setPayBackUrl(String payBackUrl) {
		this.payBackUrl = payBackUrl;
	}
}
