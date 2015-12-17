package com.fantingame.game.gamecenter.partener.appFame;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.alibaba.fastjson.JSONObject;
import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.partener.anzhi.Base64;
import com.fantingame.game.gamecenter.partener.apple.ApplePaymentObj;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;
import com.google.gson.JsonObject;

public class AppFameIosSdk extends BaseSdk {

	private static final Logger logger = Logger.getLogger(AppFameIosSdk.class);

	private static AppFameIosSdk ins;

	private static Properties prop;
	
	private ExecutorService executorService;

	private final static String PROTOCOL_HEAD = "http://";
	private final static String APPLE_PROTOCOL_HEAD = "https://";
	
	private String partnerId;
	private String appId;
	private String appKey;
	private String host;
	
	// ios
	private String iosHost;
	private String testHost;
	private String testVersion;

	public static AppFameIosSdk instance() {
		synchronized (AppFameIosSdk.class) {
			if (ins == null) {
				ins = new AppFameIosSdk();
			}
		}

		return ins;
	}

	private AppFameIosSdk() {
		loadSdkProperties();
	}

	public void reload() {
		loadSdkProperties();
	}

	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource(
					"sdk.properties"));
			appKey = prop.getProperty("AppFameIosSdk.appKey");
			appId = prop.getProperty("AppFameIosSdk.appId");
			host = prop.getProperty("AppFameIosSdk.host");
			
			// ios
			iosHost = prop.getProperty("AppleSdk.host");
			testHost = prop.getProperty("AppleSdk.testhost");
			testVersion = prop.getProperty("AppleSdk.testVersion");			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean verifyLogin(String userId, String userToken) {
		String url = PROTOCOL_HEAD + host;
		Map<String, String> params = new HashMap<String, String>();
		params.put("v", 2 + "");
		params.put("gact", 118 + "");
		params.put("appid", appId);
		params.put("userid", userId);
		params.put("usertoken", Base64.encodeToString(userToken));
		
		String jsonStr = UrlRequestUtils.execute(url, params, Mode.POST);
		jsonStr = StringUtils.replace(jsonStr,"'","\"");
		Map<String,Object> jsonMap = Json.toObject(jsonStr,Map.class);
		if(jsonMap.containsKey("result")
				&& jsonMap.get("result") != null
				&& Integer.parseInt(jsonMap.get("result").toString()) == 1){
			return true;
		}
		
		return false;
	}

	public boolean checkPayCallbackSign(AppFamePaymentObj cb) {
		try {

			String url = null;
			if (isTestVersion(cb.getVersion())) {
				url = APPLE_PROTOCOL_HEAD + testHost;
			} else {
				url = APPLE_PROTOCOL_HEAD + iosHost;
			}
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("receipt-data", cb.getReceipt());
			String jsonStr = UrlRequestUtils.executeHttpsByJson(url, jsonObject);
			logger.info("checkPayCallbackSign:" + jsonStr);
			JSONObject jObject = JSONObject.parseObject(jsonStr);
			if (jObject != null && "0".equals(jObject.getString("status"))) {
				JSONObject receipt = jObject.getJSONObject("receipt");
//				if ("com.ft.HotHero".equals(receipt.getString("bid"))) {
					cb.setAppleOrderId(receipt.getString("transaction_id"));
					return true;
//				}
			}
			return false;
		} catch (Exception e) {
			logger.error("appfame apple verify error!", e);
		}
		return false;
	}
	
	public boolean isTestVersion(String version) {

		if (StringUtils.isEmpty(testVersion) || StringUtils.isEmpty(version)) {
			return false;
		}

		String[] versions = testVersion.split(",");
		for (String ver : versions) {
			if (StringUtils.equalsIgnoreCase(ver, version)) {
				return true;
			}
		}
		return false;
	}
	
	public ExecutorService getExecutorService() {
		return executorService;
	}

	static class DefaultThreadFactory implements ThreadFactory {
		static final AtomicInteger poolNumber = new AtomicInteger(1);
		final ThreadGroup group;
		final AtomicInteger threadNumber = new AtomicInteger(1);
		final String namePrefix;

		DefaultThreadFactory() {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
			namePrefix = "appleverifyReceipt-" + poolNumber.getAndIncrement() + "-thread-";
		}

		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
			if (t.isDaemon())
				t.setDaemon(false);
			if (t.getPriority() != Thread.NORM_PRIORITY)
				t.setPriority(Thread.NORM_PRIORITY);
			return t;
		}
	}

	private static class TrustAnyTrustManager implements X509TrustManager

	{

		public void checkClientTrusted(X509Certificate[] chain, String

		authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String

		authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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

}
