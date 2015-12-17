package com.fantingame.game.gamecenter.partener.oppo;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.partener.appchina.Base64;

/**
 * OppoSdk
 * 
 * @author yezp
 */
public class OppoSdk extends BaseSdk {
	private static final Logger logger = Logger.getLogger(OppoSdk.class);

	private static OppoSdk ins;
	private static Properties prop;
	
	/**
	 * 公匙
	 */
	private String publicKey;
	private String appKey;
	private String appSecret;

	public static OppoSdk instance() {
		synchronized (OppoSdk.class) {
			if (ins == null) {
				ins = new OppoSdk();
			}
		}

		return ins;
	}

	private OppoSdk() {
		loadSdkProperties();
	}
	
	public void reload() {
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appKey = prop.getProperty("OppoSdk.appkey");
			appSecret = prop.getProperty("OppoSdk.appSecret");
			publicKey = prop.getProperty("OppoSdk.publicKey");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 验证回调签名
	 * 
	 * @param paymentObj
	 * @return
	 */
	public boolean checkPayCallbackSign(OppoPaymentObj paymentObj) {
		String content = getContentString(paymentObj);
		String charset = "utf-8";
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decodeBase64(publicKey.getBytes());
			PublicKey pubKey = keyFactory
					.generatePublic(new X509EncodedKeySpec(encodedKey));
			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initVerify(pubKey);
			signature.update(content.getBytes(charset));
			boolean bverify = signature.verify(Base64.decodeBase64(paymentObj
					.getSign().getBytes()));

			logger.info("oppo bverify:" + bverify);
			return bverify;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private String getContentString(OppoPaymentObj paymentObj) {
		StringBuilder baseString = new StringBuilder();
		baseString.append("notifyId=");
		baseString.append(paymentObj.getNotifyId());
		baseString.append("&");
		baseString.append("partnerOrder=");
		baseString.append(paymentObj.getPartnerOrder());
		baseString.append("&");
		baseString.append("productName=");
		baseString.append(paymentObj.getProductName());
		baseString.append("&");
		baseString.append("productDesc=");
		baseString.append(paymentObj.getProductDesc());
		baseString.append("&");
		baseString.append("price=");
		baseString.append(paymentObj.getPrice());
		baseString.append("&");
		baseString.append("count=");
		baseString.append(paymentObj.getCount());
		baseString.append("&");
		baseString.append("attach=");
		baseString.append(paymentObj.getAttach());

		logger.info("oppo baseString:" + baseString.toString());
		return baseString.toString();
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
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
}
