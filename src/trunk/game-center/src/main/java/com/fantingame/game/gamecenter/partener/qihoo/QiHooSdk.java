package com.fantingame.game.gamecenter.partener.qihoo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.EncryptUtil;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 *  奇虎360SDK
 * @author chenjian
 *
 */
public class QiHooSdk extends BaseSdk {
	private final static Logger logger = Logger.getLogger(QiHooSdk.class);
	
	private static QiHooSdk ins;
	
	private static Properties prop;
	
	private final static String PROTOCOL_HEAD = "https://";
	private String host;
	private String appId;
	private String appKey;
	private String appSecret;
	private String payCallback;
	private String partnerId;
	
	public static QiHooSdk instance(){
		synchronized (QiHooSdk.class) {
			if(ins == null){
				ins = new QiHooSdk();
			}
		}
		
		return ins;
	}
	
	private QiHooSdk(){
		loadSdkProperties();
	}
	
	public void reload(){
		loadSdkProperties();
	}

	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			host = prop.getProperty("QiHooSdk.host");
			appId = prop.getProperty("QiHooSdk.appId");
			appKey = prop.getProperty("QiHooSdk.appKey");
			appSecret = prop.getProperty("QiHooSdk.appSecret");
			payCallback = prop.getProperty("QiHooSdk.payCallback");
			partnerId = prop.getProperty("QiHooSdk.partnerId");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> assessToken(String code){
		String url = PROTOCOL_HEAD + host + "/oauth2/access_token";
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("grant_type", "authorization_code");
		paraMap.put("code", code);
		paraMap.put("client_id", appKey);
		paraMap.put("client_secret", appSecret);
		paraMap.put("redirect_uri", "oob");
		paraMap.put("sign", makeSign(paraMap));
		String jsonStr = UrlRequestUtils.executeHttps(url, paraMap, Mode.GET);
		Map<String, String> ret = Json.toObject(jsonStr, Map.class);
//		if(ret != null && ret.containsKey("access_token")){
//			return ret.get("access_token");
//		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> refreshToken(String accessToken, String refreshToken){
		String url = PROTOCOL_HEAD + host + "/oauth2/access_token";
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("grant_type", "refresh_token");
		paraMap.put("refresh_token", refreshToken);
		paraMap.put("client_id", appKey);
		paraMap.put("client_secret", appSecret);
		paraMap.put("scope", "basic");
//		paraMap.put("sign", makeSign(paraMap));
		String jsonStr = UrlRequestUtils.executeHttps(url, paraMap, Mode.GET);
		Map<String, String> ret = Json.toObject(jsonStr, Map.class);
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public boolean verifyPayment(PayCallbackObject cb){
		String url = PROTOCOL_HEAD + host + "/pay/verify_mobile_notification.json";
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("product_id", cb.getProduct_id());
		paraMap.put("amount", cb.getAmount());
		paraMap.put("app_uid", cb.getApp_uid());
		paraMap.put("order_id", cb.getOrder_id());
		paraMap.put("app_order_id", cb.getApp_order_id());
		paraMap.put("sign_type", cb.getSign_type());
		paraMap.put("sign_return", cb.getSign_return());
		paraMap.put("app_key", cb.getApp_key());
		paraMap.put("client_id", appId);
		paraMap.put("client_secret", appSecret);
		String jsonStr = UrlRequestUtils.executeHttps(url, paraMap, Mode.GET);
		Map<String, String> ret = Json.toObject(jsonStr, Map.class);
		if(ret != null && ret.containsKey("ret") && ret.get("ret").equals("verified")){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getUserInfo(String accessToken){
		String url = PROTOCOL_HEAD + host + "/user/me.json";
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("access_token", accessToken);
//		paraMap.put("sign", makeSign(paraMap));
		String jsonStr = UrlRequestUtils.executeHttps(url, paraMap, Mode.GET);
		Map<String, String> ret = Json.toObject(jsonStr, Map.class);
		return ret;
	}
	
	private String makeSign(Map<String, String> paraMap){
		Set<String> keys = paraMap.keySet();
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.addAll(keys);
		String signSrcStr = "";
		for(String key : treeSet){
			String value = paraMap.get(key);
			if(!StringUtils.isBlank(value)){
				signSrcStr += value + "#" ;
			}
		}
		signSrcStr += appSecret;
		
		logger.info("QiHoo sign : " + signSrcStr);		
		return EncryptUtil.getMD5(signSrcStr);
	}
	
	
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@SuppressWarnings("unchecked")
	public boolean checkPayCallbackSign(PayCallbackObject cb) {
		Map<String, String> map = Json.toObject(Json.toJson(cb), Map.class);
		map.remove("sign");
		map.remove("sign_return");
		String sign = makeSign(map);
		
		logger.info("QiHoo checkPayCallbackSign Md5Sign:" + sign);
		logger.info("QiHoo getSign:" + cb.getSign());
		return sign.equalsIgnoreCase(cb.getSign());
	}

	public String getPayCallback() {
		return payCallback;
	}

	public void setPayCallback(String payCallback) {
		this.payCallback = payCallback;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws UnsupportedEncodingException {
		String json = "{\"app_key\":\"8099d95af4d25e7dad6c938b6b232b23\",\"product_id\":\"0110032013082000000001\",\"amount\":\"1\"" +
				",\"app_uid\":\"348528246\",\"app_ext1\":\"test\",\"app_ext2\":\"def\",\"user_id\":\"348528246\",\"order_id\":\"1308201125043691681\"" +
				",\"gateway_flag\":\"success\",\"sign_type\":\"md5\",\"app_order_id\":\"0110032013082000000001\",\"sign_return\"" +
				":\"3b926066f3f3e910348e9d1fd2a92e59\",\"sign\":\"66dbca07546b2d8b9e39c42e26138154\"}";

		Map<String, String> paraMap = Json.toObject(json, Map.class);
		paraMap.remove("sign");
		paraMap.remove("sign_return");
		Set<String> keys = paraMap.keySet();
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.addAll(keys);
		String signSrcStr = "";
		for(String key : treeSet){
			String value = paraMap.get(key);
			if(!StringUtils.isBlank(value)){
				signSrcStr +=  value + "#";
			}
		}
		signSrcStr +=  "a7f85a99b32a1adcc34bcf508ed78278";
		System.out.println(signSrcStr);
		System.out.println(EncryptUtil.getMD5(signSrcStr));
		
		// String sign = "66dbca07546b2d8b9e39c42e26138154";
	}

	public String getPartnerId() {
		return partnerId;
	}
}
