package com.fantingame.game.gamecenter.partener.baidu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.EncryptUtil;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

public class BaiduSdk extends BaseSdk {
	private static final Logger logger = Logger.getLogger(BaiduSdk.class);
	private static BaiduSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String host;
	private String gameId;
	private String certKey;
	private String partnerId;

	private String payBackUrl;

	public static BaiduSdk instance() {
		synchronized (BaiduSdk.class) {
			if (ins == null) {
				ins = new BaiduSdk();
			}
		}

		return ins;
	}

	private BaiduSdk() {
		loadSdkProperties();
	}
	
	public void reload(){
		loadSdkProperties();
	}

	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			host = prop.getProperty("BaiduSdk.host");
			gameId = prop.getProperty("BaiduSdk.gameId");
			certKey = prop.getProperty("BaiduSdk.certKey");
			partnerId = prop.getProperty("BaiduSdk.partnerId");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> vertifySession(String sessionId) {
		String url = PROTOCOL_HEAD + host + "/vertify_session.xhtml";
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("apiKey", gameId);
		paraMap.put("sessionId", sessionId);
		String sign = EncryptUtil.getMD5(certKey + gameId + sessionId);
		paraMap.put("sign", sign);
		String result = UrlRequestUtils.execute(url, paraMap, Mode.POST);
		logger.info("getAccount result:" + result);
		try {
			return Json.toObject(result, Map.class);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean vertifySign(int sessionId, String cmFlag, String sign) {
		String signTmp = EncryptUtil.getMD5(certKey + sessionId + cmFlag);
		if (signTmp.equalsIgnoreCase(sign)) {
			return true;
		}
		return false;
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
		return gameId;
	}

	public void setAppId(String appId) {
		this.gameId = appId;
	}

	public String getAppKey() {
		return certKey;
	}

	public void setAppKey(String appKey) {
		this.certKey = appKey;
	}

	public boolean checkPayCallbackSign(BaiduPaymentObj cb) {
		String source = certKey + "amount" + cb.getAmount() + "api_key" + cb.getApiKey() + "back_send" + cb.getBackSend() + "currency" + cb.getCurrency() + "customInfo"
				+ cb.getCustomInfo() + "order_id" + cb.getOrderId() + "result" + cb.getResult() + "server_id" + cb.getServerId() + "timestamp" + cb.getTimestamp() + "user_id"
				+ cb.getUserId() + "wanba_oid" + cb.getWanbaOid();
		String sign = EncryptUtil.getMD5(source);
		
		return sign.equalsIgnoreCase(cb.getSign());
	}

	public String getPartnerId() {
		return partnerId;
	}
}
