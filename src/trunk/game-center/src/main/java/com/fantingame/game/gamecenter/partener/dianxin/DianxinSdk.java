package com.fantingame.game.gamecenter.partener.dianxin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

public class DianxinSdk {
	
	private static final Logger logger = Logger.getLogger(DianxinSdk.class);
	
	private static DianxinSdk ins;
	private final static String PROTOCOL_HEAD = "https://";
	private static Properties prop;
	private String client_id;
	private String sign_method;
	private String version;
	private String token_url;
	private String app_key;
	private String client_secret;
	private String game_id;
	
	public static DianxinSdk instance() {
		synchronized (DianxinSdk.class) {
			if (ins == null) {
				ins = new DianxinSdk();
			}
		}
		return ins;
	}

	private DianxinSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			client_id = prop.getProperty("DianxinSdk.client_id");
			sign_method = prop.getProperty("DianxinSdk.sign_method");
			version = prop.getProperty("DianxinSdk.version");
			token_url = prop.getProperty("DianxinSdk.token_url");
			app_key = prop.getProperty("DianxinSdk.app_key");
			client_secret = prop.getProperty("DianxinSdk.client_secret");
			game_id = prop.getProperty("DianxinSdk.game_id");
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Map<String, String> getUserInfo(String code) {
		// 请求参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "authorization_code");
		params.put("code", code);
		params.put("client_secret", client_secret);
		RequestParasUtil.signature("2", client_id, client_secret, sign_method, version, params);
		String content = UrlRequestUtils.executeHttps(token_url, params, Mode.POST);
		@SuppressWarnings("unchecked")
		Map<String, String> ret = Json.toObject(content, Map.class);
		logger.info("DianxinSdk getUserInfo : content = " + content);
		return ret;
	}
	
	/**
	 * 充值回调验证
	 * @param resultCode
	 * @param cpparam
	 * @param validatecode
	 * @return
	 */
	public boolean validatePay(String resultCode, String cpparam, String validatecode) {
		String md5Code = MD5.MD5Encode(resultCode + "&" + cpparam);
		logger.info("DianxinSdk validatePay resultCode = " + resultCode + " cpparam = " + cpparam + " validatecode = " + validatecode + " md5code = " + md5Code);
		if (md5Code.equals(validatecode)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validateSign(String cp_order_id, String correlator, String result_code, int fee, String pay_type, String method, String sign) {
		String signData = MD5.MD5Encode(cp_order_id + correlator + result_code + fee + pay_type + method + client_secret);
		logger.info("DianxinSdk validateSign signData = " + signData);
		if (signData.equals(sign)) {
			return true;
		}
		return false;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getSign_method() {
		return sign_method;
	}

	public void setSign_method(String sign_method) {
		this.sign_method = sign_method;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getToken_url() {
		return token_url;
	}

	public void setToken_url(String token_url) {
		this.token_url = token_url;
	}

	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}
}
