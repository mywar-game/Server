package com.fantingame.game.gamecenter.partener.sqw;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;

public class SanqiWanwanSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(SanqiWanwanSdk.class);
	
	private static SanqiWanwanSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String partnerId;
	private String appKey;
	private String appId;
	private String host;
	private String payhost;

	public static SanqiWanwanSdk instance() {
		synchronized (SanqiWanwanSdk.class) {
			if (ins == null) {
				ins = new SanqiWanwanSdk();
			}
		}

		return ins;
	}

	private SanqiWanwanSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appId = prop.getProperty("SanqiWanwanSdk.apiId");
			appKey = prop.getProperty("SanqiWanwanSdk.apiKey");
			partnerId = prop.getProperty("SanqiWanwanSdk.partnerId");
			host = prop.getProperty("SanqiWanwanSdk.host");
			payhost = prop.getProperty("SanqiWanwanSdk.payhost");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String verifySession(String session){
		return SanqiWanwanUtil.login(session);
	}
	

	public boolean checkPayCallbackSign(SanqiWanwanPaymentObj cb) {
		try {
			Map<String, String> payParams = new HashMap<String,String>();
			payParams.put("order_id",cb.getOrderid());
			payParams.put("pay_time",cb.getPayTime());
			payParams.put("pay_type",cb.getPayType());
			payParams.put("status",cb.getStatus());
			payParams.put("total_price",cb.getTotalPrice());
			payParams.put("tx_no",cb.getTxNo());
			payParams.put("usergameid",cb.getUsergameid());
			return SanqiWanwanUtil.verifyPay(payParams,payhost,cb.getSign(),cb.getDate());
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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
