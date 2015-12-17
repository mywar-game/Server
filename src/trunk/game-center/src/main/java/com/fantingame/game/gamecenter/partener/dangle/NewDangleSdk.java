package com.fantingame.game.gamecenter.partener.dangle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

public class NewDangleSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(NewDangleSdk.class);

	private static NewDangleSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String partnerId;
	private String apiKey;
	private String appId;
	private String host;
	private String paymentKey;

	public static NewDangleSdk instance() {
		synchronized (NewDangleSdk.class) {
			if (ins == null) {
				ins = new NewDangleSdk();
			}
		}

		return ins;
	}

	private NewDangleSdk() {
		loadSdkProperties();
	}

	public void reload() {
		loadSdkProperties();
	}

	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource(
					"sdk.properties"));
			appId = prop.getProperty("NewDangleSdk.apiId");
			paymentKey = prop.getProperty("NewDangleSdk.payment_key");
			apiKey = prop.getProperty("NewDangleSdk.apiKey");
			partnerId = prop.getProperty("NewDangleSdk.partnerId");
			host = prop.getProperty("NewDangleSdk.host");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean verifySession(String session, String uid) {
		String url = PROTOCOL_HEAD + host;
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", session);
		params.put("mid", uid);
		params.put("app_id", appId);
		String sign = "";
		try {
			sign = loginMakeSign(session, apiKey);
			params.put("sig", sign);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		String jsonStr = UrlRequestUtils.execute(url, params, Mode.GET);
		Map<String, Object> ret = Json.toObject(jsonStr, Map.class);
		if (ret != null && ret.containsKey("error_code")
				&& ret.get("error_code").equals(0)) {
			return true;
		}

		logger.info("Dangle login error_code: " + ret.get("error_code"));
		return false;
	}

	public boolean checkPayCallbackSign(DanglePaymentObj cb) {

		try {
			String signString = "order=" + cb.getOrder() + "&money="
					+ cb.getMoney() + "&mid=" + cb.getMid() + "&time="
					+ cb.getTime() + "&result=" + cb.getResult() + "&ext="
					+ cb.getExt() + "&key="
					+ NewDangleSdk.instance().getPaymentKey();
			if (StringUtils.isBlank(cb.getSignature())) {
				return false;
			}

			String md5Sign = MD5.MD5Encode(signString);
			logger.info("Dangle sign:" + signString);
			logger.info("Dangle md5Sign:" + md5Sign);
			logger.info("Dangle getSignature:" + cb.getSignature());

			if (cb.getSignature().equals(md5Sign)) {
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	private String loginMakeSign(String token, String appKey) throws Exception {
		return MD5.MD5Encode(token + "|" + appKey);
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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPaymentKey() {
		return paymentKey;
	}

	public void setPaymentKey(String paymentKey) {
		this.paymentKey = paymentKey;
	}
	

}
