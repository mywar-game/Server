package com.fantingame.game.gamecenter.partener.wandoujia;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;

/**
 * 豌豆荚
 * 
 * @author yezp
 */
public class WanDouJiaSdk {

	private static final Logger logger = Logger.getLogger(WanDouJiaSdk.class);
	
	private static WanDouJiaSdk ins;
	private final static String PROTOCOL_HEAD = "http://";
	private static Properties prop;
	
	private String appkey_id;
	private String publicKey;
	private String uidCheckUrl;
	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	public static WanDouJiaSdk instance() {
		synchronized (WanDouJiaSdk.class) {
			if (ins == null) {
				ins = new WanDouJiaSdk();
			}
		}
		return ins;
	}

	private WanDouJiaSdk() {
		loadSdkProperties();
	}

	public void reload() {
		loadSdkProperties();
	}

	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource(
					"sdk.properties"));
			appkey_id = prop.getProperty("WanDouJiaSdk.appKey");
			publicKey = prop.getProperty("WanDouJiaSdk.publicKey");
			uidCheckUrl = prop.getProperty("WanDouJiaSdk.uidCheckUrl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean checkPayCallbackSign(WDJPaymentObj paymentObj) {
		try {
			logger.info("wandoujia content:" + paymentObj.getContent());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decode(publicKey);
			PublicKey pubKey = keyFactory
					.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(paymentObj.getContent().getBytes("utf-8"));

			boolean bverify = signature.verify(Base64.decode(paymentObj
					.getSign()));
			logger.info("wandoujia bverify:" + bverify);
			return bverify;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@SuppressWarnings("deprecation")
	public boolean verifySession(String uid, String token) {
		String url = PROTOCOL_HEAD + uidCheckUrl;
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", uid);
		params.put("token", URLEncoder.encode(token));
		params.put("appkey_id", appkey_id);

		// TODO 没有文档
		String jsonStr = UrlRequestUtils.execute(url, params, Mode.GET);
		if (jsonStr.equals("true")) {
			return true;
		}
		return false;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

}
