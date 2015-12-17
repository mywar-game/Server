package com.fantingame.game.gamecenter.partener.itools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.EncryptUtil;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

public class ItoolsIOSSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(ItoolsIOSSdk.class);
	
	private static ItoolsIOSSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "https://";
	private String partnerId;
	private String apiId;
	private String apiKey;
	private String verifyUrl;//sdk登陆验证url

	public static ItoolsIOSSdk instance() {
		synchronized (ItoolsIOSSdk.class) {
			if (ins == null) {
				ins = new ItoolsIOSSdk();
			}
		}

		return ins;
	}

	private ItoolsIOSSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			apiId = prop.getProperty("ItoolsIOSSdk.appId");
			apiKey=prop.getProperty("ItoolsIOSSdk.appKey");
			partnerId = prop.getProperty("ItoolsIOSSdk.partnerId");
			verifyUrl = prop.getProperty("ItoolsIOSSdk.verifyUrl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public boolean verifySession(String session, String uid,long timestamp){
		String url = PROTOCOL_HEAD + verifyUrl;
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", apiId);
		params.put("sessionid", uid+"_"+session);
		params.put("sign",EncryptUtil.getMD5("appid="+apiId+"&sessionid="+uid+"_"+session).toLowerCase());
		String jsonStr = UrlRequestUtils.executeHttps(url, params, Mode.GET);
		jsonStr = StringUtils.replace(jsonStr,"'","\"");
		Map<String,Object> jsonMap = Json.toObject(jsonStr,Map.class);
		if(jsonMap.containsKey("status")
				&& jsonMap.get("status") != null
				&& jsonMap.get("status").toString().equals("success")){
			return true;
		}
		return false;
	}
	
	
	public boolean checkPayCallbackSign(ItoolsPaymentObj data) {
		try {
			String notifyJson = "";
			boolean verified = false;
			notifyJson = RSASignature.decrypt(data.getNotify_data());//公钥RSA解密后的json字符串
			Map<String,Object> ret = Json.toObject(notifyJson, Map.class);
			//从json中获取参数信息
			data.setOrder_id_com(ret.get("order_id_com").toString());
			data.setAccount(ret.get("account").toString());
			data.setAmount(ret.get("amount").toString());
			data.setOrder_id(ret.get("order_id").toString());
			data.setResult(ret.get("result").toString());
			data.setUser_id(ret.get("user_id").toString());
			verified=RSASignature.verify(notifyJson, data.getSign());////公钥对数据进行RSA签名校验
			return verified;
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

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getVerifyUrl() {
		return verifyUrl;
	}

	public void setVerifyUrl(String verifyUrl) {
		this.verifyUrl = verifyUrl;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

}
