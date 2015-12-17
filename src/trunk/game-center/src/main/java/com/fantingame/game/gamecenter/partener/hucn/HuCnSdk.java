package com.fantingame.game.gamecenter.partener.hucn;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;

public class HuCnSdk extends BaseSdk {

	private static final Logger logger = Logger.getLogger(HuCnSdk.class);

	private static HuCnSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String partnerId;
	private String apiKey;
	private String appId;

	public static HuCnSdk instance() {
		synchronized (HuCnSdk.class) {
			if (ins == null) {
				ins = new HuCnSdk();
			}
		}

		return ins;
	}

	private HuCnSdk() {
		loadSdkProperties();
	}

	public void reload() {
		loadSdkProperties();
	}

	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appId = prop.getProperty("HuCnSdk.appId");
			apiKey = prop.getProperty("HuCnSdk.appKey");
			partnerId = prop.getProperty("HuCnSdk.partnerId");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean verifySession(String session, String uid, String time) {
		try {
			String sign = MD5.MD5Encode(uid + "&" + time + "&" + getApiKey());
			if (session.equals(sign)) {
				return true;
			}
		} catch (Exception e) {
			logger.error("Hucn verifySession error!", e);
		}
		return false;
	}

	public boolean checkPayCallbackSign(HucnPaymentObj cb) {
		try {
			// account=77&amount=50&orderid=20130806123547852&result=0&channel=联通充值卡&msg=成功&extrainfo=25&appkey={appkey}
			String sign = "account=" + cb.getAccount() + "&amount=" + cb.getAmount() + "&orderid=" + cb.getOrderid() + "&result=" + cb.getResult() + "&channel=" + cb.getChannel() + "&msg="
					+ cb.getMsg() + "&extrainfo=" + cb.getExtrainfo() + "&appkey=" + getApiKey();
			sign = MD5.MD5Encode(sign, "UTF-8");
			if (cb.getSign().equals(sign)) {
				return true;
			}
		} catch (Exception e) {
			logger.error("hucn checkPayCallbackSign error!", e);
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

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
