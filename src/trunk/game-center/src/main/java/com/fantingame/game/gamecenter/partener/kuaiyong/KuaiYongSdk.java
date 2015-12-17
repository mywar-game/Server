package com.fantingame.game.gamecenter.partener.kuaiyong;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;

public class KuaiYongSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(KuaiYongSdk.class);
	
	private final static String PROTOCOL_HEAD = "http://";
	
	private static KuaiYongSdk ins;

	private static Properties prop;
	
	private String partnerId;
	
	private String paySecret;
	
	private String appKey;
	
	private String verifyUrl;
	
	
	public static KuaiYongSdk instance() {
		synchronized (KuaiYongSdk.class) {
			if (ins == null) {
				ins = new KuaiYongSdk();
			}
		}

		return ins;
	}
	
	public String verifySession(String session) {
			String sign = MD5.MD5Encode(appKey+session,"utf-8");
			String url = PROTOCOL_HEAD +getVerifyUrl();
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("tokenKey",session);
			paraMap.put("sign",sign);
			String jsonStr = UrlRequestUtils.execute(url, paraMap, Mode.POST);
			if (StringUtils.isNotEmpty(jsonStr)) {
				JSONObject jsonObject = JSON.parseObject(jsonStr);
				if(jsonObject.containsKey("code")
						&& "0".equals(jsonObject.getString("code"))){
					return jsonObject.getJSONObject("data").getString("guid");
				}
			}
		return null;
	}

	private KuaiYongSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			partnerId = prop.getProperty("KuaiYongSdk.partnerId");
			paySecret = prop.getProperty("KuaiYongSdk.paySecret");
			verifyUrl = prop.getProperty("KuaiYongSdk.verifyUrl");
			appKey = prop.getProperty("KuaiYongSdk.appKey");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	

	public boolean checkPayCallbackSign(KuaiYongPaymentObj cb) {
		try {
			return true;
		} catch (Exception e) {
			logger.error("apple verify error!", e);
		}
		return false;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public String getPaySecret() {
		return paySecret;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getVerifyUrl() {
		return verifyUrl;
	}
}
