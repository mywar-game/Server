package com.fantingame.game.gamecenter.partener.kuaibo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

public class KuaiBoSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(KuaiBoSdk.class);
	
	private static KuaiBoSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String partnerId;
	private String apiKey;
	private String appId;
	private String host;
	private String requetCode;

	public static KuaiBoSdk instance() {
		synchronized (KuaiBoSdk.class) {
			if (ins == null) {
				ins = new KuaiBoSdk();
			}
		}

		return ins;
	}

	private KuaiBoSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appId = prop.getProperty("KuaiBoSdk.appId");
			apiKey = prop.getProperty("KuaiBoSdk.appKey");
			partnerId = prop.getProperty("KuaiBoSdk.partnerId");
			host = prop.getProperty("KuaiBoSdk.host");
			requetCode = prop.getProperty("KuaiBoSdk.requetCode");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean verifySession(String session, String uid){
		String url = PROTOCOL_HEAD + host;
		Map<String, String> params = new HashMap<String, String>();
		params.put("MerId",getAppId());
		params.put("Act",getRequetCode());
		params.put("Uin",uid);
		params.put("SessionKey",session);
		String sign = "";
		try {
			sign = loginMakeSign(session,uid);
			params.put("EncString", sign);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		String jsonStr = UrlRequestUtils.execute(url, params, Mode.GET);
		Map<String, Object> ret = Json.toObject(jsonStr, Map.class);
		if(ret != null && ret.containsKey("ErrorCode") && ret.get("ErrorCode").equals(1)){
			return true;
		}
		return false;
	}
	

	public boolean checkPayCallbackSign(KuaiBoPaymentObj cb) {
		try {
			String sign = MD5.MD5Encode(cb.getMerId()+cb.getOrderId()+cb.getMoney()+getApiKey());
			if(sign.equals(cb.getEncString())){
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
	
	private String loginMakeSign(String token,String uid) throws Exception{
		return MD5.MD5Encode(getAppId()+getRequetCode()+uid+token+getApiKey());
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getAppId() {
		return appId;
	}

	public String getHost() {
		return host;
	}

	public String getRequetCode() {
		return requetCode;
	}
}
