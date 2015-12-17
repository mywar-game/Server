package com.fantingame.game.gamecenter.partener.mobilemm;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;

public class MobileMMSdk extends BaseSdk {
	private static final Logger logger = Logger.getLogger(MobileMMSdk.class);
	private static MobileMMSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String host;
	private String partnerId;
	private String payBackUrl;
	private String easouAppId;
	private String easouPartnerId;
	private String appId;
	private String appKey;
	private String appId1;
	private String appKey1;

	public static MobileMMSdk instance() {
		synchronized (MobileMMSdk.class) {
			if (ins == null) {
				ins = new MobileMMSdk();
			}
		}
		return ins;
	}
	
	private MobileMMSdk() {
		 loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			partnerId = prop.getProperty("MobileMMSdk.partnerId","3001");
			easouAppId = prop.getProperty("MobileMMSdk.easouAppId", "1685");
			easouPartnerId = prop.getProperty("MobileMMSdk.easouPartnerId", "1000100010001010");
			appId = prop.getProperty("MobileMMSdk.appId","300007672199");
			appKey = prop.getProperty("MobileMMSdk.appKey","9C79308BD05822A0");
			appId1 = prop.getProperty("MobileMMSdk.appId1","300008014305");
			appKey1 = prop.getProperty("MobileMMSdk.appKey1","39403D3F7FF8E4F4");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean  checkPayCallbackSign(MobileMMPaymentObj obj){
		String sign = obj.getOrderID()+"#"+obj.getChannelID()+"#"+
				obj.getPayCode()+"#";
		if(appId.equals(obj.getAppID())){
			sign = sign+getAppKey();
		}else{
			obj.setExData("01"+getPartnerId()+obj.getExData());
			sign = sign+getAppKey1();
		}
		sign = MD5.MD5Encode(sign,"utf-8").toUpperCase();
		if(obj.getMd5Sign().equals(sign)){
			return true;
		}
		return false;
	}

	public String getEasouAppId() {
		return easouAppId;
	}

	public String getAppKey() {
		return appKey;
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

	public String getEasouPartnerId() {
		return easouPartnerId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public String getAppId1() {
		return appId1;
	}

	public String getAppKey1() {
		return appKey1;
	}
	
}
